/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j;

import au.org.ncallister.alphaess4j.responses.EssSystem;
import au.org.ncallister.alphaess4j.responses.PowerData;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class AlphaEssTestClientMain 
{
    private static final Logger LOG = LoggerFactory.getLogger(AlphaEssTestClientMain.class);
    
    public static void main(String[] args) throws Exception
    {
        Properties prop = new Properties();
        try (FileReader propertiesFile = new FileReader("client.properties"))
        {
            prop.load(propertiesFile);
        }
        
        AlphaEssClient client = new AlphaEssClient(prop.getProperty("appId"), prop.getProperty("appSecret"));
        
        List<EssSystem> systemList = client.getEssList();
        
        LOG.info(systemList.toString());
        
        
        for (EssSystem system : systemList)
        {
            PowerData powerData = client.getLastPowerData(system.getSystemSerial());
            LOG.info(powerData.toString());
            LOG.info("Power sum: {}", powerData.getPvPower() + powerData.getBatteryPower() - powerData.getLoad());
            
            client.getOneDateEnergyBySn(LocalDate.now(), system.getSystemSerial());
            client.getOneDayPowerBySn(LocalDate.now(), system.getSystemSerial());
        }
    }
}
