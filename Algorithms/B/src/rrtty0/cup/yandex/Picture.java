package rrtty0.cup.yandex;

import java.util.ArrayList;

/**
 * Class of picture
 */
public class Picture {
    private static ArrayList<ArrayList<Tile>> arrTiles;

    public Picture(ArrayList<ArrayList<Tile>> picture){
        arrTiles = new ArrayList<>(picture);
    }

    public void print(){
        for(int i = 0; i < arrTiles.size(); ++i){
            String LTandRTtoOutput = new String("");
            String LDandRDtoOutput = new String("");
            for (int j = 0; j < arrTiles.get(i).size(); ++j){
                LTandRTtoOutput += String.valueOf(arrTiles.get(i).get(j).getLT()) + String.valueOf(arrTiles.get(i).get(j).getRT());
                LDandRDtoOutput += String.valueOf(arrTiles.get(i).get(j).getLD()) + String.valueOf(arrTiles.get(i).get(j).getRD());
            }
            System.out.println(LTandRTtoOutput);
            System.out.println(LDandRDtoOutput);
        }
    }

    public Tile getTile(int i, int j){
        return arrTiles.get(i).get(j);
    }
}
