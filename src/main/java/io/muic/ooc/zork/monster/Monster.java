package io.muic.ooc.zork.monster;

/**
 * Created by gigadot on 12-Jan-17.
 */
public class Monster {
    private final String Name;
    private int HP;
    private final int AttackPower;

    public Monster(String Name,int MaxHP,int AttackPower){
        this.Name = Name;
        this.HP = MaxHP;
        this.AttackPower = AttackPower;
    }

    public String getInfo(){
        return this.getName()+" \n" + "HP : "+this.HP+"\n" + "Attack Power : "+this.AttackPower+"\n";
    }

    public String getName(){
        return this.Name;
    }

    public int getHP(){
        return this.HP;
    }

    public int getAttackPower(){
        return this.AttackPower;
    }

    public int attack(){
        return this.getAttackPower();
    }

    public boolean isAlive(){
        return this.getHP() >0;
    }
    public boolean isAttacked(int attackPower){
        this.HP -=attackPower;
        return this.isAlive();
    }

}
