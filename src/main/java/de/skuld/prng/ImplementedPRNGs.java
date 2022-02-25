package de.skuld.prng;

public enum ImplementedPRNGs {
  JAVA_RANDOM, XORSHIFT128PLUS, MERSENNE_TWISTER_PYTHON, XOSHIRO128STARSTAR, XOSHIRO256STARSTAR, PCG32, GOLANGLCG, KNUTH, GLIBCLCG, ChaCha12, ChaCha8, ChaCha20;

  public static Class<? extends PRNG> getPRNG(ImplementedPRNGs prng) {
    switch (prng) {
      case JAVA_RANDOM:
        return JavaRandom.class;
      case XORSHIFT128PLUS:
        return Xorshift128Plus.class;
      case MERSENNE_TWISTER_PYTHON:
        return MersenneTwisterPython.class;
      case XOSHIRO128STARSTAR:
        return Xoshiro128StarStar.class;
      case XOSHIRO256STARSTAR:
        return Xoshiro256StarStar.class;
      case KNUTH:
        return Knuth.class;
      case PCG32:
        return de.skuld.prng.PCG32.class;
      case GLIBCLCG:
        return GlibcLCG.class;
      case GOLANGLCG:
        return GoLCG.class;
      case ChaCha12:
        return ChaCha12.class;
      case ChaCha8:
        return ChaCha8.class;
      case ChaCha20:
        return ChaCha20.class;
      default:
        return null;
    }
  }
}
