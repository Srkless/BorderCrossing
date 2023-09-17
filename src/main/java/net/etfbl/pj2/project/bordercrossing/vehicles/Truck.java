package net.etfbl.pj2.project.bordercrossing.vehicles;

import net.etfbl.pj2.project.bordercrossing.Simulation;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Thread;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import net.etfbl.pj2.project.bordercrossing.Main;

public class Truck implements Vehicle {
    private String name;
    private static int ID = 1;
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();;
    final private float declaredCargoMass;
    final private float actualCargoMass;
    private boolean documentation = false;
    private boolean onTerminal = false;

    private boolean hasPoliceProblem = false;
    private boolean hasCustomsProblem = false;
    private int X = 0;
    private int Y = 0;
    ImageView truckImage;
    public Truck()
    {
        name = "Kamion " + ID++;
        this.truckImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/Truck.png"));
        this.truckImage.setFitHeight(75);
        this.truckImage.setFitWidth(75);
        Random random = new Random();
        if(random.nextInt(0, 100) <= 50)
            this.documentation = true;
        this.declaredCargoMass = random.nextFloat(0, 100);
        if(random.nextInt(0, 100) <= 20)
            this.actualCargoMass = this.declaredCargoMass + random.nextFloat(0.3f * this.declaredCargoMass);
        else
            this.actualCargoMass = this.declaredCargoMass;
        this.PassengerGenerator();
    }

    public ArrayList<Passenger> getPassengers()
    {
        return passengers;
    }
    public float getDeclaredCargoMass()
    {
        return declaredCargoMass;
    }
    public boolean getDocumentation()
    {
        return documentation;
    }
    public float getActualCargoMass()
    {
        return actualCargoMass;
    }

    public int getX()
    {
        return this.X;
    }

    public int getY()
    {
        return this.Y;
    }

    public void setX(int x)
    {
        this.X += x;
    }

    public void setY(int y)
    {
        this.Y += y;
    }

    public boolean getPoliceProblem()
    {
        return this.hasPoliceProblem;
    }

    public void setPoliceProblem(boolean bool)
    {
        this.hasPoliceProblem = bool;
    }
    public boolean getCustomseProblem()
    {
        return this.hasCustomsProblem;
    }

    public void setCustomseProblem(boolean bool)
    {
        this.hasCustomsProblem = bool;
    }
    public boolean isOnTerminal()
    {
        return onTerminal;
    }
    public void PassengerGenerator()
    {
        Random random = new Random();
        int num = random.nextInt(3) + 1;

        for(int i = 0; i < num; i++) {
            this.passengers.add(new Passenger());
        }
    }
    public String toString()
    {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.name;
    }

    public void run()
    {
        while(true) {
            if ((this.toString()).equals((Main.vehiclesList.get(0)).toString()) && Simulation.PK.isFree) {
                break;
            }
        }
        if(Simulation.PK.isWork) {
            System.out.println("PK");
            moveTruck(this);
            Simulation.PK.temirnalIsNotFree();
            Simulation.PK.processTruck(this);
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.1), this.truckImage);

            if(!this.getPoliceProblem()) {
                synchronized (Simulation.obj1) {
                    transition1.setByX(10);
                    this.setX(10);
                    transition1.setByY(-140);
                    this.setY(-140);
                    transition1.play();
                    Simulation.PK.temirnalIsFree();
                    if (Simulation.CK.isFree && Simulation.CK.isWork) {
                        Simulation.CK.cTemirnalIsNotFree();
                        Simulation.CK.processTruck(this);
                        if(this.getCustomseProblem())
                        {
                            transition1.setByX(200);
                            transition1.setByY(-5);
                            transition1.play();
                            this.setX(200);
                        }
                        else {
                            transition1.setByY(-170);
                            this.setY(-170);
                            transition1.play();
                        }
                        Simulation.CK.cTemirnalIsFree();
                    }
                }
            }
            else
            {
                transition1.setByX(200);
                this.setX(200);
                transition1.play();
                Simulation.PK.temirnalIsFree();
                //Main.vehiclesList.remove(0);
            }
        }

    }

    public void draw()
    {
        Main.sp.getChildren().add(this.truckImage);
        this.truckImage.setX(Main.firstX);
        this.X = Main.firstX;
        this.truckImage.setY(Main.firstY + Main.a);
        this.Y = Main.firstY + Main.a;
        Main.a += 110;
    }

    public ImageView getImage()
    {
        return this.truckImage;
    }
    public synchronized void moveTruck(Truck truck)
    {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(truck != null) {
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.2), truck.truckImage);
            if (truck.getY() == Main.firstY) {
                transition1.setByX(350);
                transition1.setByY(-130);
                truck.setX(330);
                truck.setY(-130);
            }
            transition1.play();
        }

        for(Vehicle vehicle: Main.vehiclesList) {
        ImageView vehicleImage = vehicle.getImage();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), vehicleImage);

        if(vehicle.getY() >= Main.firstY) {
            transition.setByY(-110);
            vehicle.setY(-110);
            transition.play();
        }
        }
    }
}
