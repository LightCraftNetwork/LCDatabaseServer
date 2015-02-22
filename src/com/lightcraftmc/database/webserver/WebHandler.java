package com.lightcraftmc.database.webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.UUID;

import com.lightcraftmc.database.LCDatabaseServer;
import com.lightcraftmc.database.WebGraphicsHandler;
import com.lightcraftmc.database.command.WebCommandInterpreter;
import com.lightcraftmc.login.LoginManager;

public class WebHandler extends Thread {
    private Socket client;
    private BufferedReader inFromClient = null;
    private DataOutputStream outToClient = null;
    @SuppressWarnings("unused")
    private WebServer ws = null;
    public static ArrayList<UUID> requested = new ArrayList<UUID>();

    public WebHandler(Socket c, WebServer w) {
        this.client = c;
        this.ws = w;
    }

    @SuppressWarnings("unused")
    public void run() {
        try {
            this.inFromClient = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            this.outToClient = new DataOutputStream(this.client.getOutputStream());
            String requestString = this.inFromClient.readLine();
            String headerLine = requestString;
            StringTokenizer tokenizer = new StringTokenizer(headerLine);
            String httpMethod = tokenizer.nextToken();
            String httpQueryString = tokenizer.nextToken();
            while (this.inFromClient.ready()) {
                requestString = this.inFromClient.readLine();
                String[] v = requestString.split(": ");
            }
            if (httpMethod.equals("GET")) {
                if(httpQueryString.startsWith("/?")){
                    httpQueryString = httpQueryString.replaceFirst("/?", "/formatted:publicKey!!#");
                }
                if (httpQueryString.equalsIgnoreCase("/")) {
                    httpQueryString = "/formatted:publicKey!!#login-page";
                }
                httpQueryString = httpQueryString.substring(1);
                httpQueryString = httpQueryString.replace("?query=", "");
                httpQueryString = httpQueryString.replace("+", " ");
                httpQueryString = httpQueryString.replace("%2F", "/");
                httpQueryString = httpQueryString.replace("%40", "@");
                httpQueryString = httpQueryString.replace("%23", "#");

                boolean isFormatted = false;    
                if (httpQueryString.startsWith("formatted:")) {
                    isFormatted = true;
                    httpQueryString = httpQueryString.replaceFirst("formatted:", "");
                }
                if (!httpQueryString.contains("!!")) {
                    this.sendResponse(200, "FAILED: No access key has been specified.", false);
                    return;
                }
                String t = httpQueryString.split("!!")[0];
                String q = httpQueryString.split("!!")[1];
                if(isFormatted){
                    if(t.equals("publicKey")){
                        if(LoginManager.getInstance().isLoggedIn(this.client.getInetAddress().toString())){
                            t = LCDatabaseServer.getManager().getAccessKey();
                        }
                    }
                }
                if (!(t.equals(LCDatabaseServer.getManager().getAccessKey()) || t.equals("publicKey"))) {
                    this.sendResponse(200, "FAILED: Incorrect access key. Your IP and query has been logged. ", false);
                    // TODO log IP
                    return;
                }

                String response = WebCommandInterpreter.getInstance().interpret(q.replace("%20", " "), this.client.getInetAddress().toString(), !t.equals("publicKey"));
                if (isFormatted) {
                    response = WebGraphicsHandler.handleResponse(q.replace("%20", " "), response, this.client.getInetAddress().toString());
                }
                this.sendResponse(200, response, false);
                return;
            }
        } catch (Exception localException) {
        }
    }

    public void sendResponse(int statusCode, String responseString, boolean isFile) throws Exception {
        String statusLine = null;
        String serverdetails = "Server: Java HTTPServer";
        String contentLengthLine = null;
        String fileName = null;
        String contentTypeLine = "Content-Type: text/html\r\n";
        FileInputStream fin = null;
        statusLine = "HTTP/1.1 200 OK\r\n";
        switch (statusCode) {
        case 200:
            statusLine = "HTTP/1.1 200 OK\r\n";
            break;
        case 500:
            statusLine = "HTTP/1.1 500 Internal Server Error\r\n";
            break;
        case 222:
            statusLine = "HTTP/1.1 222 Ping Response\r\n";
            break;
        default:
            statusLine = "HTTP/1.1 404 Not Found\r\n";
        }
        if (isFile) {
            fileName = responseString;
            fin = new FileInputStream(fileName);
            contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
            if ((!fileName.endsWith(".htm")) && (!fileName.endsWith(".html"))) {
                contentTypeLine = "Content-Type: application/zip\r\n";
            }
        } else {
            contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
        }
        if (!this.client.isClosed()) {
            this.outToClient.writeBytes(statusLine);
            this.outToClient.writeBytes(serverdetails);
            this.outToClient.writeBytes(contentTypeLine);
            this.outToClient.writeBytes(contentLengthLine);
            this.outToClient.writeBytes("Connection: close\r\n");
            this.outToClient.writeBytes("\r\n");
            if (isFile) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fin.read(buffer)) != -1) {
                    // int bytesRead;
                    this.outToClient.write(buffer, 0, bytesRead);
                }
                fin.close();
            } else {
                this.outToClient.writeBytes(responseString);
            }
            this.outToClient.close();
        }
    }
}