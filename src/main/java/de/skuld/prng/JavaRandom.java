package de.skuld.prng;

import java.security.SecureRandomParameters;
import java.util.Date;
import java.util.Random;

public class JavaRandom extends AbstractSecureRandom implements SeedablePRNG {

  private final Random r;

  JavaRandom() {
    this.r = new Random();
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
    r.nextBytes(bytes);
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
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.JAVA_RANDOM;
  }

  @Override
  public void seed(long seed) {
    r.setSeed(seed);
  }
}
