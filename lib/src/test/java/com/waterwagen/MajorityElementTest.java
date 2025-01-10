package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class MajorityElementTest {

  @Test
  public void testByOne_Beginning_End() {
    // given
    int[] nums = {3,2,3};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  @Test
  public void testByOne_Middle() {
    // given
    int[] nums = {2,3,3,3,2};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  @Test
  public void testByAllButOne_End() {
    // given
    int[] nums = {3,3,3,2};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  @Test
  public void testByAllButOne_Beginning() {
    // given
    int[] nums = {2,3,3,3};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  @Test
  public void testByAllButOne_Middle() {
    // given
    int[] nums = {3,2,3,3};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  @Test
  public void testByAll_ManyValues() {
    // given
    int[] nums = {3,2,3,5,3,5,8,23,1,3,3,3,3,3};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  @Test
  public void testByAll() {
    // given
    int[] nums = {3,3,3};
    int expectedMajorityElement = 3;

    testMajorityElement(nums, expectedMajorityElement);
  }

  private static void testMajorityElement(int[] nums, int expectedMajorityElement) {
    // when
    int majorityElement = Study.MajorityElementSolution.majorityElement(nums);

    // then
    assertThat(majorityElement, is(equalTo(expectedMajorityElement)));
  }

}
