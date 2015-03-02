package student;

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

	public void setStudentNumb(int studentNumb) {
		this.studentNumb = studentNumb;
	}

	public int getStudentNumb() {
		return studentNumb;
	}

	public int getClassPort() {
		return classPort;
	}

	public String getClassIp() {
		return classIp;
	}

	public String getChoice() {
		return this.choice;
	}

	public String setChoice(String yourChoice) {
		this.choice = yourChoice;
		return this.choice;
	}

	/**
	 * 
	 * @param port
	 * @return
	 */
	public int setClassPort(int port) {
		this.classPort = port;
		return this.classPort;
	}

	/**
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
