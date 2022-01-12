package de.skuld.prng;

public enum ImplementedPRNGs {
  JAVA_RANDOM, MERSENNE_TWISTER_19937, MERSENNE_TWISTER_PHP, XORSHIFT128PLUS, MERSENNE_TWISTER_PYTHON;

  public static Class<? extends PRNG> getPRNG(ImplementedPRNGs prng) {
    switch (prng) {
      case JAVA_RANDOM:
        return JavaRandom.class;
      case XORSHIFT128PLUS:
        return Xorshift128Plus.class;
      case MERSENNE_TWISTER_PHP:
        return MersenneTwisterPHP.class;
      case MERSENNE_TWISTER_19937:
        return MersenneTwister19937.class;
      case MERSENNE_TWISTER_PYTHON:
        return MersenneTwisterPython.class;
      default:
        return null;
    }
  }
}
