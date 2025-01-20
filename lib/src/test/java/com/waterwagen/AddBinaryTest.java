package com.waterwagen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AddBinaryTest {

  @Test
  public void test_Example1() {
    test_AddBinary("11", "1", "100");
  }

  @Test
  public void test_Example2() {
    test_AddBinary("1010", "1011", "10101");
  }

  @Test
  public void test_AddZeroFirst() {
    test_AddBinary("0", "11", "11");
  }

  @Test
  public void test_AddZeroSecond() {
    test_AddBinary("11", "0", "11");
  }

  @Test
  public void test_AddAllOnes() {
    test_AddBinary("11", "11", "110");
  }

  private void test_AddBinary(String num1, String num2, String expectedSum) {
    // given

    // when
    String sum = Study.AddBinary.addBinary(num1, num2);

    // then
    assertThat(sum).isEqualTo(expectedSum);
  }

}
