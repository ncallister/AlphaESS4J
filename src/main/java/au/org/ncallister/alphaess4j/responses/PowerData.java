/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

import java.time.Instant;
import java.time.ZoneId;

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
     * Power sent to the grid in watts.
     */
    private double gridFeedIn;
    /**
     * Power from the grid in watts.
     */
    private double gridDraw;
    /**
     * Power coming from the battery in watts. A negative number means the power is going to the battery.
     */
    private double batteryPower;
    /**
     * The current load of power being consumed directly in watts.
     */
    private double load;
    
    public PowerData()
    {
    }
    
    public PowerData(PowerData source)
    {
        this.batteryCharge = source.getBatteryCharge();
        this.batteryPower = source.getBatteryPower();
        this.evPower = source.getEvPower();
        this.gridDraw = source.getGridDraw();
        this.gridFeedIn = source.getGridFeedIn();
        this.load = source.getLoad();
        this.pvPower = source.getPvPower();
        this.time = source.getTime();
    }

    @Override
    public String toString()
    {
        return "PowerData{" + 
               "batteryCharge=" + batteryCharge +
               ", time=" + time.atZone(ZoneId.systemDefault()) +
               ", pvPower=" + pvPower +
               ", evPower=" + evPower +
               ", gridFeedIn=" + gridFeedIn +
               ", gridDraw=" + gridDraw +
               ", batteryPower=" + batteryPower +
               ", load=" + load + 
               '}';
    }

    /**
     * Get the battery charge level as a % of the total battery capacity.
     * 
     * @return the batteryCharge
     */
    public double getBatteryCharge()
    {
        return batteryCharge;
    }

    /**
     * Set the battery charge level as a % of the total battery capacity.
     * 
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
     * @return the gridFeedIn
     */
    public double getGridFeedIn()
    {
        return gridFeedIn;
    }

    /**
     * @param gridFeedIn the gridFeedIn to set
     */
    public void setGridFeedIn(double gridFeedIn)
    {
        this.gridFeedIn = gridFeedIn;
    }

    /**
     * Get the power coming from the battery in watts. A negative number means the power is going to the battery.
     * 
     * @return the batteryPower
     */
    public double getBatteryPower()
    {
        return batteryPower;
    }

    /**
     * Set the power coming from the battery in watts. A negative number means the power is going to the battery.
     * 
     * @param batteryPower the batteryPower to set
     */
    public void setBatteryPower(double batteryPower)
    {
        this.batteryPower = batteryPower;
    }
    
    /**
     * Set the battery power by calculating the difference in power movement from the other factors.
     * <p>
     * This should only be called if the battery power isn't specified and all other points have been set.
     */
    public void calculateBatteryPower()
    {
        // Calculated as power out - power in
        // TODO: Work out how to incorporate EV Power.
        batteryPower = load + gridFeedIn - gridDraw - pvPower;
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

    /**
     * @return the gridDraw
     */
    public double getGridDraw()
    {
        return gridDraw;
    }

    /**
     * @param gridDraw the gridDraw to set
     */
    public void setGridDraw(double gridDraw)
    {
        this.gridDraw = gridDraw;
    }
}
