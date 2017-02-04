package io.muic.ooc.zork.command;

import io.muic.ooc.zork.map.GameMap;

/**
 * Created by May on 2/1/2017 AD.
 */
public class InfoCommand extends Command {

    private final GameMap currentRoom;

    public InfoCommand(GameMap currentRoom){
        this.currentRoom = currentRoom;
    }

    @Override
    public void apply() {
        System.out.printf("You are at Room lv. %d\n",currentRoom.getLevel());
        System.out.println(this.currentRoom.getInfo());
    }

    public void apply(int[] grid){
        this.apply();
        System.out.println("getting grid info ...");
        System.out.printf("Direction: x: %d , y: %d\n",grid[0],grid[1]);
        System.out.println(currentRoom.getGridDetail(grid[0],grid[1]));
    }

    public GameMap getCurrentRoom() {
        return currentRoom;
    }
}
