package io.muic.ooc.zork.item;


/**
 * Created by gigadot on 12-Jan-17.
 */
public abstract class Item {
    private String Name;
    private int quality;
    private int quantity;

    public void setName(String name){
        this.Name = name;
    }

    public void setQuality(int quality){
        this.quality = quality;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int use(){
        this.quantity-=1;
        return this.getQuality();
    }

    public void add(){
        this.quantity+=1;
    }

    public int getQuality(){
        return this.quality;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getName(){
        return this.Name;
    }

    public abstract String getInfo();
}
