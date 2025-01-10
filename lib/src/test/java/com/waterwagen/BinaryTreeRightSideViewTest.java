package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;
import com.waterwagen.Study.BinaryTreeRightSideViewSolution.TreeNode;

public class BinaryTreeRightSideViewTest {

  @Test
  public void test_FourLevels() {
    // given
    List<Integer> values = Lists.newArrayList(1, 2, 3, null, 5, null, 4, null, null, null, 6, null, null, 7, null);
    long startTime = System.nanoTime();
    Study.BinaryTreeRightSideViewSolution.TreeNode root = buildTreeNode(values);
    long totalTime = System.nanoTime() - startTime;
    System.out.println("Execution time: " + totalTime/1_000_000 + "ms");
    List<Integer> expectedView = List.of(1, 3, 4, 7);

    testBinaryTreeRightSideView(root, expectedView);
  }

  @Test
  public void test_Example1() {
    // given
    List<Integer> values = Lists.newArrayList(1, 2, 3, null, 5, null, 4);
    long startTime = System.nanoTime();
    Study.BinaryTreeRightSideViewSolution.TreeNode root = buildTreeNode(values);
    long totalTime = System.nanoTime() - startTime;
    System.out.println("Execution time: " + totalTime/1_000_000 + "ms");
    List<Integer> expectedView = List.of(1, 3, 4);

    testBinaryTreeRightSideView(root, expectedView);
  }

  private static void testBinaryTreeRightSideView(Study.BinaryTreeRightSideViewSolution.TreeNode root, List<Integer> expectedView) {
    // when
    List<Integer> actualView = Study.BinaryTreeRightSideViewSolution.rightSideView(root);

    // then
    System.out.println("Tree: " + root);
    assertThat(actualView, is(equalTo(expectedView)));
  }

  private static TreeNode buildTreeNode(List<Integer> values) {
    Integer nodeVal = values.isEmpty() ? null : values.get(0);
    if (nodeVal == null) {
      return null;
    }

    Queue<Integer> valuesQueue = new LinkedList<>(values);
    int rootValue = valuesQueue.remove();
    TreeNode root = new TreeNode(rootValue);
    TreeNode previousParent = null;
    Queue<TreeNode> parents = new LinkedList<>();
    parents.add(root);
    parents.add(root);

    while (!valuesQueue.isEmpty()) {
      Integer value = valuesQueue.remove();
      TreeNode parent = parents.remove();
      TreeNode node = value != null ? new TreeNode(value) : null;
      parents.add(node);
      parents.add(node);
      if (node != null) {
        if (previousParent != parent) {
          parent.left = node;
        }
        else {
          parent.right = node;
        }
      }
      previousParent = parent;
    }

    return root;
  }

}