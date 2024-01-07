/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

/**
 *
 */
public class EssSystem 
{
    private double batteryCapacity;
    private EmsStatus emsStatus;
    private String batteryModel;
    private String inverterModel;
    private double inverterNominalPower;
    private double pvNominalPower;
    /**
     * Actual usable battery capacity.
     */
    private double batteryCapacityRemaining;
    private String systemSerial;
    private double batteryAvailablePercentage;

    @Override
    public String toString()
    {
        return "EssSystem{" + 
               "batteryCapacity=" + batteryCapacity + 
               ", emsStatus=" + emsStatus + 
               ", batteryModel=" + batteryModel + 
               ", inverterModel=" + inverterModel + 
               ", inverterNominalPower=" + inverterNominalPower + 
               ", pvNominalPower=" + pvNominalPower +
               ", batteryCapacityRemaining=" + batteryCapacityRemaining + 
               ", systemSerial=" + systemSerial + 
               ", batteryAvailablePercentage=" + batteryAvailablePercentage + 
               '}';
    }

    /**
     * @return the batteryCapacity
     */
    public double getBatteryCapacity()
    {
        return batteryCapacity;
    }

    /**
     * @param batteryCapacity the batteryCapacity to set
     */
    public void setBatteryCapacity(double batteryCapacity)
    {
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * @return the emsStatus
     */
    public EmsStatus getEmsStatus()
    {
        return emsStatus;
    }

    /**
     * @param emsStatus the emsStatus to set
     */
    public void setEmsStatus(EmsStatus emsStatus)
    {
        this.emsStatus = emsStatus;
    }

    /**
     * @return the batteryModel
     */
    public String getBatteryModel()
    {
        return batteryModel;
    }

    /**
     * @param batteryModel the batteryModel to set
     */
    public void setBatteryModel(String batteryModel)
    {
        this.batteryModel = batteryModel;
    }

    /**
     * @return the inverterModel
     */
    public String getInverterModel()
    {
        return inverterModel;
    }

    /**
     * @param inverterModel the inverterModel to set
     */
    public void setInverterModel(String inverterModel)
    {
        this.inverterModel = inverterModel;
    }

    /**
     * @return the inverterNominalPower
     */
    public double getInverterNominalPower()
    {
        return inverterNominalPower;
    }

    /**
     * @param inverterNominalPower the inverterNominalPower to set
     */
    public void setInverterNominalPower(double inverterNominalPower)
    {
        this.inverterNominalPower = inverterNominalPower;
    }

    /**
     * @return the pvNominalPower
     */
    public double getPvNominalPower()
    {
        return pvNominalPower;
    }

    /**
     * @param pvNominalPower the pvNominalPower to set
     */
    public void setPvNominalPower(double pvNominalPower)
    {
        this.pvNominalPower = pvNominalPower;
    }

    /**
     * @return the batteryCapacityRemaining
     */
    public double getBatteryCapacityRemaining()
    {
        return batteryCapacityRemaining;
    }

    /**
     * @param batteryCapacityRemaining the batteryCapacityRemaining to set
     */
    public void setBatteryCapacityRemaining(double batteryCapacityRemaining)
    {
        this.batteryCapacityRemaining = batteryCapacityRemaining;
    }

    /**
     * @return the systemSerial
     */
    public String getSystemSerial()
    {
        return systemSerial;
    }

    /**
     * @param systemSerial the systemSerial to set
     */
    public void setSystemSerial(String systemSerial)
    {
        this.systemSerial = systemSerial;
    }

    /**
     * @return the batteryAvailablePercentage
     */
    public double getBatteryAvailablePercentage()
    {
        return batteryAvailablePercentage;
    }

    /**
     * @param batteryAvailablePercentage the batteryAvailablePercentage to set
     */
    public void setBatteryAvailablePercentage(double batteryAvailablePercentage)
    {
        this.batteryAvailablePercentage = batteryAvailablePercentage;
    }
}
