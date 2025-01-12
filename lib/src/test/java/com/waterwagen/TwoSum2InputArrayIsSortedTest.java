package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TwoSum2InputArrayIsSortedTest {

  @Test
  public void test_Large_MapLookup() {
    test_Large(Study.TwoSumIIInputArrayIsSortedSolution::twoSumMapLookup);
  }

  @Test
  @Disabled
  public void test_Large_Naive() {
    test_Large(Study.TwoSumIIInputArrayIsSortedSolution::twoSumNaive);
  }

  @Test
  public void test_Large_DivideAndConquer() {
    test_Large(Study.TwoSumIIInputArrayIsSortedSolution::twoSumDivideAndConquer);
  }

  private void test_Large(TwoSumCall twoSumCall) {
    // given
    long totalExecutionTime = 0;
    int iterationLimit = 100;
    for (int iteration = 0; iteration < iterationLimit; iteration++) {
      int numCount = 1_000_000; // set this to 10_000_000 to clearly see differences between map lookup and divide & conquer
      int[] nums = new int[numCount];
      Random random = new Random();
      for (int index = 0; index < numCount; index++) {
        nums[index] = random.nextInt(100_000);
      }
      Arrays.sort(nums);
      int firstTargetIndex = random.nextInt(numCount);
      int secondTargetIndex = random.nextInt(numCount);
      while (secondTargetIndex == firstTargetIndex) {
        secondTargetIndex = random.nextInt(numCount);
      }
      int target = nums[firstTargetIndex] + nums[secondTargetIndex];

      totalExecutionTime += testTwoSumLarge(nums, target, twoSumCall);
    }
    System.out.println("-----------------------------------------------------------------");
    System.out.println("Avg execution time: " + totalExecutionTime/iterationLimit + "ms");
  }

  @Test
  public void test_Adjacent_Positive() {
    // given
    int[] nums = {2,7,11,15};
    int target = 9;
    int[] expectedIndices = {1,2};

    testTwoSum(nums, target, expectedIndices);
  }

  @Test
  public void test_NonAdjacent_Positive() {
    // given
    int[] nums = {2,3,4};
    int target = 6;
    int[] expectedIndices = {1,3};

    testTwoSum(nums, target, expectedIndices);
  }

  @Test
  public void test_Adjacent_Negative() {
    // given
    int[] nums = {-12,7,11,15};
    int target = -5;
    int[] expectedIndices = {1,2};

    testTwoSum(nums, target, expectedIndices);
  }

  @Test
  public void test_NonAdjacent_Negative() {
    // given
    int[] nums = {-5,-2,3,4};
    int target = 2;
    int[] expectedIndices = {2,4};

    testTwoSum(nums, target, expectedIndices);
  }

  @Test
  public void test_WithZero() {
    // given
    int[] nums = {-1,0};
    int target = -1;
    int[] expectedIndices = {1,2};

    testTwoSum(nums, target, expectedIndices);
  }

  @Test
  public void test_OnlyTwo() {
    // given
    int[] nums = {2,3};
    int target = 5;
    int[] expectedIndices = {1,2};

    testTwoSum(nums, target, expectedIndices);
  }

  @Test
  public void test_Failing_SubmissionExample() {
    // given
    int[] nums = {5,25,75};
    int target = 100;
    int[] expectedIndices = {2,3};

    testTwoSum(nums, target, expectedIndices);
  }

  private static void testTwoSum(int[] nums, int target, int[] expectedIndices) {
    // when
    int[] indices = Study.TwoSumIIInputArrayIsSortedSolution.twoSumMapLookup(nums, target);

    // then
    assertThat(indices, is(equalTo(expectedIndices)));
  }

  private static long testTwoSumLarge(int[] nums, int target, TwoSumCall twoSumCall) {
    // when
    long startTime = System.nanoTime();
    int[] indices = twoSumCall.getTwoSum(nums, target);
    long endTime = System.nanoTime();
    System.out.println("Target: " + target);
    System.out.println("Large two sum indices: " + Arrays.toString(indices));
    if (indices.length > 0)
      System.out.println("Large two sum values: " + nums[indices[0] - 1] + "," + nums[indices[1] - 1]);
    long executionTime = (endTime - startTime)/1_000_000;
    System.out.println("Large two sum execution time: " + executionTime + "ms");

    // then
    assertThat(nums[indices[0] - 1] + nums[indices[1] - 1], is(equalTo(target)));

    return executionTime;
  }

  @FunctionalInterface
  private interface TwoSumCall {
    int[] getTwoSum(int[] nums, int target);
  }

}
