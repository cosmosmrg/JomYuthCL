package io.muic.ooc.zork.command;

import io.muic.ooc.zork.map.LevelOneMap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by May on 2/1/2017 AD.
 */
public class InfoCommandTest {
    @Test
    public void apply() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        InfoCommand infoCommand = new InfoCommand(levelOneMap);
        infoCommand.apply();
    }

    @Test
    public void applyWithGrid() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        InfoCommand infoCommand = new InfoCommand(levelOneMap);
        infoCommand.apply(new int[]{0,0});

        infoCommand.apply(new int[]{9,9});

    }

}