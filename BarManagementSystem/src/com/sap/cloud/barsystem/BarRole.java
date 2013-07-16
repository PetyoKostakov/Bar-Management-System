package com.sap.cloud.barsystem;

public enum BarRole {
	BARMAN(1),
	WAITER(2),
	MANAGER(3);
	
	private	 int value;
	
	private BarRole(int c) {
		this.value = c;
	}
}
