import additionally.*;
import items.*;


public class Main {

    public static void main(String[] args) {

        Map map = Map.getMap();

        Shorties neznayka = new Shorties(GardenBed.STRAWBERRY, "Незнайка"){
            @Override//Основной
            public void say(String somebody) {
                System.out.println(getName() + " помахал ему рукой ");
            }
        };
        map.addOnMap(neznayka, neznayka.getBed().getXg());//заносим коротышку в список
        neznayka.showPlace(neznayka);

        Shorties clops = new Shorties(GardenBed.POTATO, "Клопс");
        map.addOnMap(clops, clops.getBed().getXg());//заносим коротышку в список
        clops.showPlace(clops);

        Shorties fex = new Shorties(GardenBed.TOMATOES, "Фекс"){
            @Override//Основной
            public void say(String somebody) {
                System.out.println(getName() + " сказал: Салам алейкум брат! ");
            }
        };
        map.addOnMap(fex, fex.getBed().getXg());//заносим коротышку в список
        fex.showPlace(fex);

        Shorties fix = new Shorties(GardenBed.TOMATOES, "Фикс"){
            @Override//Основной
            public void say(String somebody) {
                System.out.println(getName() + " сказал: Wassup man? ");
            }
        };
        map.addOnMap(fix, fix.getBed().getXg());//заносим коротышку в список
        fix.showPlace(fix);

        Shorties milordik = new Shorties(GardenBed.CUCUMBERS, "Милордик");
        map.addOnMap(milordik, milordik.getBed().getXg());//заносим коротышку в список
        milordik.showPlace(milordik);

        Shorties cezarino = new Shorties(GardenBed.POTATO, "Цезарино");
        map.addOnMap(cezarino, cezarino.getBed().getXg());//заносим коротышку в список
        cezarino.showPlace(cezarino);

        Vegetables pomidors = new Vegetables(GardenBed.TOMATOES, "Помидоры", 15);
        map.addOnMap(pomidors, pomidors.getBed().getXg());//заносим овощ в список
        System.out.println("На грядке растет" + pomidors+ " в количестве " + pomidors.getQuantity() + " штук");

        Vegetables strawberry= new Vegetables(GardenBed.STRAWBERRY, "Клубника", 16);
        map.addOnMap(strawberry, strawberry.getBed().getXg());//заносим овощ в список
        System.out.println("На грядке растет" + strawberry+ " в количестве " + strawberry.getQuantity() + " штук");

        Vegetables cucumbers = new Vegetables(GardenBed.CUCUMBERS, "Огурцы", 18);
        map.addOnMap(cucumbers, cucumbers.getBed().getXg());//заносим овощ в список
        System.out.println("На грядке растет" + cucumbers+ " в количестве " + cucumbers.getQuantity() + " штук");

        Vegetables potato = new Vegetables(GardenBed.TOMATOES, "Картошка", 11);
        map.addOnMap(potato, potato.getBed().getXg());//заносим овощ в список
        System.out.println("На грядке растет" + potato+ " в количестве " + potato.getQuantity() + " штук");

        Gun gun = new Gun(GardenBed.HOUSE);
        map.addOnMap(gun, gun.getBed().getXg());//заносим овощ в список
        System.out.println("В " +gun.getBed().getName()+ " завалялось " + gun.getGunName());

        try {
            neznayka.think(GardenBed.STRAWBERRY);
        } catch (ExceptionOfChoosingGardenBed e) {
            System.out.println(e.getMessage());
            neznayka.setBedTwo(null);
        }
        neznayka.work();//отправляем жителя на другую грядку
        neznayka.mix(neznayka,pomidors, GardenBed.STRAWBERRY );

        try {
            cezarino.think(GardenBed.CUCUMBERS);
        } catch (ExceptionOfChoosingGardenBed e) {
            System.out.println(e.getMessage());
            cezarino.setBedTwo(null);
        }
        cezarino.work();//отправляем жителя на другую грядку

        try {
            fex.think(GardenBed.POTATO);
        } catch (ExceptionOfChoosingGardenBed e){
            System.out.println(e.getMessage());
            fex.setBedTwo(null);
        }
        fex.work();//отправляем жителя на другую грядку

        try {
            fix.think(GardenBed.STRAWBERRY);
        } catch (ExceptionOfChoosingGardenBed e){
            System.out.println(e.getMessage());
            fix.setBedTwo(null);
        }
        fix.work();//отправляем жителя на другую грядку
        fix.mix(fix,strawberry, GardenBed.POTATO );

        fex.run(fex, neznayka);

        try {
            clops.think(GardenBed.CUCUMBERS);
        } catch (ExceptionOfChoosingGardenBed e) {
            System.out.println(e.getMessage());
            clops.setBedTwo(null);
        }
        clops.work();//отправляем жителя на другую грядку
        clops.run(clops, fix);
        milordik.work();//отправляем жителя на другую грядку

        try {
            clops.think(GardenBed.HOUSE);
        }catch (ExceptionOfChoosingGardenBed e) {
            System.out.println(e.getMessage());
            clops.setBedTwo(null);
        }
        clops.work();//отправляем жителя на другую грядку

        fix.mix(fix,pomidors, GardenBed.TOMATOES );

        try {
            fex.think(GardenBed.POTATO);
        }catch (ExceptionOfChoosingGardenBed e) {
            System.out.println(e.getMessage());
            fex.setBedTwo(null);
        }
        fex.work();//отправляем жителя на другую грядку

        cezarino.run(cezarino, cezarino);

        clops.mix(clops,pomidors, GardenBed.TOMATOES );

        Dogs dog1 = new Dogs(GardenBed.HEEL,"Шавка");
        map.addOnMap(dog1, dog1.getBed().getXg());//заносим овощ в список
        dog1.showPlace(dog1);

        Dogs dog2 = new Dogs(GardenBed.HEEL,"Кобель");
        map.addOnMap(dog2, dog2.getBed().getXg());//заносим овощ в список
        dog2.showPlace(dog1);

        dog1.totalDestroy();
        dog2.totalDestroy();
        clops.fightAgainstDog(clops, dog1);
        cezarino.fightAgainstDog(cezarino, dog1);
        fex.fightAgainstDog(fex, dog1);
        fex.fightAgainstDog(fex, dog2);
        fex.fightAgainstDog(fex, dog2);
    }
}