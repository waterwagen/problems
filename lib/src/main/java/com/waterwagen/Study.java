package com.waterwagen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Study {

  class PrimeSequence {

    public static int[] getNums(int primeNumCount) {
      if (primeNumCount < 1) {
        throw new IllegalArgumentException("Need at least 2 nums for a sequence");
      }

      int[] result = new int[primeNumCount];
      int resultPointer = 0;
      int nextPrimeCandidate = 2;
      while (resultPointer < result.length) {
        if (isPrime(nextPrimeCandidate)) {
          result[resultPointer++] = nextPrimeCandidate;
        }
        nextPrimeCandidate++;
      }

      return result;
    }

    /*
      Skip evens except for 2 as that covers all evens
     */
    private static boolean isPrime(int primeCandidate) {
      if (primeCandidate == 2) {
        return true;
      }
      if (primeCandidate % 2 == 0) {
        return false;
      }

      for (int i = 3; i*i <= primeCandidate; i = i + 2) {
        if (primeCandidate % i == 0) {
          return false;
        }
      }

      return true;
    }

  }

  class NthFibonnaci {

    public static int getNum(int fibonacciNumPosition) {
      if (fibonacciNumPosition < 2) {
        throw new IllegalArgumentException("Need at least 2 nums for a sequence");
      }

      int previousPreviousNum;
      int previousNum = 0;
      int nthFibonnaciNum = 1;
      for (int index = 2; index < fibonacciNumPosition; index++) {
        previousPreviousNum = previousNum;
        previousNum = nthFibonnaciNum;
        nthFibonnaciNum = previousPreviousNum + previousNum;
      }

      return nthFibonnaciNum;
    }

  }

  class FibonnaciSequence {

    public static int[] getNums(int numCount) {
      if (numCount < 2) {
        throw new IllegalArgumentException("Need at least 2 nums for a sequence");
      }

      int[] result = new int[numCount];
      result[0] = 0;
      result[1] = 1;
      for (int index = 2; index < numCount; index++) {
        result[index] = result[index - 1] + result[index - 2];
      }

      return result;
    }

  }

  class KthLargestElement {

    public static int findKthLargest(int[] nums, int k) {
      if (nums.length < k)
        throw new IllegalArgumentException("Can't find the " + k + "th element of nums of length " + nums.length);

      PriorityQueue<Integer> orderedNums = new PriorityQueue<>();
      for (int index = 0; index < k; index++) {
        orderedNums.add(nums[index]);
      }
      for (int index = k; index < nums.length; index++) {
        int element = nums[index];
        int kthElement = orderedNums.peek();
        if (kthElement < element) {
          orderedNums.poll();
          orderedNums.add(element);
        }
      }

      return orderedNums.poll();
    }

  }

  class AddBinary {

    private static final char ONE = '1';
    private static final char ZERO = '0';

    /*
      - Create a result with a value of "0", continuously updated with the result so far
      - For each position, starting from the right, take turns adding the digit at that position from each number to the
      result
      - Update logic
        - 1. If result doesn't have digit at the position, set it to the num digit
        - 2. Else if result and num digits are both 1, set result digit to 0 and move left in result until a zero or no
             digit is found and set it to 1
        - 3. Else if num digit is 1, set result digit to 1
        - 4. Else, do nothing because num digit is zero and the result digit is unchanged no matter what it is
     */
    public static String addBinary(String num1, String num2) {
      StringBuilder result = new StringBuilder(num1);

      int pointer = num2.length() - 1;
      int resultPointer = result.length() - 1;
      while (pointer >= 0) {
        char numChar = num2.charAt(pointer);
        boolean noInsert = true;
        if (resultPointer == -1) {
          result.insert(0, numChar);
          noInsert = false;
        }
        else {
          char resultChar = result.charAt(resultPointer);
          int carryPointer = resultPointer - 1;
          if (numChar == ONE && resultChar == ONE) {
            result.setCharAt(resultPointer, ZERO);
            while (carryPointer >= 0 && result.charAt(carryPointer) == ONE) {
              result.setCharAt(carryPointer, ZERO);
              carryPointer--;
            }
            if (carryPointer < 0) {
              result.insert(0, ONE);
              noInsert = false;
            }
            else {
              result.setCharAt(carryPointer, ONE);
            }
          }
          else if (numChar == ONE) {
            result.setCharAt(resultPointer, ONE);
          }

        }
        if (noInsert)
          resultPointer--;
        pointer--;
      }

      return result.toString();
    }
  }

  class SimplifyPath {

    public static String simplifyPath(String path) {
      if (path.length() < 1)
        throw new IllegalArgumentException("The specified path is empty");
      if (path.charAt(0) != '/')
        throw new IllegalArgumentException("The specified path does not start with /");

      Deque<String> pathElements = new LinkedList<>();

      int pointer = 1;
      StringBuilder currentPathBuilder = new StringBuilder();
      while (pointer < path.length()) {
        char ch = path.charAt(pointer);
        if (ch == '/') {
          handleCurrentPath(currentPathBuilder, pathElements);
          currentPathBuilder = new StringBuilder();
        }
        else
          currentPathBuilder.append(ch);

        pointer++;
      }
      if (!currentPathBuilder.isEmpty())
        handleCurrentPath(currentPathBuilder, pathElements);

      return buildSimplifiedPath(pathElements).toString();
    }

    private static StringBuilder buildSimplifiedPath(Deque<String> pathElements) {
      StringBuilder simplifiedPath = new StringBuilder("/");
      while (!pathElements.isEmpty()) {
        simplifiedPath.append(pathElements.removeLast());
        simplifiedPath.append("/");
      }
      if (simplifiedPath.length() > 1)
        simplifiedPath.deleteCharAt(simplifiedPath.length() - 1);
      return simplifiedPath;
    }

    private static void handleCurrentPath(StringBuilder currentPathBuilder, Deque<String> pathElements) {
      String currentPath = currentPathBuilder.toString();
      if (currentPath.equals("..")) {
        if (!pathElements.isEmpty())
          pathElements.pop();
      }
      else if (!currentPath.equals(".")) {
        if (!currentPath.isBlank())
          pathElements.push(currentPath);
      }
    }

  }

  class MaximumElement {

    public static int maxElement(int[] array) {
      boolean naive = true;
      if (array.length == 0)
        throw new IllegalArgumentException("Must have some values in the array");
      if (array.length == 1) {
        return array[0];
      }

      if (naive) {
        int maxElement = array[0];
        for (int index = 0; index < array.length; index++) {
          int nextElement = array[index];
          if (nextElement > maxElement)
            maxElement = nextElement;
        }
        return maxElement;
      }

      int start = 0;
      int mid = array.length / 2;
      int end = array.length - 1;

      return Math.max(maxElement(array, start, mid), maxElement(array, mid + 1, end));
    }

    private static int maxElement(int[] array, int start, int end) {
      int elementCount = (end - start) + 1;
      if (elementCount == 1) {
        return array[start];
      }

      int mid = start + (end - start) / 2;
      return Math.max(maxElement(array, start, mid), maxElement(array, mid + 1, end));
    }
  }

  class Sort {

    public static void sort(int[] values) {
      if (values.length <= 10_000) {
        insertionSort(values);
      }
      else {
        mergeSort(values);
      }
    }

    public static void insertionSort(int[] arr) {
      int n = arr.length;
      for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
        while (j >= 0 && arr[j] > key) {
          arr[j + 1] = arr[j];
          j = j - 1;
        }
        arr[j + 1] = key;
      }
    }

    public static void quickSort(int[] values) {
      if (values.length <= 1) {
        return;
      }

      quickSort(values, 0, values.length - 1);
    }

    public static void quickSort(int[] values, int start, int end) {
      int elementCount = (end - start) + 1;
      if (elementCount <= 1) {
        return;
      }

      int pivotIndex = (new Random().nextInt(1_000) % elementCount) + start;
      int pivot = values[pivotIndex];
      //System.out.println("pivot=" + pivot);
      int[] newValues = new int[elementCount];
      int lessThanPointer = 0;
      int greaterThanOrEqualsPointer = newValues.length - 1;

      // move values to the correct side of the pivot
      for (int index = start; index <= end; index++) {
        if (values[index] < pivot) {
          newValues[lessThanPointer++] = values[index];
        }
        else if (index != pivotIndex){
          newValues[greaterThanOrEqualsPointer--] = values[index];
        }
      }
      newValues[greaterThanOrEqualsPointer] = pivot;
      int newPivotIndex = start + greaterThanOrEqualsPointer;
      //System.out.println("newValues=" + Arrays.toString(newValues));
      /*
      System.out.println("pivot=" + pivot);
      System.out.println("newValues=" + Arrays.toString(newValues));
       */
      int newValuesPointer = 0;
      for (int index = start; index <= end; index++) {
        values[index] = newValues[newValuesPointer++];
      }

      if (newPivotIndex > start)
        quickSort(values, start, newPivotIndex - 1);
      if (newPivotIndex < end)
        quickSort(values, newPivotIndex + 1, end);
    }

    public static void mergeSort(int[] values) {
      if (values.length <= 1) {
        return;
      }

      mergeSort(values, 0, values.length - 1);
    }

    public static void mergeSort(int[] values, int start, int end) {
      int elementCount = (end - start) + 1;
      if (elementCount <= 1) {
        return;
      }

      int mid = start + ((end - start) / 2);
      mergeSort(values, start, mid);
      mergeSort(values, mid + 1, end);

      merge(values, start, end);
    }

    private static void merge(int[] values, int start, int end) {
      int mid = (end - start) / 2;
      int leftPointer = 0;
      int rightPointer = mid + 1;
      int valuesPointer = start;
      int[] originalValues = Arrays.copyOfRange(values, start, end + 1);
      while (leftPointer <= mid && rightPointer < originalValues.length) {
        if (originalValues[leftPointer] <= originalValues[rightPointer]) {
          values[valuesPointer++] = originalValues[leftPointer++];
        }
        else {
          values[valuesPointer++] = originalValues[rightPointer++];
        }
      }
      while (leftPointer <= mid) {
        values[valuesPointer++] = originalValues[leftPointer++];
      }
      while (rightPointer < originalValues.length) {
        values[valuesPointer++] = originalValues[rightPointer++];
      }
    }

  }

  class BinaryTreeRightSideViewSolution {

    public static List<Integer> rightSideView(TreeNode root) {
      if (root == null) {
        return Collections.emptyList();
      }

      List<Integer> rightSide = new ArrayList<>();
      Queue<TreeNode> nodes = new LinkedList<>();
      nodes.add(root);
      int level = 0;
      int levelNodeCount = 0;
      TreeNode lastNonNullNode = null;
      while (!nodes.isEmpty()) {
        int levelNodeTotal = (int) Math.pow(2, level);
        TreeNode node = nodes.remove();
        if (node != null) {
          lastNonNullNode = node;
          nodes.add(node.left);
          nodes.add(node.right);
        }
        else {
          nodes.add(null);
          nodes.add(null);
        }
        if (++levelNodeCount == levelNodeTotal) {
          if (lastNonNullNode != null)
            rightSide.add(lastNonNullNode.val);
          else
            break;
          level++;
          levelNodeCount = 0;
          lastNonNullNode = null;
        }
      }

      return rightSide;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

      @Override
      public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
          return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
      }

      @Override
      public int hashCode() {
        return Objects.hash(val, left, right);
      }

      @Override
      public String toString() {
        return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
      }

    }

  }

  class MergeIntervalsSolution {

    public static int[][] merge(int[][] intervalsRaw) {
      List<int[]> intervals = new ArrayList<>(Arrays.stream(intervalsRaw).toList());
      int pointer = 0;
      while (pointer < intervals.size() - 1) {
        int[] currentInterval = intervals.get(pointer);
        boolean currentMerged = false;
        List<Integer> removals = new ArrayList<>();
        for (int i = pointer + 1; i < intervals.size(); i++) {
          int[] compareInterval = intervals.get(i);
          if ((currentInterval[0] <= compareInterval[1] && currentInterval[1] >= compareInterval[0])) {
            currentInterval = merge(currentInterval, compareInterval);
            intervals.set(pointer, currentInterval);
            removals.add(i);
            currentMerged = true;
          }
        }
        int index = removals.size() - 1;
        while (index >= 0) {
          int removalIndex = removals.get(index--);
          intervals.remove(removalIndex);
        }
        if (!currentMerged) {
          pointer++;
        }
      }

      return toArray(intervals);
    }

    private static int[][] toArray(List<int[]> intervals) {
      int[][] result = new int[intervals.size()][];

      int index = 0;
      for (int[] interval : intervals) {
        result[index++] = interval;
      }

      return result;
    }

    private static int[] merge(int[] interval1, int[] interval2) {
      int start = Math.min(interval1[0], interval2[0]);
      int end = Math.max(interval1[1], interval2[1]);
      return new int[]{start, end};
    }

  }

  class ValidSudokuSolution {

    public static boolean isValidSudoku(char[][] board) {
      Map<Integer, Set<Integer>> valuesBySubbox = new HashMap<>();
      Map<Integer, Set<Integer>> valuesByRow = new HashMap<>();
      Map<Integer, Set<Integer>> valuesByColumn = new HashMap<>();

      for (int row = 0; row < board.length; row++) {
        for (int column = 0; column < board[0].length; column++) {
          int subBoxId = getSubBoxId(column, row);
          char cell = board[row][column];

          // check valid cell value
          if (cell != '.' && !Character.isDigit(cell)) {
            return false;
          }
          if (cell != '.') {
            // check digit cell value is within the expected range
            int cellDigit = Integer.parseInt(Character.toString(cell));
            if (cellDigit < 1 || cellDigit > 9) {
              return false;
            }

            // check this is not a duplicate value for the row
            if (!valuesByRow.containsKey(row)) {
              valuesByRow.put(row, new HashSet<>());
            }
            Set<Integer> rowValuesFound = valuesByRow.get(row);
            if (rowValuesFound.contains(cellDigit)) {
              return false;
            }
            rowValuesFound.add(cellDigit);

            // check this is not a duplicate value for the column
            if (!valuesByColumn.containsKey(column)) {
              valuesByColumn.put(column, new HashSet<>());
            }
            Set<Integer> columnValuesFound = valuesByColumn.get(column);
            if (columnValuesFound.contains(cellDigit)) {
              return false;
            }
            columnValuesFound.add(cellDigit);

            // check this is not a duplicate value for the subbox
            if (!valuesBySubbox.containsKey(subBoxId)) {
              valuesBySubbox.put(subBoxId, new HashSet<>());
            }
            Set<Integer> subboxValuesFound = valuesBySubbox.get(subBoxId);
            if (subboxValuesFound.contains(cellDigit)) {
              return false;
            }
            subboxValuesFound.add(cellDigit);

          }
        }
      }

      return true;
    }

    private static int getSubBoxId(int zeroBasedColumn, int zeroBasedRow) {
      int column = zeroBasedColumn + 1;
      int row = zeroBasedRow + 1;
      int columnId = (column / 3) + (column % 3 == 0 ? 0 : 1);
      int rowId = (row / 3) + (row % 3 == 0 ? 0 : 1);

      return ((rowId - 1) * 3) + columnId;
    }

  }

  class GroupAnagramsSolution {

    public static List<List<String>> groupAnagrams(String[] strs) {
      Map<String, List<String>> anagrams = new HashMap<>();

      for (String str : strs) {
        char[] rawChars = str.toCharArray();
        Arrays.sort(rawChars);
        String normalizedStr = new String(rawChars);
        if (!anagrams.containsKey(normalizedStr)) {
          anagrams.put(normalizedStr, new ArrayList<>());
        }

        anagrams.get(normalizedStr).add(str);
      }

      return anagrams.values().stream().toList();
    }

  }

  class TwoSumIIInputArrayIsSortedSolution {

    public static int[] twoSumMapLookup(int[] numbers, int target) {
      if (numbers.length == 0) {
        return new int[0];
      }
      int[] indices = new int[2];

      int pointer = 0;
      Map<Integer, Integer> possibleSolutions = new HashMap<>();
      while (pointer < numbers.length) {
        Integer matchingPointer = possibleSolutions.get(numbers[pointer]);
        if (matchingPointer != null) {
          indices[0] = matchingPointer + 1;
          indices[1] = pointer + 1;
          break;
        }
        possibleSolutions.put(target - numbers[pointer], pointer);
        pointer++;
      }

      return indices;
    }

    public static int[] twoSumNaive(int[] numbers, int target) {
      if (numbers.length == 0) {
        return new int[0];
      }
      int[] indices = new int[2];

      int firstPointer = 0;
      while(firstPointer < numbers.length - 1) {
        int secondPointer = firstPointer + 1;
        while (secondPointer < numbers.length && (numbers[firstPointer] + numbers[secondPointer] < target)) {
          secondPointer++;
        }
        if (secondPointer < numbers.length && numbers[firstPointer] + numbers[secondPointer] == target) {
          indices[0] = firstPointer + 1;
          indices[1] = secondPointer + 1;
          break;
        }
        firstPointer++;
      }

      return indices;
    }

    public static int[] twoSumDivideAndConquer(int[] numbers, int target) {
      if (numbers.length <= 1) {
        throw new IllegalArgumentException("Less than 2 numbers in the array so it's not possible to find a two sum");
      }

      int pointer = 0;
      while (pointer < numbers.length - 1) {
        int secondNum = target - numbers[pointer];
        int secondIndex = find(secondNum, numbers, pointer + 1, numbers.length - 1);
        if (secondIndex >= 0) {
          return new int[]{pointer + 1, secondIndex + 1};
        }
        pointer++;
      }

      return new int[0];
    }

    private static int find(int target, int[] numbers, int start, int end) {
      int elementCount = (end - start) + 1;
      if (elementCount == 1) {
        if (numbers[start] == target) {
          return start;
        }
        return -1;
      }

      int middleIndex = start + (end - start)/2;
      if (target > numbers[middleIndex]) {
        return find(target, numbers, middleIndex + 1, end);
      }
      else {
        return find(target, numbers, start, middleIndex);
      }
    }
  }

  class ValidPalindromeSolution {

    public static boolean isPalindrome(String string) {
      char[] originalChars = string.toLowerCase().toCharArray();
      StringBuilder cleanedUpString = new StringBuilder();
      for (char ch : originalChars) {
        if (Character.isLetterOrDigit(ch))
          cleanedUpString.append(ch);
      }

      String forward = cleanedUpString.toString();
      String backward = cleanedUpString.reverse().toString();

      return forward.equals(backward);
    }

  }

  public class RotateArraySolution {

    public static void rotateArray(int[] nums, int k) {
      int shiftPlaces = k % nums.length;
      int[] wrapped = new int[shiftPlaces];
      int wrappedPointer = 0;
      // grab numbers to wrap around to the beginning
      for (int i = nums.length - shiftPlaces; i < nums.length; i++) {
        wrapped[wrappedPointer++] = nums[i];
      }
      // shift numbers over that don't wrap around to the beginning
      int swapIndex = nums.length - 1;
      for (int i = nums.length - shiftPlaces - 1; i >= 0; i--) {
        nums[swapIndex--] = nums[i];
      }
      // move numbers to the beginning that wrap around
      for (int i = 0; i < shiftPlaces; i++) {
        nums[i] = wrapped[i];
      }
    }

  }

  public class MajorityElementSolution {

    public static int majorityElement(int[] nums) {
      if (nums.length == 0) {
        throw new IllegalArgumentException("Expect a nums array with values");
      }

      Map<Integer, Integer> counts = new HashMap<>();
      int maxNumCount = -1;
      int maxNum = -1;
      for (int i = 0; i < nums.length; i++) {
        int num = nums[i];
        int numCount = counts.getOrDefault(num, 0) + 1;
        if (numCount > maxNumCount) {
          maxNumCount = numCount;
          maxNum = num;
        }
        counts.put(num, numCount);
      }

      return maxNum;
    }

  }

  public class RemoveDuplicates2Solution {

    public static int removeDuplicates(int[] nums) {
      if (nums.length <= 2) {
        return nums.length;
      }

      int pointer = 2;
      int endIndex = nums.length - 1;
      while (pointer < endIndex) {
        if (nums[pointer] == nums[pointer - 1] && nums[pointer] == nums[pointer - 2]) {
          remove(nums, pointer);
          endIndex--;
        }
        else {
          pointer++;
        }
      }
      if (nums[pointer] == nums[pointer - 1] && nums[pointer] == nums[pointer - 2]) {
        endIndex--;
      }
      return endIndex + 1;
    }

    private static void remove(int[] nums, int pointer) {
      while (pointer < (nums.length - 1)) {
        nums[pointer] = nums[pointer + 1];
        pointer++;
      }
      nums[pointer] = 0;
    }

  }

  public class RemoveDuplicatesSolution {

    public static int removeDuplicates(int[] nums) {
      if (nums.length <= 1) {
        return nums.length;
      }

      int pointer = 1;
      int removalCount = 0;
      int endIndex = nums.length - 1;
      while (pointer < endIndex) {
        if (nums[pointer] == nums[pointer - 1]) {
          remove(nums, pointer);
          endIndex--;
          removalCount++;
        }
        else {
          pointer++;
        }
      }
      if (nums[pointer] == nums[pointer - 1]) {
        removalCount++;
      }
      return nums.length - removalCount;
    }

    private static void remove(int[] nums, int pointer) {
      while (pointer < (nums.length - 1)) {
        nums[pointer] = nums[pointer + 1];
        pointer++;
      }
      nums[pointer] = 0;
    }

  }

  public class RemoveElementSolution {

    // #1 count non-equal
    // #2 slide over non-equal to beginning of array
    // #3 remove equal
    public static int removeElement(int[] nums, int val) {
      if (nums.length == 0) {
        return 0;
      }

      // find last non equals value in array
      int pointer = nums.length - 1;
      while (pointer >= 0 && nums[pointer] == val) {
        pointer--;
      }
      int lastNonEqualsIndex = pointer;

      // starting at beginning of array, swap all equals values to a position after the last non-equals value
      pointer = 0;
      while (pointer < lastNonEqualsIndex) {
        if (nums[pointer] == val) {
          int lastNonEqualsValue = nums[lastNonEqualsIndex];
          nums[lastNonEqualsIndex] = val + 1;
          nums[pointer] = lastNonEqualsValue;
          while (nums[--lastNonEqualsIndex] == val) {}
        }
        pointer++;
      }

      return lastNonEqualsIndex + 1;
    }

  }

  public class MergeSortedArraySolution {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
      int mainPointer = 0;
      int nPointer = 0;
      int endIndex = m - 1;
      while (nPointer < nums2.length) {
        if (nums2[nPointer] < nums1[mainPointer] || mainPointer > endIndex) {
          shift(nums1, mainPointer);
          nums1[mainPointer] = nums2[nPointer];
          endIndex++;
          nPointer++;
        }
        mainPointer++;
      }
    }

    private static void shift(int[] nums, int startOfShift) {
      int shiftPointer = nums.length - 2;
      while (shiftPointer >= startOfShift) {
        nums[shiftPointer + 1] = nums[shiftPointer];
        shiftPointer--;
      }
    }
  }

}
