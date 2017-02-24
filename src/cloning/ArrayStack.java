package cloning;

import java.util.EmptyStackException;

/**
 * User: gsunderam
 * Date: Jun 24, 2013
 */
public class ArrayStack implements Cloneable {
  Object [] elements;
  int size;

  public ArrayStack(int initialCapacity) {
    this.elements = new Object[initialCapacity];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if (size == 0) throw new EmptyStackException();

    Object poped = elements[--size];
    //For this object to be eligible for GC, mark it null. Otherwise this may cause memory leak.
    elements[size] = null;
    return poped;
  }

  private void ensureCapacity() {
    if (elements.length == size) {
      Object oldElements [] = elements;
      elements = new Object[2 * elements.length + 1];
      System.arraycopy(oldElements, 0, elements, 0, size);
    }
  }

  /**
   * Correct way to clone this is to make a clone of "Object" member variables manually.
   * If elements array is not manually cloned as shown, then the cloned stack and original stack will point to
   * the same "elements" array reference in the heap and hence be interchangeable!
   */
  public Object clone() throws CloneNotSupportedException {
     ArrayStack clonedStack = (ArrayStack) super.clone();
     clonedStack.elements = this.elements.clone();
     return clonedStack;
  }

}
