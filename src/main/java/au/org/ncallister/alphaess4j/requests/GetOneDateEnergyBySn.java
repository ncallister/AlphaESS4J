/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.requests;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 */
public class GetOneDateEnergyBySn extends HttpGet
{
    public static final String PATH = "/api/getOneDateEnergyBySn";
    
    public static final String PARAM_SYS_SN = "sysSn";
    public static final String PARAM_QUERY_DATE = "queryDate";
    
    private final LocalDate queryDate;
    private final String sysSn;
    
//    private final PowerDataJsonDecoder decoder = new PowerDataJsonDecoder();
    
    public GetOneDateEnergyBySn(URI hostUri, LocalDate queryDate, String sysSn) throws URISyntaxException
    {
        super(buildRequestUri(hostUri, queryDate, sysSn));
        
        this.sysSn = sysSn;
        this.queryDate = queryDate;
    }
    
    @Override
    public String toString()
    {
        return String.format("GET %s", super.getURI().toString());
    }
    
    private static URI buildRequestUri(URI hostUri, LocalDate queryDate, String sysSn) throws URISyntaxException
    {
        URIBuilder builder = new URIBuilder(hostUri);
        builder.setPath(PATH);
        builder.addParameter(PARAM_SYS_SN, sysSn);
        builder.addParameter(PARAM_QUERY_DATE, queryDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        
        return builder.build();
    }
    
//    public PowerData decodeResponseData(JSONObject responseData)
//    {
//        return decoder.decode(responseData);
//    }
}
