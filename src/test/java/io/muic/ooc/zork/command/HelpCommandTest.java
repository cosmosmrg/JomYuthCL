package io.muic.ooc.zork.command;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by May on 2/1/2017 AD.
 */
public class HelpCommandTest {
    @Test
    public void apply() throws Exception {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.apply();
    }

}