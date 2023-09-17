package net.etfbl.pj2.project.bordercrossing.terminals;

import net.etfbl.pj2.project.bordercrossing.Main;
import net.etfbl.pj2.project.bordercrossing.Simulation;
import net.etfbl.pj2.project.bordercrossing.vehicles.*;

import java.util.ArrayList;

public class CustomsTerminal {
    public boolean isFree = true;
    public boolean isWork = true;
    public void cTemirnalIsFree()
    {
        this.isFree = true;
    }
    public void cTemirnalIsNotFree()
    {
        this.isFree = false;
    }
    public void cTemirnalIsWorking()
    {
        this.isWork = true;
    }
    public void cTemirnalIsNotWorking()
    {
        this.isWork = false;
    }

    public void processTruck(Truck truck)
    {
        ArrayList<Passenger> passengers = truck.getPassengers();
        System.out.println("Obradjuje se na carini: " + truck);
        for(int i = 0; i<passengers.size();i++)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!truck.getPoliceProblem())
        {
            if(truck.getActualCargoMass() == truck.getDeclaredCargoMass()) {
                System.out.println("Kamion prosao test mase");
            }
            else{
                System.out.println("Kamion preopterecen");
                truck.setCustomseProblem(true);
            }
        }
        if(truck.getCustomseProblem()) {
            Simulation.badVehicles.add(truck);
        }
        Simulation.vehiclesOnCustomsTerminal.remove(truck);
    }
    public void processCar(Car car)
    {
        System.out.println("Obraduje se na carini: " + car);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Simulation.vehiclesOnCustomsTerminal.remove(0);
    }
    public void processBus(Bus bus)
    {
        ArrayList<Passenger> passengers = bus.getPassengers();
        System.out.println("Obradjuje se na carini: " + bus + " koji ima: " + passengers.size());
        for(int i = 0; i<passengers.size();i++)
        {
            if(passengers.get(i).getHasBaggage() && passengers.get(i).getBaggage().getIlegal())
            {
                System.out.println("Putnik napusta bus, ima ilegalne stvari");
                passengers.remove(i);
            }
            else
//            {
//                System.out.println("Putnik prosao terminal");
//            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Simulation.vehiclesOnCustomsTerminal.remove(0);
    }
}
