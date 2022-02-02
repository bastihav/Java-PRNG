package de.skuld.prng;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import java.security.SecureRandomParameters;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class JavaRandom extends AbstractSecureRandom implements SeedablePRNG {

  private final Random random;

  JavaRandom() {
    this.random = new Random();
  }

  public JavaRandom(long seed) {
    this();
    seed(seed);
  }

  @Override
  public void setSeed(long seed) {
    seed(seed);
  }

  @Override
  public void reseed(SecureRandomParameters params) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void reseed() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void nextBytes(byte[] bytes, SecureRandomParameters params) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public String getAlgorithm() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public SecureRandomParameters getParameters() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void setSeed(byte[] seed) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public byte[] generateSeed(int numBytes) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void nextBytes(byte[] bytes) {
    random.nextBytes(bytes);
  }

  @Override
  public long getDefaultSeed() {
    return new Date().getTime() / 1000;
  }

  @Override
  public boolean usesUnixTimeAsDefault() {
    return true;
  }

  @Override
  public byte[] getBytes(long offset, int length) {
    // nextBytes() always generates a new int
    // so we need to skip as many ints as we can fit in offest

    if (length < 1) {
      return new byte[0];
    }

    long intsInOffset = (offset / Integer.BYTES);
    int bytesToSkipInFirstInteger = (int) (offset % Integer.BYTES);

    for (long i = 0; i < intsInOffset; i++) {
      random.nextInt();
    }

    int bytesToTakeInFirstInteger =
        bytesToSkipInFirstInteger != 0 ? Math.min(length, Integer.BYTES - bytesToSkipInFirstInteger)
            : 0;

    byte[] result1 = new byte[bytesToTakeInFirstInteger];
    // we need to skip the bytesToSkipInFirstInteger-first bytes of the nextInt and put the rest into the result array
    if (bytesToSkipInFirstInteger > 0) {
      int x = random.nextInt();

      byte[] bytesFromInt = Ints.toByteArray(x);
      Bytes.reverse(bytesFromInt);

      System.arraycopy(bytesFromInt, bytesToSkipInFirstInteger, result1, 0,
          bytesToTakeInFirstInteger);
    }

    // we now need length - the remainder of bytes we didnt skip from the previous nextInt bytes into the result array
    byte[] result2 = new byte[Math.max(0, length - bytesToTakeInFirstInteger)];
    this.nextBytes(result2);

    return Bytes.concat(result1, result2);
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.JAVA_RANDOM;
  }

  @Override
  public void seed(long seed) {
    if (random != null) {
      random.setSeed(seed);
    }
  }
}
