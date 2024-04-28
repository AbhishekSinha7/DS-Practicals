import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5];

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up");
        } else {
            int i;
            Bully.state[up - 1] = true;
            System.out.println("Process " + up + " held election");
            for (i = up; i < 5; ++i) {
                System.out.println("Election message sent from process " + up + " to process " + (i + 1));
            }
            for (i = up + 1; i <= 5; ++i) {
                if (!state[i - 1]) continue;
                System.out.println("Alive message sent from process " + i + " to process " + up);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the process number to bring up:");
        int process = scanner.nextInt();
        up(process);
        scanner.close();
    }
}
