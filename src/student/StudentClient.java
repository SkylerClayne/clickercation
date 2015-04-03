package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class to control the client communication to the server, one StudentClient
 * object is created for every student that connects
 * 
 * @author cse23170
 *
 */
public class StudentClient {

	protected static Student student;
	protected static PrintWriter out;
	protected static BufferedReader in;
	protected static BufferedReader stdIn;
	protected static Socket echoSocket;
	protected static String hostName;
	protected static int portNumber;

	/**
	 * Constructor to
	 * 
	 * @param stnd
	 *            - The student connecting to the instructors server
	 */
	public StudentClient(Student stnd) {
		student = stnd;
		StudentClient.hostName = student.getClassIp();
		StudentClient.portNumber = student.getClassPort();
	}

	/**
	 * Close the connection to the server
	 */
	protected static void close() {
		try {
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the current choices from the instructor server
	 */
	protected void getChoices() {
		String choices;
		try {
			echoSocket = new Socket(hostName, portNumber);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			out.println(student.getStudentNumb());

			while ((choices = in.readLine()) != null) {
				if (choices.equals("sent")) {
					break;
				}
				System.out.println(choices);
			}
			sendChoice();
			close();
		} catch (Exception e) {
			if (!(student.getStudentNumb() > 0)) {
				System.out.println("Not student number");
			} else {
				System.out.println("Cannot answer question at this time");
			}
		}

	}

	/**
	 * Send the choice to the instructor server
	 */
	@SuppressWarnings("resource")
	protected void sendChoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Choice: ");
		String choice = sc.next();
		out.println(choice);

	}

}
