package com.flipkart.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.service.CatalogInterface;
import com.flipkart.service.CatalogOperation;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

// Professor rest controller class 
// Performs rest operations for different professor queries
@Path("/professor")
public class ProfessorRestController {

	ProfessorInterface professorOperation = new ProfessorOperation();

	@GET
	@Path("/courses/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getAllCourses() {
		CatalogInterface catalogOperation = new CatalogOperation();
		ArrayList<Course> courseList = catalogOperation.searchAndFetchDetails();
		return courseList;
	}

	@GET
	@Path("/view-students/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewStudents(@PathParam("professorId") int professorId) {
		Professor professor = new Professor();
		professor.setProfessorId(professorId);
		return professorOperation.viewStudents(professor);
	}

	@POST
	@Path("grade-student/{professorId}/{studentId}/{courseId}/{grade}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gradeStudent(@PathParam("professorId") int professorId, @PathParam("studentId") int studentId,
			@PathParam("courseId") int courseId, @PathParam("grade") String grade) {
		Professor professor = new Professor();
		professor.setProfessorId(professorId);
		professorOperation.gradeStudent(professor, studentId, grade, courseId);
		String result = "Grades for student Id " + studentId + " with course " + courseId + "updated with grade " + grade;
		return Response.status(201).entity(result).build();
	}

}
