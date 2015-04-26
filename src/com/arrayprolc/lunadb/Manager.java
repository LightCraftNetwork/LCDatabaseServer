package com.arrayprolc.lunadb;

import com.arrayprolc.lunadb.webserver.ServerHandler;
import com.arrayprolc.lunadb.webserver.WebServer;

public class Manager {

    private int port;
    private String accessKey;
    private boolean shouldEncrypt;
    private boolean serverStarted = false;
    private WebServer server;
    private ServerHandler handler;
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public boolean isShouldEncrypt() {
        return shouldEncrypt;
    }

    public void setShouldEncrypt(boolean shouldEncrypt) {
        this.shouldEncrypt = shouldEncrypt;
    }

    public boolean isServerStarted() {
        return serverStarted;
    }

    public void shutdownServer() {
        handler.disable();
    }

    public void startServer() {
        handler = new ServerHandler();
        handler.enable();
    }

    public WebServer getWebServer() {
        return server;
    }
}
