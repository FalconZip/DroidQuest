package com.droidquest;

import com.droidquest.levels.Level;

public interface InLevel {
	default Level level() {
		return GameState.instance().getLevel();
	}
}
