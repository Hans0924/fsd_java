package com.hans0924.fsd.process;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * @author 曾韩铄
 * @since 2020-03-03
 */
public class PMan {
    private boolean busy;

    private Selector selector;

    public PMan() throws IOException {
        selector = Selector.open();
        busy = false;
    }

    public void registerProcess(Process process) throws IOException {
        process.getChannel().configureBlocking(false);
        SelectionKey selectionKey = process.getChannel().register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, process);
        process.setSelectionKey(selectionKey);
    }

    public void run() throws IOException {
        long timeOut = busy ? 0 : 1000L;
        busy = false;
        if (selector.select(timeOut) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                Process attachment = (Process) selectionKey.attachment();
                if (attachment.run()) {
                    busy = true;
                }
            }
        }
    }
}
