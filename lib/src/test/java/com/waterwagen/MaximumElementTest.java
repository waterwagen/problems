package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class MaximumElementTest {

  private static class RandomizedTestData {
    final int[] array;
    final int expectedMaximumElement;

    private RandomizedTestData(int arraySize) {
      Random random = new Random();
      array = new int[arraySize];
      int currentMaximumElement = Integer.MIN_VALUE;

      for (int i = 0; i < arraySize; i++) {
        int nextElement = random.nextInt(1_000_000);
        array[i] = nextElement;
        if (nextElement > currentMaximumElement)
          currentMaximumElement = nextElement;
      }
      expectedMaximumElement = currentMaximumElement;
    }
  }

  @Test
  public void testMaximumElementFromArray_Mulitple_Random() {
    // given

    // when
    int iterationTotal = 10;
    long totalExecutionTime = 0;
    for (int iteration = 1; iteration <= iterationTotal; iteration++) {
      System.out.println("Iteration " + iteration + " executing");
      RandomizedTestData testData = new RandomizedTestData(100_000_000);
      long iterationExecutionStartTime = System.nanoTime();
      int maxElement = Study.MaximumElement.maxElement(testData.array);
      totalExecutionTime += System.nanoTime() - iterationExecutionStartTime;

      // then
      assertThat("Unexpected maximum element", maxElement, is(equalTo(testData.expectedMaximumElement)));
    }
    System.out.println("Average execution time: " + (totalExecutionTime/iterationTotal)/1_000_000 + "ms");
  }

}
