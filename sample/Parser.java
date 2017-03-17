package sample;

import java.util.ArrayList;

/**
 * Created by Mason Millsap on 2/28/2017.
 */
public class Parser {

    public static Fighter parseFighter(String fighter) {
        String[] info;
        info = fighter.split(":");
        Fighter out = new Fighter(10);
        out.setName(info[1]);
        for (int i = 2; i < info.length - 1; i += 2) {
            if (i < 12) {
            out.setStats(info[i],Integer.valueOf(info[i + 1]));
            } else {
            out.setWeapon(parseString(info[13]));
            }
        }
        return out;
    }

    static Weapon parseString(String s){
        Weapon toReturn = null;
        String in = s.trim();
//        System.out.println("In parseString. Before if:" + in);
        if(in.toLowerCase().equals("axe")){
            toReturn = Weapon.axe;
        }

        if(in.equals("sword")){
            toReturn = Weapon.sword;
        }
        if (in.equals("lance")) {
            toReturn = Weapon.lance;
        }
//        System.out.println("In parseString. After if:" + toReturn);
        return toReturn;
    }
}
