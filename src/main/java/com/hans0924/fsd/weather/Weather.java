package com.hans0924.fsd.weather;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanshuo Zeng
 * @since 2020-03-04
 */
public class Weather {
    public static List<WProfile> wProfiles = new ArrayList<>();

    public static WProfile getWProfile(String name) {
        for (WProfile wProfile : wProfiles) {
            if (wProfile.getName().equals(name)) {
                return wProfile;
            }
        }

        return null;
    }
}
