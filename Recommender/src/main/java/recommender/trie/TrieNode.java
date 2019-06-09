package recommender.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a node of trie
 * @author vasilev
 *
 * @param <T> Type of contents of the trie
 */
public class TrieNode<T> {

	/**
	 * Data of the trie node. Ex. a word
	 */
	public final T data;
	
	/**
	 * Its children with corresponding frequencies
	 */
	public final Map<T, ChildFrequencyTuple<T>> children;
	
	/**
	 * Constructs a new trie node
	 * @param data Data for the node
	 */
	public TrieNode(T data) {
		this.data = data;
		this.children = new HashMap<>();
	}
	
	/**
	 * Inserts child data to the node
	 * @param data Data to insert
	 * @return The newly created node
	 */
	public TrieNode<T> insert(T data) {
		ChildFrequencyTuple<T> node = children.compute(data, (k, v) -> { 
			if(v == null) {
				return new ChildFrequencyTuple<T>(new TrieNode<T>(k), 1);
			} else {
				return new ChildFrequencyTuple<T>(v.child, v.frequency + 1);
			}
		});
		return node.child;
	}
	
	/**
	 * Gets child node for specific data
	 * @param data Data to look for
	 * @return The node found
	 */
	public ChildFrequencyTuple<T> getNode(T data) {
		return children.get(data);
	}
	
	/**
	 * Gets the most frequently occurring children 
	 * (if there are more than one with the same occurrence)
	 * @return
	 */
	public List<ChildFrequencyTuple<T>> getMostFrequent() {
		List<ChildFrequencyTuple<T>> mostFrequentChildren = new ArrayList<>();
		for(ChildFrequencyTuple<T> child : children.values()) {
			if(mostFrequentChildren.size() == 0) {
				mostFrequentChildren.add(child);
			} else if(mostFrequentChildren.get(0).compareTo(child) < 0) {
				mostFrequentChildren.clear();
				mostFrequentChildren.add(child);
			} else if(mostFrequentChildren.get(0).compareTo(child) == 0) {
				mostFrequentChildren.add(child);
			}
		}
		return mostFrequentChildren;
	}
}
