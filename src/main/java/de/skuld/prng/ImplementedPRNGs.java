package de.skuld.prng;

public enum ImplementedPRNGs {
  JAVA_RANDOM, XORSHIFT128PLUS, MERSENNE_TWISTER_PYTHON, XOSHIRO128STARSTAR, XOSHIRO256STARSTAR, PCG32, GOLANGLCG, KNUTH, GLIBCLCG, CHA_CHA_12, CHA_CHA_8, CHA_CHA_20;

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
      case CHA_CHA_12:
        return ChaCha12.class;
      case CHA_CHA_8:
        return ChaCha8.class;
      case CHA_CHA_20:
        return ChaCha20.class;
      default:
        return null;
    }
  }

  public static PRNG getPRNG(ImplementedPRNGs prng, long seed) {
    switch (prng) {
      case JAVA_RANDOM:
        return new JavaRandom(seed);
      case XORSHIFT128PLUS:
        return new Xorshift128Plus(seed);
      case MERSENNE_TWISTER_PYTHON:
        return new MersenneTwisterPython(seed);
      case XOSHIRO128STARSTAR:
        return new Xoshiro128StarStar(seed);
      case XOSHIRO256STARSTAR:
        return new Xoshiro256StarStar(seed);
      case KNUTH:
        return new Knuth(seed);
      case PCG32:
        return new PCG32(seed);
      case GLIBCLCG:
        return new GlibcLCG(seed);
      case GOLANGLCG:
        return new GoLCG(seed);
      case CHA_CHA_12:
        return new ChaCha12(seed);
      case CHA_CHA_8:
        return new ChaCha8(seed);
      case CHA_CHA_20:
        return new ChaCha20(seed);
      default:
        return null;
    }
  }
}
