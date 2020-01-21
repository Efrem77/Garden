package items;

import additionally.GardenBed;
import additionally.Map;

public class Vegetables extends Subject {
    private GardenBed bed;

    private int quantity;



    public Vegetables(GardenBed bed, String name,int quantity) {
        this.quantity=quantity;
        this.bed = bed;
        setName(name);
    }

    public void setBed(GardenBed bed) {
        this.bed = bed;
    }

    public GardenBed getBed() {
        return this.bed;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void decreaseQuantity() { this.quantity=quantity-10;}
    @Override
    public String toString() {
        return " " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vegetables)) return false;

        Vegetables vegetables = (Vegetables) o;

        if (getBed() != vegetables.getBed()) return false;
        return getName() == vegetables.getName();
    }

    @Override
    public int hashCode() {
        return getBed().hashCode() +  getName().hashCode();
    }
}
