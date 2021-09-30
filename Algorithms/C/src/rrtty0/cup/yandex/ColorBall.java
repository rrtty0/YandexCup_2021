package rrtty0.cup.yandex;

public class ColorBall implements Comparable<ColorBall>{

    private int indexOfColor;
    private int minAtBox;
    private int numberOfBalls;

    public ColorBall(){
        indexOfColor = -1;
        minAtBox = -1;
        numberOfBalls = -1;
    }

    public ColorBall(int indexOfColor, int minAtBox, int numberOfBalss){
        this.indexOfColor = indexOfColor;
        this.minAtBox = minAtBox;
        this.numberOfBalls = numberOfBalss;
    }

    public void setIndexOfColor(int indexOfColor){
        this.indexOfColor = indexOfColor;
    }

    public void setMinAtBox(int minAtBox) {
        this.minAtBox = minAtBox;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public int getIndexOfColor() {
        return indexOfColor;
    }

    public int getMinAtBox() {
        return minAtBox;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public boolean decreaseNumberOfBalls(int diff){
        if (diff > numberOfBalls)
            return false;
        numberOfBalls -= diff;
        return true;
    }

    @Override
    public int compareTo(ColorBall o) {
        if(this.numberOfBalls > o.getNumberOfBalls())
            return -1;
        if(this.numberOfBalls < o.getNumberOfBalls())
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "ColorBall{ " + indexOfColor + " " + minAtBox + " " + numberOfBalls + " }";
    }
}
