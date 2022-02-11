package de.skuld.prng;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChaCha12Test extends AbstractPrngImplTest {

  @Test
  void testQuarterRound() {
    long[] array = {0x11111111L, 0x01020304L, 0x9b8d6f43L, 0x01234567L};
    long[] expected = new long[]{0xea2a92f4L, 0xcb1cf8ceL, 0x4581472eL, 0x5881c4bbL};

    ChaCha12.quarterRound(array, 0, 1, 2, 3);

    Assertions.assertArrayEquals(expected, array);
  }

  @Test
  void testDiagonalQuarterRound() {
    long[] array = {0x879531e0L, 0xc5ecf37dL, 0x516461b1L, 0xc9a62f8aL,
        0x44c20ef3L, 0x3390af7fL, 0xd9fc690bL, 0x2a5f714cL,
        0x53372767L, 0xb00a5631L, 0x974c541aL, 0x359e9963L,
        0x5c971061L, 0x3d631689L, 0x2098d9d6L, 0x91dbd320L};

    long[] expected = new long[]{0x879531e0L, 0xc5ecf37dL, 0xbdb886dcL, 0xc9a62f8aL,
        0x44c20ef3L, 0x3390af7fL, 0xd9fc690bL, 0xcfacafd2L,
        0xe46bea80L, 0xb00a5631L, 0x974c541aL, 0x359e9963L,
        0x5c971061L, 0xccc07c79L, 0x2098d9d6L, 0x91dbd320L};
    ChaCha12.quarterRound(array, 2, 7, 8, 13);

    Assertions.assertArrayEquals(expected, array);
  }

  @Test
  void testChaChaBlock() {
    long[] array = {0x61707865L, 0x3320646eL, 0x79622d32L, 0x6b206574L,
        0x03020100L, 0x07060504L, 0x0b0a0908L, 0x0f0e0d0cL,
        0x13121110L, 0x17161514L, 0x1b1a1918L, 0x1f1e1d1cL,
        0x00000001L, 0x09000000L, 0x4a000000L, 0x00000000L};

    long[] expected = new long[]{0xe4e7f110L, 0x15593bd1L, 0x1fdd0f50L, 0xc47120a3L,
        0xc7f4d1c7L, 0x0368c033L, 0x9aaa2204L, 0x4e6cd4c3L,
        0x466482d2L, 0x09aa9f07L, 0x05d7c214L, 0xa2028bd9L,
        0xd19c12b5L, 0xb94e16deL, 0xe883d0cbL, 0x4e3c50a2L};

    long[] stateCopy = Arrays.copyOf(array, array.length);

    for (int i = 0; i < 20 / 2; i++) {
      ChaCha12.doubleRound(array);
    }

    for (int i = 0; i < array.length; i++) {
      array[i] = ChaCha12.addModulo(array[i], stateCopy[i]);
    }

    Assertions.assertArrayEquals(expected, array);
  }

  @Test
  void test_chacha_construction() {
    ChaCha20 rng = new ChaCha20(0);

    int[] actual = new int[16];

    for (int i = 0; i < actual.length; i++) {
      actual[i] = rng.nextInt();
    }

    int[] expected = new int[]{
        0xade0b876, 0x903df1a0, 0xe56a5d40, 0x28bd8653,
        0xb819d2bd, 0x1aed8da0, 0xccef36a8, 0xc70d778b,
        0x7c5941da, 0x8d485751, 0x3fe02477, 0x374ad8b8,
        0xf4b8436a, 0x1ca11815, 0x69b687c3, 0x8665eeb2};

    Assertions.assertArrayEquals(expected, actual);

  }

  @Override
  byte[] getTargetBytes(long seed, int amountPerSeed) {
    return new byte[0];
  }

  @Override
  byte[] getActualBytes(long seed, int amountPerSeed) {
    byte[] bytes = new byte[amountPerSeed];
    new ChaCha12(seed).nextBytes(bytes);
    return bytes;
  }

  @Override
  protected PRNG randomSupplier(long seed) {
    return new ChaCha12(seed);
  }
}
