package io.muic.ooc.zork.command;

/**
 * Created by May on 2/1/2017 AD.
 */
public class HelpCommand extends Command{

    @Override
    public void apply() {
        System.out.println("Available command :");
        System.out.println("info()");
        System.out.println("go(direction)");
        System.out.println("take(item)");
        System.out.println("drop(item)");
        System.out.println("use(potion-item)");
        System.out.println("attack(Hand) => default");
        System.out.println("attack(weapon-item)");
        System.out.println("quit()");

    }
}
