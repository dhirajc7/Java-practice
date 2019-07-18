package cms.services.impl;

import cms.models.Teacher;
import cms.repositories.CrudRepository;
import cms.repositories.impl.TeacherRepository;
import cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class TeacherService implements BaseService {

    public CrudRepository<Teacher> repository;

    public TeacherService() {
        this.repository = new TeacherRepository();
    }

    @Override
    public void displayMenu() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select: ");
        System.out.println("1. To list all teacher");
        System.out.println("2. To print teacher details");
        System.out.println("3. To update teacher record");
        System.out.println("4. To delete teacher record");
        System.out.println("5. To insert teacher record");


        System.out.println("---------------------------");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<Teacher> teachers = repository.findAll();
                System.out.println(teachers);
                break;
            case 2:
                printInfo();
                break;
            case 3:
                updateInfo();
                break;
            case 4:
                deleteInfo();
                break;
            case 5:
                insertInfo();
                break;
            default:
                System.out.println("Invalid input");
        }


    }

    public void insertInfo() {

        System.out.println("Enter teacher id");
        Scanner insertScanner = new Scanner(System.in);
        int id = Integer.valueOf(insertScanner.next());

        TeacherRepository tr = new TeacherRepository();
        if (tr.findById(id) != null) {
            System.out.println("ID already exists.");
        } else {
            System.out.println("Enter teacher id");
            System.out.print("Enter teacher first name: ");
            String firstName = insertScanner.next();
            System.out.print("Enter teacher last name: ");
            String lastName = insertScanner.next();
            System.out.print("Enter teacher subject: ");
            String subject = insertScanner.next();
            Teacher toInsert = new Teacher(id, firstName, lastName, subject);

            boolean inserted = repository.insert(toInsert);
            if (inserted) {
                System.out.println("Successfully inserted!");
            } else {
                System.out.println("Failed!");
            }
        }
    }

    public void deleteInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student id:");
        int teacherID = sc.nextInt();
        boolean delete = repository.deleteById(teacherID);

        if (delete) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Failed!");
        }

    }


    public void updateInfo() {
        Scanner updateScanner = new Scanner(System.in);
        System.out.println("Enter teacher id");
        int id = Integer.valueOf(updateScanner.next());
        System.out.print("Enter teacher first name: ");
        String firstName = updateScanner.next();
        System.out.print("Enter teacher last name: ");
        String lastName = updateScanner.next();
        System.out.print("Enter teacher subject: ");
        String subject = updateScanner.next();
        Teacher toUpdate = new Teacher(id, firstName, lastName, subject);

        boolean updated = repository.updateInfo(toUpdate);
        if (updated) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }
    }

    public void printInfo() {
        System.out.println("Enter teacher id");
        Scanner sc = new Scanner(System.in);
        int teacherid = sc.nextInt();
        Teacher teacher = repository.findById(teacherid);
        System.out.println(teacher);
    }


}

