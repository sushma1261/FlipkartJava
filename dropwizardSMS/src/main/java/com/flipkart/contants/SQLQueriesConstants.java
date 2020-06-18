package com.flipkart.contants;

public class SQLQueriesConstants {
	
	//Login query for a user
	public static String LOGIN_QUERY = "SELECT r.role from Role r join  user u on r.roleId = u.roleId where username = ? and password = ?";
	public static String REGISTER_USER_QUERY = "insert into user(UserId, username, password, roleId) values (?,?,?,?)";
	public static String VIEW_CATALOG_QUERY = "select courseId, courseName, fees, courseDescription from Course";
	public static String VIEW_COURSE_QUERY = "select * from Course where courseId = ?";
	public static String GET_ROLE_ID_QUERY = "select roleId from role where role = ?";
	
	// Student Queries
	public static String GET_STUDENT_DETAILS_QUERY = "select studentId, studentName, branch, hasScholarship, gender from Student where studentName = ?";
	public static String REGISTER_STUDENT_QUERY = "insert into Student(studentId, studentName, branch, hasScholarship, gender, semester) values (?,?,?,?,?,?)";
	public static String ADD_COURSE_STUDENT_QUERY = "insert into RegisteredCourses(studentId, courseId, courseType, registeredDate) values(?,?,?,?)";
	public static String GET_REGISTERED_COURSES_QUERY = "select studentId, courseId, courseType, registeredDate from RegisteredCourses where studentId = ?";
	public static String DROP_COURSE_STUDENT_QUERY = "delete from RegisteredCourses where courseId = ? and studentId = ?";
	public static String COUNT_REGISTERED_COURSES_QUERY = "select count(*) from RegisteredCourses where studentId = ?";
	public static String CHECK_IF_REGISTERED_TO_COURSE_QUERY = "select count(*) from RegisteredCourses where studentId = ? and courseId = ?";
	public static String GET_STUDENT_NAME_QUERY = "select * from Student where studentId = ?";
	public static String VIEW_GRADES_QUERY = "select c.courseId, c.courseName, rc.grade from Course c join RegisteredCourses rc on rc.courseId = c.courseId where rc.studentId = ?";
	public static String CALCULATE_TOTAL_FEE = "select sum(c.fees) from RegisteredCourses rc join Course c on c.courseId = rc.courseId where rc.studentId = ?"; 
	public static String MAKE_PAYMENT_QUERY = "insert into Payment(studentId, feesPaid, paymentMethodId, paymentDate) values(?, ?, ?, ?)";
	public static String UPDATE_AFTER_PAYMENT = "update Student set isRegistered = 1 where studentId = ?";
	
	// Professor Queries
	public static String REGISTER_PROFESSOR_QUERY = "insert into Professor(professorId, professorName, gender) values (?,?,?)";
	public static String GET_PROFESSOR_DETAILS_QUERY = "select professorId, professorName, gender from Professor where professorName = ?";
	public static String GET_STUDENT_IDS_FOR_COURSE_QUERY = "select studentId from RegisteredCourses where courseId = ?";
	public static String GET_COURSE_TAUGHT_BY_PROFESSOR = "select p.courseId, c.courseName, c.courseDescription from ProfessorCourse p join Course c on c.courseId = p.courseId where p.professorId = ?";
	public static String GET_STUDENTS_TAUGHT = "select rc.studentId, rc.courseId, s.studentName, s.branch, s.gender, s.semester from Student s join RegisteredCourses rc on rc.studentId = s.studentId where rc.courseId in (select courseId from ProfessorCourse where professorId = ?) order by rc.courseId";
	public static String VALID_COURSE_FOR_PROFESSOR = "select count(*) from ProfessorCourse where professorId = ? and courseId = ?";
	public static String GRADE_STUDENT_QUERY = "update RegisteredCourses set grade = ? where studentId = ? and courseId = ?";
	public static String VALID_STUDENT_COURSE = "select count(*) from RegisteredCourses where studentId = ? and courseId = ?";
	
	// Admin Queries
	public static String VIEW_USERS_QUERY = "SELECT  u.userId, u.username, r.role from user u join Role r on r.roleId = u.roleId;";
	public static String ASSIGN_PROFESSOR_QUERY = "update ProfessorCourse SET professorId=? WHERE courseId = ?";
	public static String ADD_NEW_COURSE_QUERY = "insert into Course(courseId, courseName, fees, courseDescription, catalogId) values (?,?, ?, ?, ?)";
	public static String DELETE_COURSE_QUERY = "delete from Course where courseId = ?";
	
	
}
