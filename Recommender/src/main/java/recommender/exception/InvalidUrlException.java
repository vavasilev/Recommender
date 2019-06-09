package recommender.exception;

public class InvalidUrlException extends RuntimeException {

	private final String url;
	
	public InvalidUrlException(String url) {
		super("Provided url: '" + url + "' is not valid!");
		this.url = url;
	}
}
