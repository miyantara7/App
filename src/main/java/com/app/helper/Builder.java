package com.app.helper;

public class Builder {

	public static String build(String...strings) {
		StringBuilder sbResponseMessage = new StringBuilder();
		for (String string : strings) {
			sbResponseMessage.append(string);
		}
		return sbResponseMessage.toString();
	}
}
