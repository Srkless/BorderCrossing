package net.etfbl.pj2.project.bordercrossing.vehicles;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import net.etfbl.pj2.project.bordercrossing.Main;
import net.etfbl.pj2.project.bordercrossing.Simulation;

import java.util.ArrayList;
import java.util.Random;
public class Car implements Vehicle{
    private String name;
    private static int ID = 1;
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    private boolean hasPoliceProblem = false;
    private int X = 0;
    private int Y = 0;
    ImageView carImage;
    public Car()
    {
        name = "Car " + ID++;
        this.carImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/Car.png"));
        this.carImage.setFitHeight(75);
        this.carImage.setFitWidth(75);
        this.PassengerGenerator();
    }
    public boolean getPoliceProblem()
    {
        return this.hasPoliceProblem;
    }

    public void setPoliceProblem(boolean bool)
    {
        this.hasPoliceProblem = bool;
    }
    public ArrayList<Passenger> getPassengers()
    {
        return passengers;
    }
    public void PassengerGenerator()
    {
        Random random = new Random();
        int num = random.nextInt(5) + 1;

        for(int i = 0; i < num; i++) {
            this.passengers.add(new Passenger());
        }
    }
    public int getX(){return X;}
    public int getY(){return Y;}
    public void setX(int x){this.X += x;}
    public void setY(int y){this.Y += y;}
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
            if ((this.toString()).equals((Main.vehiclesList.get(0)).toString()) && Simulation.P1.isFree) {
                break;
            }
            else
                if ((this.toString()).equals((Main.vehiclesList.get(0)).toString()) && Simulation.P2.isFree) {
                break;
            }
        }

        if(Simulation.P1.isFree && Simulation.P1.isWork) {
            System.out.println("P1");
            moveCar(this);
            //Simulation.P1.temirnalIsNotFree();
            Simulation.P1.processCar(this);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), this.carImage);
            if (!this.getPoliceProblem()) {
                while(true)
                {
                    if ((this.toString()).equals((Simulation.vehiclesOnCustomsTerminal.get(0)).toString()) && Simulation.C1.isFree) {
                        break;
                    }
                }
                synchronized (Simulation.obj2) {
                    transition.setByX(0);
                    transition.setByY(-150);
                    this.setY(-150);
                    transition.play();
                    Simulation.P1.temirnalIsFree();
                    if (Simulation.C1.isFree && Simulation.C1.isWork) {
                        Simulation.C1.cTemirnalIsNotFree();
                        Simulation.C1.processCar(this);
                        transition.setByX(0);
                        transition.setByY(-200);
                        transition.play();
                        this.setX(200);
                        Simulation.C1.cTemirnalIsFree();
                    }
                    transition.setByX(0);
                    transition.setByY(-160);
                    transition.play();

                }
            } else {
                transition.setByX(-1000);
                this.setX(-1000);
                transition.play();
                Simulation.P1.temirnalIsFree();
                //Simulation.vehiclesOnPolicesTerminal.remove(0);
            }
        }
        else
            if(Simulation.P2.isFree && Simulation.P2.isWork)
            {
                System.out.println("P2");
                //Simulation.P2.temirnalIsNotFree();
                moveCar(this);
                Simulation.P2.processCar(this);
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), this.carImage);
                if(!this.getPoliceProblem()) {
                    while(true)
                    {
                        if ((this.toString()).equals((Simulation.vehiclesOnCustomsTerminal.get(0)).toString()) && Simulation.C1.isFree) {
                            break;
                        }
                    }
                    synchronized (Simulation.obj2) {
                        transition.setByX(-250);
                        transition.setByY(-150);
                        this.setY(-130);
                        transition.play();
                        Simulation.P2.temirnalIsFree();
                        if (Simulation.C1.isFree && Simulation.C1.isWork) {
                            Simulation.C1.cTemirnalIsNotFree();
                            Simulation.C1.processCar(this);
                            transition.setByX(0);
                            transition.setByY(-200);
                            transition.play();
                            this.setX(200);
                            Simulation.C1.cTemirnalIsFree();
                        }
                        transition.setByX(0);
                        transition.setByY(-160);
                        transition.play();

                    }
                }
                else
                {
                    transition.setByX(-1000);
                    this.setX(-1000);
                    transition.play();
                    Simulation.P2.temirnalIsFree();
                    //Simulation.vehiclesOnPolicesTerminal.remove(0);
                }
            //Simulation.P2.temirnalIsFree();
            //Simulation.vehiclesOnPolicesTerminal.remove(0);
        }
    }

    public ImageView getImage()
    {
       return this.carImage;
    }
    public void draw()
    {
        Main.sp.getChildren().add(this.carImage);
        this.carImage.setX(Main.firstX);
        this.X = Main.firstX;
        this.carImage.setY(Main.firstY + Main.a);
        this.Y = Main.firstY + Main.a;
        Main.a += 110;
    }
    public synchronized void moveCar(Car car)
    {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(car != null) {
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.2), car.carImage);

            if (car.getY() == Main.firstY) {
                if(Simulation.P1.isFree)
                {
                    Simulation.P1.temirnalIsNotFree();
                    transition1.setByX(-150);
                    transition1.setByY(-130);
                    transition1.play();
                    car.setX(-200);
                    car.setY(-130);
                }
                else if(Simulation.P2.isFree) {
                    Simulation.P2.temirnalIsNotFree();
                    transition1.setByX(100);
                    transition1.setByY(-130);
                    transition1.play();
                    car.setX(100);
                    car.setY(-130);
                }
            }
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
