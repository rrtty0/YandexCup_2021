package rrtty0.cup.yandex;

import java.util.Scanner;

public class Main {

    private static int n;
    private static int m;

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        System.out.print("Input n and m: ");
        n = scannerInt.nextInt();
        m = scannerInt.nextInt();

        Matrix matrix = new Matrix(n ,m);
        //matrix.print();
        while (matrix.getState() != State.FINISH){
            matrix.nextStep();
            //matrix.print();
        }
        System.out.println("Different numbers: " + matrix.getDifferentNumbers());
    }
}
