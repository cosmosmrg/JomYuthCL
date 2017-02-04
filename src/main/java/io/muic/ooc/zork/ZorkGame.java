package io.muic.ooc.zork;

import io.muic.ooc.zork.command.*;
import io.muic.ooc.zork.item.Potion;
import io.muic.ooc.zork.item.Weapon;
import io.muic.ooc.zork.map.LevelOneMap;
import io.muic.ooc.zork.map.LevelThreeMap;
import io.muic.ooc.zork.map.LevelTwoMap;
import io.muic.ooc.zork.monster.Monster;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by gigadot on 12-Jan-17.
 */
public class ZorkGame {
    private Player player;
    private int x;
    private int y;
    private int level;
    private HashMap<String,Weapon> playerWeaponInventory;
    private HashMap<String,Potion> playerPotionInventory;
    private HelpCommand helpCommand = new HelpCommand();
    private HashMap<String,AttackCommand> attackCommandHashMap = new HashMap<>();
    private HashMap<String,GoCommand> goCommandHashMap = new HashMap<>();
    private HashMap<Integer,InfoCommand> infoCommandHashMap = new HashMap<>();
    private HashMap<String,UseCommand> useCommandHashMap = new HashMap<>();
    private Monster[][] monsterGrid;
    private Potion[][] potionGrid;
    private Weapon[][] weaponGrid;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        System.out.println("JomYuth CL: Hello, I am Mr. Bot. What is Your name?");
        System.out.print("You: ");
        String name = scanner.nextLine();
        player = new Player(name);
        playerWeaponInventory = player.getWeaponInventory();
        playerPotionInventory = player.getPotionInventory();
        x =0;
        y= 0;
        level = 1;
        initializeInfoCommand();
        initiazlizeGoCommand();
        updateAttackCommand();
        updateUseCommand();
        monsterGrid = infoCommandHashMap.get(level).getCurrentRoom().getMonsterGrid();
        potionGrid = infoCommandHashMap.get(level).getCurrentRoom().getPotionGrid();
        weaponGrid = infoCommandHashMap.get(level).getCurrentRoom().getWeaponGrid();

        System.out.println("Welcome to JomYuth CL type \"help()\" to see all command");
        while (!quit) {
            System.out.print("You: ");
            name = scanner.nextLine();
            quit = name.equals("quit");
            if (!quit) {
                System.out.println("JomYuth CL: " + name);
            }
            if(infoCommandHashMap.get(level).getCurrentRoom().shouldTerminate(x,y)){
                infoCommandHashMap.get(level).getCurrentRoom().terminate();
                level +=1;
                if(level>3){
                    System.out.println("Congratuation, you finish the first 3 levels");
                    break;
                }
                monsterGrid = infoCommandHashMap.get(level).getCurrentRoom().getMonsterGrid();
                potionGrid = infoCommandHashMap.get(level).getCurrentRoom().getPotionGrid();
                weaponGrid = infoCommandHashMap.get(level).getCurrentRoom().getWeaponGrid();
            }
            if(name.equals("help()")){
                helpCommand.apply();
            }
            else if(name.equals("info()") || name.equals("Info")){
                System.out.println(player.getInfo());
                infoCommandHashMap.get(level).apply(new int[]{x,y});
            }
            else if(name.contains("go")){
                String direction = name.substring(3,name.length()-1);
                if(goCommandHashMap.containsKey(direction)){
                    int[] newDirection = goCommandHashMap.get(direction).apply(new int[]{x,y});
                    if (newDirection == null){
                        System.out.println("You cannot move to this dirction");
                    }
                    else{
                        x = newDirection[0];
                        y = newDirection[1];
                        infoCommandHashMap.get(level).apply(new int[]{x,y});
                    }

                }
                else{
                    System.out.println("Direction must be Capitalize ex. South");
                }
            }
            else if(name.contains("take")){
                if(potionGrid[x][y]!= null){
                    player.take(potionGrid[x][y]);
                }
                else if (weaponGrid[x][y] != null){
                    player.take(weaponGrid[x][y]);
                }
                else{
                    System.out.println("Nothing to take here");
                }
                updateAttackCommand();
                updateUseCommand();
                infoCommandHashMap.get(level).getCurrentRoom().updateWeapon();
                infoCommandHashMap.get(level).getCurrentRoom().updatePotion();

            }
            else if(name.contains("drop")){
                if(name.length()<6){
                    System.out.printf("invalid Command");
                }
                else{
                    String itemName = name.substring(6,name.length()-1);
                    player.drop(itemName);
                }

            }
            else if(name.contains("attack")){
                String weaponName = name.substring(7,name.length()-1);
                System.out.println(weaponName);
                if(monsterGrid[x][y] != null){
                    if(attackCommandHashMap.containsKey(weaponName)){
                        attackCommandHashMap.get(weaponName).apply(monsterGrid[x][y]);
                        if(!monsterGrid[x][y].isAlive()){
                            player.addEXP(50);
                        }
                    }
                    else{
                        System.out.println("You don't have this weapon");
                    }
                }
                else{
                    if(attackCommandHashMap.containsKey(weaponName)){
                        attackCommandHashMap.get(weaponName).apply();
                    }
                    else{
                        System.out.println("You don't have this weapon");
                    }
                }
                infoCommandHashMap.get(level).getCurrentRoom().updateMonster();
                updateAttackCommand();
            }
            else if(name.contains("use")){
                String potionName = name.substring(4,name.length()-1);
                System.out.println(potionName);
                if(playerPotionInventory.containsKey(potionName)){
                    int effect = playerPotionInventory.get(potionName).use();
                    player.addHP(effect);
                }
                else{
                    System.out.println("You don't have this potion");
                }
            }
            else if (name.equals("quit")){
                System.out.println("Ending JomYuth CL ..");
            }
            else{
                System.out.println("Invalid Command");
            }

        }
    }

    public void updateAttackCommand(){
        attackCommandHashMap.clear();
        for (String weaponName:playerWeaponInventory.keySet()) {
            if(playerWeaponInventory.get(weaponName).getQuantity()>0){
                attackCommandHashMap.put(weaponName,new AttackCommand(playerWeaponInventory.get(weaponName)));
            }

        }
    }

    public void updateUseCommand(){
        useCommandHashMap.clear();
        for (String potionName:playerPotionInventory.keySet()) {
            if(playerPotionInventory.get(potionName).getQuantity()>0){
                useCommandHashMap.put(potionName,new UseCommand(playerPotionInventory.get(potionName)));
            }

        }
    }
    public void initiazlizeGoCommand(){
        goCommandHashMap.put("North",new GoCommand("North",0,1));
        goCommandHashMap.put("South",new GoCommand("South",0,-1));
        goCommandHashMap.put("East",new GoCommand("East",1,0));
        goCommandHashMap.put("West",new GoCommand("West",-1,0));
    }
    public void initializeInfoCommand(){
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        infoCommandHashMap.put(1,new InfoCommand(levelOneMap));
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        infoCommandHashMap.put(2, new InfoCommand(levelTwoMap));
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        infoCommandHashMap.put(3, new InfoCommand(levelThreeMap));
    }
}
