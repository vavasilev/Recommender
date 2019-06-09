package recommender;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

/**
 * Application for indexing texts and giving word recommendations
 * @author vasilev
 *
 */
@SpringBootApplication
public class RecommenderApp {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(RecommenderApp.class, args);
	}

	@Bean
	public PromptProvider myPromptProvider() {
		return () -> new AttributedString("recommender:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	}
}
