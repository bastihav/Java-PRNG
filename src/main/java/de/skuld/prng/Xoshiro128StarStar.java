package de.skuld.prng;

import de.skuld.prng.util.NumberBasedByteSkip;
import org.apache.commons.rng.core.source32.XoShiRo128StarStar;

/**
 * As used in .net
 */
public class Xoshiro128StarStar extends AbstractSecureRandom implements SeedablePRNG {

  XoShiRo128StarStar xoShiRo128StarStar;

  public Xoshiro128StarStar() {
    xoShiRo128StarStar = new XoShiRo128StarStar(new int[4]);
  }

  public Xoshiro128StarStar(long seed) {
    this();
    seed(seed);
  }

  public Xoshiro128StarStar(int[] seeds) {
    this();
    xoShiRo128StarStar = new XoShiRo128StarStar(seeds);
  }

  @Override
  public void nextBytes(byte[] bytes) {
    xoShiRo128StarStar.nextBytes(bytes);
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
  public byte[] getBytes(long offset, int length) {
    return NumberBasedByteSkip.intBasedByteSkip(this, offset, length);
  }

  @Override
  public int nextInt() {
    return xoShiRo128StarStar.nextInt();
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.XOSHIRO128STARSTAR;
  }

  @Override
  public void seed(long seed) {
    int[] array = new int[]{0, 0, (int) seed, (int) (seed >> 32)};
    xoShiRo128StarStar = new XoShiRo128StarStar(array);
  }
}
