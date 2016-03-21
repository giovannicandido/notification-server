package info.atende.nserver.test.annotations;

import info.atende.nserver.Application;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Giovanni Silva
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {Application.class})
@ActiveProfiles(value = {"ci","test"})

public @interface SpringIntegrationTest {
}
