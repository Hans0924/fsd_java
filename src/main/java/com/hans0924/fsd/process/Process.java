package com.hans0924.fsd.process;

import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * @author Hanshuo Zeng
 * @since 2020-03-03
 */
public abstract class Process {

    public Process() {
    }

    public abstract boolean run();
}
