package annotatons;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * User: gsunderam
 * Date: Nov 5, 2014
 */
public class RunTest {
  public static void main(String[] args) throws Exception {
    Class<TestExample> clazz = TestExample.class;

    if (clazz.isAnnotationPresent(TesterInfo.class)) {
      TesterInfo testerInfo = clazz.getAnnotation(TesterInfo.class);
      System.out.println(testerInfo.priority());
      System.out.println(testerInfo.createdBy());

      boolean first = true;
      for(String tag : testerInfo.tags()) {
         if (first) {
           System.out.print(tag);
           first = false;
         } else System.out.print("," + tag + "\n");
      }

      System.out.println("Last modified " + testerInfo.lastModified());
    }

    Method[] methods = clazz.getDeclaredMethods();
    TestExample testExample = clazz.newInstance();

    for (Method m : methods) {
      if (m.isAnnotationPresent(Test.class)) {
        Test t = m.getAnnotation(Test.class);
        if (t.enabled()) {
          m.invoke(testExample);
          System.out.println("Test passed/invoked: " + m.getName());
        } else
					System.out.println("Test ignored: " + m.getName());
      }
    }
  }
}
