package items;

import additionally.*;

public class Dogs extends Creature implements Say {

    public Dogs(GardenBed bed, String name) {
        setBed(bed);
        setName(name);
    }

    public void totalDestroy() {
        int x=1;
        Map map = Map.getMap();
        Move moveToHeel=new Move(this, GardenBed.HEEL);
        moveToHeel.go();
        while (x < 100) {
            map.findVegetablesOnPoint(this, x);
            x++;

        }
        Move move = new Move(this,GardenBed.HOUSE);
        move.go();
        System.out.println(getName()+ " начала бегать вокруг " +GardenBed.HOUSE.getName());
    }

    @Override
    public void say( String somebody) {
        System.out.println(getName() + ": Гав-гав-гав ");
    }

    @Override
    public String toString() {
        return " " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dogs)) return false;

        Dogs dogs = (Dogs) o;

        if (getBed() != dogs.getBed()) return false;
        return getName() == dogs.getName();
    }

    @Override
    public int hashCode() {
        return getBed().hashCode() +  getName().hashCode();
    }
}
