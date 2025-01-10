package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class SortTest {

  @Test
  public void testSmartSort_MergeSort() {
    testSortWithRandomCollectionOfInts(Study.Sort::sort, 1_000_000);
  }

  @Test
  public void testSmartSort_InsertionSort() {
    testSortWithRandomCollectionOfInts(Study.Sort::sort, 10_000);
  }

  @Test
  public void testInsertionSort_SmallValueCount() {
    testSortWithRandomCollectionOfInts(Study.Sort::insertionSort, 100);
  }

  @Test
  public void testInsertionSort_LargeValueCount() {
    testSortWithRandomCollectionOfInts(Study.Sort::insertionSort, 10_000);
  }

  @Test
  public void testMergeSort_SmallValueCount() {
    testSortWithRandomCollectionOfInts(Study.Sort::mergeSort, 10_000);
  }

  @Test
  public void testMergeSort_LargeValueCount() {
    testSortWithRandomCollectionOfInts(Study.Sort::mergeSort, 1_000_000);
  }

  @Test
  public void testQuickSort_LargeValueCount() {
    testSortWithRandomCollectionOfInts(Study.Sort::quickSort, 1_000_000);
  }

  private void testSortWithRandomCollectionOfInts(Sorter sorter, int valueCount) {
    int iterationLimit = 100;
    long totalExecutionTime = 0;
    for (int iterationCount = 1; iterationCount <= iterationLimit; iterationCount++) {
      // given
      int[] values = new int[valueCount];
      Random random = new Random();
      for (int i = 0; i < values.length; i++) {
        values[i] = random.nextInt(1_000_000);
      }
      int[] expectedValues = Arrays.copyOf(values, values.length);
      Arrays.sort(expectedValues);

      // when
      long startTime = System.nanoTime();
      sorter.sort(values);
      long totalTimeNanos = System.nanoTime() - startTime;
      long totalTimeMillis = totalTimeNanos / 1_000_000;
      totalExecutionTime += totalTimeNanos;

      // then
      System.out.println("Iteration execution time: " + totalTimeMillis + "ms");
      //System.out.println("  actualValues=" + Arrays.toString(values));
      //System.out.println("expectedValues=" + Arrays.toString(expectedValues));
      System.out.println("------------------------");
      assertThat("Unexpected sort result", values, is(equalTo(expectedValues)));
      //assert(Arrays.equals(values, expectedValues));
    }

    long avgExecutionTime = (totalExecutionTime/iterationLimit)/1_000_000;
    System.out.println("Avg execution time: " + avgExecutionTime + "ms");
  }

  @FunctionalInterface
  private interface Sorter {
    void sort(int[] values);
  }

}
