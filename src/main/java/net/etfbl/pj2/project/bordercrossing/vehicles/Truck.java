package net.etfbl.pj2.project.bordercrossing.vehicles;

import javafx.application.Platform;
import javafx.scene.control.Alert;
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
        //this.truckImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/Truck.png"));
        this.truckImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/Truck1.png"));
        this.truckImage.setFitHeight(90);
        this.truckImage.setFitWidth(75);
        this.truckImage.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija o kamionu");
            alert.setHeaderText(null);
            alert.setContentText(this.vehicleInformation());
            alert.showAndWait();
        });
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
    public String vehicleInformation()
    {
        return "Kamion: " + this.name + " ima: " + this.passengers.size() + " putnika.\n" + "Deklarisana masa kamona: " + this.declaredCargoMass + ", stvarna masa kamiona: " + this.actualCargoMass;
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
        if(Main.paused)
        {
            synchronized (Simulation.obj3) {
                try {
                    Simulation.obj3.wait(); // Čekaj dok se ne dobije notify
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
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
        Main.vehicleCounter++;
        if(Main.vehicleCounter > 5) {
            if(Main.waitingVehiclesCounter % 8 == 0 && Main.waitingVehiclesCounter != 0) {
                Main.sideWrap += 110;
                Main.distanceI2 = 0;
            }
            Main.waitingVehiclesCounter++;

            this.truckImage.setX(215 + Main.sideWrap);
            this.X = 215 + Main.sideWrap;
            this.truckImage.setY(110 + Main.distanceI2);
            this.Y = 110 + Main.distanceI2;
            Main.interface2.getChildren().add(this.truckImage);
            Main.distanceI2 += 110;
        }
        else {

            this.truckImage.setX(Main.firstX);
            this.X = Main.firstX;
            this.truckImage.setY(Main.firstY + Main.distance);
            this.Y = Main.firstY + Main.distance;
            Main.interface1.getChildren().add(this.truckImage);
            Main.distance += 110;
        }

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


//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Main.waitingVehiclesCounter);
        if (Main.waitingVehiclesCounter == 5)
        {
            Main.distance -= 110;
        }
        else if (Main.waitingVehiclesCounter == 13)
        {
            Main.distance -= 110;
            Main.rastojanje -= 110;
        }
        else if (Main.waitingVehiclesCounter == 21)
        {
            Main.distance -= 110;
            Main.rastojanje -= 110;
            Main.rastojanje2 -= 110;
        }
        else if (Main.waitingVehiclesCounter == 29)
        {
            Main.distance -= 110;
            Main.rastojanje -= 110;
            Main.rastojanje2 -= 110;
            Main.rastojanje3 -= 110;
        }
        else if (Main.waitingVehiclesCounter == 37)
        {
            Main.distance -= 110;
            Main.rastojanje -= 110;
            Main.rastojanje2 -= 110;
            Main.rastojanje3 -= 110;
            Main.rastojanje4 -= 110;
        }
        for(Vehicle vehicle: Main.vehiclesList) {
            ImageView vehicleImage = vehicle.getImage();
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), vehicleImage);
            //System.out.println(((Car)vehicle).getName() + " " + vehicle.getX() + ", " + vehicle.getY() + " " + vehicleImage.getX() + ", " + vehicleImage.getY());
            if(vehicle.getY() >= Main.firstY && vehicle.getX() == Main.firstX) {
                transition.setByY(-110);
                vehicle.setY(-110);
                transition.play();
            }
            //System.out.println(((Car)vehicle).getName() + " " + vehicle.getX() + ", " + vehicle.getY());
            if(vehicle.getX() == 215 && vehicle.getY() == 110) // Uslov da je prvi i redu na drugom interface
            {

                vehicleImage.setX(Main.firstX);
                vehicleImage.setY(Main.firstY + Main.distance - 110);
                Main.waitingVehiclesCounter--;
                Platform.runLater(()->{
                    Main.interface1.getChildren().add(vehicleImage);
                    //System.out.println(vehicleImage.getX() + ", " + vehicleImage.getY());
                });
                vehicle.setX(Main.firstX - 215);
                vehicle.setY(Main.firstY + 3 * 110);
//                transition.setByY(-110);
                //transition.play();
                Main.distance += 110;
            }
            else if(vehicle.getX() == 325 && vehicle.getY() == 110)
            {
//                transition.setByX(-110);
//                transition.setByY(770);

                vehicleImage.setX(215);
                vehicleImage.setY(Main.rastojanje);

                //transition.play();
                vehicle.setX(-110);
                vehicle.setY(770);
                Main.rastojanje+=110;
            }
            else if(vehicle.getX() == 435 && vehicle.getY() == 110)
            {
//                transition.setByX(-110);
//                transition.setByY(770);

                vehicleImage.setX(325);
                vehicleImage.setY(Main.rastojanje2);

                //transition.play();
                vehicle.setX(-110);
                vehicle.setY(770);
                Main.rastojanje2+=110;

            }
            else if(vehicle.getX() == 545 && vehicle.getY() == 110)
            {
//                transition.setByX(-110);
//                transition.setByY(770);

                vehicleImage.setX(435);
                vehicleImage.setY(Main.rastojanje3);

                //transition.play();
                vehicle.setX(-110);
                vehicle.setY(770);
                Main.rastojanje3+=110;

            }
            else if(vehicle.getX() == 655 && vehicle.getY() == 110)
            {
//                transition.setByX(-110);
//                transition.setByY(770);

                vehicleImage.setX(545);
                vehicleImage.setY(Main.rastojanje4);

                //transition.play();
                vehicle.setX(-110);
                vehicle.setY(770);
                Main.rastojanje4+=110;
            }
            else if(vehicle.getX() == 765 && vehicle.getY() == 110)
            {
//                transition.setByX(-110);
//                transition.setByY(770);

                vehicleImage.setX(655);
                vehicleImage.setY(Main.rastojanje5);

                //transition.play();
                vehicle.setX(-110);
                vehicle.setY(770);
                Main.rastojanje5+=110;
                if(Main.rastojanje5 % 880 == 0 && Main.rastojanje5 != 880){
                    Main.rastojanje4 -= 110;
                    //Main.distance -= 110;
                }
            }
            else if ((vehicle.getX() == 215 || vehicle.getX() == 325|| vehicle.getX() == 435 || vehicle.getX() == 545 || vehicle.getX() == 655|| vehicle.getX() == 765) && vehicle.getY() >= 220) {

                transition.setByY(-110);
                vehicle.setY(-110);
                transition.play();
            }

        }

    }
}