package com.vastika.training.java.jdbc.cms.controllers;

import com.vastika.training.java.jdbc.cms.StudentRepository;
import com.vastika.training.java.jdbc.cms.models.Student;

import java.util.List;
import java.util.Scanner;

public class DeriverProgram {
    public static void main(String[] args) {
        String input = "yes";
        while (!"no".equals(input)) {
            System.out.println("Please select: ");
            System.out.println("1. To list all student");
            System.out.println("2. To print student details");
            System.out.println("3. To update student record");
            System.out.println("4. To delete student record.");
            System.out.println("5. To insert student records.");
            // add functionality to deleete

            System.out.println("---------------------------");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            StudentRepository repository = new StudentRepository();

            if (choice == 1) {
                List<Student> students = repository.findAll();

                System.out.println(students);
            } else if (choice == 2) {

                System.out.print("Enter student id: ");
                int studentId = scanner.nextInt();
                Student studentId2 = repository.findById(studentId);
                System.out.println(studentId2);
            } else if (choice == 3) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter student id: ");
                int id = Integer.valueOf(sc.next());
                System.out.print("Enter student first name: ");
                String firstName = sc.next();
                System.out.print("Enter student last name: ");
                String lastName = sc.next();
                System.out.print("Enter student gpa: ");
                double gpa = Double.valueOf(sc.next());
                Student toUpdate = new Student(id, firstName, lastName, gpa);

                boolean updated = repository.update(toUpdate);

                if (updated) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed!");
                }
            }else if(choice==4){
                System.out.println("Enter id you want to delete:");
                Scanner sc=new Scanner(System.in);
                int delid=sc.nextInt();
                boolean deleted=repository.deleteById(delid);


                if(deleted){
                    System.out.println("Deleted");

                }else{
                    System.out.println("failed");
                }

            }else if(choice==5){
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter student id: ");
                int id = Integer.valueOf(sc.next());
                System.out.print("Enter student first name: ");
                String firstName = sc.next();
                System.out.print("Enter student last name: ");
                String lastName = sc.next();
                System.out.print("Enter student gpa: ");
                double gpa = Double.valueOf(sc.next());

                Student toInsert=new Student(id,firstName,lastName,gpa);
                boolean insert=repository.insertStudents(toInsert);
                if(insert){
                    System.out.println("Inserted!");
                }else{
                    System.out.println("Failed");
                }
            }

            System.out.println("---------------------------");

            System.out.println("Do you want to search again? (yes/no)");
            Scanner inputScanner = new Scanner(System.in);
            input = inputScanner.next();
        }
    }
}
