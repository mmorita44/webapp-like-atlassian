package com.mmorita44.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Table;

/**
 * Person Entity
 *
 * @author Masato Morita
 * @since 1.0.0
 */
@Table("PERSON")
public interface PersonEntity extends Entity
{
    /**
     * Get a name
     *
     * @return a new nullable name
     */
    String getName();

    /**
     * Set a name
     *
     * @param name a nullable name
     */
    void setName(String name);

    /**
     * Get an age
     *
     * @return a new age
     */
    int getAge();

    /**
     * Set an age
     *
     * @param age a age
     */
    void setAge(int age);

    enum COLUMN
    {
        ID, NAME, AGE;

        COLUMN() {}
    }
}
