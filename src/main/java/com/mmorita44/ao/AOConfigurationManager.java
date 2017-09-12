package com.mmorita44.ao;

import net.java.ao.EntityManager;

/**
 * ActiveObjects Configuration Manager Interface
 *
 * @author Masato Morita
 * @since 1.0.0
 */
public interface AOConfigurationManager
{
    /**
     * Get a entity manager
     *
     * @return a new entity manager
     */
    EntityManager getEntityManager();
}
