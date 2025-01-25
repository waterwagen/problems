package com.waterwagen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PrimeSequenceTest {

  @Test
  public void test_Example1() {
    test_PrimeSequence(50, new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
      67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
      191, 193, 197, 199, 211, 223, 227, 229});
  }

  @Test
  public void test_Performance() {
    long totalTime = 0;
    int iterationLimit = 20;
    for (int iteration = 1; iteration <= iterationLimit; iteration++) {
      long startTime = System.nanoTime();
      Study.PrimeSequence.getNums(100_000);
      long endTime = System.nanoTime();
      long executionTime = endTime - startTime;
      System.out.println("Iteration " + iteration + " execution time: " + executionTime/1_000_000 + "ms");
      totalTime += executionTime;
    }
    System.out.println("Avg execution time: " + (totalTime/iterationLimit)/1_000_000 + "ms");
  }

  private void test_PrimeSequence(int primeNumCount, int[] expectedNums) {
    // given

    // when
    int[] nums = Study.PrimeSequence.getNums(primeNumCount);

    // then
    assertThat(nums).isEqualTo(expectedNums);
  }

}
