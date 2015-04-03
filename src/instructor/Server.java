package instructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Handles each client that connects to the server.
 * 
 * @author cse23170
 *
 */
public class Server extends Thread {

	Socket socket;
	Instructor instructor;

	/**
	 * Constructor for creating the server-client connection
	 * 
	 * @param clientSocket
	 *            - socket of the client
	 * @param instructor
	 *            - model of the instructor
	 */
	public Server(Socket clientSocket, Instructor instructor) {
		this.socket = clientSocket;
		this.instructor = instructor;
	}

	/**
	 * Handle the client connection and, and begin the thread.
	 */
	public void run() {

		PrintWriter out;
		BufferedReader in;

		try {

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);
			String studentNumb = in.readLine();

			//System.out.println("Student " + studentNumb + " is connected.");
			int studentNumber;
			try {
				studentNumber = Integer.parseInt(studentNumb);
			} catch (NumberFormatException e) {
				studentNumber = -1;
			}

			boolean isStudent = instructor.getStudents()
					.contains(studentNumber) && studentNumber > 0;
			if (isStudent) {
				if (instructor.getAnswers().get(studentNumber)
						.get(instructor.getCurrentQuestion()) == "0") {

					for (int j = 0; j < instructor.getQuestions().size(); j++) {
						out.println(instructor.getQuestions()
								.get(instructor.getCurrentQuestion()).get(j));
					}

					out.println("sent");

					String choice;
					while ((choice = in.readLine()) != null) {
						instructor.addStudentAnswer(studentNumber, choice);

					//	System.out.println("Student "
						//		+ studentNumb
							//	+ " selected "
								//+ instructor.getAnswers().get(studentNumber)
									//	.get(instructor.getCurrentQuestion()));
					}

				} else {
					for (int j = 0; j < instructor.getQuestions().size(); j++) {
						out.println(instructor.getQuestions()
								.get(instructor.getCurrentQuestion()).get(j));
					}

					// Student has already received the questions and would like
					// to change their answer.
					out.println("sent");

					String choice;
					while ((choice = in.readLine()) != null) {
						System.out.println("Student " + studentNumb
								+ " selected " + choice);
						instructor.addStudentAnswer(studentNumber, choice);
					}

				}

			} else {
				out.println("Connection not established");
			}

			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
