package io.muic.ooc.zork.command;

/**
 * Created by May on 2/1/2017 AD.
 */
public class GoCommand extends Command {
    private final String direction;
    private final int[] grid = new int[2];
    public GoCommand(String direction,int x,int y){
        this.direction = direction;
        this.grid[0] = x;
        this.grid[1] = y;
    }
    @Override
    public void apply() {
        System.out.printf("You are moving %sward\n",this.direction);
    }

    public int[] apply(int[] currentGrid){
        int[] newGrid = new int[]{grid[0]+currentGrid[0],grid[1]+currentGrid[1]};
        if (newGrid[0] < 0 || newGrid[1] < 0  || newGrid[0] > 10 || newGrid[1] > 10){
            return null;
        }
        this.apply();
        return new int[]{grid[0]+currentGrid[0],grid[1]+currentGrid[1]};
    }


}
