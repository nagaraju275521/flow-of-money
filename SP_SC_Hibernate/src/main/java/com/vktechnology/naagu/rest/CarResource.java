package com.vktechnology.naagu.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vktechnology.naagu.models.Order;
import com.vktechnology.naagu.models.Student;

@Path("/sss")
public class CarResource {
	
	    @GET
	    @Path("/ss")
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getUserById(){
	         
	        
	        return "in jersey";
	    }
	}

