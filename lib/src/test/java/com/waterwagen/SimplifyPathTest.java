package com.waterwagen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SimplifyPathTest {

  @Test
  public void test_Example1() {
    test_SimplifyPath("/home/", "/home");
  }

  @Test
  public void test_Example2() {
    test_SimplifyPath("/home//foo/", "/home/foo");
  }

  @Test
  public void test_Example3() {
    test_SimplifyPath("/home/user/Documents/../Pictures", "/home/user/Pictures");
  }

  @Test
  public void test_Example4() {
    test_SimplifyPath("/../", "/");
  }

  @Test
  public void test_Example5() {
    test_SimplifyPath("/.../a/../b/c/../d/./", "/.../b/d");
  }

  @Test
  public void test_ParentAtEnd() {
    test_SimplifyPath("/home/user/Documents/../Pictures/..", "/home/user");
  }

  private void test_SimplifyPath(String originalPath, String expectedPath) {
    // given

    // when
    String simplifiedPath = Study.SimplifyPath.simplifyPath(originalPath);

    // then
    assertThat(simplifiedPath).isEqualTo(expectedPath);
  }

}
