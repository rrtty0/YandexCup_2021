package rrtty0.cup.yandex;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static ArrayList<Boolean> firstNumber;
    public static ArrayList<Boolean> secondNumber;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input first string: ");
        String firstString = scanner.nextLine();
        System.out.print("Input second string: ");
        String secondString = scanner.nextLine();

        firstNumber = new ArrayList<>();
        Pattern pattern = Pattern.compile("(one)|(zero)");
        Matcher matcher = pattern.matcher(firstString);
        while (matcher.find()) {
            if (firstString.substring(matcher.start(), matcher.end()).equals("zero"))
                firstNumber.add(false);
            else
                firstNumber.add(true);
        }
        secondNumber = new ArrayList<>();
        matcher = pattern.matcher(secondString);
        while (matcher.find()) {
            if (secondString.substring(matcher.start(), matcher.end()).equals("zero"))
                secondNumber.add(false);
            else
                secondNumber.add(true);
        }

        System.out.print("Result: ");

        if (firstNumber.size() > secondNumber.size())
            System.out.println(">");
        else{
            if (firstNumber.size() < secondNumber.size())
                System.out.println("<");
            else{
                compareNumbers();
            }
        }
    }

    /**
     * Compare of numbers
     * @return 1 - first number > second number, -1 - first number < second number, 0 - first number = second number
     */
    public static int compareNumbers(){
        for(int i = 0; i < firstNumber.size(); ++i){
            if (firstNumber.get(i) == true &&  secondNumber.get(i) == false) {
                System.out.println(">");
                return 1;
            }
            else
            if (firstNumber.get(i) == false &&  secondNumber.get(i) == true) {
                System.out.println("<");
                return -1;
            }
        }
        System.out.println("=");
        return 0;
    }
}
