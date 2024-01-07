/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.requests;

import au.org.ncallister.alphaess4j.responses.PowerData;
import au.org.ncallister.alphaess4j.responses.PowerDataJsonDecoder;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

/**
 *
 */
public class GetLastPowerData extends HttpGet
{
    public static final String PATH = "/api/getLastPowerData";
    
    public static final String PARAM_SYS_SN = "sysSn";
    
    private final String sysSn;
    
    private final PowerDataJsonDecoder decoder = new PowerDataJsonDecoder();
    
    public GetLastPowerData(URI hostUri, String sysSn) throws URISyntaxException
    {
        super(buildRequestUri(hostUri, sysSn));
        
        this.sysSn = sysSn;
    }
    
    @Override
    public String toString()
    {
        return String.format("GET %s", super.getURI().toString());
    }
    
    private static URI buildRequestUri(URI hostUri, String sysSn) throws URISyntaxException
    {
        URIBuilder builder = new URIBuilder(hostUri);
        builder.setPath(PATH);
        builder.addParameter(PARAM_SYS_SN, sysSn);
        
        return builder.build();
    }
    
    public PowerData decodeResponseData(JSONObject responseData)
    {
        return decoder.decode(responseData);
    }
}
