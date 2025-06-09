import java.util.Scanner;

public class LabActivity3ConditionalStatement {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // 1. Prompt the user for inputs.
        System.out.println("Enter your first name: ");
        String Fname = s.nextLine();

        System.out.println("Enter your last name: ");
        String Lname = s.nextLine();

        System.out.println("Enter your age: ");
        int age = s.nextInt();
        s.nextLine(); 

       
        System.out.println("Enter hours worked in a day: ");
        double hwork = s.nextDouble();

        System.out.println("Enter hourly wage: ");
        double hwage = s.nextDouble();

        System.out.print("Enter role code (1•Manager, 2•Supervisor, 3•Staff, 4•Intern): ");
        int roleCode = s.nextInt();
        String role;

        String fullName = Lname.toUpperCase() + ", " + Fname.toUpperCase();
        // Daily salary rounded to the nearest whole number.
        double dailySalaryDouble = hwork * hwage;
        int Daily_Salary = (int) Math.round(dailySalaryDouble);

        int yearsToRetirement = Math.abs(65 - age);


        int Wsalary = Daily_Salary * 5;

        int Msalary = Wsalary * 4;

        int Ysalary = Msalary * 12;

        double Net_Ysalary;

        if(hwork > 24) {
            System.out.println("Number of hours worked cannot exceed 24 hours");
            return;
        } else if(hwork == 0){
            System.out.println("Wrong input on daily work hours");
            return;
        }

        if(Ysalary > 250000){
            Net_Ysalary = Ysalary - (0.32 * Ysalary) - 1500;
        }else{
            Net_Ysalary = Ysalary- 1500;
        }
        
        if(age < 18){
            System.out.println("Minors are not allowed");
            return;
        } else if(age >= 65){
            System.out.println("Senior Citizens are not allowed");
            return;
    
        }

        switch (roleCode) {
            case 1:
                role = "Manager";
                break;
            case 2:
                role = "Supervisor";
                break;
            case 3:
                role = "Staff";
                break;
            case 4:
                role = "Intern";
                break;
            default:
                role = "Undefined";
                break;
        }

        System.out.println("\nEmployee Information");
        System.out.println("---------------------");
        System.out.printf("Full Name:          %s%n", fullName);
        System.out.printf("Age:                %d years old%n", age);
        System.out.printf("Years to Retirement:%d years%n", yearsToRetirement);
        System.out.printf("Daily Salary:       Php %,.2f%n", (double) Daily_Salary);
        System.out.printf("Weekly Salary:      Php %,.2f%n", (double) Wsalary);
        System.out.printf("Monthly Salary:     Php %,.2f%n", (double) Msalary);
        System.out.printf("Gross Yearly Salary:Php %,.2f%n", (double) Ysalary);
        System.out.printf("Net Yearly Salary:  Php %,.2f%n", Net_Ysalary);
        System.out.printf("Role:          %s%n", role);
    }



}
