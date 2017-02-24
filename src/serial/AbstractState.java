package serial;

import java.io.Serializable;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 2, 2013
 *
 * Superclass that should be written in a way for sub classes' serialization to work correctly
 */
public abstract class AbstractState implements Serializable {
  private int x, y; //state
  private transient boolean isInitialized = false; //Non state variables should be marked transient

  public AbstractState(int x, int y) {
    initialize(x,y);
  }

  //protected constructor to initialize the sub class state.
  protected AbstractState() { stdout("Default constructor super called"); }

  protected final void initialize(int x, int y) {
     if (isInitialized) throw new IllegalStateException("Already initialized");

    this.x = x;
    this.y = y;
    //Do what the original constructor did.
    //...
    isInitialized = true;
  }

  //These getters will let the subclasses serialize the state using writeObject
  protected int getX() {
    return x;
  }

  protected int getY() {
    return y;
  }

  public boolean isInitialized() {
    return isInitialized;
  }

  //ALL public instance methods must call this to ensure the state is initialized
  private void checkInit() {
    if (!isInitialized) throw new IllegalStateException("Uninitialized");
  }
}
