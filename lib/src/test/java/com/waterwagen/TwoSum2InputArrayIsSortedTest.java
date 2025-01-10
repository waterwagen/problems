package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class TwoSum2InputArrayIsSortedTest {

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
    int[] indices = Study.TwoSumIIInputArrayIsSortedSolution.twoSum(nums, target);

    // then
    assertThat(indices, is(equalTo(expectedIndices)));
  }

}
