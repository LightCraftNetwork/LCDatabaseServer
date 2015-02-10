package com.lightcraftmc.database;

import com.lightcraftmc.database.webserver.ServerHandler;
import com.lightcraftmc.database.webserver.WebServer;

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

	}

	public void startServer() {
		handler = new ServerHandler();
		handler.enable();
	}
}
