package com.mycompany.hybelapp;


import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;


/**
 *
 * @author kristian
 */
@ApplicationPath ("api")
public class RestConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> resources = new HashSet<>();
        resources.add(MultiPartFeature.class);
        addRestResourceClasses(resources);
        return resources;
        
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.hybelapp.HybelService.class);
        
    }
    
}
