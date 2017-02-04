package io.muic.ooc.zork.command;

import io.muic.ooc.zork.item.Potion;

/**
 * Created by May on 2/4/2017 AD.
 */
public class UseCommand extends Command {
    private final Potion potion;
    public UseCommand(Potion potion){
        this.potion = potion;
    }
    @Override
    public void apply() {
        System.out.printf("Using %d\n",this.potion.getName());
    }

    public Potion getPotion() {
        return potion;
    }
}
