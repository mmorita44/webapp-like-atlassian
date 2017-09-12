package com.mmorita44.ao;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.java.ao.ActiveObjectsException;
import net.java.ao.DBParam;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * Person ActiveObjects Service Component
 *
 * @author Masato Morita
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class PersonAOServiceImpl implements PersonAOService
{
    private final AOConfigurationManager ao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonEntity[] getPersons()
    {
        try
        {
            return ao.getEntityManager().find(PersonEntity.class);
        }
        catch (SQLException e)
        {
            throw new ActiveObjectsException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PersonEntity> getPerson(String name)
    {
        try
        {
            val entity = ao.getEntityManager()
                    .find(
                            PersonEntity.class,
                            String.format(" %s = ?", PersonEntity.COLUMN.NAME),
                            name
                    );
            return entity.length == 0 ? Optional.empty() : Optional.of(entity[0]);
        }
        catch (SQLException e)
        {
            throw new ActiveObjectsException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonEntity createPerson(String name, int age)
    {
        try
        {
            return ao.getEntityManager().create(
                    PersonEntity.class,
                    new DBParam(PersonEntity.COLUMN.NAME.name(), name),
                    new DBParam(PersonEntity.COLUMN.AGE.name(), age)
            );
        }
        catch (SQLException e)
        {
            throw new ActiveObjectsException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonEntity updatePerson(int id, String name, int age)
    {
        try
        {
            val entity = ao.getEntityManager().get(PersonEntity.class, id);
            entity.setName(Objects.nonNull(name) ? name : entity.getName());
            entity.setAge(age != 0 ? age : entity.getAge());
            entity.save();
            return entity;
        }
        catch (SQLException e)
        {
            throw new ActiveObjectsException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePerson(int id)
    {
        try
        {
            ao.getEntityManager().delete(ao.getEntityManager().get(PersonEntity.class, id));
        }
        catch (SQLException e)
        {
            throw new ActiveObjectsException(e);
        }
    }
}
