package com.droidquest.materials;

import com.droidquest.GameState;
import com.droidquest.InLevel;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.PaintBrush;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Material implements Serializable, Cloneable, InLevel {
    public transient ImageIcon icon;
    private String file;
    public boolean passable;
    boolean detectable;
    protected Color color;

    Material() {
    }

    Material(String filename, boolean p, boolean d) {
        icon = new ImageIcon(filename);
        passable = p;
        detectable = d;
    }

    public Material(boolean p, boolean d) {
        passable = p;
        detectable = d;
        color = Color.black;
    }

    public Material(Color c, boolean p, boolean d) {
        passable = p;
        detectable = d;
        color = c;
    }

    public void generateIcons() {
        if (file != null) {
            icon = new ImageIcon(file);
        }
    }

    public void draw(Graphics g, RoomDisplay rd, int x, int y) {
        if (icon == null) {
            // Blank Background
            g.setColor(color);
            g.fillRect(x * 28, y * 32, 28, 32);
        }
        else {
            // Material Background
            g.drawImage(icon.getImage(), x * 28, y * 32, rd);
        }
    }

    public void touchedByItem(Item item) {
    }

    public void animate() {
    }

    public boolean passable(Item item) {
        // The PaintBrush can pass anything
        if(item instanceof PaintBrush) {
            return true;
        }
        return passable;
    }

    public boolean detectable(Item item) {
        return detectable;
    }

    public boolean equals(Material mat) {
        return getClass() == mat.getClass()
                && color == mat.color
                && passable == mat.passable
                && detectable == mat.detectable
                && (file == null ? mat.file == null : file.equals(mat.file));
    }

    public static Material findSimiliar(Material mat1) {
    	Level level = GameState.instance().getLevel();
        for (int a = 0; a < level.materials.size(); a++) {
            Material mat2 = level.materials.get(a);
            if (mat1.equals(mat2)) {
                return mat2;
            }
        }
        level.materials.add(mat1);
        return mat1;
    }

    public Object clone() {
        Object newObject = null;
        try {
            newObject = super.clone();
        }
        catch (CloneNotSupportedException e) {
        }
        return newObject;
    }

}
