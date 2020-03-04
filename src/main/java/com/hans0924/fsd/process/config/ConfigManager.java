package com.hans0924.fsd.process.config;

import com.hans0924.fsd.constants.ManageVarType;
import com.hans0924.fsd.manager.Manage;
import com.hans0924.fsd.process.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 曾韩铄
 * @since 2020-03-03
 */
public class ConfigManager extends Process {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManager.class);

    private static final long CONFIG_INTERVAL = 10_000L;
    private String fileName;

    private List<ConfigGroup> groups;

    private int nGroups;

    private int varAccess;

    private boolean changed;

    private long prevCheck;

    private long lastModify;

    public ConfigManager(String name) {
        super();
        fileName = name;
        groups = new ArrayList<>();
        nGroups = 0;
        changed = true;
        int fname = Manage.manager.addVar("config.filename", ManageVarType.ATT_VARCHAR);
        Manage.manager.setVar(fname, name);
        varAccess = Manage.manager.addVar("config.lastread", ManageVarType.ATT_DATE);
        parseFile();
    }

    public ConfigGroup getGroup(String name) {
        for (ConfigGroup group : groups) {
            if (Objects.equals(group.getName(), name)) {
                return group;
            }
        }

        return null;
    }

    public ConfigGroup createGroup(String name) {
        ConfigGroup group = new ConfigGroup(name);
        groups.add(group);
        return group;
    }

    @Override
    public boolean run() {
        long now = System.currentTimeMillis();
        if (now - prevCheck < CONFIG_INTERVAL) {
            return false;
        }
        prevCheck = now;
        Path file = Path.of(fileName);
        if (Files.notExists(file)) {
            return false;
        }
        try {
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            long fileLastModified = attr.lastModifiedTime().toMillis();
            if (fileLastModified == lastModify) {
                return false;
            }
            lastModify = fileLastModified;
            parseFile();
            return true;
        } catch (IOException e) {

            return false;
        }
    }

    public void parseFile() {
        File file = new File(fileName);
        if (!file.isFile() || !file.exists()) {
            return;
        }
        Manage.manager.setVar(varAccess, System.currentTimeMillis());
        ConfigGroup current = null;
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file));
             BufferedReader bufferedReader = new BufferedReader(read)) {
        String line;

        while ((line = bufferedReader.readLine()) != null)
        {
            if (line.charAt(0) == '#' || line.charAt(0) == '\r' || line.charAt(0) == '\n') {
                continue;
            }

            if (line.charAt(0) == '[') {
                String entry = line.substring(1, line.length() - 1);
                current = getGroup(entry);
                if (current == null) {
                    current = createGroup(entry);
                }
                continue;
            }

            if (current == null) {
                continue;
            }

            String[] split = line.split("=");
            if (split.length > 1) {
                current.handleEntry(split[0], split[1]);
            }
            if (current.isChanged()) {
                changed = true;
            }
        }
        } catch (FileNotFoundException e) {
            LOGGER.error("Config file not found: " + fileName);
        } catch (IOException e) {
            LOGGER.error("Something went wrong when parse config file: ", e);
        }
        prevCheck = System.currentTimeMillis();
    }
}
