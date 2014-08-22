package config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Execute all migrations in DataBase on Startup
 * @author Giovanni Silva
 */
@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class DbMigrator {
    @Resource(mappedName = "java:jboss/datasources/MessageServerDS")
    private DataSource dataSource;

    @PostConstruct
    private void onStartup() {
        Logger logger = logger();
        if (dataSource == null) {
            logger.severe("no datasource found to execute the db migrations!");
            throw new EJBException(
                    "no datasource found to execute the db migrations!");
        }

        Flyway flyway = new Flyway();
        flyway.setInitOnMigrate(true);
        flyway.setDataSource(dataSource);
        for (MigrationInfo i : flyway.info().all()) {
            logger.info("migrate task: " + i.getVersion() + " : "
                    + i.getDescription() + " from file: " + i.getScript());
        }
        flyway.migrate();
    }
    private Logger logger(){
        return Logger.getLogger(DbMigrator.class.getName());
    }

}
