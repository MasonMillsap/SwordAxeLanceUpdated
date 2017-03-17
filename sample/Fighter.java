package sample;

import java.util.HashMap;

/**
 * Created by Mason Millsap on 2/17/2017.
 */
public class Fighter {
    private Integer hitpoints;
    private String name;
    private Weapon weapon;
    private Integer experience;
    private Integer level;
    private HashMap<String, Integer> stats;
    private FighterClass fighterClass;

    public Fighter(Integer hp) {
        this.hitpoints = hp;
        this.weapon = Weapon.axe;
        this.level = 1;
        this.experience = 0;
        initializeStats();

    }
    private void initializeStats() {
        stats = new HashMap();
        stats.put("Attack", 0);
        stats.put("Defense", 0);
        stats.put("Vitality", 0);
        stats.put("Speed", 0);
    }
    @Override
    public String toString() {
        String out = new String();
        out += "Name:" + name + ":";
        out += "HP:" + hitpoints.toString() + ":";
        out += "Attack:" + stats.get("Attack") + ":";
        out += "Defense:" + stats.get("Defense") + ":";
        out += "Vitality:" + stats.get("Vitality") + ":";
        out += "Speed:" + stats.get("Speed") + ":";
        out += "Weapon:" + weapon.toString();
        return out;
    }

    public String getName() {return name;}
    public Integer getHP() {return hitpoints;}
    public Weapon getWeapon() {return weapon;}
    public FighterClass getFighterClass() {return fighterClass;}
    public Integer getLevel() {return level;}
    public Integer getExp() {return experience;}
    public HashMap getStats() {return stats;}

    public void setFighterClass(FighterClass fighterClass) {
        this.fighterClass = fighterClass;
        if (this.fighterClass == fighterClass.ASSASSIN) {
            setStats("Attack", 9);
            setStats("Defense", 7);
            setStats("Vitality", 7);
            setStats("Speed", 9);
        }
        if (this.fighterClass == fighterClass.WARRIOR) {
            setStats("Attack", 9);
            setStats("Defense", 7);
            setStats("Vitality", 9);
            setStats("Speed", 7);

        }
        if (this.fighterClass == fighterClass.KNIGHT) {
            setStats("Attack", 8);
            setStats("Defense", 10);
            setStats("Vitality", 8);
            setStats("Speed", 6);
        }
    }

            public void setName(String name) {this.name = name;}
    public void setHP(Integer hp) {this.hitpoints = hp;}
    public void setWeapon(Weapon weapon) {this.weapon = weapon;}
    public void setStats(String stat, Integer value) {
        stats.replace(stat, value);
    }

    public void takeHit() {this.hitpoints -= 1;}

    public void battle(String opponentWeapon) {
    }

}

