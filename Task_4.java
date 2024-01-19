
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class QuizQuestion {
    private String question;
    private String[] options;
    int correctOptionIndex;

    public QuizQuestion(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOptionIndex;
    }
}

class Quiz {
    private int score;
    private int currentQuestionIndex;
    private QuizQuestion[] questions;
    private ScheduledExecutorService scheduler;

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        askQuestion();
    }

    private void askQuestion() {
        if (currentQuestionIndex < questions.length) {
            QuizQuestion currentQuestion = questions[currentQuestionIndex];
            displayQuestion(currentQuestion);

            // Schedule a new task for the next question
            scheduler.schedule(() -> {
                System.out.println("\nTime's up! Moving to the next question.");
                currentQuestionIndex++;
                askQuestion();
            }, 15, TimeUnit.SECONDS); // 15 seconds timer, adjust as needed

            getUserAnswer(currentQuestion);
        } else {
            endQuiz();
        }
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\nQuestion: " + question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private void getUserAnswer(QuizQuestion question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer (1-" + question.getOptions().length + "): ");

        try {
            int userAnswer = scanner.nextInt();

            if (userAnswer >= 1 && userAnswer <= question.getOptions().length) {
                if (question.isCorrect(userAnswer-1 )) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + (question.getOptions()[question.correctOptionIndex]));
                }

                currentQuestionIndex++;
                askQuestion();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + question.getOptions().length + ".\n");
                getUserAnswer(question);
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.\n");
            scanner.nextLine(); // Consume the invalid input
            getUserAnswer(question);
        } finally {
            scanner.close();
        }
    }

    private void endQuiz() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.length);

        // Shutdown the scheduler
        scheduler.shutdown();
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Create a list of QuizQuestion objects
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?", new String[]{"Paris", "Berlin", "Rome", "Madrid"}, 0));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0));
        questions.add(new QuizQuestion("What is the largest mammal?", new String[]{"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"}, 1));
        questions.add(new QuizQuestion("Who wrote 'Romeo and Juliet'?", new String[]{"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"}, 1));
        questions.add(new QuizQuestion("What is the capital of Japan?", new String[]{"Beijing", "Seoul", "Tokyo", "Bangkok"}, 2));
        questions.add(new QuizQuestion("Which gas do plants absorb from the atmosphere?", new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"}, 1));
        questions.add(new QuizQuestion("In which year did Christopher Columbus reach the Americas?", new String[]{"1492", "1520", "1607", "1776"}, 0));
        questions.add(new QuizQuestion("What is the largest planet in our solar system?", new String[]{"Earth", "Jupiter", "Mars", "Venus"}, 1));
        questions.add(new QuizQuestion("Which programming language is known for its 'write once, run anywhere' principle?", new String[]{"Java", "Python", "C++", "JavaScript"}, 0));
        questions.add(new QuizQuestion("What is the currency of Australia?", new String[]{"Dollar", "Euro", "Pound", "Yen"}, 0));

        // Shuffle the list to randomize the order of questions
        Collections.shuffle(questions);

        // Create a Quiz instance with randomized questions
        Quiz quiz = new Quiz(questions.toArray(new QuizQuestion[0]));

        // Start the quiz
        quiz.startQuiz();
    }
}
