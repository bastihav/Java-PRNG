package de.skuld.prng;

/**
 * https://www.mscs.dal.ca/~selinger/random/ verified manually
 */
public class GlibcLCG extends AbstractSecureRandom implements SeedablePRNG {

  private int[] state;
  private int n;
  private final int STATE_SIZE = 344;

  public GlibcLCG() {
    this(1);
  }

  public GlibcLCG(long seed) {
    seed(seed);
  }

  @Override
  public long getDefaultSeed() {
    return 1;
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
    return ImplementedPRNGs.GLIBCLCG;
  }

  @Override
  public void seed(long seed) {
    state = new int[STATE_SIZE];

    if (seed == 0) {
      seed = 1;
    }

    state[0] = (int) seed;
    for (int i = 1; i < 31; i++) {
      state[i] = (int) ((16807 * (long) state[i - 1]) % 2147483647);
    }

    System.arraycopy(state, 0, state, 31, 3);

    for (int i = 34; i < STATE_SIZE; i++) {
      state[i] = state[i - 31] + state[i - 3];
    }

    n = 0;
  }

  @Override
  public void nextBytes(byte[] bytes) {
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = nextByte();
    }
  }

  public byte nextByte() {
    return (byte) Math.floorMod(nextInt(), 256);
  }

  @Override
  public int nextInt() {
    int x = state[n % STATE_SIZE] = state[(n + 313) % STATE_SIZE] + state[(n + 341) % STATE_SIZE];
    n = (n + 1) % STATE_SIZE;
    return (x >> 1);
  }
}
