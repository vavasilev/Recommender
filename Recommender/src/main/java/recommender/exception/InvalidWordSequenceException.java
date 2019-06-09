package recommender.exception;

public class InvalidWordSequenceException extends RuntimeException {
	public InvalidWordSequenceException(int n) {
		super("Provided seqence is invalid. Maximum " + n + " words are allowed.");
	}
}
