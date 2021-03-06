package org.mayocat.flyway;

import com.googlecode.flyway.core.Flyway;
import com.yammer.dropwizard.cli.ConfiguredCommand;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.ConfigurationStrategy;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import net.sourceforge.argparse4j.inf.Namespace;

/**
 * @version $Id$
 */
public class FlywayMigrateCommand<T extends Configuration> extends ConfiguredCommand<T>
{
    public static final String MAYOAPP_MIGRATIONS = "mayoapp/migrations";

    private final ConfigurationStrategy<T> strategy;

    private final Class<T> configurationClass;

    public FlywayMigrateCommand(ConfigurationStrategy<T> strategy,
            Class<T> configurationClass)
    {
        super("flyway-migrate", "Migrate database with Flyway");

        this.strategy = strategy;
        this.configurationClass = configurationClass;
    }

    @Override
    protected Class<T> getConfigurationClass() {
        return configurationClass;
    }

    @Override
    protected void run(Bootstrap<T> bootstrap, Namespace namespace, T configuration) throws Exception
    {
        final DatabaseConfiguration dbConfig = strategy.getDatabaseConfiguration(configuration);
        dbConfig.setMaxSize(1);
        dbConfig.setMinSize(1);

        Flyway flyway = new Flyway();
        flyway.setLocations(MAYOAPP_MIGRATIONS);
        flyway.setDataSource(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
        flyway.migrate();
    }
}
