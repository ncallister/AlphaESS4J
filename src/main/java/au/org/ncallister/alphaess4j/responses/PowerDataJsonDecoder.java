/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

import java.time.Instant;
import org.json.JSONObject;

/**
 *
 */
public class PowerDataJsonDecoder 
{
    public static final String KEY_BATTERY_CHARGE = "soc";
    public static final String KEY_PV_POWER = "ppv";
    public static final String KEY_EV_POWER = "pev";
    public static final String KEY_GRID_POWER = "pgrid";
    public static final String KEY_BATTERY_POWER = "pbat";
    public static final String KEY_LOAD = "pload";
    
    public PowerData decode(JSONObject source)
    {
        PowerData sink = new PowerData();
        
        sink.setTime(Instant.now());
        sink.setBatteryCharge(source.getDouble(KEY_BATTERY_CHARGE));
        sink.setPvPower(source.getDouble(KEY_PV_POWER));
        sink.setEvPower(source.getDouble(KEY_EV_POWER));
        sink.setGridPower(source.getDouble(KEY_GRID_POWER));
        sink.setBatteryPower(source.getDouble(KEY_BATTERY_POWER));
        sink.setLoad(source.getDouble(KEY_LOAD));
        
        return sink;
    }
}
