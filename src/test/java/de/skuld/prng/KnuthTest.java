package de.skuld.prng;

import java.util.Arrays;

public class KnuthTest extends AbstractPrngImplTest {

  @Override
  byte[] getTargetBytes(long seed, int amountPerSeed) {
    return new byte[0];
  }

  @Override
  byte[] getActualBytes(long seed, int amountPerSeed) {
    byte[] bytes = new byte[amountPerSeed];
    new Knuth(seed).nextBytes(bytes);
    System.out.println(seed);
    System.out.println(Arrays.toString(bytes));
    return bytes;
  }

  @Override
  protected PRNG randomSupplier(long seed) {
    return new Knuth((int) seed);
  }
}
