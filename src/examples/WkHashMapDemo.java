package examples;

import log.Logger;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * User: gsunderam
 * Date: Sep 28, 2016
 *
 * Keys held by weak hash map are stored java.lang.ref.WeakReference
 */
public class WkHashMapDemo {
	public static void main(String[] args) {
		Map<String, String> map = new WeakHashMap<String, String>();

		/** Use new Object so that the GC indeed happens */
		map.put(new String("1"), "one");
		map.put(new String("2"), "two");
		map.put(new String("3"), "three");

		String s = "4";
		map.put(s, "four");

		Logger.log("Size of map before GC: " + map.size());
		System.gc();
		Logger.log("Size of map after GC: " + map.size());
	}
}
