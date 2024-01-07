/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

import java.time.Instant;

/**
 * Real time power data
 */
public class PowerData 
{
    private Instant time;
    /**
     * Current battery charge in %
     */
    private double batteryCharge;
    /**
     * Current solar power generation in watts.
     */
    private double pvPower;
    /**
     * Power ?From?To? the EV in watts.
     */
    private double evPower;
    /**
     * Power from the grid in watts. A negative number means the power is going to the grid.
     */
    private double gridPower;
    /**
     * Power coming from the battery in watts. A negative number means the power is going to the battery.
     */
    private double batteryPower;
    /**
     * The current load of power being consumed directly in watts.
     */
    private double load;

    @Override
    public String toString()
    {
        return "PowerData{" + 
               "batteryCharge=" + batteryCharge +
               ", pvPower=" + pvPower +
               ", evPower=" + evPower +
               ", gridPower=" + gridPower +
               ", batteryPower=" + batteryPower +
               ", load=" + load + 
               '}';
    }

    /**
     * @return the batteryCharge
     */
    public double getBatteryCharge()
    {
        return batteryCharge;
    }

    /**
     * @param batteryCharge the batteryCharge to set
     */
    public void setBatteryCharge(double batteryCharge)
    {
        this.batteryCharge = batteryCharge;
    }

    /**
     * @return the pvPower
     */
    public double getPvPower()
    {
        return pvPower;
    }

    /**
     * @param pvPower the pvPower to set
     */
    public void setPvPower(double pvPower)
    {
        this.pvPower = pvPower;
    }

    /**
     * @return the evPower
     */
    public double getEvPower()
    {
        return evPower;
    }

    /**
     * @param evPower the evPower to set
     */
    public void setEvPower(double evPower)
    {
        this.evPower = evPower;
    }

    /**
     * @return the gridPower
     */
    public double getGridPower()
    {
        return gridPower;
    }

    /**
     * @param gridPower the gridPower to set
     */
    public void setGridPower(double gridPower)
    {
        this.gridPower = gridPower;
    }

    /**
     * @return the batteryPower
     */
    public double getBatteryPower()
    {
        return batteryPower;
    }

    /**
     * @param batteryPower the batteryPower to set
     */
    public void setBatteryPower(double batteryPower)
    {
        this.batteryPower = batteryPower;
    }

    /**
     * @return the load
     */
    public double getLoad()
    {
        return load;
    }

    /**
     * @param load the load to set
     */
    public void setLoad(double load)
    {
        this.load = load;
    }

    /**
     * @return the time
     */
    public Instant getTime()
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Instant time)
    {
        this.time = time;
    }
}
