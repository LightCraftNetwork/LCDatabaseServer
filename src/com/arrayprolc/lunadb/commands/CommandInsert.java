package com.arrayprolc.lunadb.commands;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.util.UtilFile;
import com.arrayprolc.lunadb.util.UtilQuery;

/**
 * 
 * @author Justin
 *
 */
public class CommandInsert extends Command {

    public CommandInsert() {
        super("insert");
        setDescription("Set a specific data value into the database!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (args.length == 0) {
            return ("Usage: insert category key value (Please use _ in place of spaces.)");
        }
        try {
            final String category = args[0].toLowerCase();
            final String key = args[1];
            String value = args[2];
            if (value.startsWith("@api?")) {
                value = value.substring(5);
                final String v = value;
                Thread th = new Thread() {
                    public void run() {
                        String insert = "0";
                        try {
                            String url = new CommandRetrieve().runCommand("127.0.0.1", true, new String[] { "@api", v.split("!!")[0] }, true);
                            for (int i = 0; i < 50; i++) {
                                if (url.contains("$" + i) && v.contains("$" + i)) {
                                    for (String s : v.split("!!")[1].split(",,")) {
                                        String repl = s.split("=")[0];
                                        String name = s.split("=")[1];
                                        url = url.replace(repl, name);
                                    }
                                }
                            }
                            String s = UtilQuery.query(url);
                            insert = s;

                            UtilFile.save(category, key, insert);

                        } catch (Exception ex) {
                            UtilFile.save(category, key, "0");
                            ex.printStackTrace();
                        }
                    }
                };
                th.start();
                return "SUCCESS: Queueing insertion of API-found " + value + " into " + category + " with HTTP/API string " + key;
            } else {
                UtilFile.save(category, key, value);
                return ("SUCCESS: Inserting " + value + " into " + category + " with key " + key);
            }
        } catch (Exception ex) {
            return "FAILED: An error occured, please try again. || Usage: insert category key value (Please use _ in place of spaces.)";
        }
    }

}
