package de.skuld.prng;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * https://datatracker.ietf.org/doc/html/rfc8439 https://github.com/rust-random/rand/blob/master/rand_chacha/src/chacha.rs
 * https://github.com/rust-random/rand/blob/master/rand_chacha/src/guts.rs
 * https://docs.rs/rand_core/0.6.3/src/rand_core/block.rs.html#123-128
 */
public abstract class AbstractChaCha extends AbstractSecureRandom implements SeedablePRNG {

  private final int STATE_SIZE = 16;

  // we simulate unsigned ints with these longs
  private long[] state;
  private byte[] out;
  private int[] seed;

  private int index;

  private static final long UNSIGNED_INT_MASK = ((long) Integer.MAX_VALUE << 1) + 1;
  private final int ROUNDS;
  private static final int SEED_SIZE_IN_BYTES = 32;

  private long readVal;
  private byte readPos;

  private long counter = 0;
  private final long streamId = 0;

  public AbstractChaCha(long seed, int rounds) {
    this.ROUNDS = rounds;
    seed(seed);
  }

  public AbstractChaCha(int rounds) {
    this(0, rounds);
  }

  public static void quarterRound(long[] state, int indexA, int indexB, int indexC, int indexD) {
    state[indexA] = addModulo(state[indexA], state[indexB]);
    state[indexD] ^= state[indexA];
    state[indexD] = leftRollInteger(state[indexD], 16);
    state[indexC] = addModulo(state[indexC], state[indexD]);
    state[indexB] ^= state[indexC];
    state[indexB] = leftRollInteger(state[indexB], 12);
    state[indexA] = addModulo(state[indexA], state[indexB]);
    state[indexD] ^= state[indexA];
    state[indexD] = leftRollInteger(state[indexD], 8);
    state[indexC] = addModulo(state[indexC], state[indexD]);
    state[indexB] ^= state[indexC];
    state[indexB] = leftRollInteger(state[indexB], 7);
  }

  public static long addModulo(long a, long b) {
    return addModulo(a, b, (long) Math.pow(2, 32));
  }

  public static long addModulo(long a, long b, long modulo) {
    return (a + b) % modulo;
  }

  /**
   * performs a n-bit left roll on a
   */
  private static long leftRollInteger(long a, int n) {
    // 1. discard the n left bits by shifting and then masking to an int
    // 2. get the n left bits as lowest order bits
    // 3. or that together
    return ((a << n) & UNSIGNED_INT_MASK) | ((a << n) & 0xffffffff00000000L) >>> Integer.SIZE;
  }

  @Override
  public long getDefaultSeed() {
    return 0;
  }

  @Override
  public void nextBytes(byte[] bytes) {
    // like rust fill bytes
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = nextByte();
    }
  }

  public byte nextByte() {
    if (index >= out.length) {
      generate();
    }
    return out[index++];
  }

  @Override
  public int nextInt() {
    if (index >= out.length) {
      generate();
    }
    // we do not lose any bytes here, as we only wrapped unsigned ints in long
    ByteBuffer byteBuffer = ByteBuffer.wrap(out);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    byteBuffer.position(index);
    int result = byteBuffer.getInt();
    index += 4;
    return result;
  }

  private void generate() {
    refill_wide();
    counter++;
    index = 0;
  }

  private void refill_wide() {
    long[] workingState = new long[16];
    workingState[0] = 0x61707865L;
    workingState[1] = 0x3320646eL;
    workingState[2] = 0x79622d32L;
    workingState[3] = 0x6b206574L;

    fillSeed(workingState, seed);

    workingState[12] = state[12];
    workingState[13] = state[13];
    workingState[14] = state[14];
    workingState[15] = state[15];

    long[] stateCopy = Arrays.copyOf(workingState, workingState.length);

    for (int i = 0; i < ROUNDS / 2; i++) {
      doubleRound(workingState);
    }

    for (int i = 0; i < workingState.length; i++) {
      workingState[i] = addModulo(workingState[i], stateCopy[i]);
    }

    out = makeOutputBuffer(workingState);

    addPos(state, 3, 4);
  }

  private static void addPos(long[] state, int row, int toAdd) {
    long pos0 = (state[row * 4 + 1] << 32) | state[row * 4];
    long pos = addModulo(pos0, toAdd, (long) Math.pow(2, 64));
    state[row * 4 + 1] = pos >> 32;
    state[row * 4] = (int) pos;
  }

  private static byte[] makeOutputBuffer(long[] state) {
    byte[] buffer = new byte[state.length * Integer.BYTES];
    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    for (long l : state) {
      byteBuffer.putInt((int) l);
    }

    return buffer;
  }

  public static void doubleRound(long[] state) {
    quarterRound(state, 0, 4, 8, 12);
    quarterRound(state, 1, 5, 9, 13);
    quarterRound(state, 2, 6, 10, 14);
    quarterRound(state, 3, 7, 11, 15);

    quarterRound(state, 0, 5, 10, 15);
    quarterRound(state, 1, 6, 11, 12);
    quarterRound(state, 2, 7, 8, 13);
    quarterRound(state, 3, 4, 9, 14);
  }

  @Override
  public boolean usesUnixTimeAsDefault() {
    return false;
  }

  @Override
  public byte[] getBytes(long byteOffset, int length) {
    for (long i = 0; i < byteOffset; i++) {
      nextByte();
    }
    byte[] result = new byte[length];
    nextBytes(result);
    return result;
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return null;
  }

  @Override
  public void seed(long seed) {
    this.state = new long[STATE_SIZE];
    this.seed = new int[SEED_SIZE_IN_BYTES / Integer.BYTES];

    generate();
  }

  private static void fillSeed(long[] state, int[] seed) {
    for (int i = 4; i <= 11; i++) {
      state[i] = seed[i - 4];
    }
  }
}
