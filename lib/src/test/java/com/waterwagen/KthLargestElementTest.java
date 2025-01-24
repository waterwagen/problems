package com.waterwagen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class KthLargestElementTest {

  @Test
  public void test_Example1() {
    test_KthLargestElement(new int[]{3,2,1,5,6,4}, 2, 5);
  }

  @Test
  public void test_Example2() {
    test_KthLargestElement(new int[]{3,2,3,1,2,4,5,5,6}, 4, 4);
  }

  @Test
  public void test_BiggestElement() {
    test_KthLargestElement(new int[]{3,2,3,1,6,2,4,5,5,6}, 1, 6);
  }

  private void test_KthLargestElement(int[] nums, int k, int expectedElement) {
    // given

    // when
    int element = Study.KthLargestElement.findKthLargest(nums, k);

    // then
    assertThat(element).isEqualTo(expectedElement);
  }

}
