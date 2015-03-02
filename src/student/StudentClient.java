package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class StudentClient {

	public static Student student;
	public static PrintWriter out;
	public static BufferedReader in;
	public static BufferedReader stdIn;
	public static Socket echoSocket;
	static String hostName;
	static int portNumber;

	public StudentClient(Student stnd) {
		student = stnd;
		StudentClient.hostName = student.getClassIp();
		StudentClient.portNumber = student.getClassPort();
	}

	public static void close() {
		try {
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getChoices() {
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

	@SuppressWarnings("resource")
	public void sendChoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Choice: ");
		String choice = sc.next();
		out.println(choice);

	}

}
