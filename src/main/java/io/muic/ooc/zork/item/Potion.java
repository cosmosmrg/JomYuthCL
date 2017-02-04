package io.muic.ooc.zork.item;

/**
 * Created by May on 1/31/2017 AD.
 */
public class Potion extends Item{
    public Potion(String name,int quality, int quantity){
        this.setName(name);
        this.setQuality(quality);
        this.setQuantity(quantity);
    }

    @Override
    public String getInfo() {
        return "Potion : "+this.getName()+" ["+this.getQuantity()+"]\n" + "Effect: "+this.getQuality()+"\n";
    }
}
