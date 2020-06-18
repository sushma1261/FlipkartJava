package com.flipkart.rest;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;
import com.flipkart.model.Course;
import com.flipkart.model.Student;
import com.flipkart.service.CatalogInterface;
import com.flipkart.service.CatalogOperation;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;

// Student rest controller class 
// Performs rest operations for different student queries
@Path("/student")
public class StudentRestController {
	StudentInterface studentOperation = new StudentOperation();
	private static Logger logger = Logger.getLogger(StudentRestController.class);
	
	// View catalog 
	@GET
	@Path("/courses/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getAllCourses() {
		CatalogInterface catalogOperation = new CatalogOperation();
		ArrayList<Course> courseList = catalogOperation.searchAndFetchDetails();
		return courseList;
	}
	
	// View a course
	@GET
	@Path("/view-course/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getRegisteredCourses(@PathParam("id") int id) {
		Student student = new Student();
		student.setStudentId(id);
		ArrayList<Course> registeredCourses = studentOperation.viewRegisteredCourses(student);
		return registeredCourses;	
	}
	
	// View Grades
	@GET
	@Path("view-grade/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> viewGrades(@PathParam("id") int id) {
		Student student = new Student();
		student.setStudentId(id);
		return 	studentOperation.viewGrades(student);
	}
	
	// Choose course
	@POST
	@Path("/add-course/{studentId}/{courseId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourses(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) {
		System.out.println("hit post service");
		System.out.println("value of course id:" + courseId);
		System.out.println("value of student id:" + studentId);
		String result = "Saved "  + courseId + studentId;
		Student student = new Student();
		student.setStudentId(studentId);
		studentOperation.chooseCourse(student, courseId);
		return Response.status(201).entity(result).build();
	}
	
	// make payment
	@POST
	@Path("/make-payment/{studentId}/{paymentMethod}/{fees}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment(@PathParam("studentId") int studentId, @PathParam("paymentMethod") int paymentMethod, @PathParam("fees") int fees) {
		System.out.println("hit post service");
		System.out.println("value of student id:" + studentId);
		System.out.println("value of payment Method" + paymentMethod);
		System.out.println("value of fees:" + fees);
		Student student = new Student();
		student.setStudentId(studentId);
		String result = "Made Payment for student with student Id " + studentId;
		
		studentOperation.makePayment(student, paymentMethod, fees);
		return Response.status(201).entity(result).build();
	}
	
	// delete course from registered courses
	@DELETE
	@Path("/delete-course/{studentId}/{courseId}")
	public Response deleteCustomer(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) throws URIReferenceException{
		Course course = new Course();
		Student student = new Student();
		course.setCourseId(courseId);
		student.setStudentId(studentId);
		studentOperation.dropCourse(course, student);
		return Response.status(200).entity("The course " + courseId + " for student " + studentId + "deleted" ).build();
	}
	
	
	
	
}
