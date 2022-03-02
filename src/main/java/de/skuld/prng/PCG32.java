package de.skuld.prng;

import de.skuld.prng.util.NumberBasedByteSkip;
import org.apache.commons.rng.core.source32.PcgXshRr32;

/**
 * as used in rust
 */
public class PCG32 extends AbstractSecureRandom implements SeedablePRNG {

  PcgXshRr32 pcgXshRr32;

  public PCG32() {
    pcgXshRr32 = new PcgXshRr32(getDefaultSeed());
  }

  public PCG32(long seed) {
    this();
    pcgXshRr32 = new PcgXshRr32(seed);
  }

  @Override
  public long getDefaultSeed() {
    return 0;
  }

  @Override
  public boolean usesUnixTimeAsDefault() {
    return false;
  }

  @Override
  public byte[] getBytes(long byteOffset, int length) {
    return NumberBasedByteSkip.intBasedByteSkip(this, byteOffset, length);
  }

  @Override
  public void nextBytes(byte[] bytes) {
    pcgXshRr32.nextBytes(bytes);
  }

  @Override
  public int nextInt() {
    return pcgXshRr32.nextInt();
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return ImplementedPRNGs.PCG32;
  }

  @Override
  public void seed(long seed) {

  }
}
