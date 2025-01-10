package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class ValidSudokuTest {

  @Test
  public void test_ValidBoard() {
    // given
    char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                      {'6','.','.','1','9','5','.','.','.'},
                      {'.','9','8','.','.','.','.','6','.'},
                      {'8','.','.','.','6','.','.','.','3'},
                      {'4','.','.','8','.','3','.','.','1'},
                      {'7','.','.','.','2','.','.','.','6'},
                      {'.','6','.','.','.','.','2','8','.'},
                      {'.','.','.','4','1','9','.','.','5'},
                      {'.','.','.','.','8','.','.','7','9'}};
    boolean expectedIsValid = true;

    testValidSudoku(board, expectedIsValid);
  }

  @Test
  public void test_InValidBoard() {
    // given
    char[][] board = {{'8','3','.','.','7','.','.','.','.'},
                      {'6','.','.','1','9','5','.','.','.'},
                      {'.','9','8','.','.','.','.','6','.'},
                      {'8','.','.','.','6','.','.','.','3'},
                      {'4','.','.','8','.','3','.','.','1'},
                      {'7','.','.','.','2','.','.','.','6'},
                      {'.','6','.','.','.','.','2','8','.'},
                      {'.','.','.','4','1','9','.','.','5'},
                      {'.','.','.','.','8','.','.','7','9'}};
    boolean expectedIsValid = false;

    testValidSudoku(board, expectedIsValid);
  }

  @Test
  public void test_Example() {
    // given
    char[][] board = {{'.','.','.','.','5','.','.','1','.'},
                      {'.','4','.','3','.','.','.','.','.'},
                      {'.','.','.','.','.','3','.','.','1'},
                      {'8','.','.','.','.','.','.','2','.'},
                      {'.','.','2','.','7','.','.','.','.'},
                      {'.','1','5','.','.','.','.','.','.'},
                      {'.','.','.','.','.','2','.','.','.'},
                      {'.','2','.','9','.','.','.','.','.'},
                      {'.','.','4','.','.','.','.','.','.'}};
    boolean expectedIsValid = false;

    testValidSudoku(board, expectedIsValid);
  }

  private static void testValidSudoku(char[][] board, boolean expectedIsValid) {
    // when
    boolean isValid = Study.ValidSudokuSolution.isValidSudoku(board);

    // then
    assertThat(isValid, is(equalTo(expectedIsValid)));
  }

}
