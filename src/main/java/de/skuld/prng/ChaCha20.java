package de.skuld.prng;

public class ChaCha20 extends AbstractChaCha implements SeedablePRNG {

  public ChaCha20(long seed) {
    super(seed, 20);
  }

  public ChaCha20() {
    super(20);
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.CHA_CHA_20;
  }
}
