/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.responses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class DayPower 
{
    private String serialNo;
    private LocalDate date;
    private List<PowerData> data = new ArrayList<>();

    @Override
    public String toString()
    {
        return "DayPower{" + "serialNo=" + serialNo + ", date=" + date + ", data=" + data + '}';
    }

    /**
     * @return the date
     */
    public LocalDate getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    
    public int dataPointCount()
    {
        return data.size();
    }
    
    public PowerData getDataPoint(int index)
    {
        return new PowerData(data.get(index));
    }

    /**
     * @return the data
     */
    public List<PowerData> getData()
    {
        return data.stream().map(i -> new PowerData(i)).collect(Collectors.toList());
    }

    /**
     * @param data the data to set
     */
    public void setData(List<PowerData> data)
    {
        this.data = new ArrayList<>(data);
    }
    
    public void addData(PowerData newData)
    {
        data.add(newData);
    }
    
    public void sortData()
    {
        data.sort((c1, c2) -> c1.getTime().compareTo(c2.getTime()));
    }

    /**
     * @return the serialNo
     */
    public String getSerialNo()
    {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }
}
