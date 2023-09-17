package net.etfbl.pj2.project.bordercrossing.vehicles;

import javafx.scene.image.ImageView;

public interface Vehicle extends Runnable{
    public void PassengerGenerator();
    public void run();
    public void draw();
    public ImageView getImage();
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
}
