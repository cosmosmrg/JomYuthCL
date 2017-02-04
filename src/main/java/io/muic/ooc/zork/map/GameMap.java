package io.muic.ooc.zork.map;

import io.muic.ooc.zork.item.Potion;
import io.muic.ooc.zork.item.Weapon;
import io.muic.ooc.zork.monster.Monster;

/**
 * Created by gigadot on 12-Jan-17.
 */
public abstract interface GameMap {
    int getLevel();
    void create();
    void terminate();
    boolean shouldTerminate(int x, int y);

    String getInfo();
    int getMonsterAmount();
    int getPotionAmount();
    int getWeaponAmount();
    Monster[][] getMonsterGrid();
    Potion[][] getPotionGrid();
    String[][] getGrid();
    Weapon[][] getWeaponGrid();
    String getGridDetail(int x, int y);
    void updateWeapon();
    void updatePotion();
    void updateMonster();
}
