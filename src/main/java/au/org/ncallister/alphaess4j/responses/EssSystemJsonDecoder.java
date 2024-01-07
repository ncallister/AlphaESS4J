/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

import org.json.JSONObject;

/**
 *
 */
public class EssSystemJsonDecoder 
{
    public static final String KEY_BATTERY_CAPACITY = "cobat";
    public static final String KEY_EMS_STATUS = "emsStatus";
    public static final String KEY_BATTERY_MODEL = "mbat";
    public static final String KEY_INVERTER_MODEL = "minv";
    public static final String KEY_INVERTER_POWER = "poinv";
    public static final String KEY_PV_POWER = "popv";
    public static final String KEY_BATTERY_REMAINING = "surplusCobat";
    public static final String KEY_SYSTEM_SERIAL = "sysSn";
    public static final String KEY_BATTERY_AVAILABLE_PERCENT = "usCapacity";
    
    public EssSystem decode(JSONObject source)
    {
        EssSystem sink = new EssSystem();
        
        sink.setBatteryCapacity(source.getDouble(KEY_BATTERY_CAPACITY));
        sink.setEmsStatus(EmsStatus.byApiString(source.getString(KEY_EMS_STATUS)));
        sink.setBatteryModel(source.getString(KEY_BATTERY_MODEL));
        sink.setInverterModel(source.getString(KEY_INVERTER_MODEL));
        sink.setInverterNominalPower(source.getDouble(KEY_INVERTER_POWER));
        sink.setPvNominalPower(source.getDouble(KEY_PV_POWER));
        sink.setBatteryCapacityRemaining(source.getDouble(KEY_BATTERY_REMAINING));
        sink.setSystemSerial(source.getString(KEY_SYSTEM_SERIAL));
        sink.setBatteryAvailablePercentage(source.getDouble(KEY_BATTERY_AVAILABLE_PERCENT));
        
        return sink;
    }
}
