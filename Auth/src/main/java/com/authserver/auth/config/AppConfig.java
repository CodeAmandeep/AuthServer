package com.authserver.auth.config;

import com.authserver.auth.endpoints.AuthEndpoint;
import com.authserver.auth.endpoints.SSOEndpoint;
import com.authserver.auth.endpoints.ServiceEndpoint;
import com.authserver.auth.endpoints.TokenEndpoint;
import com.authserver.auth.filters.AuthApiRespFilter;
import com.commons.config.JacksonObjectResolver;
import com.commons.entity.Token;
import com.commons.exception.mapper.*;
import com.commons.objectify.OfyService;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppConfig extends Application {
    private static final ObjectifyFactory ofyService = new ObjectifyFactory();
   public AppConfig(){

      registringOfEntities();

    }


    private void registringOfEntities() {

        /// registring the entities
        OfyService.factory().register(Token.class);
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        // registring com.endpoints
        classes.add(ServiceEndpoint.class);
        classes.add(AuthEndpoint.class);
        classes.add(SSOEndpoint.class);
        classes.add(TokenEndpoint.class);
        
        // json com.config
        classes.add(JacksonObjectResolver.class);
        classes.add(ObjectifyJacksonModule.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();



        // filters
        singletons.add(new AuthApiRespFilter());

        //exception mappers
        singletons.add(new IllegalArgExceptionMapper());
        singletons.add(new ResourceNotFoundExceptionMapper());
        singletons.add(new ForbiddenExceptionMapper());
        singletons.add(new NotAllowedExceptionMapper());
        singletons.add(new JaxRsForbiddenExceptionMapper());
        singletons.add(new InvalidFormatExceptionMapper());
        singletons.add(new GenericExceptionMapper());

        // for now allowing all based on requested origins

        return singletons;
    }
}