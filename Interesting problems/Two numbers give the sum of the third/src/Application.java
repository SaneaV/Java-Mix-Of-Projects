import static java.lang.System.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Application {

  private static final String FOUND = "\nNumber %s was found!\n\n";
  private static final String NOT_FOUND = "\nNumber %s was not found!\n\n";
  private static final String ENTER_VALUES = "First enter the values a, b, c.\n\n";

  private static List<Integer> NUMBERS = new ArrayList<>();
  private static final Scanner scanner = new Scanner(in);
  private static int a, b, c;
  private static boolean numberFound = false;

  public static void main(String[] args) {
    boolean exit = false;
    int choice;

    while (!exit) {
      System.out.println("1. Input a, b, c.");
      System.out.println("2. Quick count.");
      System.out.println("3. Optimized count.");
      // Use VM options for big numbers: -Xss1024M (1024 enough for a=5, b=7, c=10.000.000)
      System.out.println("4. Recursive count.");
      System.out.println("5. Exit.");

      System.out.print("Your choice: ");
      choice = scanner.nextInt();

      switch (choice) {
        case 1: {
          do {
            inputValues();
          } while (!correctData());

          if (a + b == c) { //Immediately check if a + b is equal to c
            System.out.printf(FOUND, c);
            NUMBERS.clear();
          } else { //Otherwise, add to the list for verification using the algorithm
            NUMBERS.add(a);
            NUMBERS.add(b);
          }
          System.out.println("\n\n");
          break;
        }
        case 2: {
          if (NUMBERS.size() == 0) {
            System.out.println(ENTER_VALUES);
          } else {
            long start = System.currentTimeMillis(); // Start timer
            quickCount();
            long end = System.currentTimeMillis(); // End timer
            System.out.println("Took : " + ((end - start)) + " milliseconds");
            System.out.println("\n\n");
          }
          break;
        }
        case 3: {
          if (NUMBERS.size() == 0) {
            System.out.println(ENTER_VALUES);
          } else {
            long start = System.currentTimeMillis(); // Start timer
            optimizedCount();
            long end = System.currentTimeMillis(); // End timer
            System.out.println("Took : " + ((end - start)) + " milliseconds");
            NUMBERS.clear();
            System.out.println("\n\n");
          }
          break;
        }
        case 4: {
          if (NUMBERS.size() == 0) {
            System.out.println(ENTER_VALUES);
          } else {
            numberFound = false;
            long start = System.currentTimeMillis(); // Start timer
            try {
              recursive(a, b);
            } catch (RuntimeException e) { //Catch exception if number was found (an option to stop recursion)
              System.out.printf(FOUND, c);
            }
            long end = System.currentTimeMillis(); // End timer

            if (!numberFound) { //If flag wasn't changed, the number wasn't found
              System.out.printf(NOT_FOUND, c);
            }

            System.out.println("Took : " + ((end - start)) + " milliseconds");
            System.out.println("\n\n");
          }
          break;
        }
        case 5: {
          exit = true;
          break;
        }
        default: {
          System.out.println("Wrong option, please try again.\n\n");
        }
      }
    }
    System.out.println("Program completed.");
  }

  private static void inputValues() {
    System.out.print("Input a value: ");
    a = scanner.nextInt();

    System.out.print("Input b value: ");
    b = scanner.nextInt();

    System.out.print("Input c value: ");
    c = scanner.nextInt();
  }

  private static boolean correctData() {
    //Checking if a + b is less than c and all numbers are greater than zero
    if (a + b > c) {
      System.out.println("The sum of \"a\" and \"b\" is greater than \"c\". Re-enter.\n");
      return false;
    }
    if ((a == b || a == c) && a <= 0) {
      System.out.println("Variable values must be greater than zero. Re-enter.\n");
      return false;
    }
    return true;
  }

  private static void quickCount() {
    int iteration = 0, reset = 4, sublist = 2;
    boolean stop = false;

    while (!stop) {
      int abSum = NUMBERS.get(iteration) + NUMBERS.get(iteration + 1); //Variable sum a+b or b+a
      if (abSum == c) { //Checking if the amount has been found.
        System.out.printf(FOUND, c);
        stop = true;
      }

      //Adding elements to the left and right side of the root
      NUMBERS.add(NUMBERS.get(iteration));
      NUMBERS.add(abSum);
      NUMBERS.add(NUMBERS.get(iteration + 1));
      NUMBERS.add(abSum);

      if (NUMBERS.size() - sublist == reset) {
        if (checkIfNo(sublist, c)) {
          System.out.printf(NOT_FOUND, c);
          stop = true;
        }
        sublist = NUMBERS.size(); // Next time we will sublist elements that have already been counted
        reset *= 2; // How many iterations should be done to filter the list again
      }

      iteration += 2;
    }
  }

  private static boolean checkIfNo(int i, int c) {
    long count = IntStream.range(i, NUMBERS.size() - 1) // Create range from iteration to last element
        .mapToObj(j -> NUMBERS.subList(j, j + 2)) // Split list to two items list
        .filter(n -> !(n.get(0) + n.get(1) > c)) // Filter elements whose sum in the next calc will give an amount greater than c
        .count();

    return count == 0; // If count > 0, then there is a chance to get a + b = c
  }

  private static void optimizedCount() {
    int iteration = 0, reset = 6, sublist = 2;
    boolean stop = false;

    while (!stop) {
      int abSum = NUMBERS.get(iteration) + NUMBERS.get(iteration + 1); //Variable sum a+b or b+a
      if (abSum == c) { //Checking if the amount has been found.
        System.out.printf(FOUND, c);
        stop = true;
      }

      //Adding elements to the left and right side of the root
      NUMBERS.add(NUMBERS.get(iteration));
      NUMBERS.add(abSum);
      NUMBERS.add(NUMBERS.get(iteration + 1));
      NUMBERS.add(abSum);

      iteration += 2;
      if (NUMBERS.size() == reset) {
        filterItems(c, sublist);

        //If after filtering there are no elements left, then it is impossible to get "with" the sum of the numbers "a" and "b"
        if (NUMBERS.size() == 0) {
          System.out.printf(NOT_FOUND, c);
          stop = true;
        } else {
          sublist = NUMBERS.size(); // Next time we will remove elements that have already been counted
          reset = NUMBERS.size() * 2 + sublist; // How many iterations should be done to filter the list again
          iteration = 0; // Resetting the entire iteration
        }
      }
    }
  }

  private static void filterItems(int c, int sublist) {
    NUMBERS = NUMBERS.subList(sublist, NUMBERS.size()); //Remove elements whose sum we have already received.
    for (int i = 0; i < NUMBERS.size() - 1; i += 2) {
      //Remove elements whose sum in the next calculation will give an amount greater than "c"
      if (NUMBERS.get(i) + NUMBERS.get(i + 1) > c) {
        NUMBERS.remove(i);
        NUMBERS.remove(i);
        i -= 2;
      }
    }
  }

  private static void recursive(int a, int b) {
    if (a + b == c) { //Stop full recursion if a + b == c
      numberFound = true; //Flag to indicate that number was found
      throw new RuntimeException(); //Throw exception to stop recursion
    }

    if (a + b > c) { //Stop current recursion if a + b > c
      return;
    }

    recursive(a + b, b);
    recursive(a, b + a);
  }
}
