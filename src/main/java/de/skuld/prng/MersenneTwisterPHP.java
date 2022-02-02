package de.skuld.prng;

public class MersenneTwisterPHP extends MersenneTwister {

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
    return new byte[0];
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.MERSENNE_TWISTER_PHP;
  }

  @Override
  public void seed(long seed) {

  }
}
