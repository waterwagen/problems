package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class RotateArrayTest {

  @Test
  public void test_One() {
    // given
    int[] nums = {3,2,3};
    int[] expectedNums = {3,3,2};

    testRotateArray(1, nums, expectedNums);
  }

  @Test
  public void test_Two() {
    // given
    int[] nums = {3,2,3};
    int[] expectedNums = {2,3,3};

    testRotateArray(2, nums, expectedNums);
  }

  @Test
  public void test_OneCompleteRotation() {
    // given
    int[] nums = {3,2,3};
    int[] expectedNums = {3,2,3};

    testRotateArray(3, nums, expectedNums);
  }

  @Test
  public void test_MoreThanCompleteRotation() {
    // given
    int[] nums = {3,2,3};
    int[] expectedNums = {3,3,2};

    testRotateArray(4, nums, expectedNums);
  }

  @Test
  public void test_Example1() {
    // given
    int[] nums = {1,2,3,4,5,6,7};
    int[] expectedNums = {5,6,7,1,2,3,4};

    testRotateArray(3, nums, expectedNums);
  }

  @Test
  public void test_Example2() {
    // given
    int[] nums = {-1,-100,3,99};
    int[] expectedNums = {3,99,-1,-100};

    testRotateArray(2, nums, expectedNums);
  }

  private static void testRotateArray(int rotationPlaces, int[] nums, int[] expectedNums) {
    // when
    Study.RotateArraySolution.rotateArray(nums, rotationPlaces);

    // then
    assertThat(nums, is(equalTo(expectedNums)));
  }

}
