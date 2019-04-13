package com.droidquest;

import java.util.Observable;

import com.droidquest.levels.Level;

public class GameState extends Observable{
	private static final GameState _instance = new GameState();
	
	private boolean canFlipDevice;
	private boolean canChangeHotCursor;
	private boolean usingHotCursor;
	private boolean canLoadChip;
	private boolean canUsePaintBrush;
	private boolean usingPaintBrush;
	private boolean canSwitchRemote;
	private boolean usingRemote;
	private boolean canRotate;
	private boolean canSwitchCursor;
	private boolean usingCursor;
	private boolean canUseSolderPen;
	private boolean usingSolderPen;
	private boolean canUseToolbox;
	private boolean isInLab;
	
	private volatile Level level;
	
	public static GameState instance() {
		return _instance;
	}
	
	private GameState() {
		reset();
	}
	
	public void reset() {
		canFlipDevice = false;
		canChangeHotCursor = false;
		usingHotCursor = false;
		canLoadChip = false;
		canUsePaintBrush = false;
		usingPaintBrush = false;
		canSwitchRemote = false;
		usingRemote = false;
		canRotate = false;
		canSwitchCursor = false;
		usingCursor = true;
		canUseSolderPen = false;
		usingSolderPen = false;
		canUseToolbox = false;
		isInLab = false;
		
		publish();
	}

	
	private void publish() {
		setChanged();
		notifyObservers();
	}
	
	public boolean canFlipDevice() {
		return canFlipDevice;
	}
	
	public void setCanFlipDevice(boolean canFlipDevice) {
		this.canFlipDevice = canFlipDevice;
		publish();
	}
	
	public boolean changeHotCursor() {
		return canChangeHotCursor;
	}
	
	public void setCanChangeHotCursor(boolean canChangeHotCursor) {
		this.canChangeHotCursor = canChangeHotCursor;
		publish();
	}
	
	public boolean isUsingHotCursor() {
		return usingHotCursor;
	}
	
	public void setUsingHotCursor(boolean usingHotCursor) {
		this.usingHotCursor = usingHotCursor;
		publish();
	}
	
	public boolean canLoadChip() {
		return canLoadChip;
	}
	
	public void setCanLoadChip(boolean canLoadChip) {
		this.canLoadChip = canLoadChip;
		publish();
	}
	
	public boolean canUsePaintBrunsh() {
		return canUsePaintBrush;
	}
	
	public void setCanUsePaintBrunsh(boolean canUsePaintBrush) {
		this.canUsePaintBrush = canUsePaintBrush;
		this.canFlipDevice = false;
		publish();
	}
	
	public boolean isUsingPaintBrush() {
		return usingPaintBrush;
	}

	public void usePaintBrush() {
		this.usingPaintBrush = true;
		this.canUseToolbox = false;
		this.canChangeHotCursor = false;
		publish();
	}

	public boolean canSwitchRemote() {
		return canSwitchRemote;
	}
	
	public void setCanUseRemote(boolean canSwitchRemote) {
		this.canSwitchRemote = canSwitchRemote;
		publish();
	}
	
	public boolean isUsingRemote() {
		return usingRemote;
	}
	
	public void setUsingRemote(boolean usingRemote) {
		this.usingRemote = usingRemote;
		publish();
	}
	
	public boolean canRotate() {
		return canRotate;
	}
	
	public void setCanRotate(boolean canRotate) {
		this.canRotate = canRotate;
		publish();
	}
	
	public boolean canUseSolderPen() {
		return canUseSolderPen;
	}
	
	public void setCanUseSolderPen(boolean canUseSolderPen) {
		this.canUseSolderPen = canUseSolderPen;
		publish();
	}
	
	public boolean canUseToolbox() {
		return canUseToolbox;
	}
	
	public void setCanUseToolbox(boolean canUseToolbox) {
		this.canUseToolbox = canUseToolbox;
		publish();
	}

	public boolean canSwitchCursor() {
		return canSwitchCursor;
	}

	public void setCanSwitchCursor(boolean canSwitchCursor) {
		this.canSwitchCursor = canSwitchCursor;
		publish();
	}

	public boolean isUsingCursor() {
		return usingCursor;
	}

	public void useCursor() {
		this.usingCursor = true;
		this.usingSolderPen = false;
		this.canUseToolbox = true;
		this.canChangeHotCursor = isInLab;
		publish();
	}

	public boolean isUsingSolderPen() {
		return usingSolderPen;
	}

	public void useSolderPen() {
		this.usingSolderPen = true;
		this.usingCursor = false;
		this.canUseToolbox = false;
		this.canChangeHotCursor = false;
		this.canFlipDevice = true;
		publish();
	}

	public boolean isInLab() {
		return isInLab;
	}

	public void setInLab(boolean isinLab) {
		this.isInLab = isinLab;
		this.canChangeHotCursor = isInLab;
		this.usingHotCursor = false;
		publish();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		System.out.println("Set level " + level);
		if(this.level == level) {
			return;
		}
		if(this.level != null) {
			this.level.Empty();
		}
		this.level = level;
	}
	
	
}
