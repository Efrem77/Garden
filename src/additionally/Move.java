package additionally;

import items.*;
import static java.lang.Math.*;

public class Move {
    private static Map map = Map.getMap();
    private static Creature creature;
    private static GardenBed bed2;
    private static int x1;
    private static int x2;
    private static int x;
    private static boolean b = true;



    public Move(Creature creature, GardenBed bed2) {
        this.creature = creature;
        this.bed2 = bed2;
        x1 = creature.getBed().getXg();
        x2 = bed2.getXg();
        x = x1;
        b=true;
    }

    public static void go() {
        if (!creature.getDead()) {
            while (x != x2 && b) {
                map.removeOnMap(creature, x);
                x += signum(x2 - x1);//шагает по x
                map.addOnMap(creature, x);
                coordinates();
            }
        }
        else{
            System.out.println(creature+ " уже свое отходил");
        }
    }

    private static void coordinates() {
        if (x == x2) {
            System.out.println(creature.getName() + " пришел на " + bed2.getName());
            creature.setBed(bed2);
            if(creature instanceof  Shorties) {
                Shorties shortie1 = (Shorties) creature;
                map.findShortieOnPoint(shortie1, x);
            }
            b = false;
        }
    }
}



