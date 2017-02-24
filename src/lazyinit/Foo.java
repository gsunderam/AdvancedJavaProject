package lazyinit;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 1, 2013
 *
 * Costly object to be initialized.
 */
public class Foo {
  int state = 1;

  //Only the holder class should be able to see this. State will be mutated just once
  //by the holder when the holder is loaded by the JVM
  Foo() {
    setState(state++);
    stdout("Initialized costly object state " + state);
  }

  private void setState(int state) {
    this.state = state;
  }
}
