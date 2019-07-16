package com.vastika.training.java.jdbc.cms;

import java.util.List;
import java.util.Scanner;

public class DriverProgramTeacher {
    public static void main(String[] args) {
        String input="yes";
        while (!"no".equals(input)) {
            System.out.println("Please select: ");
            System.out.println("1. To list all teachers");
            System.out.println("2. To print teacher details");
            System.out.println("3. To update teacher record");
            System.out.println("4. To delete teacher record");
            System.out.println("5. To insert teacher record");

            // add functionality to delete

            System.out.println("---------------------------");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            TeacherRepository repository = new TeacherRepository();

            if (choice == 1) {
                List<Teacher> teachers = repository.findAll();

                System.out.println(teachers);
            } else if (choice == 2) {

                System.out.print("Enter teacher id: ");
                int teacherId = scanner.nextInt();
                Teacher teacherId2 = repository.findById(teacherId);
                System.out.println(teacherId2);
            } else if (choice == 3) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter teacher id: ");
                int id = Integer.valueOf(sc.next());
                System.out.print("Enter teacher first name: ");
                String firstName = sc.next();
                System.out.print("Enter teacher last name: ");
                String lastName = sc.next();
                System.out.print("Enter teacher subject: ");
                String subject = sc.next();
                Teacher teacherUpdate = new Teacher(id, firstName, lastName, subject);

                boolean updated = repository.update(teacherUpdate);

                if (updated) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed!");
                }
            }else if(choice==4){
                System.out.print("Enter teacher id: ");
                int teacherId = scanner.nextInt();
                boolean updated=repository.deleteById(teacherId);
                if (updated) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed!");
                }
            }else if(choice==5){
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter teacher id: ");
                int id = Integer.valueOf(sc.next());
                System.out.print("Enter teacher first name: ");
                String firstName = sc.next();
                System.out.print("Enter teacher last name: ");
                String lastName = sc.next();
                System.out.print("Enter teacher subject: ");
                String subject = sc.next();

                Teacher teacherInsert = new Teacher(id, firstName, lastName, subject);

                boolean inserted = repository.insertTeacher(teacherInsert);

                if (inserted) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Failed!");
                }

            }

            System.out.println("---------------------------");

            System.out.println("Do you want to search again? (yes/no)");
            Scanner inputScanner = new Scanner(System.in);
            input = inputScanner.next();
        }
    }
}
