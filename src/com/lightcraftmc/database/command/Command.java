package com.lightcraftmc.database.command;

public abstract class Command {

	private String name;
	private String[] subNames;
	private String sendBack = "";

	public Command(String name) {
		this.name = name;
		this.subNames = new String[] {};
	}

	public abstract String runCommand(boolean isLocal, String[] args);

	public void throwError(String error) {
		System.out.println("[COMMAND ERROR] " + error);
		return;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSubNames() {
		return subNames;
	}

	public void setSubNames(String[] subNames) {
		this.subNames = subNames;
	}

	public void sendBack(String s) {
		sendBack = s;
	}

	public String getSendBack() {
		String sb = sendBack;
		sendBack = "";
		return sb;
	}

}
