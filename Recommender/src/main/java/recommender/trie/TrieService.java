package recommender.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import recommender.exception.InvalidWordSequenceException;

/**
 * A service for managing text index with recommendations
 * @author vasilev
 *
 */
@Service
public class TrieService {
	
	public static final int DEPTH = 6;
	public static final String DELIM_PATTERN = "([\\s]|[\\,]|[\\.]|[\\!]|[\\?]|[\\;]|[\\-]|“|”)+";
	
	// The root of the index
	private TrieNode<String> root = new TrieNode<String>("");

	/**
	 * Add text content to the index
	 * @param text The text content
	 */
	public void addToIndex(String text) {
		String [] words = text.split(DELIM_PATTERN);
		for(int i = 0; i < words.length; i++) {
			if(words[i].isBlank()) {
				continue;
			}
			String word = words[i].trim().toLowerCase();
			TrieNode<String> nextNode = root.insert(word);
			for(int j = 1; j <= DEPTH && j + i < words.length; j++) {
				if(words[i + j].isBlank()) {
					break;
				}
				String nextWord = words[i + j].trim().toLowerCase();
				nextNode = nextNode.insert(nextWord);
			}
		}
	}
	
	/**
	 * Gets recommendation, based on a list of words
	 * @param words The words to get recommendation for
	 * @return 
	 */
	public List<String> getRecommendation(List<String> words) {
		if(words == null || words.size() == 0) {
			return new ArrayList<>();
		}
		
		if(words == null || words.size() > DEPTH - 1) {
			throw new InvalidWordSequenceException(DEPTH - 1);
		}
		
		TrieNode<String> nextNode = root;
		for(String word : words) {
			ChildFrequencyTuple<String> tuple = nextNode.getNode(word.toLowerCase());
			if(tuple == null) {
				break;
			}
			nextNode = tuple.child;
		}
		return nextNode != null ? 
				nextNode.getMostFrequent().stream().map(el -> el.child.data).collect(Collectors.toList()):
				new ArrayList<>();
	}
	
	/**
	 * Cleans up the index 
	 */
	public void clearIndex() {
		root = new TrieNode<String>("");
	}
}
