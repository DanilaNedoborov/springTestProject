package searchengine.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Setter
@Getter
public class Site {
    private String url;
    private String name;
}
