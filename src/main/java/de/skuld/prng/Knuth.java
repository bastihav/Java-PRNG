package de.skuld.prng;

/**
 * As implemented in .net 4.8 https://referencesource.microsoft.com/#mscorlib/system/random.cs
 * verified manually
 */
public class Knuth extends AbstractSecureRandom implements SeedablePRNG {

  private final int MSEED = 161803398;
  private final int MZ = 0;

  private int inext;
  private int inextp;
  private final int[] seedArray = new int[56];

  public Knuth() {
    this(0);
  }

  public Knuth(long seed) {
    seed(seed);
  }

  private int internalSample() {
    int retVal;
    int locINext = inext;
    int locINextp = inextp;

    if (++locINext >= 56) {
      locINext = 1;
    }
    if (++locINextp >= 56) {
      locINextp = 1;
    }

    retVal = seedArray[locINext] - seedArray[locINextp];

    if (retVal == Integer.MAX_VALUE) {
      retVal--;
    }
    if (retVal < 0) {
      retVal += Integer.MAX_VALUE;
    }

    seedArray[locINext] = retVal;

    inext = locINext;
    inextp = locINextp;

    return retVal;
  }

  @Override
  public int nextInt() {
    return internalSample();
  }

  @Override
  public void nextBytes(byte[] buffer) {
    for (int i = 0; i < buffer.length; i++) {
      buffer[i] = nextByte();
    }
  }

  private byte nextByte() {
    return (byte) (internalSample() % (Byte.MAX_VALUE + 1));
  }

  @Override
  public long getDefaultSeed() {
    return 0;
  }

  @Override
  public boolean usesUnixTimeAsDefault() {
    return true;
  }

  @Override
  public byte[] getBytes(long byteOffset, int length) {
    for (long i = 0; i < byteOffset; i++) {
      nextByte();
    }
    byte[] result = new byte[length];
    nextBytes(result);
    return result;
  }

  @Override
  public ImplementedPRNGs getPRNG() {
    return null;
  }

  @Override
  public void seed(long seed) {
    int ii;
    int mj, mk;

    //Initialize our Seed array.
    //This algorithm comes from Numerical Recipes in C (2nd Ed.)
    int subtraction = ((int) seed == Integer.MIN_VALUE) ? Integer.MIN_VALUE : Math.abs((int) seed);
    mj = MSEED - subtraction;
    seedArray[55] = mj;
    mk = 1;
    for (int i = 1; i < 55;
        i++) {  //Apparently the range [1..55] is special (Knuth) and so we're wasting the 0'th position.
      ii = (21 * i) % 55;
      seedArray[ii] = mk;
      mk = mj - mk;
      if (mk < 0) {
        mk += Integer.MAX_VALUE;
      }
      mj = seedArray[ii];
    }
    for (int k = 1; k < 5; k++) {
      for (int i = 1; i < 56; i++) {
        seedArray[i] -= seedArray[1 + (i + 30) % 55];
        if (seedArray[i] < 0) {
          seedArray[i] += Integer.MAX_VALUE;
        }
      }
    }
    inext = 0;
    inextp = 21;
  }
}
