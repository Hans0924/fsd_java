package com.hans0924.fsd.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hanshuo Zeng
 * @since 2020-03-03
 */
public class PMan {
    private final static Logger LOGGER = LoggerFactory.getLogger(PMan.class);

    public static List<Process> processes = new ArrayList<>();

    private boolean busy;

    public PMan() {
        busy = false;
    }

    public void registerProcess(Process process) {
        processes.add(process);
    }

    public void run() {
        for (Process process : processes) {
            if (process.run()) {
                busy = true;
            }
        }
    }
}
