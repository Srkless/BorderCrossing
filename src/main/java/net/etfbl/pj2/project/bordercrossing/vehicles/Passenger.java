package net.etfbl.pj2.project.bordercrossing.vehicles;
import javafx.scene.control.cell.CheckBoxListCell;
import net.etfbl.pj2.project.bordercrossing.vehicles.Document;

import java.util.Random;
public class Passenger {
    private Document document;
    private boolean validDocument = true;
    private boolean hasBaggage = false;

    private Baggage baggage;

    public Passenger()
    {
        this.document = new Document();
        Random random = new Random();

        if(random.nextInt(0, 100) <= 3)
            this.validDocument = false;
    }
    public void setBaggage()
    {
        Random random = new Random();
        if(random.nextInt(0, 100) <= 70)
        {
            Baggage baggage = new Baggage();
            this.baggage = baggage;
            this.hasBaggage = true;
        }
        else
            this.baggage = null;

    }
    public Baggage getBaggage()
    {
        return this.baggage;
    }
    public boolean getHasBaggage()
    {
        return this.hasBaggage;
    }

    public boolean isValidDocument()
    {
        return this.validDocument;
    }
}
