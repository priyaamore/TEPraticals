
import java.util.Scanner;

public class VacuumCleanerAgent {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String location;
        String A, B;

        System.out.println("Vacuum Cleaner World");

        System.out.print("Enter status of Room A (Clean/Dirty): ");
        A = sc.nextLine();

        System.out.print("Enter status of Room B (Clean/Dirty): ");
        B = sc.nextLine();

        System.out.print("Enter vacuum cleaner location (A/B): ");
        location = sc.nextLine();

        while (A.equalsIgnoreCase("Dirty") ||
               B.equalsIgnoreCase("Dirty")) {

            if (location.equalsIgnoreCase("A")) {

                if (A.equalsIgnoreCase("Dirty")) {
                    System.out.println("Room A is Dirty -> Suck");
                    A = "Clean";
                } else {
                    System.out.println("Room A is Clean -> Move Right");
                    location = "B";
                }

            } else if (location.equalsIgnoreCase("B")) {

                if (B.equalsIgnoreCase("Dirty")) {
                    System.out.println("Room B is Dirty -> Suck");
                    B = "Clean";
                } else {
                    System.out.println("Room B is Clean -> Move Left");
                    location = "A";
                }
            }
        }

        System.out.println("\nFinal Status:");
        System.out.println("Room A: " + A);
        System.out.println("Room B: " + B);

        System.out.println("Both rooms are clean!");
        System.out.println("Vacuum Cleaner Task Completed.");

        sc.close();
    }
}
