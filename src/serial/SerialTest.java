package serial;

import java.io.*;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 2, 2013
 */
public class SerialTest {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
     AbstractState state = new State(1,2);
     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testSubSerial.ser"));
     oos.writeObject(state);
     oos.close();

     ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testSubSerial.ser"));
     AbstractState s = (State) ois.readObject();
     ois.close();
     stdout("State is x: " + s.getX() + " y: " + s.getY() + " Initialized: " + s.isInitialized());
  }
}
