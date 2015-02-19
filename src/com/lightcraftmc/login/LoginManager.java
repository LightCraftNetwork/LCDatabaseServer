package com.lightcraftmc.login;

import java.util.HashMap;

public class LoginManager {

    private final HashMap<String, Long> logins = new HashMap<String, Long>();
    private static LoginManager instance;
    public static long totalLoginTime = (10*60*1000);

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null)
            instance = new LoginManager();
        return instance;
    }

    /**
     * @return true if failed
     */
    public boolean addLogin(String address) {
        updateLogins();
        return addLogin(address, System.currentTimeMillis());
    }

    /**
     * @return true if failed
     */
    public boolean addLogin(String address, long timeMillis) {
        if (logins.containsKey(address)) {
            return true;
        }
        logins.put(address, timeMillis);
        updateLogins();
        return false;
    }

    public void updateLogins() {
        for (String s : logins.keySet()) {
            long l = Long.valueOf(logins.get(s));
            if ((System.currentTimeMillis() - l) > totalLoginTime) {
                logins.remove(s);
            }
        }
    }

    public boolean isLoggedIn(String address) {
        updateLogins();
        return logins.containsKey(address);
    }

    /**
     * @return true if success
     */
    public boolean logOut(String address) {
        updateLogins();
        if (!logins.containsKey(address)) {
            return false;
        }
        logins.remove(address);
        return true;
    }

}
