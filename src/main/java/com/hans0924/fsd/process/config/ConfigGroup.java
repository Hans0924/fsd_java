package com.hans0924.fsd.process.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Hanshuo Zeng
 * @since 2020-03-04
 */
public class ConfigGroup {

    private String name;

    private List<ConfigEntry> entries;

    private int nEntries;

    private boolean changed;

    public ConfigGroup(String name) {
        this.name = name;
        entries = new ArrayList<>();
        nEntries = 0;
        changed = true;
    }

    public ConfigEntry getEntry(String name) {
        for (ConfigEntry entry : entries) {
            if (Objects.equals(entry.getVar(), name)) {
                return entry;
            }
        }

        return null;
    }

    public ConfigEntry createEntry(String var, String data) {
        ConfigEntry configEntry = new ConfigEntry(var, data);
        entries.add(configEntry);
        return configEntry;
    }

    public void handleEntry(String var, String data) {
        ConfigEntry entry = getEntry(var);
        if (entry == null) {
            createEntry(var, data);
            changed = true;
            return;
        }

        if (Objects.equals(entry.getData(), data)) {
            return;
        }

        entry.setData(data);
        changed = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
