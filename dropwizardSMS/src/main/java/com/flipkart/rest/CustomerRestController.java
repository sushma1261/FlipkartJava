package com.flipkart.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import com.flipkart.model.Customer;

@Path("/customer")
public class CustomerRestController {

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerDetails() {
		Customer customer = new Customer();
		customer.setId(612);
		customer.setName("sushma12");
		customer.setAddress("Bhimavaram");
		return customer;
	}
	
	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postFunction(Customer customer) {
		System.out.println("hit post service");
		System.out.println("value of id:" + customer.getId());
		System.out.println("value of name:" + customer.getName());
		String result = "Saved" + customer;
		return Response.status(201).entity(result).build();
	}
	
	
	@DELETE
	@Path("/delete/{customerId}")
	public Response deleteCustomer(@PathParam("customerId") int customerId) throws URIReferenceException{
		return Response.status(200).entity("Customer is "+ customerId + " successfully deleted").build();
	}
	
	
	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer customer) {
		customer.setName(customer.getName() + "updated");
		return customer;
	}
}
