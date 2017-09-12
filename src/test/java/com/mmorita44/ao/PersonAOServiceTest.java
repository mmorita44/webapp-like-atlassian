package com.mmorita44.ao;

import lombok.val;
import net.java.ao.DBParam;
import net.java.ao.EntityManager;
import net.java.ao.test.jdbc.Data;
import net.java.ao.test.jdbc.DatabaseUpdater;
import net.java.ao.test.junit.ActiveObjectsJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Person ActiveObjects Service Component Test
 *
 * @author Masato Morita
 * @since 1.0.0
 */
@RunWith(ActiveObjectsJUnitRunner.class)
@Data(PersonAOServiceTest.PersonAOServiceTestDatabaseUpdater.class)
public class PersonAOServiceTest
{
    private EntityManager entityManager;
    private PersonAOService personAOService;

    /**
     * Setup properties
     */
    @Before
    public void setUp()
    {
        assertNotNull(entityManager);
        val ao = mock(AOConfigurationManagerImpl.class);
        when(ao.getEntityManager()).thenReturn(entityManager);
        personAOService = new PersonAOServiceImpl(ao);
    }

    /**
     * Test the getPersons method
     */
    @Test
    public void testGetPersons()
    {
        val entity = personAOService.getPersons();
        assertEquals(2, entity.length);
    }

    /**
     * Test the getPerson method
     */
    @Test
    public void testGetPerson()
    {
        // Check whether Mike exist
        Optional<PersonEntity> safeEntity = personAOService.getPerson("Mike");
        assertEquals("Mike", safeEntity.get().getName());
        assertEquals(12, safeEntity.get().getAge());

        // Check whether Ken does not exist
        safeEntity = personAOService.getPerson("Ken");
        assertEquals(Optional.empty(), safeEntity);
    }

    /**
     * Test the createPerson method
     */
    @Test
    public void testCreatePerson()
    {
        // Check whether Ken is created
        PersonEntity entity = personAOService.createPerson("Ken", 13);
        assertEquals("Ken", entity.getName());
        assertEquals(13, entity.getAge());

        // Check whether Ken exist
        Optional<PersonEntity> safeEntity = personAOService.getPerson("Ken");
        assertEquals("Ken", safeEntity.get().getName());
        assertEquals(13, safeEntity.get().getAge());
    }

    /**
     * Test the updatePerson method
     */
    @Test
    public void testUpdatePerson()
    {
        // Check whether Mike is updated
        Optional<PersonEntity> sageEntity = personAOService.getPerson("Mike");
        PersonEntity entity = personAOService.updatePerson(sageEntity.get().getID(),"Ken", 13);
        assertEquals("Ken", entity.getName());
        assertEquals(13, entity.getAge());

        // Check whether Ken exist
        sageEntity = personAOService.getPerson("Ken");
        assertEquals("Ken", sageEntity.get().getName());
        assertEquals(13, sageEntity.get().getAge());
    }

    /**
     * Test the deletePerson method
     */
    @Test
    public void testDeletePerson()
    {
        Optional<PersonEntity> sageEntity = personAOService.getPerson("Mike");
        personAOService.deletePerson(sageEntity.get().getID());

        // Check whether Mike does not exist
        sageEntity = personAOService.getPerson("Mike");
        assertEquals(Optional.empty(), sageEntity);
    }

    public static class PersonAOServiceTestDatabaseUpdater implements DatabaseUpdater
    {
        @Override
        public void update(EntityManager entityManager) throws SQLException
        {
            // Migrate a person entity
            entityManager.migrate(PersonEntity.class);

            // Create Mike as a person entity
            entityManager.create(
                    PersonEntity.class,
                    new DBParam(PersonEntity.COLUMN.NAME.name(), "Mike"),
                    new DBParam(PersonEntity.COLUMN.AGE.name(), 12)
            );
            // Create Bob as a person entity
            entityManager.create(
                    PersonEntity.class,
                    new DBParam(PersonEntity.COLUMN.NAME.name(), "Bob"),
                    new DBParam(PersonEntity.COLUMN.AGE.name(), 14)
            );
        }
    }
}
