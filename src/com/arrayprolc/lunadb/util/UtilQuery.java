package com.arrayprolc.lunadb.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UtilQuery {

    public static String query(String url2) {
        try {
            URL url = new URL(url2);
            URLConnection c = url.openConnection();

            c.setConnectTimeout(500);
            c.setReadTimeout(700);

            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));

            String s = "";
            s = in.readLine();

            in.close();

            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FAILED: No response was sent.";
        }

    }

}
