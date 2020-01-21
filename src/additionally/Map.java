package additionally;


import items.*;

public class Map {
    private static Map map;
    private static Cage[] arr = new Cage[100];

    private Map() {//Прописали синголтон
        for (int i = 0; i < 100; i++) {
            arr[i] = new Cage();
        }
    }

    public static Map getMap() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    public void removeOnMap(Subject subject, int x1) {
        arr[x1].remove(subject);
    }//удаляем из ячейки

    public void addOnMap(Subject subject, int x2) {
        arr[x2].add(subject);
    }//добавляем в ячейку

    public void findShortieOnPoint(Shorties shorties, int x) {//ищем в ячйке
        for (int i = 0; i < arr[x].size(); i++) {
            if ((arr[x].get(i) instanceof Shorties) &&(!arr[x].get(i).equals(shorties))) {//Проверяем какие коротышки видят коротышку
                Meeting.meetShortie(shorties, arr[x].get(i));
            }
        }
    }
    public void findHelp(Shorties shorties, int x) {//ищем в ячйке
        for (int i = 0; i < arr[x].size(); i++) {
            if (arr[x].get(i) instanceof Shorties) {
                Shorties shortieHelper = (Shorties) arr[x].get(i);
                shorties.createHelper(shortieHelper);
                Move move = new Move(shortieHelper, shorties.getBed());
                move.go();
            }
            }
        }

    public void findVegetablesOnPoint(Dogs dogs, int x) {//ищем в ячйке
        boolean noShortie=false;
        for (int i = 0; i < arr[x].size(); i++) {
            if ((arr[x].get(i) instanceof Vegetables) && (!(arr[x].get(i) instanceof Shorties))) {
                noShortie = true;
                for (int j = 0; j < arr[x].size(); j++) {
                    if (arr[x].get(j) instanceof Shorties) {
                        noShortie = false;
                        Meeting.meetDog((Shorties) arr[x].get(j),dogs );
                    }
                }
            }
            if (noShortie) {
                GardenBed bed = ((Vegetables) arr[x].get(i)).getBed();
                dogs.setBed(bed);
                if (((Vegetables) arr[x].get(i)).getQuantity() > 9) {
                    ((Vegetables) arr[x].get(i)).decreaseQuantity();
                    System.out.println(dogs + " растоптал " + (Vegetables) arr[x].get(i) + " на " + ((Vegetables) arr[x].get(i)).getBed().getName() + " , осталось " + ((Vegetables) arr[x].get(i)).getQuantity() + " целых штук");
                }
            }
        }
    }
}


