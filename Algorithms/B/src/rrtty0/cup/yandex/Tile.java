package rrtty0.cup.yandex;

/**
 * Class of tile(2x2)
 */
public class Tile {
     private char[][] tile = new char[2][2];

     public Tile(char LT, char RT, char LD, char RD){
         tile[0][0] = LT;
         tile[0][1] = RT;
         tile[1][0] = LD;
         tile[1][1] = RD;
     }

    public Tile(char[][] tile){
        this.tile[0][0] = tile[0][0];
        this.tile[0][1] = tile[0][1];
        this.tile[1][0] = tile[1][0];
        this.tile[1][1] = tile[1][1];
    }

     public void setTile(char LT, char RT, char LD, char RD){
         tile[0][0] = LT;
         tile[0][1] = RT;
         tile[1][0] = LD;
         tile[1][1] = RD;
     }

    public void setLT(char LT){
        tile[0][0] = LT;
    }

    public void setRT(char RT){
        tile[0][1] = RT;
    }

    public void setLD(char LD){
        tile[1][0] = LD;
    }

    public void setRD(char RD){
        tile[1][1] = RD;
    }

    public char getLT(){
        return tile[0][0];
    }

    public char getRT(){
        return tile[0][1];
    }

    public char getLD(){
        return tile[1][0];
    }

    public char getRD(){
        return tile[1][1];
    }

    /**
     * turn 90 degrees to the right
     * @return turned tile
     */
    public Tile rotateRight(){
         char[][] tileCopy = new char[2][2];
         tileCopy[0][0] = tile[1][0];
         tileCopy[0][1] = tile[0][0];
         tileCopy[1][1] = tile[0][1];
         tileCopy[1][0] = tile[1][1];

         return new Tile(tileCopy);
    }

    /**
     * turn 90 degrees to the left
     * @return turned tile
     */
    public Tile rotateLeft(){
        char[][] tileCopy = new char[2][2];
        tileCopy[0][0] = tile[0][1];
        tileCopy[1][0] = tile[0][0];
        tileCopy[1][1] = tile[1][0];
        tileCopy[0][1] = tile[1][1];

        return new Tile(tileCopy);
    }

    public void print(){
         System.out.println(tile[0][0] + " "+ tile[0][1]);
         System.out.println(tile[1][0] + " " + tile[1][1]);
    }

    @Override
    public boolean equals(Object otherObject){

         if(this == otherObject)
             return true;

         if(otherObject == null)
             return false;

         if(getClass() != otherObject.getClass())
             return false;

         Tile otherTile = (Tile) otherObject;

         return  tile[0][0] == otherTile.getLT() &&
                 tile[0][1] == otherTile.getRT() &&
                 tile[1][0] == otherTile.getLD() &&
                 tile[1][1] == otherTile.getRD();
    }
}
