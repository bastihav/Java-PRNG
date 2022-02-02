package de.skuld.prng;

public class Xorshift128PlusTest extends AbstractPrngImplTest {

  @Override
  public long[] getSeeds() {
    // TODO -1 and 0 seems to be a problem
    return new long[]{-9, -8, -7, -6, -5, -4, -3, -2, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1634668549};
  }

  @Override
  byte[] getTargetBytes(long seed, int amountPerSeed) {
    byte[] bytes = new byte[amountPerSeed];
    new Xorshift128Plus(seed).nextBytes(bytes);
    return bytes;
  }

  @Override
  byte[] getActualBytes(long seed, int amountPerSeed) {
    byte[] bytes = new byte[amountPerSeed];
    new Xorshift128Plus(seed).nextBytes(bytes);
    return bytes;
  }

  @Override
  protected PRNG randomSupplier(long seed) {
    return new Xorshift128Plus(seed);
  }
}
