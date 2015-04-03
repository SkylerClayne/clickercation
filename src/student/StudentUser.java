package student;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Provide a command line user interface for the student user.
 * 
 * @author cse23170
 *
 */
public class StudentUser {

	private static StudentCommand studentCommand;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pattern delimiters = Pattern.compile(System
				.getProperty("line.separator") + "|\\s");
		sc.useDelimiter(delimiters);
		PrintStream ps = System.out;
		ps.print("Please enter a command: ");
		String command = "";
		studentCommand = new StudentCommand();

		/* Begin reading in commands */
		while (sc.hasNext()) {
			command = sc.next();

			/* What to do if the student enters the command "student_number" */
			if (command.equals("student_number")) {
				ps.print("Please enter your student number: ");
				int studentNumber;
				try {
					studentNumber = sc.nextInt();
					studentCommand.setStudentNumber(studentNumber); // Update the
					// model with
					// the students
					// id
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Not a student number");
				}
				
				ps.println("Your student number is: "
						+ studentCommand.getStudent().getStudentNumb());

				/* What to do if the student enters the command "class_info" */
			} else if (command.equals("class_info")) {
				ps.print("Please enter your class port: ");
				int port = sc.nextInt();
				ps.print("Please enter your class ip address: ");
				String ip = sc.next();
				studentCommand.classInfo(port, ip); // Update the model with the
													// class port and ip address
				ps.println("Your class ip is "
						+ studentCommand.getStudent().getClassIp()
						+ " and the port is "
						+ studentCommand.getStudent().getClassPort());

				/* What to do if the student enters the command "enter_choice" */
			} else if (command.contains("enter_choice")) {
				studentCommand.getChoices(); // Get the choices from the
												// instructor
				/* What to do if the student enters the command "exit" */
			} else if (command.equals("exit")) {
				break; // Kill the user interface
			} else {
				ps.println(command + " is not a valid command");
			}
			ps.print("Please enter a command: ");

		}

	}

}
