package lazyinit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 1, 2013
 *
 * Sample to show the safe lazy initialization using FutureTask. The value of the
 * object is always 1. "Initialize on demand class holder" pattern
 */
class FooHolder {
  static final Foo foo = new Foo();
}

public class Bar {
  public static void main(String[] args) {
    List<FutureTask<Foo>> tasks = new ArrayList<FutureTask<Foo>>(5);
    final Thread [] threads = new Thread[5];

    for(int i = 0; i < 5; i++) {
      tasks.add(i, new FutureTask(new Callable<Foo>() {

        public Foo call() throws Exception {
          return FooHolder.foo;
        }
      }));
    }

    for(int i = 0; i < tasks.size(); i++) {
       threads[i] = new Thread(tasks.get(i));
       threads[i].start();
    }

    for(FutureTask<Foo> task : tasks) {
      try {
        stdout("Foo is " + task.get().state);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
  }
}

                                                                                           
