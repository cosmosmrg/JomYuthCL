package io.muic.ooc.zork.map;

import io.muic.ooc.zork.item.ItemFactory;
import io.muic.ooc.zork.item.Potion;
import io.muic.ooc.zork.item.Weapon;
import io.muic.ooc.zork.monster.Monster;
import io.muic.ooc.zork.monster.MonsterFactory;

import java.util.List;
import java.util.Random;

/**
 * Created by May on 2/4/2017 AD.
 */
public class LevelThreeMap implements GameMap{
    private final String[][] grid;
    private Monster[][] monsterGrid;
    private Potion[][] potionGrid;
    private Weapon[][] weaponGrid;
    private int monsterAmount;
    private int potionAmount;
    private int weaponAmount;

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public int getMonsterAmount() {
        return monsterAmount;
    }

    @Override
    public int getPotionAmount() {
        return potionAmount;
    }

    @Override
    public int getWeaponAmount() {
        return weaponAmount;
    }

    @Override
    public Monster[][] getMonsterGrid() {
        return monsterGrid;
    }

    @Override
    public Potion[][] getPotionGrid() {
        return potionGrid;
    }

    @Override
    public String[][] getGrid() {
        return grid;
    }

    @Override
    public Weapon[][] getWeaponGrid() {
        return weaponGrid;
    }

    public LevelThreeMap(){
        grid = new String[10][10];
        monsterGrid = new Monster[10][10];
        potionGrid = new Potion[10][10];
        weaponGrid = new Weapon[10][10];
        this.setGrid();
        this.setStart(0,0);
        this.setExit(9,9);
    }

    public void setStart(int x,int y){
        this.setGridDetail(x,y,"Starting Point");
    }

    public void setExit(int x,int y){
        this.setGridDetail(x,y,"Exit");
    }

    public void setGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.setGridDetail(i,j,"Nothing Here");
            }
        }
    }


    public boolean isEmpty(int x, int y){
        return this.grid[x][y] == "Nothing Here";
    }


    public void setGridDetail(int x ,int y, String details) {
        this.grid[x][y] = details;
    }

    @Override
    public String getGridDetail(int x, int y){
        return this.grid[x][y];
    }

    @Override
    public void create(){
        MonsterFactory monsterFactory = new MonsterFactory();
        ItemFactory itemFactory = new ItemFactory();
        monsterFactory.init();
        itemFactory.init();
        List<Monster> monsters = monsterFactory.createMonster(3,1);
        List<Potion> potions = itemFactory.createPotion(5);
        List<Weapon> weapons = itemFactory.createWeapon(5);
        this.monsterAmount = 1;
        this.potionAmount = 5;
        this.weaponAmount = 5;
        Random rand = new Random();
        for (Monster monster:monsters) {
            int x = 0;
            int y = 0;
            while(!isEmpty(x,y)){
                x = rand.nextInt(10);
                y = rand.nextInt(10);

            }
            this.setGridDetail(x,y,monster.getInfo());
            monsterGrid[x][y] = monster;
        }

        for (Potion potion:potions) {
            int x = 0;
            int y = 0;
            while(!isEmpty(x,y)){
                x = rand.nextInt(10);
                y = rand.nextInt(10);

            }
            this.setGridDetail(x,y,potion.getInfo());
            potionGrid[x][y] = potion;
        }

        for (Weapon weapon:weapons) {
            int x = 0;
            int y = 0;
            while(!isEmpty(x,y)){
                x = rand.nextInt(10);
                y = rand.nextInt(10);

            }
            this.setGridDetail(x,y,weapon.getInfo());
            weaponGrid[x][y] = weapon;
        }

    }

    @Override
    public void terminate() {
        System.out.println("Congratulations, you passed the level 3");
    }

    @Override
    public boolean shouldTerminate(int x,int y) {
        return this.grid[x][y] == "Exit" && this.monsterAmount == 0;
    }

    public void updateMonster(){
        Monster[][] newMonsterGrid = new Monster[10][10];
        int newMonsterAmount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.monsterGrid[i][j] != null && this.monsterGrid[i][j].isAlive()) {
                    newMonsterGrid[i][j] = this.monsterGrid[i][j];
                    newMonsterAmount++;
                }
            }
        }
        this.monsterAmount = newMonsterAmount;

    }

    public void updatePotion(){
        Potion[][] newPotionGrid = new Potion[10][10];
        int newPotionAmount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.potionGrid[i][j] != null && this.potionGrid[i][j].getQuantity() != 0) {
                    newPotionGrid[i][j] = this.potionGrid[i][j];
                    newPotionAmount++;
                }
            }
        }
        this.potionAmount = newPotionAmount;
    }

    public void updateWeapon(){
        Weapon[][] newWeaponGrid = new Weapon[10][10];
        int newWeaponAmount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(this.weaponGrid[i][j]!= null && this.weaponGrid[i][j].getQuantity() != 0){
                    newWeaponGrid[i][j] = this.weaponGrid[i][j];
                    newWeaponAmount++;
                }

            }
        }
        this.weaponAmount = newWeaponAmount;
    }

    @Override
    public String getInfo() {
        String re = "";
        re+="There are "+this.potionAmount+" potions left in the map\n";
        re+="There are "+this.weaponAmount+" weapons left in the map\n";
        if(this.monsterAmount == 0){
            re+= "Go to the Exit at X: 9 Y: 9\n";
        }
        else{
            re+= "There are "+this.monsterAmount+" monsters left in the map, Go and Kill them\n";
        }
        return re;
    }
}
