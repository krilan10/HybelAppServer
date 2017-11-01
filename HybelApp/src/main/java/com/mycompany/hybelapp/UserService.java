/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hybelapp;

import com.mycompany.domain.Users;

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
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {
    @PersistenceContext
    EntityManager em;
    
    @GET
    public List<Users> getUsers(){
        List<Users> result;
        result = em.createQuery("SELECT u FROM Users u",
                Users.class).getResultList();
        return result != null ? result : Collections.EMPTY_LIST;
    }
    
    @GET
    @Path("new")
    public Response newUser(@QueryParam("name")String name){
        Users u = new Users(name);
        em.persist(u);
        return Response.ok(u).build();
        
    }
    
}
