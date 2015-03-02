package student;

import student.Student;
import student.StudentClient;

public class StudentCommand {

	private Student student;
	private StudentClient client;

	public StudentCommand() {
		this.student = new Student();
	}

	public Student getStudent() {
		return student;
	}

	/**
	 * Associated with the CLASS_INFO command, getting ready to establish a
	 * connection.
	 * 
	 * @param port
	 *            - port number of the server
	 * @param ip
	 *            - address of the server
	 */
	public void classInfo(int port, String ip) {
		student.setClassPort(port);
		student.setClassIP(ip);
	}

	/**
	 * Send the answer to the server with the above credentials
	 * 
	 * @param yourChoice
	 *            - the selection.
	 */
	public void enterChoice(String yourChoice) {
		student.setChoice(yourChoice);
		if (student.getClassPort() > 0 && student.getClassPort() > 0) {

			client.sendChoice();
		}
		// send answer and student number

	}

	/**
	 * Associated with the SETUDENT_NUMBER command
	 * 
	 * @param studentNumber
	 *            - the users student number
	 * @return The student number in the class
	 */
	public void setStudentNumber(int studentNumber) {
		student.setStudentNumb(studentNumber);
	}

	public void getChoices() {
		if (student.getClassPort() > 0 && student.getClassPort() > 0) {
			this.client = new StudentClient(this.student);
			client.getChoices();

		}
	}

}
