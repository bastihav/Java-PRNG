package de.skuld.prng;

public class ChaCha8 extends AbstractChaCha implements SeedablePRNG {

  public ChaCha8(long seed) {
    super(seed, 8);
  }

  public ChaCha8() {
    super(8);
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.ChaCha8;
  }
}
