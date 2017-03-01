package cloning;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 25, 2013
 *
 * LinkedList based custom Hashtable implementation
 */
public class CustomHashtable implements Cloneable {
  Entry [] buckets;

  public CustomHashtable(int initialCapacity) {
      buckets = new Entry[initialCapacity];
      initialize(buckets);
  }

  //Make the class static as Entries are instance independent. buckets hold references to this data structure
  //and hence "static" is appropriate
  static class Entry {
    String key;
    String value;
    Entry next;

    Entry(String key, String value, Entry next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }

    //Its a good pratice to overwrite tostring to provide a meaningful representation
    public String toString() {
      return "[" + key + ": " + value + "]";
    }

//    public Entry deepCopy() {
//      return new Entry(this.key, this.value, next == null ? null : next.deepCopy());
//    }
  }

  /**
   * deep cloning is necessary to ensure "new" instances of entries get created.
   * @return
   */
  public Object clone() {
    //return shallowClone();
    return deepClone();
  }


  private Object deepClone() {
    CustomHashtable cloned = null;
    try {
      cloned = (CustomHashtable) super.clone();
      populateEntries(cloned);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return cloned;
  }

	/**
	 * Now clone ALSO the individual object "Entry" object entries in the collection. In general when
	 * the collection contains objects a deep clone of them is needed
	 * @param cloned
	 */
	private void populateEntries(CustomHashtable cloned) {
    cloned.buckets = new Entry[buckets.length];
    boolean first = true;
    for (int i = buckets.length - 1; i >= 0; i--) {
      if (first) {
        cloned.buckets[i] = new Entry(buckets[i].key, buckets[i].value, null);
        first = false;
      } else {
        cloned.buckets[i] = new Entry(buckets[i].key, buckets[i].value, cloned.buckets[i + 1]);
      }
    }
  }

  //This version of clone has a bug! cloned.buckets all point to the same array references as the original.
  //See deep clone for correct cloning
  private Object shallowClone() {
    CustomHashtable cloned = null;
    try {
      cloned = (CustomHashtable) super.clone();
      cloned.buckets = buckets.clone(); //Needs fix
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return cloned;
  }

  private void initialize(Entry [] entries) {
//    entries = hashtable.buckets;
    boolean first = true;

    //Iterate the buckets in a reverse manner as the last next pointer is null
    //Use the entries = i + 1, to initialize entries = i
    for(int i = entries.length - 1; i >= 0; i--) {
      if (first) {
          entries[i] = new Entry("key" + i, "Value" + i, null);
          first = false;
      } else {
          entries[i] = new Entry("key" + i, "Value" + i, entries[i + 1]);
      }
    }
  }

  private static void printHashtable(CustomHashtable hashtable) {
    for (Entry entry : hashtable.buckets) {
      stdout("Key: " + entry.key);
      stdout("Value: " + entry.value);
      stdout("Next pointer is: " + entry.next);
    }
  }

  public static void main(String[] args) {
    CustomHashtable hashtable = new CustomHashtable(4);
    printHashtable(hashtable);
  }
}
