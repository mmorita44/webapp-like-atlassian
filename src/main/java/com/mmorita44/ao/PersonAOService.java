package com.mmorita44.ao;

import java.util.Optional;

/**
 * Person ActiveObjects Service Component Interface
 *
 * @author Masato Morita
 * @since 1.0.0
 */
public interface PersonAOService
{
    /**
     * Get a array of person entities
     *
     * @return a new or empty array of person entities
     */
    PersonEntity[] getPersons();

    /**
     * Get a person entity
     *
     * @return a new optional person entity
     */
    Optional<PersonEntity> getPerson(String name);

    /**
     * Create a new person entity
     *
     * @param name a nullable name of a person entity
     * @param age an age of a person entity
     * @return a new person entity
     */
    PersonEntity createPerson(String name, int age);

    /**
     * Update a person entity
     *
     * @param id an ID of a person entity
     * @param name a nullable name of a person entity
     * @param age an age of a person entity
     * @return a updated person entity
     */
    PersonEntity updatePerson(int id, String name, int age);

    /**
     * Delete a person entity
     *
     * @param id an ID of a person entity
     */
    void deletePerson(int id);
}
