package com.arrayprolc.lunadb;

public class LunaStarter {

    public static LunaDB database;

    public static void main(String[] args) {
        database = new LunaDB();
        database.run(args);
    }

}
