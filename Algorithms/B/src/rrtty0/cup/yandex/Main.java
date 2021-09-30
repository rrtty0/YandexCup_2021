package rrtty0.cup.yandex;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static int k;
    private static ArrayList<Tile> inputedTiles;
    private static int n;
    private static int m;
    private static Picture picture;

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        System.out.print("Input k: ");
        k = scannerInt.nextInt();

        System.out.println("Input tailes:");
        inputedTiles = new ArrayList<>();
        inputTiles();

        //outputTiles();

        System.out.println("Input n and m: ");
        n = scannerInt.nextInt();
        m = scannerInt.nextInt();

        System.out.println("Input picture: ");
        inputPicture();

        //picture.print();

        boolean result = takePicture();

        if (result)
            System.out.println("\nResult: Yes");
        else
            System.out.println("\nResult: No");

    }

    /**
     * Input tiles
     */
    private static void inputTiles(){
        Scanner scannerStr = new Scanner(System.in);
        for(int i = 0; i < k; ++i){
            String firstString = scannerStr.nextLine();
            String secondString = scannerStr.nextLine();
            inputedTiles.add(new Tile(firstString.charAt(0), firstString.charAt(1), secondString.charAt(0), secondString.charAt(1)));
        }
    }

    /**
     * Output tiles
     */
    private static void outputTiles(){
        for (int i = 0; i < inputedTiles.size(); ++i){
            inputedTiles.get(i).print();
            System.out.println("----");
        }
    }

    /**
     * Input picture
     */
    private static void inputPicture(){
        Scanner scannerStr = new Scanner(System.in);
        ArrayList<ArrayList<Tile>> pict = new ArrayList<>();
        for(int i = 0; i < n/2; ++i){
            pict.add(new ArrayList<>());
            String firstString = scannerStr.nextLine();
            String secondString = scannerStr.nextLine();
            for(int j = 0; j < m/2; ++j){
                pict.get(pict.size() - 1).add(new Tile(firstString.charAt(2*j), firstString.charAt(2*j+1), secondString.charAt(2*j), secondString.charAt(2*j+1)));
            }
        }
        picture = new Picture(pict);
    }

    /**
     * Checking the equality of tiles when flipping one of them by 90, 180 and 270 degrees
     * @param tile1 first tile
     * @param tile2 second tile
     * @return true - equal, false - not equal
     */
    private static boolean equalsByRotated(Tile tile1, Tile tile2){
        return (tile1.equals(tile2) || tile1.equals(tile2.rotateLeft()) ||
                tile1.equals(tile2.rotateRight()) || tile1.equals(tile2.rotateLeft().rotateLeft()));
    }

    /**
     * Composing a picture from tiles
     * @return true - successfully, false - not successfully
     */
    private static boolean takePicture(){
        boolean foundTile = true;
        int counted = 0;
        for(int i = 0; i < n/2 && foundTile; ++i){
            for(int j = 0; j < m/2 && foundTile; ++j){
                for(int z = 0; z < inputedTiles.size(); ++z){
                    if (equalsByRotated(picture.getTile(i, j), inputedTiles.get(z))){
                        ++counted;
                        foundTile = true;
                        inputedTiles.remove(z);
                        break;
                    }
                    else
                        foundTile = false;
                }
            }
        }

        return (foundTile && counted == n*m/4);
    }
}
