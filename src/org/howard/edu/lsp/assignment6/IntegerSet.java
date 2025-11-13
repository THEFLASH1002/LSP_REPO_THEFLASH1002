package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mutable mathematical set of unique integers.
 * <p>
 * This class supports standard set operations such as union,
 * intersection, difference, and complement. Duplicate values
 * are not allowed, and all mutator methods modify the current
 * instance rather than returning a new one.
 */
public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * Removes all elements from the set.
   */
  public void clear() {
    set.clear();
  }

  /**
   * Returns the number of elements in the set.
   *
   * @return the size of the set
   */
  public int length() {
    return set.size();
  }

  /**
   * Compares this set with another object for equality.
   * Two IntegerSet instances are equal if they contain the
   * same elements, regardless of order.
   *
   * @param o the object to compare to this set
   * @return {@code true} if both sets contain the same values,
   *         {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    IntegerSet other = (IntegerSet) o;

    if (this.length() != other.length()) {
      return false;
    }

    return this.set.containsAll(other.set) && other.set.containsAll(this.set);
  }

  /**
   * Determines whether the set contains a specific value.
   *
   * @param value the value to search for
   * @return {@code true} if the value is in the set,
   *         {@code false} otherwise
   */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * Returns the largest value in the set.
   *
   * @return the maximum integer in the set
   * @throws IllegalStateException if the set is empty
   */
  public int largest() {
    if (set.isEmpty()) {
      throw new IllegalStateException("Cannot get largest from an empty set.");
    }
    int max = set.get(0);
    for (int val : set) {
      if (val > max) {
        max = val;
      }
    }
    return max;
  }

  /**
   * Returns the smallest value in the set.
   *
   * @return the minimum integer in the set
   * @throws IllegalStateException if the set is empty
   */
  public int smallest() {
    if (set.isEmpty()) {
      throw new IllegalStateException("Cannot get smallest from an empty set.");
    }
    int min = set.get(0);
    for (int val : set) {
      if (val < min) {
        min = val;
      }
    }
    return min;
  }

  /**
   * Adds a value to the set if it is not already present.
   *
   * @param item the value to add
   */
  public void add(int item) {
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /**
   * Removes a value from the set if it exists.
   * Does nothing if the value is not present.
   *
   * @param item the value to remove
   */
  public void remove(int item) {
    set.remove(Integer.valueOf(item));
  }

  /**
   * Performs a union operation between this set and another set.
   * After the operation, this set contains all unique elements
   * that appear in either set.
   *
   * @param other the other set to union with
   */
  public void union(IntegerSet other) {
    for (int value : other.set) {
      if (!this.set.contains(value)) {
        this.set.add(value);
      }
    }
  }

  /**
   * Performs an intersection operation between this set and another set.
   * After the operation, this set contains only elements that appear in both sets.
   *
   * @param other the other set to intersect with
   */
  public void intersect(IntegerSet other) {
    set.retainAll(other.set);
  }

  /**
   * Performs a difference operation (this \ other).
   * After the operation, this set contains only elements that are in
   * this set but not in the other set.
   *
   * @param other the other set whose elements will be removed from this set
   */
  public void diff(IntegerSet other) {
    set.removeAll(other.set);
  }

  /**
   * Replaces this set with the complement relative to another set.
   * After the operation, this set becomes all elements that are in
   * {@code other} but not in {@code this}.
   *
   * @param other the set providing the universe for the complement
   */
  public void complement(IntegerSet other) {
    List<Integer> result = new ArrayList<Integer>();
    for (int value : other.set) {
      if (!this.set.contains(value)) {
        result.add(value);
      }
    }
    this.set = result;
  }

  /**
   * Determines whether the set is empty.
   *
   * @return {@code true} if the set contains no elements,
   *         {@code false} otherwise
   */
  public boolean isEmpty() {
    return set.isEmpty();
  }

  /**
   * Returns a string representation of the set in the format
   * {@code [1, 2, 3]}.
   *
   * @return a formatted string showing the set’s elements
   */
  @Override
  public String toString() {
    return set.toString();
  }
}
