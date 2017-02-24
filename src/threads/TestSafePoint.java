package threads;

import java.util.concurrent.ExecutionException;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 21, 2013
 *
 * Refer this link http://stackoverflow.com/questions/12028925/private-constructor-to-avoid-race-condition
 * for more info on this test class. The guy is excellent
 */
public class TestSafePoint {
  public static void main(String[] args) {
    TestSafePoint test = new TestSafePoint();
    test.createThreadsAndRun();
  }

  private void createThreadsAndRun() {
    final SafePoint originalSafePoint = new SafePoint(1,1);

    //One Thread is trying to change this SafePoint
    new Thread(new Runnable() {
        public void run() {
            originalSafePoint.set(2, 2);
            System.out.println("Original : " + originalSafePoint.toString());
        }
    }).start();

    //The other Thread is trying to create a copy. The copy, depending on the JVM, MUST be either (1,1) or (2,2)
    //depending on which Thread starts first, but it can not be (1,2) or (2,1) for example.
    new Thread(new Runnable() {
        public void run() {
            SafePoint copySafePoint = new SafePoint(originalSafePoint);
            System.out.println("Copy : " + copySafePoint.toString());
        }
    }).start();
  }
}
