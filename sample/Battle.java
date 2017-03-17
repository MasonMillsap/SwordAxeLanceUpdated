package sample;

import java.util.Random;

/**
 * Created by Mason Millsap on 3/1/2017.
 */
public class Battle {
    public Fighter player;
    public Fighter enemy;
    public Integer playerDmgTaken;
    public Integer enemyDmgTaken;
    public static boolean isOver;
    public static Fighter winner;

    public Battle(Fighter player, Fighter enemy) {
        this.enemy = enemy != null ? enemy : null;
        this.isOver = false;
        if (enemy == null) {
            this.enemy = new Fighter(5);
        }
        this.player = player;
    }

    public void fight() {
        Random rand = new Random();
        int n = rand.nextInt(9);
        if (!ChooseOpponentController.isHumanOpponent) {
            if (n >= 0 && n <= 2) {
                enemy.setWeapon(Weapon.axe);
            } else if (n >= 3 && n <= 5) {
                enemy.setWeapon(Weapon.sword);
            } else {
                enemy.setWeapon(Weapon.lance);
            }
        }
        if (player.getWeapon() == Weapon.axe) {
            if (enemy.getWeapon() == Weapon.axe) {
                player.setHP(player.getHP() - 1);
                enemy.setHP(enemy.getHP() - 1);
                playerDmgTaken = 1;
                enemyDmgTaken = 1;
            }
            if (enemy.getWeapon() == Weapon.sword) {
                player.setHP(player.getHP() - 2);
                enemy.setHP(enemy.getHP() - 1);
                playerDmgTaken = 2;
                enemyDmgTaken = 1;
            }
            if (enemy.getWeapon() == Weapon.lance) {
                player.setHP(player.getHP() - 1);
                enemy.setHP(enemy.getHP() - 2);
                playerDmgTaken = 1;
                enemyDmgTaken = 2;
            }
        }
        if (player.getWeapon() == Weapon.sword) {
            if (enemy.getWeapon() == Weapon.sword) {
                player.setHP(player.getHP() - 1);
                enemy.setHP(enemy.getHP() - 1);
                playerDmgTaken = 1;
                enemyDmgTaken = 1;
            }
            if (enemy.getWeapon() == Weapon.axe) {
                player.setHP(player.getHP() - 1);
                enemy.setHP(enemy.getHP() - 2);
                playerDmgTaken = 1;
                enemyDmgTaken = 2;
            }
            if (enemy.getWeapon() == Weapon.lance) {
                player.setHP(player.getHP() - 2);
                enemy.setHP(enemy.getHP() - 1);
                playerDmgTaken = 2;
                enemyDmgTaken = 1;
            }
        }
        if (player.getWeapon() == Weapon.lance) {
            if (enemy.getWeapon() == Weapon.lance) {
                player.setHP(player.getHP() - 1);
                enemy.setHP(enemy.getHP() - 1);
                playerDmgTaken = 1;
                enemyDmgTaken = 1;
            }
            if (enemy.getWeapon() == Weapon.sword) {
                player.setHP(player.getHP() - 1);
                enemy.setHP(enemy.getHP() - 2);
                playerDmgTaken = 1;
                enemyDmgTaken = 2;
            }
            if (enemy.getWeapon() == Weapon.axe) {
                player.setHP(player.getHP() - 2);
                enemy.setHP(enemy.getHP() - 1);
                playerDmgTaken = 2;
                enemyDmgTaken = 1;
            }
        }
        if (player.getHP() <= 0 | enemy.getHP()<= 0) {
            if ( player.getHP() > 0) {
                winner = player;
            }
            else {
                winner = enemy;
            }
            player.setHP(10);
            this.isOver = true;
        }
    }
}
