package instructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the data structures necessary for the course instructor
 * 
 * @author Skylerlayne
 *
 */
public class Instructor {

	public ArrayList<Integer> students;

	// First dimension is the student number, second is their answers
	public Map<Integer, ArrayList<String>> answers;
	public ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
	public int currentQuestion;

	/**
	 * Default constructor, creates an array of questions, and generates the
	 * list of students in the class
	 */
	public Instructor() {
		// create the student list.
		populateStudents();
		// create the questions.
		populateQuestions();
		this.answers = new HashMap<Integer, ArrayList<String>>();
		populateAnswers();
		this.currentQuestion = 0;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(int currentQuestion) {
		if ((currentQuestion > 0) && (currentQuestion > this.questions.size())) {
			System.out.println("Not a valid Question!");
		} else {

			this.currentQuestion = currentQuestion;
		}
	}

	/**
	 * Adds the students in the class to the class list "students"
	 */
	@SuppressWarnings("unchecked")
	public void populateStudents() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(212166906);
		temp.add(123);
		temp.add(2121);
		this.students = (ArrayList<Integer>) temp.clone();
	}

	/**
	 * Adds default answers for every student on the class list for later
	 * consideration.
	 */
	@SuppressWarnings("unchecked")
	public void populateAnswers() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < questions.size(); i++) {
			temp.add("0");
		}
		for (int j = 0; j < students.size(); j++) {

			this.answers.put(students.get(j), (ArrayList<String>) temp.clone());

		}
	}

	/**
	 * Adds the questions to the array of questions
	 */
	public void populateQuestions() {
		ArrayList<String> one = new ArrayList<String>();
		one.add("A: Question 1");
		one.add("B: Question 1");
		one.add("C: Question 1");
		this.questions.add(one);
		ArrayList<String> two = new ArrayList<String>();
		two.add("A: Question 2");
		two.add("B: Question 2");
		two.add("C: Question 2");
		this.questions.add(two);
		ArrayList<String> three = new ArrayList<String>();
		three.add("A: Question 3");
		three.add("B: Question 3");
		three.add("C: Question 3");
		this.questions.add(three);
	}

	/**
	 * Get the list of questions
	 * 
	 * @return - list of all questions and choices
	 */
	public ArrayList<ArrayList<String>> getQuestions() {
		return questions;
	}

	/**
	 * Get the list of students
	 * 
	 * @return - list of students in the class
	 */
	public ArrayList<Integer> getStudents() {
		return students;
	}

	/**
	 * Get the student answers
	 * 
	 * @return - students and their answers
	 */
	public Map<Integer, ArrayList<String>> getAnswers() {
		return answers;
	}

	/**
	 * Add the students answer to the current question.
	 * 
	 * @param student
	 *            - student answering question
	 * @param ans
	 *            - student's answer
	 */
	public void addStudentAnswer(int student, String ans) {
		if (!answers.containsKey(student)) {
			ArrayList<String> e = new ArrayList<String>();
			e.add(this.getCurrentQuestion(), ans);
			answers.put(student, e);
		} else {
			if (answers.get(student).get(this.currentQuestion) == "0") {
				ArrayList<String> e = new ArrayList<String>();
				for (int j = 0; j < answers.get(student).size(); j++) {
					e.add(answers.get(student).get(j));
				}
				e.add(this.getCurrentQuestion(), ans);
				answers.put(student, e);
			} else {
				// answers.add(answers.indexOf(student), this.currentQuestion);
				ArrayList<String> e = new ArrayList<String>();
				for (int j = 0; j < answers.get(student).size(); j++) {
					e.add(answers.get(student).get(j));
				}
				answers.put(student, e);
			}
		}
	}

}
