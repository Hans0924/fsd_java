
package com.hans0924.fsd.process.metar;

/**
 * @author Hanshuo Zeng
 * @since 2020/03/04
 */
public class Mmq {

    private String destination;

    private String metarId;

    private int fd;

    private int parsed;

    private Mmq prev;

    private Mmq next;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMetarId() {
        return metarId;
    }

    public void setMetarId(String metarId) {
        this.metarId = metarId;
    }

    public int getFd() {
        return fd;
    }

    public void setFd(int fd) {
        this.fd = fd;
    }

    public int isParsed() {
        return parsed;
    }

    public void setParsed(int parsed) {
        this.parsed = parsed;
    }

    public Mmq getPrev() {
        return prev;
    }

    public void setPrev(Mmq prev) {
        this.prev = prev;
    }

    public Mmq getNext() {
        return next;
    }

    public void setNext(Mmq next) {
        this.next = next;
    }
}
