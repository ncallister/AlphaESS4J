/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class DayPowerJsonDecoder 
{
    public static final String KEY_DATA = "data";
    
    public static final String KEY_SYSTEM_SERIAL_NUMBER = "sysSn";
    public static final String KEY_DATE_TIME = "uploadTime";
    public static final String KEY_SOLAR = "ppv";
    public static final String KEY_LOAD = "load";
    public static final String KEY_BATTERY_PERCENT = "cbat";
    public static final String KEY_GRID_FEED_IN = "feedIn";
    public static final String KEY_GRID_DRAW = "gridCharge";
    public static final String KEY_EV_POWER = "pchargingPile";
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public DayPower decode(JSONArray source) throws ParseException, JSONException
    {
        DayPower sink = new DayPower();
        
        if (source.length() > 0)
        {
            sink.setSerialNo(source.getJSONObject(0).getString(KEY_SYSTEM_SERIAL_NUMBER));
        }
        
        Iterator<Object> dataIterator = source.iterator();
        while (dataIterator.hasNext())
        {
            Object next = dataIterator.next();
            if (next instanceof JSONObject)
            {
                sink.addData(decodePowerPoint((JSONObject)next));
            }
        }
        
        if (sink.dataPointCount() > 0)
        {
            sink.setDate(LocalDate.ofInstant(sink.getDataPoint(0).getTime(), ZoneId.systemDefault()));
        }
        
        return sink;
    }
    
    public PowerData decodePowerPoint(JSONObject source) throws ParseException, JSONException
    {
        PowerData sink = new PowerData();
        
        sink.setTime(DATE_FORMAT.parse(source.getString(KEY_DATE_TIME)).toInstant());
        sink.setPvPower(source.getDouble(KEY_SOLAR));
        sink.setLoad(source.getDouble(KEY_LOAD));
        sink.setBatteryCharge(source.getDouble(KEY_BATTERY_PERCENT));
        sink.setGridFeedIn(source.getInt(KEY_GRID_FEED_IN));
        sink.setGridDraw(source.getInt(KEY_GRID_DRAW));
        sink.setEvPower(source.getDouble(KEY_EV_POWER));
        
        // Calculate the battery power movement for this point
        sink.calculateBatteryPower();
        
        return sink;
    }
}
