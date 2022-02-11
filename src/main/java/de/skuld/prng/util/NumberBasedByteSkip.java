package de.skuld.prng.util;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import de.skuld.prng.PRNG;

public class NumberBasedByteSkip {

  public static byte[] intBasedByteSkip(PRNG rng, long offset, int length) {
    // nextBytes() always generates a new int
    // so we need to skip as many ints as we can fit in offset

    if (length < 1) {
      return new byte[0];
    }

    long intsInOffset = (offset / Integer.BYTES);
    int bytesToSkipInFirstInteger = (int) (offset % Integer.BYTES);

    for (long i = 0; i < intsInOffset; i++) {
      rng.nextInt();
    }

    int bytesToTakeInFirstInteger =
        bytesToSkipInFirstInteger != 0 ? Math.min(length, Integer.BYTES - bytesToSkipInFirstInteger)
            : 0;

    byte[] result1 = new byte[bytesToTakeInFirstInteger];
    // we need to skip the bytesToSkipInFirstInteger-first bytes of the nextInt and put the rest into the result array
    if (bytesToSkipInFirstInteger > 0) {
      int x = rng.nextInt();

      byte[] bytesFromInt = Ints.toByteArray(x);
      if (rng.lowerBitsFirst()) {
        Bytes.reverse(bytesFromInt);
      }

      System.arraycopy(bytesFromInt, bytesToSkipInFirstInteger, result1, 0,
          bytesToTakeInFirstInteger);
    }

    // we now need length - the remainder of bytes we didn't skip from the previous nextInt bytes into the result array
    byte[] result2 = new byte[Math.max(0, length - bytesToTakeInFirstInteger)];
    rng.nextBytes(result2);

    return Bytes.concat(result1, result2);
  }

  public static byte[] longBasedByteSkip(PRNG rng, long offset, int length) {
    // nextBytes() always generates a new long
    // so we need to skip as many longs as we can fit in offset

    if (length < 1) {
      return new byte[0];
    }

    long longsInOffset = (offset / Long.BYTES);
    int bytesToSkipInFirstLong = (int) (offset % Long.BYTES);

    for (long i = 0; i < longsInOffset; i++) {
      rng.nextLong();
    }

    int bytesToTakeInFirstLong =
        bytesToSkipInFirstLong != 0 ? Math.min(length, Long.BYTES - bytesToSkipInFirstLong)
            : 0;

    byte[] result1 = new byte[bytesToTakeInFirstLong];
    // we need to skip the bytesToSkipInFirstLong-first bytes of the nextLong and put the rest into the result array
    if (bytesToSkipInFirstLong > 0) {
      long x = rng.nextLong();

      byte[] bytesFromLong = Longs.toByteArray(x);
      if (rng.lowerBitsFirst()) {
        Bytes.reverse(bytesFromLong);
      }

      System.arraycopy(bytesFromLong, bytesToSkipInFirstLong, result1, 0,
          bytesToTakeInFirstLong);
    }

    // we now need length - the remainder of bytes we didn't skip from the previous nextLong bytes into the result array
    byte[] result2 = new byte[Math.max(0, length - bytesToTakeInFirstLong)];
    rng.nextBytes(result2);

    return Bytes.concat(result1, result2);
  }
}
