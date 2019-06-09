package recommender.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Service;

import recommender.exception.FileDownloadException;

/**
 * A service for downloading files from network
 * @author vasilev
 *
 */
@Service
public class FileDownloadService {

	/**
	 * Downloads a file from network url
	 * @param url HTTP url to download from. Should point to a text file.
	 * @return The contents of the downloaded file
	 */
	public String downloadFile(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			      .uri(URI.create(url))
			      .build();
		try {
			HttpResponse<String> response =
				      client.send(request, BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			throw new FileDownloadException(url, e);
		}
	}
}
