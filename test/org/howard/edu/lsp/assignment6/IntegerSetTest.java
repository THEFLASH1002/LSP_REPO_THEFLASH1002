package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests for IntegerSet.
 */
public class IntegerSetTest {

  @Test
  public void testClearAndIsEmptyAndLength() {
    IntegerSet set = new IntegerSet();
    assertTrue(set.isEmpty());
    assertEquals(0, set.length());

    set.add(1);
    set.add(2);
    assertFalse(set.isEmpty());
    assertEquals(2, set.length());

    set.clear();
    assertTrue(set.isEmpty());
    assertEquals(0, set.length());
  }

  @Test
  public void testAddNoDuplicates() {
    IntegerSet set = new IntegerSet();
    set.add(1);
    set.add(1);  // duplicate
    set.add(2);
    assertEquals(2, set.length());
    assertTrue(set.contains(1));
    assertTrue(set.contains(2));
  }

  @Test
  public void testRemove() {
    IntegerSet set = new IntegerSet();
    set.add(1);
    set.add(2);
    set.remove(1);
    assertFalse(set.contains(1));
    assertTrue(set.contains(2));

    // Removing non-existing item should not crash
    set.remove(99);
    assertEquals(1, set.length());
  }

  @Test
  public void testLargestAndSmallestNormal() {
    IntegerSet set = new IntegerSet();
    set.add(5);
    set.add(1);
    set.add(10);

    assertEquals(10, set.largest());
    assertEquals(1, set.smallest());
  }

  @Test
  public void testLargestAndSmallestOnEmptyThrows() {
    IntegerSet empty = new IntegerSet();

    assertThrows(IllegalStateException.class, () -> empty.largest());
    assertThrows(IllegalStateException.class, () -> empty.smallest());
  }

  @Test
  public void testEqualsSameElementsDifferentOrder() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);
    set1.add(3);

    IntegerSet set2 = new IntegerSet();
    set2.add(3);
    set2.add(2);
    set2.add(1);

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
  }

  @Test
  public void testEqualsDifferentSets() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);

    IntegerSet set2 = new IntegerSet();
    set2.add(1);
    set2.add(3);

    assertFalse(set1.equals(set2));
  }

  @Test
  public void testUnion() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(3);

    set1.union(set2);  // mutates set1

    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertEquals(3, set1.length());
  }

  @Test
  public void testIntersect() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);
    set1.add(3);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(3);
    set2.add(4);

    set1.intersect(set2);  // mutates set1

    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertEquals(2, set1.length());
  }

  @Test
  public void testDiff() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);
    set1.add(3);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(4);

    set1.diff(set2);  // set1 \ set2

    assertTrue(set1.contains(1));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(2));
    assertEquals(2, set1.length());
  }

  @Test
  public void testComplement() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);

    IntegerSet set2 = new IntegerSet();
    set2.add(1);
    set2.add(2);
    set2.add(3);
    set2.add(4);

    // set1 becomes (set2 \ set1) = {3, 4}
    set1.complement(set2);

    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(2));
    assertEquals(2, set1.length());
  }

  @Test
  public void testComplementWithEmptyThis() {
    IntegerSet set1 = new IntegerSet(); // empty
    IntegerSet set2 = new IntegerSet();
    set2.add(5);
    set2.add(6);

    set1.complement(set2);

    assertTrue(set1.contains(5));
    assertTrue(set1.contains(6));
    assertEquals(2, set1.length());
  }

  @Test
  public void testToStringFormat() {
    IntegerSet set = new IntegerSet();
    set.add(2);
    set.add(5);

    String s = set.toString();
    assertTrue(s.startsWith("["));
    assertTrue(s.endsWith("]"));
    // Order is not strictly enforced, but both 2 and 5 should appear
    assertTrue(s.contains("2"));
    assertTrue(s.contains("5"));
  }
}
