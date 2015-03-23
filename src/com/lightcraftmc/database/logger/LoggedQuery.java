package com.lightcraftmc.database.logger;

import java.net.InetAddress;

public class LoggedQuery {

    InetAddress address;
    String query;
    String method;
    int responseCode;
    String response;
    boolean isFile;

    public LoggedQuery(InetAddress address, String query, String method) {
        super();
        this.address = address;
        this.query = query;
        this.method = method;
    }
    

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }

}
