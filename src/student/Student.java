package student;

/**
 * Student models the operations/needed information of a student.
 * 
 * @author cse23170
 *
 */
public class Student {

	private int studentNumb;
	private int classPort;
	private String classIp;
	private String choice;

	/**
	 * Default constructor
	 */
	public Student() {

	}

	/**
	 * Sets the ID of this student
	 * 
	 * @param studentNumb
	 *            - Students id
	 */
	public void setStudentNumb(int studentNumb) {
		this.studentNumb = studentNumb;
	}

	/**
	 * Get the id of this student
	 * 
	 * @return - this students id
	 */
	public int getStudentNumb() {
		return studentNumb;
	}

	/**
	 * Get this class port
	 * 
	 * @return - this class port
	 */
	public int getClassPort() {
		return classPort;
	}

	/**
	 * Get this class ip address
	 * 
	 * @return - this ip address
	 */
	public String getClassIp() {
		return classIp;
	}

	/**
	 * Get the current choices
	 * 
	 * @return - the choices
	 */
	public String getChoice() {
		return this.choice;
	}

	/**
	 * Set the choice that this student has made
	 * 
	 * @param yourChoice
	 *            - the students choice
	 * @return - this students choice
	 */
	public String setChoice(String yourChoice) {
		this.choice = yourChoice;
		return this.choice;
	}

	/**
	 * Set this class port
	 * 
	 * @param port
	 *            - the class port
	 * @return - this class port
	 */
	public int setClassPort(int port) {
		this.classPort = port;
		return this.classPort;
	}

	/**
	 * Set the class IP address
	 * 
	 * @param ip
	 *            - Class ip address
	 * @return the current ip address
	 */
	public String setClassIP(String ip) {
		this.classIp = ip;
		return this.classIp;
	}

}
