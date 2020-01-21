package items;

import additionally.GardenBed;
import additionally.Say;

public class Creature extends Subject implements Say {
    private GardenBed bed;
    private boolean dead;
    @Override
    public void say( String somebody) {
    }
    public void showPlace(Creature creature) {
        class CreaturePlace implements Say {
            final String place = "Я, " + creature + " появился на " + creature.getBed().getName();

            @Override
            public void say(String somebody) {
                if (creature instanceof Shorties) {
                    System.out.println(place);
                } else {
                    System.out.println(creature + ": Гав-гав-гав (С собачьего переводится как: Я, " + creature + " появился на " + creature.getBed().getName());
                }
            }
        }
        CreaturePlace place = new CreaturePlace();
        place.say(creature.getName());
    }
    public void setBed(GardenBed bed) {
        this.bed = bed;
    }

    public GardenBed getBed() {
        return this.bed;
    }

    public boolean getDead(){
        return dead;
    }

    public void setDead(boolean dead){
        this.dead=dead;
    }
}
