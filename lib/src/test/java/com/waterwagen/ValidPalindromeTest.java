package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class ValidPalindromeTest {

  @Test
  public void test_WhitespaceOnly_OneCharacter_Palindrome() {
    // given
    String string = " ";

    testValidPalindrome(string, true);
  }

  @Test
  public void test_WhitespaceOnly_MultipleCharacter_Palindrome() {
    // given
    String string = "  ";

    testValidPalindrome(string, true);
  }

  @Test
  public void test_Simple_OneCharacter_Palindrome() {
    // given
    String string = "a";

    testValidPalindrome(string, true);
  }

  @Test
  public void test_Simple_ThreeCharacters_Palindrome() {
    // given
    String string = "aba";

    testValidPalindrome(string, true);
  }

  @Test
  public void test_Simple_Minimum_NonPalindrome() {
    // given
    String string = "ab";

    testValidPalindrome(string, false);
  }

  @Test
  public void test_Simple_Longer_NonPalindrome() {
    // given
    String string = "ababab";

    testValidPalindrome(string, false);
  }

  @Test
  public void test_Complex_NonPalindrome() {
    // given
    String string = "race a car";

    testValidPalindrome(string, false);
  }

  @Test
  public void test_Complex_Palindrome() {
    // given
    String string = "A man, a plan, a canal: Panama";

    testValidPalindrome(string, true);
  }

  private static void testValidPalindrome(String string, boolean expectedIsPalindrome) {
    // when
    boolean isPalindrome = Study.ValidPalindromeSolution.isPalindrome(string);

    // then
    assertThat(isPalindrome, is(equalTo(expectedIsPalindrome)));
  }

}
