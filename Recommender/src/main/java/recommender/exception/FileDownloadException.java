package recommender.exception;

public class FileDownloadException extends RuntimeException {

	public FileDownloadException(String url, Exception e) {
		super("Error occurred while downloading '" + url + "'!", e);
	}
}
