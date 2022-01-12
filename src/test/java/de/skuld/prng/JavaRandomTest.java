package de.skuld.prng;

import java.util.Random;

public class JavaRandomTest extends AbstractPrngImplTest {

  @Override
  public byte[] getTargetBytes(long seed, int amountPerSeed) {
    Random random = new Random(seed);
    byte[] result = new byte[amountPerSeed];
    random.nextBytes(result);

    return result;
  }

  @Override
  public byte[] getActualBytes(long seed, int amountPerSeed) {
    byte[] bytes = new byte[amountPerSeed];
    new JavaRandom(seed).nextBytes(bytes);
    return bytes;
  }
}
