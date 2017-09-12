package com.mmorita44.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Person Bean
 *
 * @author Masato Morita
 * @since  1.0.0
 */
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonBean
{
    /**
     * -- GETTER --
     * Get an ID
     *
     * @return a new ID
     *
     * -- SETTER --
     * Set an ID
     *
     * @param id a new ID
     */
    @XmlElement
    private int id;

    /**
     * -- GETTER --
     * Get a name
     *
     * @return a nullable new name
     *
     * -- SETTER --
     * Set a name
     *
     * @param id a new nullable name
     */
    @XmlElement
    private String name;

    /**
     * -- GETTER --
     * Get an age
     *
     * @return a new age
     *
     * -- SETTER --
     * Set an age
     *
     * @param id a new age
     */
    @XmlElement
    private int age;
}
