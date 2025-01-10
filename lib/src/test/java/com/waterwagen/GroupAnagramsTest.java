package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class GroupAnagramsTest {

  @Test
  public void test_SameCharacterButNotAnagrams() {
    // given
    String[] strs = {"dddg", "gddd", "dddgd"};
    List<List<String>> expectedAnagrams = List.of(List.of("dddg","gddd"), List.of("dddgd"));

    testGroupAnagrams(strs, expectedAnagrams);
  }

  @Test
  public void test_Example1() {
    // given
    String[] strs = {"eat","tea","tan","ate","nat","bat"};
    List<List<String>> expectedAnagrams = List.of(List.of("bat"), List.of("nat","tan"), List.of("ate","eat","tea"));

    testGroupAnagrams(strs, expectedAnagrams);
  }

  @Test
  public void test_Example2() {
    // given
    String[] strs = {""};
    List<List<String>> expectedAnagrams = List.of(List.of(""));

    testGroupAnagrams(strs, expectedAnagrams);
  }

  @Test
  public void test_Example3() {
    // given
    String[] strs = {"a"};
    List<List<String>> expectedAnagrams = List.of(List.of("a"));

    testGroupAnagrams(strs, expectedAnagrams);
  }

  private static void testGroupAnagrams(String[] strs, List<List<String>> expectedAnagrams) {
    // when
    Set<Set<String>> anagrams = toSets(Study.GroupAnagramsSolution.groupAnagrams(strs));

    // then
    assertThat(anagrams, is(equalTo(toSets(expectedAnagrams))));
  }

  private static Set<Set<String>> toSets(List<List<String>> lists) {
    return lists.stream()
      .map(HashSet::new)
      .collect(Collectors.toSet());
  }

}
