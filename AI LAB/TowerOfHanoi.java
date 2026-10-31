
import java.util.*;

public class TowerOfHanoi {

    static int count = 0;

    static void solve(int n, char source,
                      char auxiliary, char destination) {

        if (n == 1) {
            System.out.println("Move disk 1 from " +
                               source + " to " + destination);
            count++;
            return;
        }

        // Move n-1 disks from source to auxiliary
        solve(n - 1, source, destination, auxiliary);

        // Move largest disk to destination
        System.out.println("Move disk " + n + " from " +
                           source + " to " + destination);
        count++;

        // Move n-1 disks from auxiliary to destination
        solve(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        System.out.println("\nTower of Hanoi Solution:");

        solve(n, 'A', 'B', 'C');

        System.out.println("\nTotal moves: " + count);

        sc.close();
    }
}
