package com.sms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

    	    Scanner sc = new Scanner(System.in);

    	    Login login = new Login();
    	    int attempts = 3;
    	    boolean isLoggedIn = false;

    	    // ================= LOGIN =================
    	    while (attempts > 0) {

    	        System.out.println("------------------------------------------------");
    	        System.out.println("           Student Management System");
    	        System.out.println("------------------------------------------------");

    	        System.out.print("USERNAME : ");
    	        String username = sc.nextLine();

    	        System.out.print("PASSWORD : ");
    	        String password = sc.nextLine();

    	        if (login.validate(username, password)) {
    	        	System.out.println("\n==============================================");
    	        	System.out.println("          LOGIN SUCCESSFUL");
    	        	System.out.println("==============================================");

    	        	System.out.println("Welcome, " + username + "!");

    	        	LocalDateTime now = LocalDateTime.now();

    	        	DateTimeFormatter format =
    	        	        DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

    	        	System.out.println("Login Time : " + now.format(format));

    	        	System.out.println("==============================================");
    	            isLoggedIn = true;
    	            break;
    	        } else {
    	            attempts--;
    	            System.out.println("\nINVALID Username or Password!");

    	            if (attempts > 0) {
    	                System.out.println("Remaining Attempts : " + attempts);
    	            }
    	        }
    	    }

    	    // Login Failed
    	    if (!isLoggedIn) {
    	        System.out.println("\nToo Many Failed Login Attempts!");
    	        System.out.println("Program Terminated.");
    	        sc.close();
    	        return;
    	    }

    	    // ================= MENU =================
    	    StudentDAO dao = new StudentDAO();

    	    while (true) {

    	        System.out.println("=============================================");
    	        System.out.println("********* Student Management System *********");
    	        System.out.println("=============================================");
    	        System.out.println(" 1. Add Student");
    	        System.out.println(" 2. View Students");
    	        System.out.println(" 3. Update Student");
    	        System.out.println(" 4. Delete Student");
    	        System.out.println(" 5. Search Student By ID");
    	        System.out.println(" 6. Search Student By Name");
    	        System.out.println(" 7. Count Total Students");
    	        System.out.println(" 8. Exit");
    	        System.out.println("=============================================");

    	        System.out.print("Enter your choice: ");

    	        int choice = sc.nextInt();
    	        sc.nextLine();

    	        switch (choice) {

    	        case 1:
    	            Student student = new Student();

    	            System.out.println("---------------------------------------------");
    	            System.out.println("************* ADD NEW STUDENT ***************");
    	            System.out.println("---------------------------------------------");

    	            String name;

    	            while (true) {

    	                System.out.print("Enter Name : ");
    	                name = sc.nextLine();

    	                if (name.matches("[a-zA-Z ]+") && !name.trim().isEmpty()) {
    	                    student.setName(name);
    	                    break;
    	                }

    	                System.out.println("--------------------------------");
    	                System.out.println("Invalid Name!");
    	                System.out.println("Name should contain only letters.");
    	                System.out.println("--------------------------------");
    	            }

    	            int age;

    	            while (true) {

    	                System.out.print("Enter Age : ");
    	                age = sc.nextInt();
    	                sc.nextLine();

    	                if (age >= 16 && age <= 100) {
    	                    student.setAge(age);
    	                    break;
    	                }

    	                System.out.println("--------------------------------");
    	                System.out.println("Invalid Age!");
    	                System.out.println("Age must be between 16 and 100.");
    	                System.out.println("--------------------------------");
    	            }

    	            String course;

    	            while (true) {

    	                System.out.print("Enter Course : ");
    	                course = sc.nextLine();

    	                if (!course.trim().isEmpty()) {
    	                    student.setCourse(course);
    	                    break;
    	                }

    	                System.out.println("--------------------------------");
    	                System.out.println("Course cannot be empty!");
    	                System.out.println("--------------------------------");
    	            }
    	            String email;

    	            while (true) {

    	                System.out.print("Enter Email : ");
    	                email = sc.nextLine();

    	                if (email.contains("@") &&
    	                    email.contains(".") &&
    	                    !email.startsWith("@") &&
    	                    !email.endsWith(".")) {

    	                    student.setEmail(email);
    	                    break;
    	                }

    	                System.out.println("--------------------------------");
    	                System.out.println("Invalid Email Address!");
    	                System.out.println("Example: yash@gmail.com");
    	                System.out.println("--------------------------------");
    	            }

    	            dao.addStudent(student);
    	            break;

    	        case 2:
    	            dao.viewStudents();
    	            break;

    	        case 3:
    	            Student updateStudent = new Student();

    	            System.out.println("---------------------------------------------");
    	            System.out.println("************* UPDATE STUDENT ****************");
    	            System.out.println("---------------------------------------------");

    	            System.out.print("Enter Student ID : ");
    	            updateStudent.setId(sc.nextInt());
    	            sc.nextLine();

    	            System.out.print("Enter New Name : ");
    	            updateStudent.setName(sc.nextLine());

    	            System.out.print("Enter New Age : ");
    	            updateStudent.setAge(sc.nextInt());
    	            sc.nextLine();

    	            System.out.print("Enter New Course : ");
    	            updateStudent.setCourse(sc.nextLine());

    	            System.out.print("Enter New Email : ");
    	            updateStudent.setEmail(sc.nextLine());

    	            dao.updateStudent(updateStudent);
    	            break;

    	        case 4:
    	            System.out.println("---------------------------------------------");
    	            System.out.println("************* DELETE STUDENT ****************");
    	            System.out.println("---------------------------------------------");

    	            System.out.print("Enter Student ID : ");
    	            int deleteId = sc.nextInt();
    	            sc.nextLine();

    	            dao.deleteStudent(deleteId);
    	            break;

    	        case 5:
    	            System.out.print("Enter Student ID : ");
    	            int id = sc.nextInt();
    	            sc.nextLine();

    	            dao.searchStudent(id);
    	            break;

    	        case 6:
    	            System.out.print("Enter Student Name : ");
    	            String name1 = sc.nextLine();

    	            dao.searchStudentByName(name1);
    	            break;

    	        case 7:
    	            dao.countStudents();
    	            break;

    	        case 8:
    	            System.out.println("************************************************");
    	            System.out.println(" THANK YOU FOR USING STUDENT MANAGEMENT SYSTEM ");
    	            System.out.println("************************************************");
    	            System.out.println(" Developed By YASH ");
    	            System.out.println("************************************************");

    	            sc.close();
    	            System.exit(0);
    	            break;

    	        default:
    	            System.out.println("Invalid Choice!");
    	        }
    	    }
    	}
}
