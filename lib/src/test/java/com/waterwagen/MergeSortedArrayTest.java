package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class MergeSortedArrayTest {

  @Test
  public void testMerge_Mixed() {
    int[] nums1 = {1,2,3,0,0,0};
    int m = 3;
    int[] nums2 = {2,5,6};
    int n = 3;
    int[] expectedNums = {1,2,2,3,5,6};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_Sequential_OneIsFirst() {
    int[] nums1 = {1,2,3,0,0,0};
    int m = 3;
    int[] nums2 = {4,5,6};
    int n = 3;
    int[] expectedNums = {1,2,3,4,5,6};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_Sequential_TwoIsFirst() {
    int[] nums1 = {4,5,6,0,0,0};
    int m = 3;
    int[] nums2 = {1,2,3};
    int n = 3;
    int[] expectedNums = {1,2,3,4,5,6};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_Both_Zero() {
    int[] nums1 = {};
    int m = 0;
    int[] nums2 = {};
    int n = 0;
    int[] expectedNums = {};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_First_Zero_Second_One() {
    int[] nums1 = {0};
    int m = 0;
    int[] nums2 = {2};
    int n = 1;
    int[] expectedNums = {2};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_First_Zero_Second_Multiple() {
    int[] nums1 = {0,0};
    int m = 0;
    int[] nums2 = {2,5};
    int n = 2;
    int[] expectedNums = {2,5};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_First_One_Second_Zero() {
    int[] nums1 = {2};
    int m = 1;
    int[] nums2 = {};
    int n = 0;
    int[] expectedNums = {2};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_First_Multiple_Second_Zero() {
    int[] nums1 = {2,4};
    int m = 2;
    int[] nums2 = {};
    int n = 0;
    int[] expectedNums = {2,4};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  @Test
  public void testMerge_All_Same() {
    int[] nums1 = {3,3,0,0};
    int m = 2;
    int[] nums2 = {3,3};
    int n = 2;
    int[] expectedNums = {3,3,3,3};

    testMerge(nums1, m, nums2, n, expectedNums);
  }

  private void testMerge(int[] nums1, int m, int[] nums2, int n, int[] expectedNums1) {
    // when
    Study.MergeSortedArraySolution.merge(nums1, m, nums2, n);

    //then
    assertThat(nums1, is(equalTo(expectedNums1)));
  }

}
