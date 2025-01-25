package com.waterwagen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NthFibonacciTest {

  @Test
  public void test_Example1() {
    test_Fibonnaci(3, 1);
  }

  @Test
  public void test_Example2() {
    test_Fibonnaci(13, 144);
  }

  @Test
  public void test_Example3() {
    test_Fibonnaci(2, 1);
  }

  private void test_Fibonnaci(int fibonnaciNumPosition, int expectedNum) {
    // given

    // when
    int num = Study.NthFibonnaci.getNum(fibonnaciNumPosition);

    // then
    assertThat(num).isEqualTo(expectedNum);
  }

}
