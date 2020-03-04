package com.hans0924.fsd.process.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 曾韩铄
 * @since 2020-03-03
 */
public class ConfigEntry {

    private String var;

    private String data;

    private List<String> parts;

    private boolean changed;

    private int nParts;

    public ConfigEntry(String var, String data) {
        this.var = var;
        this.data = data;
        changed = true;
        parts = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getInt() {
        return Integer.parseInt(data);
    }

    public void fillParts() {
        String[] split = data.split(",");
        for (String part : split) {
            parts.add(part.strip());
        }
        nParts = split.length;
    }

    public boolean inList(String entry) {
        if (parts.isEmpty()) {
            fillParts();
        }
        for (String part : parts) {
            if (Objects.equals(part, entry)) {
                return true;
            }
        }
        return false;
    }

    public int getNParts() {
        if (parts.isEmpty()) {
            fillParts();
        }
        return nParts;
    }

    public String getPart(int num) {
        if (parts.isEmpty()) {
            fillParts();
        }
        if (num >= nParts) {
            return null;
        }

        return parts.get(num);
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }


}
