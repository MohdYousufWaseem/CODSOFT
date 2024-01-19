import java.util.*;
class Course
{
    public String coursecode;
    private String coursetitle;
    private String coursedescription;
    private String courseschedule;
    private int coursecapacity;
    private List<Student> enrolledstudents; 
    Course(String coursecode,String coursetitle,String coursedescription,String courseschedule,int coursecapacity)
    {
        this.coursecode=coursecode;
        this.coursetitle=coursetitle;
        this.coursedescription=coursedescription;
        this.courseschedule=courseschedule;
        this.coursecapacity=coursecapacity;
        this.enrolledstudents = new ArrayList<>();
    }
    public boolean registerStudents(Student student)
    {
        if(enrolledstudents.size()<coursecapacity)
        {
            enrolledstudents.add(student);
            return true;
        }
        else
        {
            System.out.println("Course is full! Cannot Register further Students");
            return false;
        }
    }
    public boolean removestudents(Student student)
    {
        return enrolledstudents.remove(student);
    }
    public void displayregisteredcourses()
    {
        System.out.println("COURSE TITLE "+coursetitle);
        System.out.println("COURSE CODE "+coursecode);
        System.out.println("COURSE DESCRIPTION "+coursedescription);
        System.out.println("COURSE SCHEDULE "+courseschedule);
        System.out.println("COURSE CAPACITY "+coursecapacity);
        System.out.println("ENROLLED STUDENTS "+enrolledstudents.size());
    }
}
class Student
{
    private int studentid;
    private String name;
    private List<Course> registeredcourses;
    public Student(int studentid,String name)
    {
        this.studentid=studentid;
        this.name=name;
        this.registeredcourses = new ArrayList<>(); 
    }  
    public boolean registerForCourse(Course course) 
    {
        if (!registeredcourses.contains(course)) 
        {
            if (course.registerStudents(this)) 
            {
                registeredcourses.add(course);
                return true;
            } else 
            {
                System.out.println("Registration failed. Course is full.");
                return false;
            }
        } else 
        {
            System.out.println("Already registered for this course.");
            return false;
        }
    }
    public boolean dropCourse(Course course) 
    {
        if (registeredcourses.contains(course)) 
        {
            if (course.removestudents(this)) 
            {
                registeredcourses.remove(course);
                return true;
            } else 
            {
                System.out.println("Error dropping the course.");
                return false;
            }
        } else 
        {
            System.out.println("Not registered for this course.");
            return false;
        }
    }

    public void displaystudentdetails()
    {
        System.out.println("STUDENT ID : "+studentid);
        System.out.println("STUDENT NAME : "+name);
        //System.out.println("REGISTERED COURSES : "+registeredcourses);
    }
}
class courseregistrationsystem 
{
    public List<Course> courses;
    private List<Student> students;
    public courseregistrationsystem()
    {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }
    public void addcourse(Course course)
    {
        courses.add(course);
    }
    public void addstudent(Student student)
    {
        students.add(student);
    }
    public void displaycourselisting()
    {
        System.out.println("Available Courses ");
        for(Course course : courses)
        {
            course.displayregisteredcourses();
            System.out.println("---------------------------");
        }
        
    }
    public void displaystudentlisting()
    {
        System.out.println("Student Registered ");
        for(Student student : students)
        {
            student.displaystudentdetails();
            System.out.println("----------------------------");
        }
        
    }
}
public class Main
{
    public static void main(String[] args) 
    {
        courseregistrationsystem registrationsystem = new courseregistrationsystem();
        Course mathCourse = new Course("MATH101", "Introduction to Mathematics", "Basic math concepts",  "Mon, Wed, Fri",1);
        Course physicsCourse = new Course("PHYS201", "Physics Fundamentals", "Introductory physics principles",  "Tue, Thu",25);
        Course programmingCourse = new Course("COMP301", "Programming in Java", "Introduction to Java programming",  "Mon, Wed",20);


        registrationsystem.addcourse(mathCourse);
        registrationsystem.addcourse(physicsCourse);
        registrationsystem.addcourse(programmingCourse);
        registrationsystem.displaycourselisting();
        // Get input from the user
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Enter the number of inputs you want");
        n=scanner.nextInt();
        for(int i=0;i<n;i++)
        {
        System.out.println("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.println("Enter student name: ");
        String studentName = scanner.next();

        // Create a new student object with the provided ID and name
        Student newStudent = new Student(studentId, studentName);
        registrationsystem.addstudent(newStudent);

        // Register the new student for a course
        System.out.println("Enter course code to register: ");
        String courseCode = scanner.next();
        for (Course course : registrationsystem.courses) {
            if (course.coursecode.equals(courseCode)) {
                newStudent.registerForCourse(course);
                break;
            }
        }

        // Display course and student details
        registrationsystem.displaystudentlisting();
    }
    registrationsystem.displaycourselisting();
        scanner.close();
    }
}

       