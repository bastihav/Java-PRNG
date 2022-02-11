package de.skuld.prng;

import de.skuld.prng.util.NumberBasedByteSkip;
import java.security.SecureRandomParameters;
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
  public int nextInt() {
    return random.nextInt();
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
    return NumberBasedByteSkip.intBasedByteSkip(this, offset, length);
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
