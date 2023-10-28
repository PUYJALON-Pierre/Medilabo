package com.medilabo.clientui.constant;

public enum RiskLevel {
	
	NONE("None"), BORDERLINE("Borderline"), IN_DANGER("In Danger"), EARLY_ONSET("Early onset");

	private final String level;

	public String getValue() {
		return level;
	}

	RiskLevel(String level) {
		this.level = level;
	}
}
