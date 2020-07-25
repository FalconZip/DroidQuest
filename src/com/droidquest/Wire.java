package com.droidquest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Device;
import com.droidquest.levels.Level;
import com.droidquest.sound.Sounds;

public class Wire implements Serializable, InLevel {
    public transient Port fromPort; // Connected First
    public transient Port toPort;   // Connected 2nd
    public transient Port inPort;   // Connected to Input
    public transient Port outPort;  // Connected to Output (Source of Value)
    public boolean value;

    public Wire() {
    }

    public Wire(Port from, Port to) {
        if (from.myDevice != null) {
            if (from.myDevice.room != null) {
                if (from.myDevice.room.wires == null) {
                    System.out.println("f.myDevice.room.wires is null");
                }
            }
            else {
                System.out.println("f.myDevice.room is null");
            }
        }
        else {
            System.out.println("f.myDevice is null");
        }

        from.myDevice.room.wires.add(this);
        from.myDevice.room.playSound(Sounds.ATTACH);

        if (from.type == Port.TYPE_INPUT) {
            if (to.type == Port.TYPE_INPUT) {
                Remove();
                return;
            }
            if (to.type == Port.TYPE_OUTPUT) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
                inPort = fromPort;
                outPort = toPort;
                return;
            }
            if (to.type == Port.TYPE_UNDEFINED) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
                inPort = fromPort;
                outPort = toPort;
                to.type = Port.TYPE_OUTPUT;
                return;
            }
        }
        if (from.type == Port.TYPE_OUTPUT) {
            if (to.type == Port.TYPE_INPUT) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
                outPort = fromPort;
                inPort = toPort;
                return;
            }
            if (to.type == Port.TYPE_OUTPUT) {
                Remove();
                return;
            }
            if (to.type == Port.TYPE_UNDEFINED) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
                outPort = fromPort;
                inPort = toPort;
                to.type = Port.TYPE_INPUT;
                return;
            }
        }
        if (from.type == Port.TYPE_UNDEFINED) {
            if (to.type == Port.TYPE_INPUT) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
                outPort = fromPort;
                inPort = toPort;
                from.type = Port.TYPE_OUTPUT;
                return;
            }
            if (to.type == Port.TYPE_OUTPUT) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
                inPort = fromPort;
                outPort = toPort;
                from.type = Port.TYPE_INPUT;
                return;
            }
            if (to.type == Port.TYPE_UNDEFINED) {
                fromPort = from;
                toPort = to;
                from.myWire = this;
                to.myWire = this;
            }
        }
    }

    void writeRef(ObjectOutputStream s) throws IOException {
        int a;
        Level level = level();
        s.writeInt(level.items.indexOf(fromPort.myDevice)); // Index of fromport device
        a = 0;
        while (((Device) fromPort.myDevice).ports[a] != fromPort) {
            a++;
        }
        s.writeInt(a); // Index of fromport (as device.ports[?]

        s.writeInt(level.items.indexOf(toPort.myDevice)); // Index of toPort device
        a = 0;
        while (((Device) toPort.myDevice).ports[a] != toPort) {
            a++;
        }
        s.writeInt(a); // Index of toPort (as device.ports[?]

        s.writeInt(level.items.indexOf(inPort.myDevice)); // Index of inPort device
        a = 0;
        while (((Device) inPort.myDevice).ports[a] != inPort) {
            a++;
        }
        s.writeInt(a); // Index of inPort (as device.ports[?]

        s.writeInt(level.items.indexOf(outPort.myDevice)); // Index of outPort device
        a = 0;
        while (((Device) outPort.myDevice).ports[a] != outPort) {
            a++;
        }
        s.writeInt(a); // Index of outPort (as device.ports[?]
    }

    void readRef(ObjectInputStream s, Level level) throws IOException {
        Device tempDevice;
        tempDevice = (Device) level.FindItem(s.readInt());
        fromPort = tempDevice.ports[s.readInt()];
        tempDevice = (Device) level.FindItem(s.readInt());
        toPort = tempDevice.ports[s.readInt()];
        tempDevice = (Device) level.FindItem(s.readInt());
        inPort = tempDevice.ports[s.readInt()];
        tempDevice = (Device) level.FindItem(s.readInt());
        outPort = tempDevice.ports[s.readInt()];
    }

    public void ConnectTo(Port t) {
        fromPort.myDevice.room.playSound(Sounds.DETATCH);

        if (toPort.myDevice == level().solderingPen) {
            toPort.value = false;
            toPort.type = Port.TYPE_UNDEFINED;
            toPort.myWire = null;

            if (fromPort.type == Port.TYPE_INPUT) {
                if (t.type == Port.TYPE_INPUT) {
                    Remove();
                    return;
                }
                if (t.type == Port.TYPE_OUTPUT) {
                    toPort = t;
                    t.myWire = this;
                    inPort = fromPort;
                    outPort = toPort;
                    return;
                }
                if (t.type == Port.TYPE_UNDEFINED) {
                    toPort = t;
                    t.myWire = this;
                    inPort = fromPort;
                    outPort = toPort;
                    t.type = Port.TYPE_OUTPUT;
                    return;
                }
            }
            if (fromPort.type == Port.TYPE_OUTPUT) {
                if (t.type == Port.TYPE_INPUT) {
                    toPort = t;
                    t.myWire = this;
                    outPort = fromPort;
                    inPort = toPort;
                    return;
                }
                if (t.type == Port.TYPE_OUTPUT) {
                    Remove();
                    return;
                }
                if (t.type == Port.TYPE_UNDEFINED) {
                    toPort = t;
                    t.myWire = this;
                    outPort = fromPort;
                    inPort = toPort;
                    t.type = Port.TYPE_INPUT;
                    return;
                }
            }
            if (fromPort.type == Port.TYPE_UNDEFINED) {
                if (t.type == Port.TYPE_INPUT) {
                    toPort = t;
                    t.myWire = this;
                    outPort = fromPort;
                    inPort = toPort;
                    fromPort.type = Port.TYPE_OUTPUT;
                    return;
                }
                if (t.type == Port.TYPE_OUTPUT) {
                    toPort = t;
                    t.myWire = this;
                    inPort = fromPort;
                    outPort = toPort;
                    fromPort.type = Port.TYPE_INPUT;
                    return;
                }
                if (t.type == Port.TYPE_UNDEFINED) {
                    toPort = t;
                    t.myWire = this;
                }
            }
        }
        else {
            fromPort.value = false;
            fromPort.type = Port.TYPE_UNDEFINED;
            fromPort.myWire = null;

            if (toPort.type == Port.TYPE_INPUT) {
                if (t.type == Port.TYPE_INPUT) {
                    Remove();
                    return;
                }
                if (t.type == Port.TYPE_OUTPUT) {
                    fromPort = t;
                    t.myWire = this;
                    inPort = toPort;
                    outPort = fromPort;
                    return;
                }
                if (t.type == Port.TYPE_UNDEFINED) {
                    fromPort = t;
                    t.myWire = this;
                    inPort = toPort;
                    outPort = fromPort;
                    t.type = Port.TYPE_OUTPUT;
                    return;
                }
            }
            if (toPort.type == Port.TYPE_OUTPUT) {
                if (t.type == Port.TYPE_INPUT) {
                    fromPort = t;
                    t.myWire = this;
                    outPort = toPort;
                    inPort = fromPort;
                    return;
                }
                if (t.type == Port.TYPE_OUTPUT) {
                    Remove();
                    return;
                }
                if (t.type == Port.TYPE_UNDEFINED) {
                    fromPort = t;
                    t.myWire = this;
                    outPort = toPort;
                    inPort = fromPort;
                    t.type = Port.TYPE_INPUT;
                    return;
                }
            }
            if (toPort.type == Port.TYPE_UNDEFINED) {
                if (t.type == Port.TYPE_INPUT) {
                    fromPort = t;
                    t.myWire = this;
                    outPort = toPort;
                    inPort = fromPort;
                    toPort.type = Port.TYPE_OUTPUT;
                    return;
                }
                if (t.type == Port.TYPE_OUTPUT) {
                    fromPort = t;
                    t.myWire = this;
                    inPort = toPort;
                    outPort = fromPort;
                    toPort.type = Port.TYPE_INPUT;
                    return;
                }
                if (t.type == Port.TYPE_UNDEFINED) {
                    fromPort = t;
                    t.myWire = this;
                }
            }
        }

    }

    public void Remove() {
        Room room = fromPort.myDevice.room;

        room.playSound(Sounds.DETATCH);

        fromPort.myWire = null;
        toPort.myWire = null;
        fromPort = null;
        toPort = null;
        inPort = null;
        outPort = null;
        room.wires.remove(this);

    }

    public void Draw(Graphics g) {
        g.setColor(Color.white);
        value = false;
        if (fromPort.type == Port.TYPE_OUTPUT && fromPort.value) {
            g.setColor(new Color(255, 128, 0));
            value = true;
        }
        if (toPort.type == Port.TYPE_OUTPUT && toPort.value) {
            g.setColor(new Color(255, 128, 0));
            value = true;
        }

        Dimension d1, d2;
        int x1, y1, x2, y2;
        d1 = fromPort.myDevice.GetXY();
        d2 = toPort.myDevice.GetXY();
        x1 = d1.width + fromPort.x;
        y1 = d1.height + fromPort.y;
        x2 = d2.width + toPort.x;
        y2 = d2.height + toPort.y;
        switch ((((Device) fromPort.myDevice).rotation + fromPort.rotation) % 4) {
            case 0: // Up
                x1 += 1;
                y1 += 1;
                break;
            case 1: // Right
                x1 -= 2;
                y1 += 1;
                break;
            case 2: // Down
                x1 -= 2;
                y1 -= 2;
                break;
            case 3: // Left
                x1 += 1;
                y1 -= 2;
                break;
        }
        switch ((((Device) toPort.myDevice).rotation + toPort.rotation) % 4) {
            case 0: // Up
                x2 += 1;
                y2 += 1;
                break;
            case 1: // Right
                x2 -= 2;
                y2 += 1;
                break;
            case 2: // Down
                x2 -= 2;
                y2 -= 2;
                break;
            case 3: // Left
                x2 += 1;
                y2 -= 2;
                break;
        }

        g.fillRect(Math.min(x1, x2), y1, Math.abs(x1 - x2), 2);
        g.fillRect(x2, Math.min(y1, y2), 2, Math.abs(y1 - y2));
        g.fillRect(x1, y1, 2, 2);
        g.fillRect(x2, y2, 2, 2);
        g.fillRect(x2, y1, 2, 2);

    }

    public Port otherPort(Port p) {
        if (fromPort == p) {
            return toPort;
        }
        if (toPort == p) {
            return fromPort;
        }
        return null;
    }

}