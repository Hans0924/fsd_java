package com.hans0924.fsd.process.network;

/**
 * @author Hanshuo Zeng
 * @since 2020-03-05
 */
public class Guard {
    private long prevTry;

    private String host;

    private int port;

    public long getPrevTry() {
        return prevTry;
    }

    public void setPrevTry(long prevTry) {
        this.prevTry = prevTry;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
