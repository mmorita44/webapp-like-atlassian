package com.mmorita44.ao;

import net.java.ao.DefaultSchemaConfiguration;
import net.java.ao.EntityManagerConfiguration;
import net.java.ao.SchemaConfiguration;
import net.java.ao.atlassian.*;
import net.java.ao.schema.*;
import net.java.ao.schema.info.CachingEntityInfoResolverFactory;
import net.java.ao.schema.info.EntityInfoResolverFactory;

/**
 * Atlassian Entity Manager Configuration
 *
 * @author Masato Morita
 * @since 1.0.0
 */
public class AtlassianEntityManagerConfiguration implements EntityManagerConfiguration
{
    @Override
    public boolean useWeakCache()
    {
        // Sets a default value
        return false;
    }

    @Override
    public NameConverters getNameConverters()
    {
        return new NameConverters()
        {
            @Override
            public TableNameConverter getTableNameConverter()
            {
                // Sets the Atlassian table name converter
                return new TableAnnotationTableNameConverter(new AtlassianTableNameConverter(s -> s));
            }

            @Override
            public FieldNameConverter getFieldNameConverter()
            {
                // Sets the Atlassian field name converter
                return new AtlassianFieldNameConverter();
            }

            @Override
            public SequenceNameConverter getSequenceNameConverter()
            {
                // Sets the Atlassian sequence name converter
                return new AtlassianSequenceNameConverter();
            }

            @Override
            public TriggerNameConverter getTriggerNameConverter()
            {
                // Sets the Atlassian trigger name converter
                return new AtlassianTriggerNameConverter();
            }

            @Override
            public IndexNameConverter getIndexNameConverter()
            {
                // Sets the Atlassian index name converter
                return new AtlassianIndexNameConverter();
            }

            @Override
            public UniqueNameConverter getUniqueNameConverter()
            {
                // Sets the Atlassian unique name converter
                return new AtlassianUniqueNameConverter();
            }
        };
    }

    @Override
    public SchemaConfiguration getSchemaConfiguration()
    {
        // Sets a default object
        return new DefaultSchemaConfiguration();
    }

    @Override
    public EntityInfoResolverFactory getEntityInfoResolverFactory()
    {
        // Sets a default object
        return new CachingEntityInfoResolverFactory();
    }
}
