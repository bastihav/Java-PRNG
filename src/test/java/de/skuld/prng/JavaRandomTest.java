package de.skuld.prng;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Override
  public PRNG randomSupplier(long seed) {
    return new JavaRandom(seed);
  }


}
