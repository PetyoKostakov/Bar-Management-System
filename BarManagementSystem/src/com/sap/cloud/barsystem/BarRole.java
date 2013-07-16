package com.sap.cloud.barsystem;

public enum BarRole {
	BARMAN(1),
	WAITER(2),
	MANAGER(3);
	
	private final int value;
	
	private BarRole(int c) {
		this.value = c;
	}

	public int getValue() {
		return value;
	}
	
	public static BarRole fromValue(int value) {
		if (value == 1) return BARMAN;
		if (value == 2) return WAITER;
		if (value == 3) return MANAGER;
		throw new IllegalArgumentException();
	}
}
