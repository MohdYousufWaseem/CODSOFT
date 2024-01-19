import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int uppbound=100;
        int lowbound=1;
        int numberattempts=5;
        int score = 0;
        int b=0;
        System.out.println("Welcome to the number guessing game ");
        String playagain="";
        do
        {
            int x = rand.nextInt(uppbound-lowbound+1)+lowbound;
            int attempts=0;
            int userguess;
            System.out.println("I have generated the number between "+lowbound+" and "+uppbound);
            while(attempts<numberattempts)
            {
                System.out.println("Enter your guess ");
                userguess = sc.nextInt();
                attempts++;
                b++;
                if(userguess==x)
                {
                    score++;
                    System.out.println("Congratulations you got it right! Your score is "+score+" out of 5 attempts");
                    break;
                }
                else if (userguess>x)
                System.out.println("Try Again! Your guess is too high");
                                //System.out.println("The number was = "+x);
                else 
                    System.out.println("Try Again! Your guess is too low");
                                   // System.out.println("The number was = "+x);
            }
            if(attempts==numberattempts)
            System.out.println("Sorry! You have run out of the attempts");
            System.out.println("Do you want to play again ? Yes or No");
            playagain = sc.next().toLowerCase();
        
    }while(playagain.equalsIgnoreCase("yes"));
        System.out.println("Thanks for playing . Your total score is "+ score+" out of "+b+" attempts");
    sc.close();
}
}
