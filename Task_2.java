import java.util.*;
public class Grade_Calculator 
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the marks of the subjects given below out of 100");
        System.out.println();
        System.out.println("Enter the marks of Math");
        int math=sc.nextInt();
        System.out.println("Enter the marks of English");
        int english=sc.nextInt();
        System.out.println("Enter the marks of Computers");
        int computers=sc.nextInt();
        System.out.println("Enter the marks of Physics");
        int physics=sc.nextInt();
        System.out.println("Enter the marks of Chemistry");
        int chemistry=sc.nextInt();
        int total = math+english+computers+physics+chemistry;
        double percent = total/5;
        char grade;
        if(percent>=90)
        grade='A';
        else if(percent>=80&&percent<90)
        grade='B';
        else if(percent>=70&&percent<80)
        grade='C';
        else if(percent>=60&&percent<70)
        grade='D';
        else if(percent>=35&&percent<60)
        grade='E';
        else
        grade='F';
        System.out.println("The total marks of all five subjects = "+total);
        System.out.println("The average Percentage of the student = "+percent+"%");
        System.out.println("The assigned grade according to the average percentage = "+grade);
    }
    
}
