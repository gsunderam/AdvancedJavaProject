package threads;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 20, 2013
 */
public class SafeThread {
  private final Thread t;

  //Don't start a thread from the constructor. Allow the constructor to complete so that the Object is
  //fully constructed
  public SafeThread() {
    t = new Thread(new Runnable() {

      public void run() {
        stdout("Safe Publication demo");
      }
    });
  }

  //Always have a start method like this so that "this" doesn't escape
  public void start() {
    t.start();
  }

  public static void main(String[] args) {
    SafeThread test = new SafeThread();
    test.start(); //test is a fully constructed instance
  }
}

