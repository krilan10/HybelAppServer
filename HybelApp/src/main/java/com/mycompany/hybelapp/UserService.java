/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hybelapp;

import Domain.User;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @PersistenceContext
    EntityManager em;
    
    @GET
    public List<User> getUsers(){
        List<User> result;
        result = em.createQuery("SELECT u FROM User u",
                User.class).getResultList();
        return result != null ? result : Collections.EMPTY_LIST;
    }
    
    @GET
    @Path("new")
    public Response newUser(@QueryParam("name")String name){
        User u = new User(name);
        em.persist(u);
        return Response.ok(u).build();
        
    }
    
}
