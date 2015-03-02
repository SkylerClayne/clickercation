package instructor;


import instructor.Instructor;
import instructor.InstructorServer;

import java.util.ArrayList;

public class InstructorCommand {
	static InstructorServer server;
	static Instructor instructor;
	Runnable r;
	Thread t;
	public static boolean running = false;

	/**
	 * default constructor
	 */
	public InstructorCommand() {
		InstructorCommand.server = new InstructorServer();
		InstructorCommand.instructor = new Instructor();
		server.connect(instructor);

	}

	public void startQuestion() {

		int question = instructor.getCurrentQuestion() + 1;
		System.out.println("Question " + question + " started");
		running = true;
		server.connect(instructor);
	}

	public void endQuestion() {

		System.out.println("Question Ended");
		running = false;
		server.closeServer();

	}

	public void setQuestion(int question) {
		if ((question > 0) && (question > instructor.getQuestions().size())) {
			System.out.println("Not a valid Question!");
		} else {
			instructor.setCurrentQuestion((question - 1));
		}
	}

	/**
	 * Lists students who send answers
	 */
	public void list() {
		ArrayList<Integer> students = instructor.getStudents();
		for (int i = 0; i < students.size(); i++) {
			System.out.println("Student: " + students.get(i));
		}

	}

	public void startServer() {

		int question = instructor.getCurrentQuestion() + 1;
		System.out.println("Question " + question + " started");
		running = true;
		server.connect(instructor);

	}
}
