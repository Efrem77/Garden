package items;

import additionally.GardenBed;
import additionally.Move;

public class Gun extends Subject {
    private GardenBed bed;
    private static String gunName;
    private static int ammo=6;
    static Gun gun= new Gun(GardenBed.HOUSE);
    public Gun(String gunName, GardenBed bed){
        this.bed=bed;
        gunName=gunName;
    }

    public Gun(GardenBed bed) {
        this.bed = bed;
        gunName = "Охотничье ружье";
    }

    public static class GunsGuide{
        public void readGunsGuide(Shorties shorties){
            Move move= new Move(shorties, GardenBed.HOUSE);
            move.go();
            shorties.increaseShootingSkill();
            System.out.println(shorties+" прочитал раздел "+gunName+" в книге обучения стрельбе, и теперь его уровень стрельбы равен "+shorties.getShootingSkill());
        }
    }

    public class Shot {
        Shorties shortie1;
        Dogs dogs;

        public Shot(Shorties shortie1, Dogs dogs) {
            this.shortie1 = shortie1;
            this.dogs = dogs;
        }

        public void fire(){
            if (getAmmo() > 0 && (dogs.getBed()==GardenBed.HOUSE)) {
                        int accurancy = (4 - shortie1.getShootingSkill()) * 10;
                        System.out.println(shortie1+" взял "+gunName+ " вышел из "+shortie1.getBed().getName() + " и стреляет из ружья в слетевшую с катушек "+ dogs+" - пуля пролетает в " + accurancy + " см от " + dogs);
                        decreaseAmmo();
                        System.out.println(dogs+" в страхе убежала на "+ GardenBed.HEEL.getName());
                        Move move=new Move(dogs, GardenBed.HEEL);
                        move.go();
                        System.out.println(shortie1 +" положил "+gunName+ " на положенное ему место в " + GardenBed.HOUSE.getName());
                    } else if(getAmmo() <= 0){
                        System.out.println(shortie1 + " хотел выстрелить из " + gunName + " , но в ружье кончились патроны ");
                    }else if(!(dogs.getBed()==GardenBed.HOUSE)) {
                        System.out.println("На горизонте нет "+dogs);
            }
        }
    }

    public void decreaseAmmo() {
        if(ammo>0){
            ammo--;
        }
    }

    public int  getAmmo() {
        return ammo;
    }

    public String getGunName() {
        return gunName;
    }

    public void setBed(GardenBed bed) {
        this.bed = bed;
    }

    public GardenBed getBed() {
        return this.bed;
    }

    @Override
    public String toString() {
        return " " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gun)) return false;

        Gun gun = (Gun) o;

        if (getBed() != gun.getBed()) return false;
        return getName() == gun.getName();
    }

    @Override
    public int hashCode() {
        return getBed().hashCode() +  getName().hashCode();
    }
}
