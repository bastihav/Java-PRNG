package de.skuld.prng;

import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public abstract class AbstractRandom extends Random {

  @Override
  public boolean nextBoolean() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public double nextDouble() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public synchronized double nextGaussian() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public DoubleStream doubles() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public DoubleStream doubles(long streamSize) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public DoubleStream doubles(long streamSize, double randomNumberOrigin,
      double randomNumberBound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public float nextFloat() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public int nextInt() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public int nextInt(int bound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public IntStream ints() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public IntStream ints(long streamSize) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public long nextLong() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public LongStream longs() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public LongStream longs(long streamSize) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
