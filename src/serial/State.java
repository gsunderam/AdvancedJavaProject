package serial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 2, 2013
 *
 * Subclass that shows how to properly serialize and deserialize the state variables.
 */
public class State extends AbstractState implements Serializable {
  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
      stdout("Deserializing Object");
      ois.defaultReadObject();

      int x = ois.readInt();
      int y = ois.readInt();

      //Call initialize so that the state is correctly set in the super class.
      initialize(x, y);
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
      stdout("Serializing Object");
      oos.defaultWriteObject();

      //Call the getters to do the serialization of the super class state(s)
      oos.writeInt(getX());
      oos.writeInt(getY());
  }

  public State(int x, int y) {
    super(x, y);
  }
}
