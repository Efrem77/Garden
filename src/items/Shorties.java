package items;

import additionally.*;

import static java.lang.Math.signum;

public class Shorties extends Creature implements Say, Think, Run, Mix, Help, Ambulance, FightAgainstDog {
    private GardenBed bedTwo;
    private int trapDeathCount;
    private static boolean trapOn=true;
    private int shootingSkill;
    Shorties shortieHelper;

    public Shorties(GardenBed bed, String name) {
        setBed(bed);
        this.trapDeathCount=5;
        setDead(false);
        this.shootingSkill=0;
        setName(name);
    }


    public void work() {
        if(!getDead()) {
            if (bedTwo == null) {
                System.out.println("\n" + getName() + " остался работать на " + getBed().getName());
            } else {
                System.out.println(getName() + " отправился из " + getBed().getName() + " на " + bedTwo.getName());
                Move move = new Move(this, bedTwo);
                Move.go();//определяем текущий город и отправляем в указвнный
                bedTwo = null;
            }
        }else{
            System.out.println(getName()+ " недееспособен ");
        }
    }


    public void setBedTwo(GardenBed bedTwo) {
        this.bedTwo = bedTwo;
    }

    @Override//Основной
    public void say(String somebody) {
        if (!getDead()) {
            System.out.println(getName() + " говорит: привет " + somebody);
        }
    }

    @Override
    public void think(GardenBed bedTwo) throws ExceptionOfChoosingGardenBed, DeathException {
        if (!getDead()) {
            if (bedTwo == getBed()) {
                throw new ExceptionOfChoosingGardenBed();
            } else {
                System.out.println("\n" + getName() + " подумал отправиться на " + bedTwo.getName());
            }
            this.bedTwo = bedTwo;
        }
        else{
            System.out.println(getName()+ " не способен думать");
        }
    }

    @Override
    public void run(Shorties shortie1, Shorties shortie2) {
        if (!shortie1.getDead() && !shortie2.getDead() && !(shortie1==shortie2)) {
            Map map = Map.getMap();
            boolean b = true;
            int x = shortie1.getBed().getXg();
            System.out.println(shortie1.getName() + " бросился догонять " + shortie2.getName());
            while (x != shortie2.getBed().getXg() && b) {
                map.removeOnMap(shortie1, x);
                x += signum(shortie2.getBed().getXg() - shortie1.getBed().getXg());//шагает по x
                map.addOnMap(shortie1, x);
                if (x == shortie2.getBed().getXg()) {
                    System.out.println(shortie1.getName() + " догнал " + shortie2.getName());
                    shortie1.setBed(shortie2.getBed());
                    b = false;
                }
                if ((x == GardenBed.TRAP.getXg()) && trapOn) {
                    System.out.println(shortie1 + " попал в капкан - минус нога. "+shortie1+ " позвал на помощь " );
                    shortie1.setBed(GardenBed.TRAP);
                    help(shortie1);
                    trapOn = false;
                    break;

                }
            }
        } else {
            System.out.println("Догонялки невозможны");
            if (shortie1.getDead()) {
                System.out.println(shortie1 + " мертв");
            } else if (shortie2.getDead()) {
                System.out.println(shortie2 + " мертв");
            }
            else if(shortie1==shortie2){
                System.out.println(shortie1+" не может бегать сам за собой ");
            }
        }
    }

    public void createHelper(Shorties shorties){
        shortieHelper=shorties;
    }
    public Shorties getHelper(){
        return shortieHelper;
    }
    @Override
    public void help(Shorties shortie1) throws DeathException{
        Map map = Map.getMap();
        boolean shortie1InTrap = true;
        int xs = shortie1.getBed().getXg();
        int xP=xs;
        int xM=xs;
        int distP=0;
        int distM=0;
        int minDist=100;
        while (shortie1InTrap) {
            try {
                distP=Math.abs(xs-xP);
                xP=xP+1;
                distM=Math.abs(xs-xM);
                xM=xM-1;
                map.findHelp(shortie1, xP);
                map.findHelp(shortie1, xM);
                shortie1.getHelper();
                if(shortieHelper!=null) {
                    System.out.println(shortieHelper + " пришел на помощь " + shortie1 + " и освободил его из  " + shortie1.getBed().getName());
                    shortie1InTrap = false;
                    GardenBed bed= GardenBed.HOUSE;
                    System.out.println(shortieHelper+ " понес пострадавшего "+ shortie1+ " в "+ GardenBed.HOUSE.getName());
                    ambulance(shortie1,shortieHelper,bed);
                }
                reduceTrapDeathCount(shortie1);
            }
            catch (DeathException e){
                shortie1.setName("труп "+ shortie1);
                shortie1.setDead(true);
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void ambulance(Shorties shortie1,Shorties shortie3, GardenBed bed){
        Map map = Map.getMap();
        boolean b = true;
        int xw=bed.getXg();
        int xs = shortie1.getBed().getXg();
        int xs3 = shortie3.getBed().getXg();
        while ( ((xs!=xw)||(xs3!=xw))&&b){
            map.removeOnMap(shortie1, xs);
            map.removeOnMap(shortie3, xs3);
            xs += signum(xw - xs);
            xs3 += signum(xw - xs3);
            map.addOnMap(shortie1, xs);
            map.addOnMap(shortie3, xs3);
            if ((xs==xw)&&(xs3 == xw)) {
                if(shortie1.getDead()==true){
                    System.out.println(shortie3 + " и "  + " принесли и закопали " + shortie1 + " за " + bed.getName());
                    shortie1.setBed(bed);
                    map.removeOnMap(shortie1, xs);
                    shortie3.setBed(bed);
                    b = false;
                }
                else {
                    System.out.println(shortie3 + " принесли пострадавшего " + shortie1 + " в " + bed.getName());
                    shortie1.setBed(bed);
                    shortie3.setBed(bed);
                    b = false;
                }
            }
        }
    }

    public void reduceTrapDeathCount(Shorties shortie1) throws DeathException{
        shortie1.trapDeathCount--;
        if ((shortie1.trapDeathCount <= 0) &&(!getDead())){
            setDead(true);
            throw new DeathException();

        }
    }

    @Override
    public void mix(Shorties shortie1, Vegetables vegetable, GardenBed bed) {
        Map map = Map.getMap();
        boolean b = true;
        int x = shortie1.getBed().getXg();
        if (!getDead()) {
            if (x == vegetable.getBed().getXg()) {
                System.out.println(shortie1.getName() + " взял с собой" + vegetable + " и понес на " + bed.getName());
                while (x != bed.getXg() && b) {
                    map.removeOnMap(shortie1, x);
                    map.removeOnMap(vegetable, x);
                    x += signum(bed.getXg() - shortie1.getBed().getXg());//шагает по x
                    map.addOnMap(shortie1, x);
                    map.addOnMap(vegetable, x);
                    if (x == bed.getXg()) {
                        map.findShortieOnPoint(shortie1, x);
                        System.out.println(shortie1.getName() + " вывалил " + vegetable + " на " + bed.getName());
                        shortie1.setBed(bed);
                        vegetable.setBed(bed);
                        b = false;
                    }
                }
            } else {
                System.out.println(shortie1 + " хотел собрать урожай, но на " + shortie1.getBed().getName() + " нет" + vegetable);
            }
        }
        else {
            System.out.println(getName()+ " не может тоскать овощи, так как он RIP");
        }
    }
    public void fightAgainstDog(Shorties shorties, Dogs dogs) {
        if (!shorties.getDead()) {
            Gun.GunsGuide gunsGuide = new Gun.GunsGuide();
            gunsGuide.readGunsGuide(shorties);
            Gun.Shot shot = Gun.gun.new Shot(shorties, dogs);
            shot.fire();
        }
        else{
            System.out.println(shorties+" плевать на животных ");
        }
    }
    public int getShootingSkill(){
        return shootingSkill;
    }

    public void increaseShootingSkill(){
        if(shootingSkill<=3){
            shootingSkill++;
        }
    }
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shorties)) return false;

        Shorties shorties = (Shorties) o;

        if (getBed() != shorties.getBed()) return false;
        return getName() == shorties.getName();
    }

    @Override
    public int hashCode() {
        return getBed().hashCode() +  getName().hashCode();
    }
}

