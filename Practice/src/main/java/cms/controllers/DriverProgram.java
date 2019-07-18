package cms.controllers;

import cms.services.BaseService;
import cms.services.impl.StaffService;
import cms.services.impl.StudentService;
import cms.services.impl.TeacherService;

import java.util.Scanner;

public class DriverProgram {
    public static void main(String[] args) {
        BaseService studentService=new StudentService();
        BaseService teacherService=new TeacherService();
        BaseService staffService=new StaffService();

        String input="yes";
        while(!"no".equals(input)) {
            System.out.println("Select \nEnter 1. For Teacher.\nEnter 2. For Students\nEnter 3. For Staffs");
            Scanner sc = new Scanner(System.in);
            int mainchoice = sc.nextInt();

            if (mainchoice == 1) {
                teacherService.displayMenu();
            } else if (mainchoice == 2) {
                studentService.displayMenu();
            }else if(mainchoice==3){
                staffService.displayMenu();
            }

        System.out.println("-----------------------------------------------");
        System.out.println("Do you want to continue?(yes/no)");
        Scanner inputScanner=new Scanner(System.in);
        input=inputScanner.next();
    }
}


}