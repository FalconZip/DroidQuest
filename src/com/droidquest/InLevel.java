package com.droidquest;

import com.droidquest.levels.Level;

public interface InLevel {
	default Level level() {
		return gameState().getLevel();
	}
	
	default GameState gameState() {
		return GameState.instance();
	}
}
