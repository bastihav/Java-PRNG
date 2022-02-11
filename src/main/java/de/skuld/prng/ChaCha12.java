package de.skuld.prng;

public class ChaCha12 extends AbstractChaCha implements SeedablePRNG {

  public ChaCha12(long seed) {
    super(seed, 12);
  }

  public ChaCha12() {
    super(12);
  }
}
