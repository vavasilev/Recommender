package recommender.trie;

/**
 * Combines a child node of a TrieNode with its frequency of occurances
 * @author vasilev
 *
 * @param <T> Type of contents of the trie
 */
public class ChildFrequencyTuple<T> implements Comparable<ChildFrequencyTuple<T>> {
	/**
	 * A child node
	 */
	public final TrieNode<T> child;
	/**
	 * Its frequency of occurances
	 */
	public final Integer frequency;
	
	public ChildFrequencyTuple(TrieNode<T> child, Integer frequency) {
		this.child = child;
		this.frequency = frequency;
	}

	@Override
	public int compareTo(ChildFrequencyTuple<T> o) {
		return frequency.compareTo(o.frequency);
	}
}
