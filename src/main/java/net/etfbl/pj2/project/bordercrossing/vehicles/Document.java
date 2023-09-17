package net.etfbl.pj2.project.bordercrossing.vehicles;

public class Document {
    private String name;
    private static int ID = 0;

    public Document()
    {
        this.name = "Putnik" + ID++;
    }
}
