package rrtty0.cup.yandex;

import java.util.*;

public class Main {

    private static int k;
    private static int sumOfBalls = 0;
    private static int minBallsAtBox = 0;
    private static Queue<ColorBall> priorityQueueOfColorBalls = new PriorityQueue<>();
    private static ColorBall[] colorBallsSortedByIndex;
    private static int numberOfBoxes;
    private static int numberOfBallsAtBox;
    private static ArrayList<ArrayList<Integer>> boxes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input k: ");
        k = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Input number of balls:");
        String inputString = scanner.nextLine();
        String[] numberOfBalls = inputString.split("\\s+");
        System.out.println("Input minimum number of balls at boxes:");
        inputString = scanner.nextLine();
        String[] minNumberOfBalls = inputString.split("\\s+");

        colorBallsSortedByIndex = new ColorBall[k];
        for(int i = 0; i < k; ++i){
            ColorBall colorBall = new ColorBall(i + 1, Integer.parseInt(minNumberOfBalls[i]), Integer.parseInt(numberOfBalls[i]));
            priorityQueueOfColorBalls.add(colorBall);
            colorBallsSortedByIndex[i] = colorBall;
            sumOfBalls += Integer.parseInt(numberOfBalls[i]);
            minBallsAtBox += Integer.parseInt(minNumberOfBalls[i]);
        }

        numberOfBoxes = getNumberOfBoxes();
        if(numberOfBoxes <= 0)
            System.out.println("It is impossible to arrange balls in boxes!");
        else{
            System.out.println("Number of boxes: " + numberOfBoxes);
            numberOfBallsAtBox = (int)sumOfBalls/numberOfBoxes;
            System.out.println("Number of balls: " + numberOfBallsAtBox);

            ballsToBoxes();
            System.out.println("Boxes:");
            for (int i = 0; i < boxes.size(); ++i) {
                Collections.sort(boxes.get(i));
                System.out.println(boxes.get(i));
            }
        }
    }

    /***
     * Distributing balls to boxes
     */
    public static void ballsToBoxes(){
        boxes = new ArrayList<>();

        for(int box = 0; box < numberOfBoxes; ++box){
            boxes.add(new ArrayList<Integer>());
            int freePositionAtCurrentBox = numberOfBallsAtBox;
            for(int i = 0; i < k && freePositionAtCurrentBox != 0; ++i){
                if (colorBallsSortedByIndex[i].getNumberOfBalls() > 0 &&
                        colorBallsSortedByIndex[i].getMinAtBox() > 0 &&
                        freePositionAtCurrentBox >= colorBallsSortedByIndex[i].getMinAtBox()) {
                    freePositionAtCurrentBox -= colorBallsSortedByIndex[i].getMinAtBox();
                    colorBallsSortedByIndex[i].decreaseNumberOfBalls(colorBallsSortedByIndex[i].getMinAtBox());
                    for(int j = 0; j < colorBallsSortedByIndex[i].getMinAtBox(); ++j)
                        boxes.get(boxes.size() - 1).add(i + 1);
                }
            }

            for(int i = 0; i < k && freePositionAtCurrentBox != 0; ++i){
                if(colorBallsSortedByIndex[i].getMinAtBox() == 0 && colorBallsSortedByIndex[i].getNumberOfBalls() > 0){
                    if(colorBallsSortedByIndex[i].getNumberOfBalls() <= freePositionAtCurrentBox){
                        freePositionAtCurrentBox -= colorBallsSortedByIndex[i].getNumberOfBalls();
                        for(int j = 0; j < colorBallsSortedByIndex[i].getNumberOfBalls(); ++j)
                            boxes.get(boxes.size() - 1).add(i + 1);
                        colorBallsSortedByIndex[i].setNumberOfBalls(0);
                    }
                    else{
                        colorBallsSortedByIndex[i].decreaseNumberOfBalls(freePositionAtCurrentBox);
                        for(int j = 0; j < freePositionAtCurrentBox; ++j)
                            boxes.get(boxes.size() - 1).add(i + 1);
                        freePositionAtCurrentBox = 0;
                    }
                }
            }

            while (freePositionAtCurrentBox != 0){
                ColorBall colorBall = priorityQueueOfColorBalls.poll();
                colorBall.decreaseNumberOfBalls(1);
                priorityQueueOfColorBalls.add(colorBall);
                freePositionAtCurrentBox -= 1;
                boxes.get(boxes.size() - 1).add(colorBall.getIndexOfColor());
            }
        }
    }

    /**
     * getting the maximum number of boxes for a given constraint
     * @return number of boxes
     */
    public static int getNumberOfBoxes(){
        int upperBound;
        if (minBallsAtBox == 0)
            upperBound = sumOfBalls;
        else
            upperBound = sumOfBalls/minBallsAtBox;

        ArrayList<Integer> dividers = getPrimeFactor(sumOfBalls, upperBound);
        Collections.sort(dividers);
        int currentNumberOfBoxes = -1;
        for(int i = dividers.size()-1; i >= 0 && currentNumberOfBoxes == -1; --i){
            currentNumberOfBoxes = dividers.get(i);
            for(int j = 0; j < k; ++j)
                if(currentNumberOfBoxes*colorBallsSortedByIndex[i].getMinAtBox() > colorBallsSortedByIndex[i].getNumberOfBalls()){
                    currentNumberOfBoxes = -1;
                    break;
                }
        }
        return currentNumberOfBoxes;
    }

    /**
     * Getting divisors of a number
     * @param n the number to be divisible
     * @param upperBound upper limit of values for divisors
     * @return Divisors of n
     */
    public static ArrayList<Integer> getPrimeFactor(int n, int upperBound){
        ArrayList<Integer> dividers = new ArrayList<>();
        if(n == 1){
            dividers.add(1);
            return dividers;
        }
        int middle = n/2;
        for(int i = 1; i <= middle && upperBound >= i; ++i){
            if(n%i == 0){
                if(!dividers.contains((int)n/i) && upperBound >= (int)n/i)
                    dividers.add(n/i);
                if(!dividers.contains(i) && upperBound >= i)
                    dividers.add(i);
            }
        }

        return dividers;
    }
}
