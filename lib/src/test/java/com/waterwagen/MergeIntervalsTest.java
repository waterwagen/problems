package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class MergeIntervalsTest {

  @Test
  public void test_Example1() {
    // given
    int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
    int[][] expectedIntervals = {{1,6},{8,10},{15,18}};

    testMergeIntervals(intervals, expectedIntervals);
  }

  @Test
  public void test_Example2() {
    // given
    int[][] intervals = {{1,4},{4,5}};
    int[][] expectedIntervals = {{1,5}};

    testMergeIntervals(intervals, expectedIntervals);
  }

  @Test
  public void test_Example3() {
    // given
    int[][] intervals = {{1,4},{2,3}};
    int[][] expectedIntervals = {{1,4}};

    testMergeIntervals(intervals, expectedIntervals);
  }

  @Test
  public void test_OutOfOrder() {
    // given
    int[][] intervals = {{4,5},{1,4}};
    int[][] expectedIntervals = {{1,5}};

    testMergeIntervals(intervals, expectedIntervals);
  }

  @Test
  public void test_Same() {
    // given
    int[][] intervals = {{4,5},{4,5}};
    int[][] expectedIntervals = {{4,5}};

    testMergeIntervals(intervals, expectedIntervals);
  }

  @Test
  public void test_AllOverlapping() {
    // given
    int[][] intervals = {{4,5},{1,6},{-1,4},{2,8}};
    int[][] expectedIntervals = {{-1,8}};

    testMergeIntervals(intervals, expectedIntervals);
  }

  private static void testMergeIntervals(int[][] intervals, int[][] expectedIntervals) {
    // when
    int[][] actualIntervals = Study.MergeIntervalsSolution.merge(intervals);

    // then
    assertThat(actualIntervals, is(equalTo(expectedIntervals)));
  }

}
