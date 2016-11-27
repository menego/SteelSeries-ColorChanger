/**
 * 11/26/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: Utility.java
 * Version: 1.0
 */
package com.utilities;

public class Utility {

    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * This method che if the code is running on a Windows operative system.
     *
     * @return whether the code is running on a Windows OS or not.
     */
    public static boolean isWindows() {

        return OS.contains("win");

    }

    /**
     * This method che if the code is running on a Mac operative system.
     *
     * @return whether the code is running on a Mac OS or not.
     */
    public static boolean isMac() {

        return OS.contains("mac");

    }

    /**
     * This method che if the code is running on a Linux/Unix operative system.
     *
     * @return whether the code is running on a Linux OS or not.
     */
    public static boolean isUnix() {

        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));

    }


}
