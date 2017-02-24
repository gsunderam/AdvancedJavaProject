package threads;

/**
 * User: gsunderam
 * Date: Oct 21, 2013
 *
 * private constructor capture idiom
 */
public class SafePoint {
  private int x, y;

  //The sole purpose of private constructor is to avoid race condition
  private SafePoint(int [] points) {
    this(points[0], points[1]);
  }

  //This is reading. So this needs to be thread safe and so should synchronize on the same lock as the set method
  public SafePoint(SafePoint point) {
    this(point.get()); //This works because get is synchronoized on the same lock as set
//     this(point.x, point.y); //This won't work as Java doesn't allow synced constructors
  }

  public SafePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public synchronized void set(int x, int y) {
    this.x = x;

    //Simulated to create interleaving between reads and writes.
    try {
       Thread.sleep(10 * 100);
    } catch (InterruptedException e) {
       e.printStackTrace();
    }
    
    this.y = y;
  }

  public synchronized int[] get() {
    return new int[] {x, y};
  }

  @Override
  public String toString() {
    return "SafePoint{" +
            "x=" + x +
            ", y=" + y +
            '}';
  }
}
