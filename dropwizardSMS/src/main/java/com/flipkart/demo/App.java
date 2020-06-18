package com.flipkart.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.rest.AdminRestController;
import com.flipkart.rest.CustomerRestController;
import com.flipkart.rest.ProfessorRestController;
import com.flipkart.rest.StudentRestController;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	 
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
 
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new CustomerRestController());
        e.jersey().register(new StudentRestController());
        e.jersey().register(new AdminRestController());
        e.jersey().register(new ProfessorRestController());
    }
 
    public static void main(String[] args) throws Exception {
    	LOGGER.info("Main");
        new App().run(args);
    }
}
