package cms.services.impl;

import cms.models.Staff;
import cms.repositories.CrudRepository;
import cms.repositories.impl.StaffRepository;
import cms.services.BaseService;

import java.util.List;
import java.util.Scanner;

public class StaffService implements BaseService {
    private CrudRepository<Staff> repository;

    public StaffService() {
        this.repository = new StaffRepository();
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

        System.out.println("---------------------------");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<Staff> students = repository.findAll();
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

    public void insertInfo() {
        Scanner insertChoice=new Scanner(System.in);
        System.out.println("Enter staff id");
        int id=Integer.valueOf(insertChoice.nextInt());
        System.out.println("Enter staff first name");
        String firstname=insertChoice.next();
        System.out.println("Enter staff last name");
        String lastname=insertChoice.next();
        System.out.println("Enter staff job role");
        String job_role=insertChoice.next();

        Staff toInsert=new Staff(id,firstname,lastname,job_role);
        boolean inserted=repository.insert(toInsert);

        if(inserted){
            System.out.println("Successfully inserted");
        }else{
            System.out.println("Failed to insert");
        }

    }

    @Override
    public void printInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter staff id:");
        int printInfo = scanner.nextInt();
        Staff id = repository.findById(printInfo);
        System.out.println(id);
    }


    @Override
    public void updateInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter staff id: ");
        int id = Integer.valueOf(sc.next());
        System.out.print("Enter staff first name: ");
        String firstName = sc.next();
        System.out.print("Enter staff last name: ");
        String lastName = sc.next();
        System.out.print("Enter staff job_role: ");
        String job_role = sc.next();
        Staff toUpdate = new Staff(id, firstName, lastName, job_role);

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

        System.out.print("Enter staff id: ");
        int staffId = scanner.nextInt();
        boolean deleted = repository.deleteById(staffId);

        if (deleted) {
            System.out.println("Success");
        } else {
            System.out.println("Failed!");
        }
    }


}
