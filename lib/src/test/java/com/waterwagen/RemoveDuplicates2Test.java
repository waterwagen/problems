package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class RemoveDuplicates2Test {

  @Test
  public void testRemoveDuplicates_Beginning() {
    // given
    int[] nums = {1,1,1,2,4,8,9};
    int[] expectedNums = {1,1,2,4,8,9};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_End() {
    // given
    int[] nums = {1,4,8,9,12,12,12};
    int[] expectedNums = {1,4,8,9,12,12};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_Middle() {
    // given
    int[] nums = {1,4,8,8,8,9,12};
    int[] expectedNums = {1,4,8,8,9,12};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_All() {
    // given
    int[] nums = {1,1,1,4,4,4,8,8,8,9,9,9};
    int[] expectedNums = {1,1,4,4,8,8,9,9};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_Multiple() {
    // given
    int[] nums = {1,4,4,4,8,8,8,9,9};
    int[] expectedNums = {1,4,4,8,8,9,9};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_NoDuplicates() {
    // given
    int[] nums = {1,4,4,8,8,9,12};
    int[] expectedNums = {1,4,4,8,8,9,12};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_Empty() {
    // given
    int[] nums = {};
    int[] expectedNums = {};

    testRemoveDuplicates(nums, expectedNums);
  }

  @Test
  public void testRemoveDuplicates_AllSame() {
    // given
    int[] nums = {12,12,12};
    int[] expectedNums = {12,12};

    testRemoveDuplicates(nums, expectedNums);
  }

  private static void testRemoveDuplicates(int[] nums, int[] expectedNums) {
    // when
    int uniqueElementCount = Study.RemoveDuplicates2Solution.removeDuplicates(nums);

    // then
    assertThat(uniqueElementCount, is(equalTo(expectedNums.length)));
    for (int i = 0; i < expectedNums.length; i++) {
      assertThat(nums[i], is(equalTo(expectedNums[i])));
    }
  }

}
