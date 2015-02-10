package com.lightcraftmc.database.command;

public abstract class Command {

	private String name;
	private String[] subNames;
	private String description = "undefined";

	public Command(String name) {
		this.name = name;
		this.subNames = new String[] {};
	}

	public abstract String runCommand(boolean isLocal, String[] args);

	public String throwError(String error) {
		System.out.println("[COMMAND ERROR] " + error);
		return "[COMMAND ERROR] " + error;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
