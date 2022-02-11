package de.skuld.prng;

public class Xoshiro256StarStarTest extends AbstractPrngImplTest {

  @Override
  byte[] getTargetBytes(long seed, int amountPerSeed) {
    return new byte[0];
  }

  @Override
  byte[] getActualBytes(long seed, int amountPerSeed) {
    return new byte[0];
  }

  @Override
  protected PRNG randomSupplier(long seed) {
    return new Xoshiro256StarStar(seed);
  }
}
