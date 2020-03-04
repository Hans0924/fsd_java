package com.hans0924.fsd.process;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;

/**
 * @author 曾韩铄
 * @since 2020-03-03
 */
public abstract class Process {

    private SelectableChannel channel;

    private SelectionKey selectionKey;

    public Process() {
    }

    public int calcMask() {
        return 0;
    }

    public abstract boolean run();

    public SelectableChannel getChannel() {
        return channel;
    }

    public void setChannel(SelectableChannel channel) {
        this.channel = channel;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }
}
