package de.skuld.prng;

import de.skuld.prng.util.NumberBasedByteSkip;
import org.apache.commons.math3.random.MersenneTwister;

/**
 * Implements https://github.com/python/cpython/blob/3.10/Lib/random.py with seeding variant version
 * 2
 */
public class MersenneTwisterPython extends de.skuld.prng.MersenneTwister {

  private final MersenneTwister twister = new MersenneTwister();

  public MersenneTwisterPython(long seed) {
    super();
    this.seed(seed);
  }

  @Override
  public void nextBytes(byte[] bytes) {
    twister.nextBytes(bytes);
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
  public int nextInt() {
    return twister.nextInt();
  }

  @Override
  public byte[] getBytes(long byteOffset, int length) {
    return NumberBasedByteSkip.intBasedByteSkip(this, byteOffset, length);
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.MERSENNE_TWISTER_PYTHON;
  }

  @Override
  public void seed(long seed) {
    int[] intArray;
    if (Long.numberOfLeadingZeros(seed) >= 32) {
      intArray = new int[]{(int) seed};
    } else {
      intArray = new int[]{(int) seed, (int) (seed >> 32)};
    }
    twister.setSeed(intArray);
  }
}
