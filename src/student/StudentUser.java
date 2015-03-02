package student;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

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

		while (sc.hasNext()) {
			command = sc.next();

			if (command.equals("student_number")) {
				ps.print("Please enter your student number: ");
				int studentNumber = sc.nextInt();
				studentCommand.setStudentNumber(studentNumber);
				ps.println("Your student number is: "
						+ studentCommand.getStudent().getStudentNumb());

			} else if (command.equals("class_info")) {
				ps.print("Please enter your class port: ");
				int port = sc.nextInt();
				ps.print("Please enter your class ip address: ");
				String ip = sc.next();
				studentCommand.classInfo(port, ip);
				ps.println("Your class ip is "
						+ studentCommand.getStudent().getClassIp()
						+ " and the port is "
						+ studentCommand.getStudent().getClassPort());
			} else if (command.contains("enter_choice")) {
				studentCommand.getChoices();

			} else if (command.equals("exit")) {
				break;
			} else {
				ps.println(command + " is not a valid command");
			}
			ps.print("Please enter a command: ");

		}

	}

}
