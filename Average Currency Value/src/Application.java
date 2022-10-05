import static java.lang.Math.abs;
import static java.lang.System.in;
import static java.util.Objects.isNull;

import java.util.Scanner;

public class Application {

  private static final String X = "x";
  private static final String SPACE = " ";
  private static final String NEW_LINE = "\n\n";
  private static final String INPUT_SUM_OF_CURRENCY = "Enter the amount of the purchased/sold (with minus) currency (x - to exit): ";
  private static final String INPUT_PRICE_OF_CURRENCY = "Enter the price of the bought/sold (with minus) currency (x - to exit):";
  private static final String REPEAT_YOU_INPUT = "The price cannot be negative. Repeat your input.";

  private static final Scanner scanner = new Scanner(in);
  private static float[][] history = new float[100][2];

  public static void main(String[] args) {
    float sum;
    float price;
    Float temp;

    do {
      //Entering the purchased/sold amount
      temp = input(INPUT_SUM_OF_CURRENCY, true);
      if (isNull(temp)) {
        break;
      }
      sum = temp;
      //Entering the buy/sell price (the sell price does not affect anything)
      temp = input(INPUT_PRICE_OF_CURRENCY, false);
      if (isNull(temp)) {
        break;
      }
      price = temp;

      addToHistory(sum, price);
      updateHistory();
      removeZeroAndNegativeSum();
      print();
      System.out.println(getAveragePrice());

    } while (true);
  }

  private static Float input(String message, boolean possibleMinus) {
    System.out.print(message);
    final String input = scanner.next();
    if (exit(input)) {
      return null;
    }

    final float number = getFloat(input);

    if (!possibleMinus && number < 0) {
      System.out.println(REPEAT_YOU_INPUT);
      return input(message, false);
    }
    return number;
  }

  private static void addToHistory(Float sum, Float price) {
    int lastIndex = getLastLine();
    history[lastIndex][0] = price;
    history[lastIndex][1] = sum;
  }

  private static void updateHistory() {
    for (int i = 0; i < history.length; i++) {
      float sum = history[i][1];
      if (sum < 0) {
        while (sum != 0) {
          for (int j = 0; j < history.length && sum != 0; j++) {
            float positiveSum = history[j][1];
            if (positiveSum > 0) {
              //If the amount from the purchase history is greater than or equal to the last sale, then subtract the purchase amount from it and reset sum
              if (positiveSum >= abs(sum)) {
                history[j][1] += sum;
                sum = 0;
              } else {
                //Otherwise, subtract the purchase amount from sum, which cannot cover the sale amount, and move on to the next one
                sum = sum + positiveSum;
                history[j][1] = 0;
              }
            }
          }
        }
      }
    }
  }

  //For simplicity of logic, create a new array, in which we will not add entries with 0 in total
  private static void removeZeroAndNegativeSum() {
    float[][] output = new float[100][2];

    for (int i = 0, j = 0; i < history.length; i++) {
      if (history[i][1] > 0) {
        output[j][0] = history[i][0];
        output[j++][1] = history[i][1];
      }
    }
    history = output;
  }

  //The average price is calculated according to the formula: Total amount of currency in rubles / Total amount of currency
  // (10$ × 80₽ + 15$ × 85₽) / (10$ + 15$) = 83₽.
  private static Float getAveragePrice() {
    float boughtInNativeCurrency = 0;
    float boughtInCurrency = 0;

    for (float[] floats : history) {
      float price = floats[0];
      float sum = floats[1];
      boughtInNativeCurrency += sum * price;
      boughtInCurrency += sum;
    }

    return boughtInNativeCurrency / boughtInCurrency;
  }

  private static int getLastLine() {
    for (int i = 0; i < history.length; i++) {
      if (history[i][0] == 0) {
        return i;
      }
    }
    return 0;
  }

  private static float getFloat(String input) {
    return Float.parseFloat(input);
  }

  private static boolean exit(String input) {
    return input.equalsIgnoreCase(X);
  }

  private static void print() {
    for (float[] lines : history) {
      for (float record : lines) {
        if (record != 0) {
          System.out.print(record + SPACE);
        }
      }
      if (lines[0] != 0) {
        System.out.println();
      }
    }

    System.out.println(NEW_LINE);
  }
}