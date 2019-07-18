package cms.services.impl;

import cms.models.Staff;
import cms.models.Student;
import cms.repositories.CrudRepository;
import cms.repositories.impl.StaffRepository;
import cms.repositories.impl.StudentRepository;
import cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class StudentService implements BaseService {
    private CrudRepository<Student> repository;

    public StudentService() {
        this.repository=new StudentRepository();
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select: ");
        System.out.println("1. To list all staff");
        System.out.println("2. To print staff details");
        System.out.println("3. To update staff record");
        System.out.println("4. To delete staff record");
        System.out.println("5. To insert staff record");

        System.out.println("--------------------------");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<Student> students = repository.findAll();
                System.out.println(students);
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
                break;
        }
    }


    @Override
    public void printInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student id: ");
        int studentId = scanner.nextInt();
        Student studentId2 = repository.findById(studentId);
        System.out.println(studentId2);
    }

    @Override
    public void updateInfo() {
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

        boolean updated = repository.updateInfo(toUpdate);

        if (updated) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }
    }

    @Override
    public void deleteInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student id: ");
        int studentId = scanner.nextInt();
        boolean deleted = repository.deleteById(studentId);

        if (deleted) {
            System.out.println("Success");
        } else {
            System.out.println("Failed!");
        }
    }

    @Override
    public void insertInfo() {
        Scanner insertChoice=new Scanner(System.in);
        System.out.println("Enter student id");
        int id=Integer.valueOf(insertChoice.nextInt());
        System.out.println("Enter student first name");
        String firstname=insertChoice.next();
        System.out.println("Enter student last name");
        String lastname=insertChoice.next();
        System.out.println("Enter student gpa");
        Double gpa=insertChoice.nextDouble();

        Student toInsert=new Student(id,firstname,lastname,gpa);
        boolean inserted=repository.insert(toInsert);

        if(inserted){
            System.out.println("Successfully inserted");
        }else{
            System.out.println("Failed to insert");
        }
    }
}
