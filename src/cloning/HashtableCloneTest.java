package cloning;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 30, 2013
 *
 * This test is without deep clone. buckets of BOTH the Original and Cloned point to the SAME memory location
 * in the heap and hence affects each other. DeepClone will fix this
 */
public class HashtableCloneTest {
  public static void main(String[] args) {
    CustomHashtable hashtable = new CustomHashtable(4);
    CustomHashtable cloned = (CustomHashtable) hashtable.clone();
    stdout("Hashcode of orig buckets: " + hashtable.buckets);
    stdout("Cloned buckets: " + cloned.buckets);

    for (CustomHashtable.Entry entry : hashtable.buckets) {
       stdout("hashcode of orig array: " + entry.hashCode());
    }

    stdout("The below refs are the same as the above because of shallow cloning");
    
    for (CustomHashtable.Entry entry : cloned.buckets) {
       stdout("hashcode of orig array: " + entry.hashCode());
       stdout("Key: " + entry.key);
       stdout("Entry: " + entry.value);
       stdout("Next: " + entry.next);
       stdout("---------------------------");
    }
  }
}
