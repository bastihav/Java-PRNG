package de.skuld.prng;

import de.skuld.prng.util.NumberBasedByteSkip;
import org.apache.commons.rng.core.source64.XoShiRo256StarStar;

public class Xoshiro256StarStar extends AbstractSecureRandom implements SeedablePRNG {

  XoShiRo256StarStar xoshiro256StarStar;

  public Xoshiro256StarStar() {
    xoshiro256StarStar = new XoShiRo256StarStar(new long[]{0L, 0L, 0L, 0L});
  }

  public Xoshiro256StarStar(long seed) {
    this();
    seed(seed);
  }

  @Override
  public void nextBytes(byte[] bytes) {
    xoshiro256StarStar.nextBytes(bytes);
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
    return NumberBasedByteSkip.longBasedByteSkip(this, offset, length);
  }

  @Override
  public long nextLong() {
    return xoshiro256StarStar.nextLong();
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.XOSHIRO256STARSTAR;
  }

  @Override
  public void seed(long seed) {
    long[] array = new long[]{0, 0, 0, seed};
    xoshiro256StarStar = new XoShiRo256StarStar(array);
  }
}
