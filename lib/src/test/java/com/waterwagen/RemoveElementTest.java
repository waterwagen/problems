package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

public class RemoveElementTest {

  @Test
  public void testRemoveElement_MultipleInstances() {
    // given
    int[] nums = {1,4,12,8,9,12};
    int val = 12;
    List<Integer> expectedNums = Lists.newArrayList(1,4,8,9);

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_SingleInstance() {
    // given
    int[] nums = {1,4,12,8,9};
    int val = 12;
    List<Integer> expectedNums = Lists.newArrayList(1,4,8,9);

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_First_Middle() {
    // given
    int[] nums = {12,1,4,12,8,9};
    int val = 12;
    List<Integer> expectedNums = Lists.newArrayList(1,4,8,9);

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_Last_Middle() {
    // given
    int[] nums = {1,4,12,8,9,12};
    int val = 12;
    List<Integer> expectedNums = Lists.newArrayList(1,4,8,9);

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_NotPresent() {
    // given
    int[] nums = {1,4,8,9};
    int val = 12;
    List<Integer> expectedNums = Lists.newArrayList(1,4,8,9);

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_AllValues() {
    // given
    int[] nums = {12,12,12,12};
    int val = 12;
    List<Integer> expectedNums = Lists.newArrayList();

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_FirstExample() {
    // given
    int[] nums = {3,2,2,3};
    int val = 3;
    List<Integer> expectedNums = Lists.newArrayList(2,2);

    testRemoveElement(nums, val, expectedNums);
  }

  @Test
  public void testRemoveElement_SecondExample() {
    // given
    int[] nums = {0,1,2,2,3,0,4,2};
    int val = 2;
    List<Integer> expectedNums = Lists.newArrayList(0,1,4,0,3);

    testRemoveElement(nums, val, expectedNums);
  }

  private static void testRemoveElement(int[] nums, int val, List<Integer> expectedNums) {
    // when
    int nonEqualElementCount = Study.RemoveElementSolution.removeElement(nums, val);

    // then
    assertThat(nonEqualElementCount, is(equalTo(expectedNums.size())));
    List<Integer> actualNums = new ArrayList<>();
    for (int i = 0; i < expectedNums.size(); i++) {
      actualNums.add(nums[i]);
    }
    Collections.sort(actualNums);
    Collections.sort(expectedNums);
    assertThat(actualNums, is(equalTo(expectedNums)));
  }

}
