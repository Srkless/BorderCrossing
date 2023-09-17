package net.etfbl.pj2.project.bordercrossing;

import net.etfbl.pj2.project.bordercrossing.vehicles.*;
import net.etfbl.pj2.project.bordercrossing.terminals.*;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulation {
    static public PoliceTerminal P1 = new PoliceTerminal();
    static public PoliceTerminal P2 = new PoliceTerminal();
    static public PoliceTerminal PK = new PoliceTerminal();
    static public CustomsTerminal C1 = new CustomsTerminal();
    static public CustomsTerminal CK = new CustomsTerminal();
    static public ArrayList<Vehicle> vehiclesOnCustomsTerminal= new ArrayList<>();
    static public ArrayList<Vehicle> vehiclesOnPolicesTerminal= new ArrayList<>();
    static public ArrayList<Vehicle> vehiclesOnTruckPolicesTerminal= new ArrayList<>();
    static public ArrayList<Vehicle> badVehicles= new ArrayList<>();

    final public static Object obj1 = new Object();
    final public static Object obj2 = new Object();
    final public static Object obj3 = new Object();

}
