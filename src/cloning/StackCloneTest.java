package cloning;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 25, 2013
 */
public class StackCloneTest {
  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack(3);
    ArrayStack cloned = null;
    try {
       cloned = (ArrayStack) stack.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

		stdout("Orig Stack " + stack + " cloned stack " + cloned);

    stack.push("GS");
    stack.push("gets");
    stack.push("success");

    stdout("Last element of stack " + stack.elements[stack.size - 1]); //success
    stdout("Last element of clone " + cloned.elements[stack.size - 1]); //null
    stdout("size of the stack " + stack.size); //3
    stdout("size of the clone " + cloned.size); //0

    stack.push("Great");

    //Capacity of the stack doubles, while the cloned remains the same as before i.e. 3
    stdout("Capacity of the stack " + stack.elements.length + " hashcode " + stack.elements.hashCode()); //7
    stdout("Capacity of the clone " + cloned.elements.length + " hashcode " + cloned.elements.hashCode()); //3
  }
}
