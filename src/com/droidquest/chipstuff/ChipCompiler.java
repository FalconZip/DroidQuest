package com.droidquest.chipstuff;

import java.util.Iterator;

import com.droidquest.InLevel;
import com.droidquest.Wire;
import com.droidquest.devices.Device;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.PrototypeChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.Item;

public class ChipCompiler extends Thread implements InLevel {
    public static int chipSpeed = 1;

    public ChipCompiler(PrototypeChip pc, SmallChip sc) {
        pc.grabbable = false;
        sc.grabbable = false;

        sc.Empty();

        for (int a = 0; a < pc.InternalRoom.wires.size(); a++) {
            sc.signals.add(new Signal());
        }

        Signal dummy = new Signal();
        dummy.working = false;
        sc.signals.add(dummy);

        for (int a = 0; a < 8; a++) {
            Wire wire = pc.portdevices[a].ports[0].myWire;
            int index = pc.InternalRoom.wires.indexOf(wire);
            if (index >= 0) {
                sc.portSignals[a].internalSignal = sc.signals.get(index);
            }
            sc.ports[a].type = pc.ports[a].type;
            sc.portSignals[a].type = pc.ports[a].type;
        }

        for (Item item: level().items) {
            if (item.room == pc.InternalRoom) {
                if (item.isDevice()) {
                    Device device = (Device) item;
                    Gate gate = null;

                    if (device instanceof com.droidquest.devices.ANDGate) {
                        gate = new Gate("AND");
                    }
                    if (device instanceof com.droidquest.devices.ORGate) {
                        gate = new Gate("OR");
                    }
                    if (device instanceof com.droidquest.devices.NOTGate) {
                        gate = new Gate("NOT");
                    }
                    if (device instanceof com.droidquest.devices.XORGate) {
                        gate = new Gate("XOR");
                    }
                    if (device instanceof com.droidquest.devices.FlipFlop) {
                        gate = new Gate("FF");
                        gate.state = ((FlipFlop) device).state;
                    }
                    if (device instanceof com.droidquest.devices.Node) {
                        gate = new Gate("NODE");
                    }
                    if (device instanceof com.droidquest.devices.SmallChip) {
                        gate = new Gate((SmallChip) device);
                    }
                    if (gate != null) {
                        sc.gates.add(gate);
                        for (int p = 0; p < device.ports.length; p++) {
                            if (device.ports[p].myWire != null) {
                                int index = pc.InternalRoom.wires.indexOf(device.ports[p].myWire);
                                gate.portSignals[p].externalSignal = sc.signals.get(index);
                            }
                            else {
                                gate.portSignals[p].externalSignal = dummy;
                            }
                        }
                    }
                }
            }
        }

        // Remove Node Gates, and transfer Signals
        for (Iterator<Gate> it = sc.gates.iterator(); it.hasNext();) //For every Gate in the chip
        {
            Gate gate1 = it.next();
            if (gate1.type.equals("NODE")) {
                for (int ap = 1; ap < 4; ap++) // For every output Signal in the Node
                {
                    Signal s1 = gate1.portSignals[ap].externalSignal;
                    if (s1 != null && s1 != dummy) {
                        for (int b = 0; b < sc.gates.size(); b++) // For every other Gate in the Chip
                        {
                            Gate gate2 = sc.gates.get(b);
                            if (gate1 != gate2) {
                                for (int bp = 0; bp < 8; bp++) // For every Signal connection in that other gate
                                {
                                    Signal s2 = gate2.portSignals[bp].externalSignal;
                                    if (s1 == s2) // If  Signal is an output Node signal
                                    {
                                        gate2.portSignals[bp].externalSignal
                                                = gate1.portSignals[0].externalSignal;
                                    }
                                }
                            }
                        }
                        for (int ps = 0; ps < 8; ps++) {
                            if (sc.portSignals[ps].internalSignal == s1) {
                                sc.portSignals[ps].internalSignal
                                        = gate1.portSignals[0].externalSignal;
                            }
                        }
                    }
                }
                it.remove();;
            }
        }

        // Remove unused Signals
        for (Iterator<Signal> it = sc.signals.iterator(); it.hasNext();) {
            boolean used = false;
            Signal sig1 = it.next();
            for (int g = 0; g < sc.gates.size(); g++) {
                Gate gate = sc.gates.get(g);
                for (int s = 0; s < 8; s++) {
                    Signal sig2 = gate.portSignals[s].externalSignal;
                    if (sig2 != null)
                    {
                        if (sig1 == sig2) {
                            used = true;
                        }
                    }
                }
            }
            for (int ps = 0; ps < 8; ps++) {
                if (sc.portSignals[ps].internalSignal == sig1) {
                    used = true;
                }
            }
            if (!used) {
                it.remove();
            }
        }

        // Set Signal types
        for (int a = 0; a < 8; a++) {
            if (sc.portSignals[a] != null) {
                sc.portSignals[a].type = sc.ports[a].type;
            }
        }

        // Debug report
        System.out.println(sc.signals.size() + " Signals");
        System.out.println(sc.gates.size() + " Gates");
        for (int a = 0; a < sc.gates.size(); a++) {
            Gate gate1 = sc.gates.get(a);
            for (int b = 0; b < 8; b++) {
                if (gate1.portSignals[b].externalSignal != null) {
                    System.out.println(a + ": " + gate1.type
                            + " gate["
                            + b
                            + "] = Signal "
                            + sc.signals.indexOf(gate1.portSignals[b].externalSignal));
                }
            }
        }
        for (int a = 0; a < 8; a++) {
            if (sc.portSignals[a].internalSignal != null) {
                System.out.println("PortSignal "
                        + a
                        + " = Signal "
                        + sc.signals.indexOf(sc.portSignals[a].internalSignal));
            }
        }

        sc.speed = chipSpeed;
        if (pc.label != null) {
            sc.label = pc.label;
        }
        if (pc.description != null) {
            sc.description = pc.description;
        }
        sc.generateIcons();
        pc.grabbable = true;
        sc.grabbable = true;
    }

}
