package additionally;

import items.Shorties;
import items.Subject;
import items.Dogs;

public class Meeting {
    public static void meetShortie(Shorties shortie1, Subject subject) {
        Shorties shortie2 = (Shorties) subject;
        System.out.println(shortie1+" увидел, перед собой "+ shortie2);
        if((!shortie1.getDead())&&(!shortie2.getDead())) {
            shortie1.say(shortie2.getName());
            shortie2.say(shortie1.getName());
        }
    }
    public static void meetDog(Shorties shortie1, Subject subject) {
        Dogs dogs = (Dogs) subject;
        System.out.println(shortie1+" увидел, перед собой "+ dogs);
        System.out.println(shortie1.getName() +" сказал: Пошла вон, "+ dogs.getName());
        dogs.say(shortie1.getName());
    }
}

