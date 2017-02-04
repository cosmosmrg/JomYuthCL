package io.muic.ooc.zork.command;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/1/2017 AD.
 */
public class GoCommandTest {
    @Test
    public void applyWithGrid() throws Exception {
        int[] newGrid;
        GoCommand goNorthCommand = new GoCommand("North",0,1);
        newGrid = goNorthCommand.apply(new int[]{0,0});
        assertEquals(0,newGrid[0]);
        assertEquals(1,newGrid[1]);

        GoCommand goSouthCommand = new GoCommand("South",0,-1);
        newGrid = goSouthCommand.apply(new int[]{0,0});
        assertEquals(null,newGrid);

        GoCommand goEastCommand = new GoCommand("East",1,0);
        newGrid = goEastCommand.apply(new int[]{0,0});
        assertEquals(1,newGrid[0]);
        assertEquals(0,newGrid[1]);

        GoCommand goWestCommand = new GoCommand("West",-1,0);
        newGrid = goWestCommand.apply(new int[]{0,0});
        assertEquals(null,newGrid);
    }

    @Test
    public void apply() throws Exception {
        GoCommand goNorthCommand = new GoCommand("North",0,1);
        goNorthCommand.apply();
        GoCommand goSouthCommand = new GoCommand("South",0,-1);
        goSouthCommand.apply();
        GoCommand goEastCommand = new GoCommand("East",1,0);
        goEastCommand.apply();
        GoCommand goWestCommand = new GoCommand("West",-1,0);
        goWestCommand.apply();
    }

}