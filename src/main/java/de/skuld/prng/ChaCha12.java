package de.skuld.prng;

public class ChaCha12 extends AbstractChaCha implements SeedablePRNG {

  public ChaCha12(long seed) {
    super(seed, 12);
  }

  public ChaCha12() {
    super(12);
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.CHA_CHA_12;
  }
}
