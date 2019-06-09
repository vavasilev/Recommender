package recommender;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import recommender.exception.InvalidUrlException;
import recommender.http.FileDownloadService;
import recommender.trie.TrieService;

@ShellComponent
public class RecommenderCommands {
	
	@Autowired
	FileDownloadService fileDownloadService;
	@Autowired
	TrieService trieService;
	
	@ShellMethod("Add UTF-8 text document to repository")
	public void add(@ShellOption() String url) {
		boolean valid = UrlValidator.getInstance().isValid(url);
		if(!valid) {
			throw new InvalidUrlException(url);
		}
		String contents = fileDownloadService.downloadFile(url);
		trieService.addToIndex(contents);
	}
	
	@ShellMethod("Get recommended word")
	public String recommend(
			@ShellOption() String word1, 
			@ShellOption(defaultValue="") String word2,
			@ShellOption(defaultValue="") String word3,
			@ShellOption(defaultValue="") String word4,
			@ShellOption(defaultValue="") String word5) {
		List<String> words = new ArrayList<>();
		words.add(word1);
		if(!word2.isEmpty()) {
			words.add(word2);
		}
		if(!word3.isEmpty()) {
			words.add(word3);
		}
		if(!word4.isEmpty()) {
			words.add(word4);
		}
		if(!word5.isEmpty()) {
			words.add(word5);
		}
		return StringUtils.join(trieService.getRecommendation(words), ",");
	}
	
	@ShellMethod("Cleans up the repository")
	public void cleanup() {
		trieService.clearIndex();
	}
}
