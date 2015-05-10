package com.arrayprolc.lunadb.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Justin
 * @info This class is to be implemented in your own programs. You can also grab
 *       the lunadb complete binary or the API binary on GitHub. Enjoy! <3
 *       http://www.github.com/lunadb
 *
 */
public class LunaServer {

    private String ip;
    private String accessKey;
    public String programName = "java_api";

    public LunaServer(String ip, String accessKey) {
        super();
        this.ip = ip;
        this.accessKey = accessKey;
    }

    public LunaServer(String ip) {
        super();
        this.ip = ip;
        this.accessKey = "publicKey";
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @SuppressWarnings("deprecation")
    public String query(String inquery) throws MalformedURLException, IOException {
        inquery = applyVariables(inquery);
        inquery = Base64.encodeBase64String(inquery.getBytes());
        String query = "execas " + programName.replace(",", "").replace(" ", "") + ",@icodec=base64,@rcodec=base64 " + inquery;
        query = URLEncoder.encode(query);
        URL url = new URL("http://" + getIP() + "/" + getAccessKey() + "!!" + query.replace(" ", "%20"));
        URLConnection c = url.openConnection();

        c.setConnectTimeout(500);
        c.setReadTimeout(100);

        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));

        String s = "";
        s = in.readLine();

        in.close();
        
        s = new String(Base64.decodeBase64(s), "UTF-8");

        return s;
    }

    private String applyVariables(String query) {
        // TODO add local variables.
        return query;
    }

}
