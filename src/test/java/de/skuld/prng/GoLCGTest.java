package de.skuld.prng;

import java.util.Arrays;

public class GoLCGTest extends AbstractPrngImplTest {

  @Override
  byte[] getTargetBytes(long seed, int amountPerSeed) {
    return new byte[0];
  }

  @Override
  byte[] getActualBytes(long seed, int amountPerSeed) {
    byte[] bytes = new byte[amountPerSeed];
    new GoLCG(seed).nextBytes(bytes);
    System.out.println(seed);
    System.out.println(Arrays.toString(bytes));
    return bytes;
  }

  @Override
  protected PRNG randomSupplier(long seed) {
    return new GoLCG(seed);
  }
}
