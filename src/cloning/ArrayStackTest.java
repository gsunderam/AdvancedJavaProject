package cloning;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 24, 2013
 */
public class ArrayStackTest {
  public static void main(String[] args) throws CloneNotSupportedException {
    ArrayStack stack = new ArrayStack(4);
    stack.push("GS");
    stack.push("Gets");
    stack.push("Success");
    stack.push("Home");

    stdout("No of elements " + stack.elements.length + " size = " + stack.size);
    stack.push("Got it!");
    stdout("New capacity " + stack.elements.length + " Size = " + stack.size);
    int size = stack.size;
    stdout("Last element " + stack.elements[size - 1]);
    String poped = (String) stack.pop();
    stdout("Poped element " + poped + " Check that last element is null..." + " Orig hash " + stack.elements.hashCode());
    stdout("Last " + stack.elements[size - 1]);
  }
}
