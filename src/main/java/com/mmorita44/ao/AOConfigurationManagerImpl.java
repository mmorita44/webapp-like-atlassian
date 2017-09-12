package com.mmorita44.ao;

import lombok.val;
import net.java.ao.EntityManager;
import net.java.ao.builder.EntityManagerBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ActiveObjects Configuration Manager Interface
 *
 * @author Masato Morita
 * @since 1.0.0
 */
public class AOConfigurationManagerImpl implements AOConfigurationManager
{
    private final EntityManager entityManager;

    public AOConfigurationManagerImpl() throws IOException, SQLException
    {
        val properties = getJDBCProperties();

        // Configures the properties, gets an original entity manager
        val originEntityManager = EntityManagerBuilder
                .url(properties.getProperty("db.uri"))
                .username(properties.getProperty("db.username"))
                .password(properties.getProperty("db.password"))
                .auto()
                .build();

        // Sets the Atlassian name converter to the entity manager
        entityManager = new EntityManager(
                originEntityManager.getProvider(),
                new AtlassianEntityManagerConfiguration()
        );

        // Migrates the entities
        entityManager.migrate(PersonEntity.class);
    }

    /**
     * Get JDBC properties
     *
     * @return a new JDBC properties
     */
    private Properties getJDBCProperties() throws IOException
    {
        val properties =  new Properties();
        properties.load(getClass().getResourceAsStream("/db.properties"));
        return  properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityManager getEntityManager()
    {
        return entityManager;
    }
}
