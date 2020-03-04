package com.hans0924.fsd;

import com.hans0924.fsd.manager.Manage;
import com.hans0924.fsd.process.PMan;
import com.hans0924.fsd.process.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 曾韩铄
 * @since 2020-03-03
 */
public class Fsd {
    private final static Logger LOGGER = LoggerFactory.getLogger(Fsd.class);

    public static ConfigManager configManager;


    private int clientPort;

    private int serverPort;

    private int systemPort;

    private PMan pManager;

    private String certFile;

    private String whazzupFile;

    private int timer;
    private int prevNotify;
    private int prevLagCheck;
    private int certFileStat;
    private int prevCertCheck;

    public Fsd(String configFile) {
        LOGGER.info("Booting Server");
        pManager = new PMan();

        /* Start the information manager */
        Manage.manager = new Manage();

        configManager = new ConfigManager(configFile);
        pManager.registerProcess(configManager);
    }
}
