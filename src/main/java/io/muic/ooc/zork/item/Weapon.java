package io.muic.ooc.zork.item;

/**
 * Created by May on 1/31/2017 AD.
 */
public class Weapon extends Item{
    public Weapon(String name,int quality, int quantity){
        this.setName(name);
        this.setQuality(quality);
        this.setQuantity(quantity);
    }

    @Override
    public String getInfo() {
        return "Weapon : "+this.getName()+" ["+this.getQuantity()+"]\n" + "Attack Power: "+this.getQuality()+"\n";
    }
}
