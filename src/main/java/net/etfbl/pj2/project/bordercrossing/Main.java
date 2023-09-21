package net.etfbl.pj2.project.bordercrossing;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class Main extends Application {

    public static Group interface1 = new Group();

    Scene scene = new Scene(interface1);
    public static Group interface2 = new Group();
    public static Scene column = new Scene(interface2);
    public static Group interface3 = new Group();
    public static Scene badVehiclesColumn = new Scene(interface3);
    public static ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    public static int firstX = 450;
    public static int firstY = 350;
    public static int distance = 0;
    public static int distanceI2 = 0;
    public static int sideWrap = 0;
    public static int Wrap = 0;
    public static int vehicleCounter = 0;
    public static int waitingVehiclesCounter = 0;
    public static int rastojanje = 880;
    public static int rastojanje2 = 880;
    public static int rastojanje3 = 880;
    public static int rastojanje4 = 880;
    public static int rastojanje5 = 880;
    public static boolean paused = true;
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Border Crossing Application");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        //primaryStage.setFullScreen(true);


//        ImageView P1w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/PoliceWorking.png"));
//        ImageView P2w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/PoliceWorking.png"));
//        ImageView PKw = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/PoliceWorking.png"));
//
//        ImageView C1w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/customsWorking.png"));
//        ImageView CKw = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/customsWorking.png"));
//
//        ImageView borderImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Icons/BorderCrossing.png"));

        ImageView P1w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/policeTerminal.png"));
        ImageView P2w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/policeTerminal.png"));
        ImageView PKw = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/policeTerminal.png"));

        ImageView C1w = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/customsTerminal.png"));
        ImageView CKw = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/customsTerminal.png"));

        ImageView borderImage = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/borderCrossing.png"));

        ImageView backgroundInterface1 = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/background.png"));
        ImageView backgroundInterface2 = new ImageView(new Image("file:/home/srkless/ETF/Java/Project/BorderCrossing/src/Pictures/background2.png"));
        interface1.getChildren().add(backgroundInterface1);
        interface2.getChildren().add(backgroundInterface2);

        P1w.setFitHeight(100);
        P1w.setFitWidth(100);
        P1w.setX(200);
        P1w.setY(200);
        interface1.getChildren().add(P1w);

        P2w.setFitHeight(100);
        P2w.setFitWidth(100);
        P2w.setX(450);
        P2w.setY(200);
        interface1.getChildren().add(P2w);

        PKw.setFitHeight(100);
        PKw.setFitWidth(100);
        PKw.setX(700);
        PKw.setY(200);
        interface1.getChildren().add(PKw);

        C1w.setFitHeight(90);
        C1w.setFitWidth(90);
        C1w.setX(200);
        C1w.setY(50);
        interface1.getChildren().add(C1w);

        CKw.setFitHeight(90);
        CKw.setFitWidth(90);
        CKw.setX(700);
        CKw.setY(50);
        interface1.getChildren().add(CKw);

        primaryStage.setScene(scene);



        Button i2Button = new Button("Column of vehicles");
        i2Button.setStyle("-fx-background-color: #5e5b5b;");
        i2Button.setTextFill(Color.GRAY);
        i2Button.setOnMouseEntered(event -> {
            i2Button.setStyle("-fx-background-color: #403d3d;");
        });
        i2Button.setOnMouseExited(event -> {
            i2Button.setStyle("-fx-background-color: #5e5b5b;"); // Vratite podrazumevanu boju
        });
        i2Button.setLayoutX(845);
        i2Button.setPrefHeight(50);
        interface1.getChildren().add(i2Button);

        Button simulationButton = new Button("Simulation");
        simulationButton.setStyle("-fx-background-color: #5e5b5b;");
        simulationButton.setTextFill(Color.GRAY);
        simulationButton.setOnMouseEntered(event -> {
            simulationButton.setStyle("-fx-background-color: #403d3d;");
        });
        simulationButton.setOnMouseExited(event -> {
            simulationButton.setStyle("-fx-background-color: #5e5b5b;"); // Vratite podrazumevanu boju
        });
        simulationButton.setLayoutX(10);
        simulationButton.setPrefHeight(50);
        interface2.getChildren().add(simulationButton);

        Button i3Button = new Button("Bad vehicles column");
        i3Button.setStyle("-fx-background-color: #5e5b5b;");
        i3Button.setTextFill(Color.GRAY);
        i3Button.setOnMouseEntered(event -> {
            i3Button.setStyle("-fx-background-color: #403d3d;");
        });
        simulationButton.setOnMouseExited(event -> {
            i3Button.setStyle("-fx-background-color: #5e5b5b;"); // Vratite podrazumevanu boju
        });
        i3Button.setLayoutX(838);
        i3Button.setPrefHeight(50);
        interface2.getChildren().add(i3Button);

        //column.setFill(Color.GRAY);
        badVehiclesColumn.setFill(Color.GRAY);

        i2Button.setOnAction(e -> primaryStage.setScene(column));
        simulationButton.setOnAction(e -> primaryStage.setScene(scene));
        i3Button.setOnAction(e -> primaryStage.setScene(badVehiclesColumn));

        borderImage.setFitHeight(80);
        borderImage.setFitWidth(100);
        borderImage.setX(200);
        borderImage.setY(0);
        interface2.getChildren().add(borderImage);

        //Simulation.P2.isFree = false;
        //Simulation.P1.isFree = false;
        for(int i = 0; i < 35; i++)
            vehiclesList.add(new Car());
        for(int i = 0; i < 10; i++)
            vehiclesList.add(new Truck());
        for(int i = 0; i < 5; i++)
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
        Button startButton = new Button("Start the simulation");
        Button exitButton = new Button("Exit");
        Button pauseButton = new Button("Pause/ Resume");
        startButton.setStyle("-fx-background-color: #32a856;");
        startButton.setTextFill(Color.GRAY);
        startButton.setOnMouseEntered(event -> {
            startButton.setStyle("-fx-background-color: #1d6633;");
        });
        startButton.setOnMouseExited(event -> {
            startButton.setStyle("-fx-background-color: #32a856;"); // Vratite podrazumevanu boju
        });
        startButton.setLayoutX(250);
        startButton.setLayoutY(350);
        startButton.setPrefHeight(100);
        startButton.setPrefWidth(500);
        startButton.setTextFill(Color.WHITE);
        startButton.setOnAction(e -> {startButton.setVisible(false); exitButton.setVisible(false);for (Vehicle vehicle : vehiclesList) {
            new Thread(vehicle).start();
        }});
        interface1.getChildren().add(startButton);


        exitButton.setStyle("-fx-background-color: #c72222;");
        exitButton.setTextFill(Color.GRAY);
        exitButton.setOnMouseEntered(event -> {
            exitButton.setStyle("-fx-background-color: #700f0f;");
        });
        exitButton.setOnMouseExited(event -> {
            exitButton.setStyle("-fx-background-color: #c72222;"); // Vratite podrazumevanu boju
        });
        exitButton.setLayoutX(275);
        exitButton.setLayoutY(500);
        exitButton.setPrefHeight(100);
        exitButton.setPrefWidth(450);
        exitButton.setTextFill(Color.WHITE);
        exitButton.setOnAction(e -> { primaryStage.close();});
        interface1.getChildren().add(exitButton);


        pauseButton.setStyle("-fx-background-color: #5e5b5b;");
        pauseButton.setTextFill(Color.GRAY);
        pauseButton.setOnMouseEntered(event -> {
            pauseButton.setStyle("-fx-background-color: #403d3d;");
        });
        pauseButton.setOnMouseExited(event -> {
            pauseButton.setStyle("-fx-background-color: #5e5b5b;"); // Vratite podrazumevanu boju
        });
        pauseButton.setLayoutX(450);
        pauseButton.setPrefHeight(25);
        pauseButton.setTextFill(Color.WHITE);
        pauseButton.setOnAction(e -> {
            synchronized (Simulation.obj3) {
                paused = !paused;
                if (!paused)
                    Simulation.obj3.notifyAll();
                else
                    System.out.println("nije");
            }

        });
        interface1.getChildren().add(pauseButton);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}