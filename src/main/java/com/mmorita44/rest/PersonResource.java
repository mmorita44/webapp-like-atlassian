package com.mmorita44.rest;

import com.mmorita44.ao.PersonAOService;
import com.mmorita44.ao.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Person Resource
 *
 * @author Masato Morita
 * @since 1.0.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Path("person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource
{
    private final PersonAOService personAOService;

    /**
     * Get an all person
     *
     * @return a new response which has an ok status and an array of person beans
     */
    @GET
    public Response getAll()
    {
        val entities = personAOService.getPersons();
        val beans = Stream.of(entities).map(e -> CONVERT_TO_BEAN.apply(e)).toArray(PersonBean[]::new);
        return Response.ok(beans).build();
    }

    /**
     * Create a new person
     *
     * @return a new response which has an ok status and a created person bean
     */
    @POST
    public Response create(PersonBean bean)
    {
        val entity = personAOService.createPerson(bean.getName(), bean.getAge());
        return Response.ok(CONVERT_TO_BEAN.apply(entity)).build();
    }

    /**
     * Update a person
     *
     * @return a new response which has an ok status and a updated person bean
     */
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") final int id, PersonBean bean)
    {
        val entity = personAOService.updatePerson(id, bean.getName(), bean.getAge());
        return Response.ok(CONVERT_TO_BEAN.apply(entity)).build();
    }

    /**
     * Delete a person
     *
     * @return a new response which has an ok status
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final int id)
    {
        personAOService.deletePerson(id);
        return Response.ok().build();
    }

    private Function<PersonEntity, PersonBean> CONVERT_TO_BEAN =
            e -> PersonBean.builder().id(e.getID()).name(e.getName()).age(e.getAge()).build();
}
