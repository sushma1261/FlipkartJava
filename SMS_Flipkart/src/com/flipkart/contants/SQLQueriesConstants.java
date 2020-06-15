package com.flipkart.contants;

public class SQLQueriesConstants {
	
	//Login query for a user
	public static String LOGIN_QUERY = "select username, password, role from user where username = ? and password = ?";
	public static String REGISTER_USER_QUERY = "insert into user(UserId, username, password, role) values (?,?,?,?)";
	public static String VIEW_CATALOG_QUERY = "select courseId, courseName, professor from Catalog";
	public static String VIEW_COURSE_QUERY = "select * from Catalog where courseId = ?";
	
	// Student Queries
	public static String GET_STUDENT_DETAILS_QUERY = "select studentId, studentName, branch, hasScholarship, gender from Student where studentName = ?";
	public static String REGISTER_STUDENT_QUERY = "insert into Student(studentId, studentName, branch, hasScholarship, gender, semester) values (?,?,?,?,?,?)";
	public static String ADD_COURSE_STUDENT_QUERY = "insert into RegisteredCourses(studentId, courseId, courseType, registeredDate) values(?,?,?,?)";
	public static String GET_REGISTERED_COURSES_QUERY = "select studentId, courseId, courseType, registeredDate from RegisteredCourses where studentId = ?";
	public static String DROP_COURSE_STUDENT_QUERY = "delete from RegisteredCourses where courseId = ? and studentId = ?";
	public static String COUNT_REGISTERED_COURSES_QUERY = "select count(*) from RegisteredCourses where studentId = ?";
	public static String CHECK_IF_REGISTERED_TO_COURSE_QUERY = "select count(*) from RegisteredCourses where studentId = ? and courseId = ?";
	public static String GET_STUDENT_NAME_QUERY = "select * from Student where studentId = ?";
	public static String VIEW_GRADES_QUERY = "select c.courseId, c.courseName, rc.grade from Catalog c join RegisteredCourses rc on rc.courseId = c.courseId where rc.studentId = ?";
	
	// Professor Queries
	public static String REGISTER_PROFESSOR_QUERY = "insert into Professor(professorId, professorName, gender) values (?,?,?)";
	public static String GET_PROFESSOR_DETAILS_QUERY = "select professorId, professorName, gender from Professor where professorName = ?";
	public static String GET_STUDENT_IDS_FOR_COURSE_QUERY = "select studentId from RegisteredCourses where courseId = ?";
	public static String GET_COURSE_TAUGHT_BY_PROFESSOR = "select courseId from Catalog where professor = ?";
	public static String GET_STUDENTS_TAUGHT = "SELECT s.studentId, rc.courseId, s.studentName, s.branch, s.gender, s.semester from student s join RegisteredCourses rc  on rc.studentId = s.studentId where rc.courseId in (select courseId from Catalog where professor = ?) ORDER BY rc.courseId";
	public static String VALID_COURSE_FOR_PROFESSOR = "select count(*) from Catalog where professor = ? and courseId = ?";
	public static String GRADE_STUDENT_QUERY = "update RegisteredCourses set grade = ? where studentId = ? and courseId = ?";
	
	// Admin Queries
	public static String VIEW_USERS_QUERY = "select * from user";
	public static String ASSIGN_PROFESSOR_QUERY = "update Catalog SET professor=? WHERE courseId = ?";
	public static String ADD_NEW_COURSE_QUERY = "insert into Catalog(courseId, courseName) values (?,?)";
	public static String DELETE_COURSE_QUERY = "delete from Catalog where courseId = ?";
	
	
}
