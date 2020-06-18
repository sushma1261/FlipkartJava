package com.flipkart.rest;

import java.util.ArrayList;
import java.util.List;

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

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.CatalogInterface;
import com.flipkart.service.CatalogOperation;

//Admin rest controller class 
//Performs rest operations for different admin queries
@Path("/admin")
public class AdminRestController {
	AdminInterface adminOperation = new AdminOperation();
	
	@GET
	@Path("/courses/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getAllCourses() {
		CatalogInterface catalogOperation = new CatalogOperation();
		ArrayList<Course> courseList = catalogOperation.searchAndFetchDetails();
		return courseList;
	}
	
	@GET
	@Path("/view-users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> viewUsers() {
		
		List<User> userList = adminOperation.viewUsers();
		return userList;
	}
	
	@POST
	@Path("/add-course")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		System.out.println("Adding new course");
		System.out.println(course.getCourseId());
		System.out.println(course.getCourseName());
		adminOperation.addNewCourseInCatalog(course);
		String result = "Added "  + course;
		return Response.status(201).entity(result).build();
	}
	
	// Drop course
	@DELETE
	@Path("/delete-course/{courseId}")
	public Response deleteCourse(@PathParam("courseId") int courseId) throws URIReferenceException{
		Course course = new Course();
		course.setCourseId(courseId);
		adminOperation.deleteCourse(course);
		return Response.status(200).entity("Course " + " with course id " + courseId + " successfully deleted").build();
	}
	
	// Update professor course
	@PUT
	@Path("/update-professor-course/{courseId}/{professorId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public void assignProfessorToCourse(@PathParam("professorId") int professorId, @PathParam("courseId") int courseId) {
		Professor professor = new Professor();
		professor.setProfessorId(professorId);
		adminOperation.assignProfessor(professor, courseId);
	}
	
	
	
}
