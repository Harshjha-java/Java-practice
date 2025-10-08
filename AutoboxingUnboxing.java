import java.util.ArrayList;
import java.util.Scanner;

public class SumWithAutoboxing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by space:");
        String input = sc.nextLine();
        String[] tokens = input.split(" ");

        for (String token : tokens) {
            // parse string to int, autobox to Integer while adding to ArrayList
            numbers.add(Integer.parseInt(token));
        }

        int sum = 0;
        for (Integer num : numbers) {
            // unboxing Integer to int
            sum += num;
        }

        System.out.println("Total sum: " + sum);
    }
}
