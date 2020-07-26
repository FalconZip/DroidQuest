package com.droidquest.items;

import java.util.ArrayList;
import java.util.List;

import com.droidquest.Wire;
import com.droidquest.chipstuff.Gate;
import com.droidquest.chipstuff.Port;
import com.droidquest.chipstuff.Signal;
import com.droidquest.devices.ANDGate;
import com.droidquest.devices.Device;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.NOTGate;
import com.droidquest.devices.Node;
import com.droidquest.devices.ORGate;
import com.droidquest.devices.PortDevice;
import com.droidquest.devices.PrototypeChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.devices.XORGate;

class ChipDecompiler extends Thread {
    public ChipDecompiler(PrototypeChip pc, SmallChip sc) {
        List<Device> deviceList = new ArrayList<Device>();
        pc.grabbable = false;
        sc.grabbable = false;

        // Remove all wires and devices from Prototype Chip, expect for PortDevices
        for (int a = pc.InternalRoom.wires.size() - 1; a >= 0; a--) {
            Wire wire = pc.InternalRoom.wires.get(a);
            wire.remove();
        }

        for (int a = 0; a < 8; a++) {
            pc.portdevices[a].ports[0].type = Port.TYPE_UNDEFINED;
            pc.ports[a].type = Port.TYPE_UNDEFINED;
            pc.ports[a].value = false;
        }

        pc.level().items.removeIf(item -> 
		        item.room == pc.InternalRoom
		        && item instanceof Device
		        && !(item instanceof PortDevice)
        		);

        // Set the PortDevice types to match the SC PortSignal types
        for (int a = 0; a < 8; a++) {
            if (sc.portSignals[a].type != Port.TYPE_UNDEFINED) {
                pc.portdevices[a].ports[0].type = 1 - sc.portSignals[a].type;
            }
            else {
                pc.portdevices[a].ports[0].type = Port.TYPE_UNDEFINED;
            }
        }

        // Add a corresponding Device for every Gate in the SmallChip
        for (int a = 0; a < sc.gates.size(); a++) {
            Gate gate = sc.gates.get(a);
            if (gate.type.equalsIgnoreCase("AND")) {
                ANDGate andGate = new ANDGate(10 * 28, 6 * 32, pc.InternalRoom);
                deviceList.add(andGate);
                pc.level().items.add(andGate);
            }
            if (gate.type.equalsIgnoreCase("OR")) {
                ORGate orGate = new ORGate(10 * 28, 6 * 32, pc.InternalRoom);
                deviceList.add(orGate);
                pc.level().items.add(orGate);
            }
            if (gate.type.equalsIgnoreCase("NOT")) {
                NOTGate notGate = new NOTGate(10 * 28, 6 * 32, pc.InternalRoom);
                deviceList.add(notGate);
                pc.level().items.add(notGate);
            }
            if (gate.type.equalsIgnoreCase("XOR")) {
                XORGate xorGate = new XORGate(10 * 28, 6 * 32, pc.InternalRoom);
                deviceList.add(xorGate);
                pc.level().items.add(xorGate);
            }
            if (gate.type.equalsIgnoreCase("FF")) {
                FlipFlop flipflop = new FlipFlop(10 * 28, 6 * 32, pc.InternalRoom);
                deviceList.add(flipflop);
                pc.level().items.add(flipflop);
                flipflop.state = gate.state;
            }
            if (gate.type.equalsIgnoreCase("Chip")) {
                SmallChip smallchip = new SmallChip(10 * 28, 6 * 32, pc.InternalRoom, "X");
                deviceList.add(smallchip);
                pc.level().items.add(smallchip);
                smallchip.speed = gate.speed;

                for (int b = 0; b < gate.mySignals.size(); b++) {
                    Signal newsig = new Signal();
                    Signal oldsig = gate.mySignals.get(b);
                    newsig.Set(oldsig.Get());
                    newsig.working = oldsig.working;
                    smallchip.signals.add(newsig);
                }
                for (int b = 0; b < gate.myGates.size(); b++) {
                    Gate oldgate = gate.myGates.get(b);
                    Gate newgate = new Gate(oldgate);
                    smallchip.gates.add(newgate);
                    for (int c = 0; c < 8; c++) {
                        if (oldgate.portSignals[c].externalSignal != null) {
                            int sigIndex = gate.mySignals.indexOf(oldgate.portSignals[c].externalSignal);
                            newgate.portSignals[c].externalSignal = smallchip.signals.get(sigIndex);
                        }
                    }
                }
                for (int b = 0; b < 8; b++) {
                    if (gate.portSignals[b].internalSignal != null) {
                        int sigIndex = gate.mySignals.indexOf(gate.portSignals[b].internalSignal);
                        smallchip.portSignals[b].internalSignal = smallchip.signals.get(sigIndex);
                    }
                    smallchip.portSignals[b].type = gate.portSignals[b].type;
                    smallchip.ports[b].type = gate.portSignals[b].type;
                }
            }
        }


        // Wire Devices according to the way the Gates are linked to Signals
        for (int a = 0; a < sc.signals.size(); a++) {
            Signal sig = sc.signals.get(a);
            if (sig.working) {
                int numConnections = 0;
                for (int b = 0; b < 8; b++) {
                    if (sc.portSignals[b].internalSignal == sig) {
                        numConnections++;
                    }
                }
                for (int b = 0; b < sc.gates.size(); b++) {
                    Gate thisGate = sc.gates.get(b);
                    for (int c = 0; c < 8; c++) {
                        if (thisGate.portSignals[c].externalSignal == sig) {
                            numConnections++;
                        }
                    }
                }
                if (numConnections == 2) // Simple wire from A to B
                {
                    Port port1 = FindPort(sig, 1, pc, sc, deviceList);
                    Port port2 = FindPort(sig, 2, pc, sc, deviceList);
                    if (port1 != null && port2 != null) {
                        Wire dummy = new Wire(port1, port2);
                    }
                    else {
                        System.out.println("Could not make connection for Signal " + a);
                        if (port1 == null) {
                            System.out.println("port1=null");
                        }
                        else {
                            System.out.println("port1 is in " + port1.myDevice.getClass());
                        }
                        if (port2 == null) {
                            System.out.println("port2=null");
                        }
                        else {
                            System.out.println("port2 is in " + port2.myDevice.getClass());
                        }

                    }
                }

                else if (numConnections == 3) // Need a regular Node
                {
                    Port port1 = FindPort(sig, 1, pc, sc, deviceList);
                    Port port2 = FindPort(sig, 2, pc, sc, deviceList);
                    Port port3 = FindPort(sig, 3, pc, sc, deviceList);
                    Node node = new Node(10 * 28, 6 * 32, pc.InternalRoom, Node.TYPE_STRAIGHT);
                    pc.level().items.add(node);
                    deviceList.add(node);
                    if (port1.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port1, node.ports[0]);
                        Wire dummy2 = new Wire(port2, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                    }
                    else if (port2.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port2, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                    }
                    else if (port3.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port3, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port2, node.ports[2]);
                    }
                    else if (port1.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port1, node.ports[0]);
                        Wire dummy2 = new Wire(port2, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                    }
                    else if (port2.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port2, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                    }
                    else if (port3.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port3, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port2, node.ports[2]);
                    }
                }

                else if (numConnections == 4) // Need a 3 output Node
                {
                    Port port1 = FindPort(sig, 1, pc, sc, deviceList);
                    Port port2 = FindPort(sig, 2, pc, sc, deviceList);
                    Port port3 = FindPort(sig, 3, pc, sc, deviceList);
                    Port port4 = FindPort(sig, 4, pc, sc, deviceList);
                    Node node = new Node(10 * 28, 6 * 32, pc.InternalRoom, Node.TYPE_THREE);
                    pc.level().items.add(node);
                    deviceList.add(node);
                    if (port1.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port1, node.ports[0]);
                        Wire dummy2 = new Wire(port2, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                        Wire dummy4 = new Wire(port4, node.ports[3]);
                    }
                    else if (port2.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port2, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                        Wire dummy4 = new Wire(port4, node.ports[3]);
                    }
                    else if (port3.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port3, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port2, node.ports[2]);
                        Wire dummy4 = new Wire(port4, node.ports[3]);
                    }
                    else if (port4.type == Port.TYPE_OUTPUT) {
                        Wire dummy1 = new Wire(port4, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port2, node.ports[2]);
                        Wire dummy4 = new Wire(port3, node.ports[3]);
                    }
                    else if (port1.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port1, node.ports[0]);
                        Wire dummy2 = new Wire(port2, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                        Wire dummy4 = new Wire(port4, node.ports[3]);
                    }
                    else if (port2.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port2, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port3, node.ports[2]);
                        Wire dummy4 = new Wire(port4, node.ports[3]);
                    }
                    else if (port3.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port3, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port2, node.ports[2]);
                        Wire dummy4 = new Wire(port4, node.ports[3]);
                    }
                    else if (port4.type == Port.TYPE_UNDEFINED) {
                        Wire dummy1 = new Wire(port4, node.ports[0]);
                        Wire dummy2 = new Wire(port1, node.ports[1]);
                        Wire dummy3 = new Wire(port2, node.ports[2]);
                        Wire dummy4 = new Wire(port3, node.ports[3]);
                    }
                }

                else if (numConnections > 4) // Need many nodes
                {
                    Port[] ports = new Port[numConnections];
                    for (int b = 0; b < numConnections; b++) {
                        ports[b] = FindPort(sig, b + 1, pc, sc, deviceList);
                    }

                    Node[] nodes = new Node[numConnections - 2];
                    for (int b = 0; b < numConnections - 2; b++) {
                        nodes[b] = new Node(10 * 28, 6 * 32, pc.InternalRoom, Node.TYPE_STRAIGHT);
                        pc.level().items.add(nodes[b]);
                        deviceList.add(nodes[b]);
                    }

                    for (int b = 1; b < numConnections - 2; b++) {
                        Wire dummy = new Wire(nodes[b - 1].ports[2], nodes[b].ports[0]);
                    }

                    int nodecounter = 0;
                    boolean inputfound = false;
                    for (int b = 0; b < numConnections; b++) {
                        if (ports[b].type == Port.TYPE_OUTPUT) {
                            Wire dummy = new Wire(nodes[0].ports[0], ports[b]);
                            inputfound = true;
                        }
                        else if (ports[b].type == Port.TYPE_INPUT) {
                            if (nodecounter < nodes.length) {
                                Wire dummy = new Wire(nodes[nodecounter].ports[1], ports[b]);
                                nodecounter++;
                            }
                            else {
                                Wire dummy = new Wire(nodes[nodecounter - 1].ports[2], ports[b]);
                            }
                        }
                        else {
                            System.out.println(ports[b].myDevice.getClass()
                                    + " port " + b + " id undefined.");
                        }
                    }
                    if (!inputfound) {
                        for (int b = 0; b < numConnections; b++) {
                            if (ports[b].type == Port.TYPE_UNDEFINED) {
                                Wire dummy = new Wire(nodes[0].ports[0], ports[b]);
                            }
                        }
                    }
                }
            }

        }

        // Routing: Move the devices around based on what they are connected to.
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < deviceList.size(); b++) {
                Device device = deviceList.get(b);
                int numConnections = 1;
                int x = device.x;
                int y = device.y;
                for (int c = 0; c < device.ports.length; c++) {
                    if (device.ports[c].myWire != null) {
                        if (device.ports[c].type == Port.TYPE_INPUT) {
                            x += device.ports[c].myWire.outPort.myDevice.x;
                            y += device.ports[c].myWire.outPort.myDevice.y;
                        }
                        else if (device.ports[c].type == Port.TYPE_OUTPUT) {
                            x += device.ports[c].myWire.inPort.myDevice.x;
                            y += device.ports[c].myWire.inPort.myDevice.y;
                        }
                        numConnections++;
                    }
                }
                device.x = x / numConnections;
                device.y = y / numConnections;
                if (device.x < 56) {
                    device.x = 56;
                }
                if (device.x > 504) {
                    device.x = 504;
                }
                if (device.y < 32) {
                    device.y = 32;
                }
                if (device.y > 320) {
                    device.y = 320;
                }
            }
        }

        // Rotate devices to point to the "next" device. AND, OR, NOT, XOR only
        for (int a = 0; a < deviceList.size(); a++) {
            Device device = deviceList.get(a);
            if (!(device instanceof SmallChip
                    || device instanceof Node
                    || device instanceof FlipFlop)) {
                Port port;
                if (device instanceof NOTGate) {
                    port = device.ports[1];
                }
                else {
                    port = device.ports[2];
                }
                if (port.myWire != null) {
                    Port otherPort = port.myWire.otherPort(port);
                    Device otherDevice = (Device) otherPort.myDevice;
                    int dx = otherDevice.x - device.x;
                    int dy = otherDevice.y - device.y;
                    if (Math.abs(dx) > Math.abs(dy)) {
                        if (dx < 0) {
                            device.rotate(-1);
                        }
                        else {
                            device.rotate(1);
                        }
                    }
                    else {
                        if (dy > 0) {
                            device.rotate(1);
                            device.rotate(1);
                        }
                    }
                }

            }
        }

        pc.grabbable = true;
        sc.grabbable = true;

    }

    Port FindPort(Signal sig, int num, PrototypeChip pc, SmallChip sc, List<Device> deviceList) {
        // Find Nth port that this signal attaches to in given SmallChip.

        int n = 0;

        for (int a = 0; a < 8; a++) {
            if (sc.portSignals[a].internalSignal == sig) {
                n++;
                if (n == num) {
                    return pc.portdevices[a].ports[0];
                }
            }
        }
        for (int a = 0; a < sc.gates.size(); a++) {
            Gate thisgate = sc.gates.get(a);
            for (int b = 0; b < 8; b++) {
                if (thisgate.portSignals[b].externalSignal == sig) {
                    n++;
                    if (n == num) {
                        Device device = deviceList.get(a);
                        return device.ports[b];
                    }
                }
            }
        }
        return null;
    }

}
