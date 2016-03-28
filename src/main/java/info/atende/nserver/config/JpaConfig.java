package info.atende.nserver.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Giovanni Silva
 */
@Configuration
@ConfigurationProperties(prefix = "params.datasource")
@EntityScan(basePackages = {"info.atende.webutil.jpa","info.atende.nserver.entity"})
public class JpaConfig extends HikariConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(this);
    }

}
