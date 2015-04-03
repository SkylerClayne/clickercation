package instructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * This class establishes a server connection allowing many clients to connect
 * to it.
 * 
 * @author cse23170
 *
 */
public class InstructorServer {

	private static ServerSocket serverSocket;
	private static int portNumb = 2080;
	public static Instructor instructor;

	private static Thread one;

	/**
	 * Connect client to server.
	 * 
	 * @param inst
	 *            - Instructor singleton passed from the caller
	 */
	public void connect(Instructor inst) {
		// Socket socket;
		instructor = inst;

		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			// Open a thread to run multiple client connections
			one = new Thread(new Runnable() {
				public void run() {
					Socket socket;
					while (true) {

						while (!Mutex.open) {
						}
						if (Mutex.open) {
							try {
								socket = serverSocket.accept();
								new Server(socket, instructor).start();

							} catch (IOException e) {

							}
						} else {
							System.out.println("closed");
						}

					}

				}

			});
			one.start();

			// Deal with user input
			String input;
			System.out.print("Enter Command: ");
			while ((input = sc.next().toLowerCase()) != null) {
				if (input.equals("start_question")) {
					if (Mutex.open == true) {
						System.out.println("Connection is opened");
					} else {
						if (serverSocket == null) {
							System.out.print("Which question: ");
							String question = sc.next();
							int questionNumber = Integer.parseInt(question) - 1;
							instructor.setCurrentQuestion(questionNumber);
							if (questionNumber < instructor.getQuestions()
									.size()) {
								serverSocket = new ServerSocket(portNumb);
								int quest = instructor.getCurrentQuestion() + 1;
								System.out
										.println("Connection opened for question "
												+ quest);
								Mutex.open = true;
							} else {
								System.out.println("Question does not exist");

							}
						}
					}

				} else if (input.equals("end_question")) {
					if (Mutex.open == false) {
						System.out.println("Connection is closed");
					} else {
						if (serverSocket != null) {
							serverSocket.close();
							serverSocket = null;
							Mutex.open = false;
							int quest = instructor.getCurrentQuestion() + 1;
							System.out.println("Connection to question "
									+ quest + " is closed");
						}
					}

				} else if (input.equals("list")) {
					list();

				} else {
					System.out.println("Unknown command");
				}
				System.out.print("Enter Command: ");

			}

		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumb + " or listening for a connection");
			System.out.println(e.getMessage());
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close down the server connection listening on port.
	 */
	public void closeServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * List all the answers given by everyone in the class.
	 */
	public static void list() {
		Map<Integer, ArrayList<String>> answers = instructor.getAnswers();

		for (Map.Entry<Integer, ArrayList<String>> stu : answers.entrySet()) {
			System.out.println("Student: " + stu.getKey());
			for (int i = 0; i < stu.getValue().size(); i++) {
				int question = i + 1;
				if (stu.getValue().get(i) != "0") {
					if (question <= instructor.getQuestions().size()) {
						System.out.println(" answered: "
								+ stu.getValue().get(i) + " for question "
								+ question);
					}
				}
			}
		}
	}

}
