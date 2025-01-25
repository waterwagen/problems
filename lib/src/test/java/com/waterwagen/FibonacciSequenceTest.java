package com.waterwagen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FibonacciSequenceTest {

  @Test
  public void test_Example1() {
    test_Fibonnaci(3, new int[]{0,1,1});
  }

  @Test
  public void test_Example2() {
    test_Fibonnaci(13, new int[]{0,1,1,2,3,5,8,13,21,34,55,89,144});
  }

  private void test_Fibonnaci(int numCount, int[] expectedNums) {
    // given

    // when
    int[] nums = Study.FibonnaciSequence.getNums(numCount);

    // then
    assertThat(nums).isEqualTo(expectedNums);
  }

}
