package com.hronik.expandablelistviewtest.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	public final static String BOOKS_KEY = "books";
	public final static String MOVIES_KEY = "movies";
	public final static String GAMES_KEY = "games";

	/**
	 * A map of types of dummyitems to a map of instances of sample (dummy) items
	 */
	public static Map<String, Map<String, DummyItem>> TYPE_MAP = new HashMap<String, Map<String, DummyItem>>();

	static {
		// Add 3 sample items.
		addItem(BOOKS_KEY, new DummyItem("1", "Book 1"));
		addItem(BOOKS_KEY, new DummyItem("2", "Book 2"));
		addItem(BOOKS_KEY, new DummyItem("3", "Book 3"));
		addItem(MOVIES_KEY, new DummyItem("1", "Movie 1"));
		addItem(MOVIES_KEY, new DummyItem("2", "Movie 2"));
		addItem(GAMES_KEY, new DummyItem("1", "Game 1"));
		addItem(GAMES_KEY, new DummyItem("2", "Game 2"));
		addItem(GAMES_KEY, new DummyItem("3", "Game 3"));
		addItem(GAMES_KEY, new DummyItem("4", "Game 4"));
	}

	/**
	 * Places a dummyitem in the map dictated by typeKey
	 * 
	 * @param typeKey key to dictate which map to add typeKey to
	 * @param item item to be place, uses its id as the key to insert itself into its approprate map
	 */
	private static void addItem(String typeKey, DummyItem item) {
		Map<String, DummyItem> itemMap = TYPE_MAP.get(typeKey);
		if (itemMap == null)
			itemMap = new HashMap<String, DummyItem>();
		itemMap.put(item.id, item);
		TYPE_MAP.put(typeKey, itemMap);
	}

	/**
	 * gets the dummyitem located at itemKey location of map at typeKey location
	 * 
	 * @param typeKey key to dictate which map to search for the dummy item
	 * @param itemKey key to find the dummy item within its appropriate map
	 * @return the found dummy item, or null if does not exist
	 */
	public static DummyItem getItem(String typeKey, String itemKey) {
		Map<String, DummyItem> itemMap = TYPE_MAP.get(typeKey);
		if (itemMap == null)
			return null;
		else
			return itemMap.get(itemKey);
	}
	
	/**
	 * returns the entire item map specified by type key
	 * 
	 * @param typeKey the key into typemap
	 * @return the itemmap or null if does not exist
	 */
	public static Map<String, DummyItem> getItemMap(String typeKey) {
		return TYPE_MAP.get(typeKey);
	}
	
	/**
	 * gets the set of keys for types
	 * 
	 * @return set of keys of type, or null
	 */
	public static Set<String> getTypeKeySet() {
		return TYPE_MAP.keySet();
	}
	
	/**
	 * gets all of the keys and returns them as an array
	 * 
	 * @return an Array representation of the type keys, or null
	 */
	public static String[] getTypeKeyArray() {
		Set<String> keySet = TYPE_MAP.keySet();
		String[] keyArray = null;
		
		if (keySet != null)
			keyArray = keySet.toArray(new String[0]);
		
		return keyArray;
	}
	
	/**
	 * method to return an array representation of of all dummyItems for
	 * a given typeKey
	 * 
	 * @param typeKey the key into the type map
	 * @return an array of all dummyitems of a given type, or null
	 */
	public static DummyItem[] getDummyItemArrayForKey(String typeKey) {
		Map<String, DummyItem> dummyItemMap = TYPE_MAP.get(typeKey);
		
		if (dummyItemMap != null && dummyItemMap.values() != null)
			return dummyItemMap.values().toArray(new DummyItem[0]);
		else
			return null;
	}
	
	/**
	 * gets an ordered 2d array of dummyitems corresponding to the ordering
	 * of the input typekeys
	 * 
	 * @param typeKeys the array containing the all of the keys
	 * @return an ordered 2d array of all dummyitems corresponding to each key, or null
	 */
	public static DummyItem[][] getAllDummyItems(String[] typeKeys) {
		if (typeKeys == null)
			return null;
		
		DummyItem[][] dummyItems = new DummyItem[typeKeys.length][];
		for (int i = 0; i < typeKeys.length; i++) {
			String key = typeKeys[i];
			dummyItems[i] = getDummyItemArrayForKey(key);
		}
		
		return dummyItems;
	}
	
	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String content;

		public DummyItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
