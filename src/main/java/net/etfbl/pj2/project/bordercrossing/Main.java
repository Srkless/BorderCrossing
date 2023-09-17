package net.etfbl.pj2.project.bordercrossing;

import net.etfbl.pj2.project.bordercrossing.vehicles.*;

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Main extends Application {

    public static Group sp = new Group();
    public static ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    public static int firstX = 450;
    public static int firstY = 350;
    public static int a = 0;
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Border Crossing Application");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);
        //primaryStage.setFullScreen(true);


        ImageView P1w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/PoliceWorking.png"));
        ImageView P2w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/PoliceWorking.png"));
        ImageView PKw = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/PoliceWorking.png"));

        ImageView C1w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/customsWorking.png"));
        ImageView CKw = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/customsWorking.png"));

        P1w.setFitHeight(100);
        P1w.setFitWidth(100);
        P1w.setX(200);
        P1w.setY(200);
        sp.getChildren().add(P1w);

        P2w.setFitHeight(100);
        P2w.setFitWidth(100);
        P2w.setX(450);
        P2w.setY(200);
        sp.getChildren().add(P2w);

        PKw.setFitHeight(100);
        PKw.setFitWidth(100);
        PKw.setX(700);
        PKw.setY(200);
        sp.getChildren().add(PKw);

        C1w.setFitHeight(100);
        C1w.setFitWidth(100);
        C1w.setX(200);
        C1w.setY(50);
        sp.getChildren().add(C1w);

        CKw.setFitHeight(100);
        CKw.setFitWidth(100);
        CKw.setX(700);
        CKw.setY(50);
        sp.getChildren().add(CKw);

        //Adding HBox to the scene
        Scene scene = new Scene(sp);
        scene.setFill(Color.GRAY);
        primaryStage.setScene(scene);

        //Simulation.P1.isFree = false;
        for(int i = 0; i < 5; i++)
            vehiclesList.add(new Car());
        for(int i = 0; i < 3; i++)
            vehiclesList.add(new Truck());
        for(int i = 0; i < 4; i++)
            vehiclesList.add(new Bus());
        Collections.shuffle(vehiclesList);

        for(Vehicle vehicle: vehiclesList)
        {

            if(vehicle instanceof Truck)
            {
                //System.out.println(vehicle);
                Simulation.vehiclesOnTruckPolicesTerminal.add(vehicle);
            }
            else
                Simulation.vehiclesOnPolicesTerminal.add(vehicle);

            vehicle.draw();
        }
        int i = 0;
        for(Vehicle vehicle: vehiclesList)
        {
            new Thread(vehicle).start();
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}