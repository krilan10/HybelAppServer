
package com.mycompany.hybelapp;

import com.mycompany.domain.Hybel;
import com.mycompany.domain.Username;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kristian
 */
@Stateless
@Path("hybel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HybelService {
    @PersistenceContext
    EntityManager em;
    
    @GET
    public List<Hybel> getHybler(){
        List<Hybel> result;
        result = em.createQuery("SELECT h FROM Hybel h",
                Hybel.class).getResultList();
        return result != null ? result : Collections.EMPTY_LIST;
    }
    
    @Path("user")
    @GET
    public List<Username> getUsers(){
        List<Username> result;
        result = em.createQuery("SELECT u FROM Username u",
                Username.class).getResultList();
        return result != null ? result : Collections.EMPTY_LIST;
    }
    
    
    
    @GET
    @Path("newuser")
    public Response newUser(@QueryParam("name")String name){
        Username u = new Username(name);
        em.persist(u);
        return Response.ok(u).build();  
    }
    
    @GET
    @Path("newhybel")
    public Response newHybel(@QueryParam("name")String name){
        Hybel h = new Hybel(name);
        em.persist(h);
        return Response.ok(h).build();  
    }
    
}
