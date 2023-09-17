package net.etfbl.pj2.project.bordercrossing.vehicles;

import java.util.Random;

public class Baggage {
    private boolean ileagal = false;

    public Baggage()
    {
        Random random = new Random();
        if(random.nextInt(0, 100) <= 10)
            this.ileagal = true;
    }

    public boolean getIlegal()
    {
        return this.ileagal;
    }
}
