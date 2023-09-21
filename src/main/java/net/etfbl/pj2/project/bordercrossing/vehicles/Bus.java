package net.etfbl.pj2.project.bordercrossing.vehicles;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import net.etfbl.pj2.project.bordercrossing.Main;
import net.etfbl.pj2.project.bordercrossing.Simulation;

import java.util.ArrayList;
import java.util.Random;

public class Bus implements Vehicle{
    final private String name;
    private static int ID = 1;
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private boolean hasPoliceProblem = false;
    private int X = 0;
    private int Y = 0;
    ImageView busImage;
    public Bus()
    {
        name = "Bus "+ ID++;
        //this.busImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/Bus.png"));
        this.busImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/Bus1.png"));
        this.busImage.setFitHeight(90);
        this.busImage.setFitWidth(80);
        this.busImage.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija o autobusu");
            alert.setHeaderText(null);
            alert.setContentText(this.vehicleInformation());
            alert.showAndWait();
        });
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
    public String vehicleInformation()
    {
        return "Auto: " + this.name + " ima: " + this.passengers.size() + " putnika.";
    }
    public void PassengerGenerator()
    {
        Random random = new Random();
        int num = random.nextInt(52) + 1;

        for(int i = 0; i < num; i++) {
            Passenger passenger = new Passenger();
            passenger.setBaggage();
            this.passengers.add(passenger);
        }
    }
    public ArrayList<Passenger> getPassengers()
    {
        return passengers;
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
        if(Simulation.P1.isFree && Simulation.P1.isWork) {
            System.out.println("P1");
            moveBus(this);
            //Simulation.P1.temirnalIsNotFree();
            Simulation.P1.processBus(this);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), this.busImage);
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
                        Simulation.C1.processBus(this);
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
            } else
            {
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
            moveBus(this);
            Simulation.P2.processBus(this);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), this.busImage);
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
                        Simulation.C1.processBus(this);
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
        return this.busImage;
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

            this.busImage.setX(215 + Main.sideWrap);
            this.X = 215 + Main.sideWrap;
            this.busImage.setY(110 + Main.distanceI2);
            this.Y = 110 + Main.distanceI2;
            Main.interface2.getChildren().add(this.busImage);
            Main.distanceI2 += 110;
        }
        else {

            this.busImage.setX(Main.firstX);
            this.X = Main.firstX;
            this.busImage.setY(Main.firstY + Main.distance);
            this.Y = Main.firstY + Main.distance;
            Main.interface1.getChildren().add(this.busImage);
            Main.distance += 110;
        }

    }

    public synchronized void moveBus(Bus bus)
    {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(bus != null) {
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.2), bus.busImage);

            if (bus.getY() == Main.firstY) {
                if(Simulation.P1.isFree)
                {
                    Simulation.P1.temirnalIsNotFree();
                    transition1.setByX(-150);
                    transition1.setByY(-130);
                    transition1.play();
                    bus.setX(-150);
                    bus.setY(-130);
                }
                else if(Simulation.P2.isFree) {
                    Simulation.P2.temirnalIsNotFree();
                    transition1.setByX(100);
                    transition1.setByY(-130);
                    transition1.play();
                    bus.setX(100);
                    bus.setY(-130);
                }
            }
        }

//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for(Vehicle vehicle: Main.vehiclesList) {
//            ImageView vehicleImage = vehicle.getImage();
//            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), vehicleImage);
//
//            if(vehicle.getY() == 110 && vehicle.getX() == 215)
//            {
//                Platform.runLater(()->{
//                    vehicleImage.setX(Main.firstX);
//                    vehicleImage.setY(Main.firstY + Main.distance);
//                    Main.distance += 110;
//                    Main.interface1.getChildren().add(vehicleImage);
//                    transition.setByY(-110);
//                    transition.play();
//                    vehicle.setX(Main.firstX-215);
//                    vehicle.setY(Main.firstY + 3 * 110);
////                    System.out.println(vehicle.getX());
////                    System.out.println(vehicle.getY());
//                    for(Vehicle vehicle1: Main.vehiclesList) {
//                        ImageView vehicleImage1 = vehicle1.getImage();
//                        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.1), vehicleImage1);
//                        if (vehicle1.getX() == 215 && vehicle1.getY() >= 220) {
//                            transition1.setByY(-110);
//                            vehicle1.setY(-110);
//                            transition1.play();
//                        }
//                    }
//                });
//
//
//            }
//
//            if(vehicle.getY() >= Main.firstY && vehicle.getX() == Main.firstX) {
//                transition.setByY(-110);
//                vehicle.setY(-110);
//                transition.play();
//            }
//        }
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
