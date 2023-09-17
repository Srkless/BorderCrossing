package net.etfbl.pj2.project.bordercrossing.terminals;

import net.etfbl.pj2.project.bordercrossing.Main;
import net.etfbl.pj2.project.bordercrossing.vehicles.*;
import net.etfbl.pj2.project.bordercrossing.Simulation;
import java.util.ArrayList;
import java.lang.Thread;
public class PoliceTerminal {
    public boolean isFree = true;
    public boolean isWork = true;
    public void temirnalIsFree()
    {
        this.isFree = true;
    }
    public void temirnalIsNotFree()
    {
        this.isFree = false;
    }
    public void temirnalIsWorking()
    {
        this.isWork = true;
    }
    public void temirnalIsNotWorking()
    {
        this.isWork = false;
    }
    public void processTruck(Truck truck)
    {
        ArrayList<Passenger> passengers = truck.getPassengers();
        Main.vehiclesList.remove(0);
        System.out.println("Obradjuje se: " + truck);
        for(int i = 0; i<passengers.size();i++)
        {
            if(!passengers.get(i).isValidDocument() && i == 0)
            {
                System.out.println("Kamion otpada, vozac ima los dokument");
                truck.setPoliceProblem(true);
            }
            else if(!passengers.get(i).isValidDocument())
            {
                System.out.println("Putnik napusta kamion");
                passengers.remove(i);
            }
//            else
//            {
//                System.out.println("Putnik prosao terminal");
//            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void processCar(Car car)
    {
        ArrayList<Passenger> passengers = car.getPassengers();
        Main.vehiclesList.remove(0);
        System.out.println("Obradjuje se: " + car);
        for(int i = 0; i<passengers.size();i++)
        {
            if(!passengers.get(i).isValidDocument() && i == 0)
            {
                System.out.println("Auto otpada, vozac ima los dokument");
                car.setPoliceProblem(true);
            }
            else if(!passengers.get(i).isValidDocument())
            {
                System.out.println("Putnik napusta auto");
                passengers.remove(i);
            }
//            else
//            {
//                System.out.println("Putnik prosao terminal");
//            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(car.getPoliceProblem()) {
            Simulation.badVehicles.add(car);
        }
        else {
            Simulation.vehiclesOnCustomsTerminal.add(car);
        }

    }
    public void processBus(Bus bus)
    {
        ArrayList<Passenger> passengers = bus.getPassengers();
        Main.vehiclesList.remove(0);
        System.out.println("Obradjuje se: " + bus);
        for(int i = 0; i<passengers.size();i++)
        {
            if(!passengers.get(i).isValidDocument() && i == 0)
            {
                System.out.println("Bus otpada, vozac ima los dokument");
                bus.setPoliceProblem(true);
            }
            else if(!passengers.get(i).isValidDocument())
            {
                System.out.println("Putnik napusta bus");
                passengers.remove(i);
            }
//            else
//            {
//                System.out.println("Putnik prosao terminal");
//            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(bus.getPoliceProblem()) {
            Simulation.badVehicles.add(bus);
        }
        else {
            Simulation.vehiclesOnCustomsTerminal.add(bus);
        }

    }
}
