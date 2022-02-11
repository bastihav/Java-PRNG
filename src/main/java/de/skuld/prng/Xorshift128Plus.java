package de.skuld.prng;


/**
 * Implementation based on: https://github.com/v8/v8/blob/main/src/base/utils/random-number-generator.cc
 */
public class Xorshift128Plus extends AbstractSecureRandom implements SeedablePRNG {

  long initialSeed;
  long state0;
  long state1;

  public Xorshift128Plus(long seed) {
    seed(seed);
  }

  @Override
  public void nextBytes(byte[] bytes) {
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = nextByte();
    }
  }

  @Override
  public int nextInt() {
    this.xorshift128(state0, state1);
    return (int) (state0 + state1 >> 32);
  }

  @Override
  public long getDefaultSeed() {
    return 0;
  }

  @Override
  public boolean usesUnixTimeAsDefault() {
    return false;
  }

  @Override
  public byte[] getBytes(long byteOffset, int length) {
    for (int i = 0; i < byteOffset; i++) {
      nextByte();
    }

    byte[] result = new byte[length];
    nextBytes(result);
    return result;
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.XORSHIFT128PLUS;
  }

  @Override
  public void seed(long seed) {
    this.initialSeed = seed;
    state0 = murmurHash3(seed);
    state1 = murmurHash3(state0);

    if (state0 == 0 && state1 == 0) {
      throw new RuntimeException("Only one state may be zero at a time.");
    }
  }

  private byte nextByte() {
    xorshift128(state0, state1);
    return ((Long) (state0 + state1 >> 56)).byteValue();
  }

  private void xorshift128(long state0, long state1) {
    long s1 = state0;
    this.state0 = state1;
    s1 ^= s1 << 23;
    s1 ^= s1 >> 17;
    s1 ^= state1;
    s1 ^= state1 >> 26;
    this.state1 = s1;
  }

  private long murmurHash3(long h) {
    h ^= h >> 33;
    h *= Long.parseUnsignedLong("ff51afd7ed558ccd", 16);
    h ^= h >> 33;
    h *= Long.parseUnsignedLong("c4ceb9fe1a85ec53", 16);
    h ^= h >> 33;
    return h;
  }
}
