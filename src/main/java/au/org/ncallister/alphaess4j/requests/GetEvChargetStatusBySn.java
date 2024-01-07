/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.requests;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 */
public class GetEvChargetStatusBySn extends HttpGet
{
    public static final String PATH = "/api/getEvChargerStatusBySn";
    
    public static final String PARAM_SYS_SN = "sysSn";
    public static final String PARAM_EVCHARGER_SN = "evchargerSn";
    
    private final String sysSn;
    private final String evchargerSn;
    
    public GetEvChargetStatusBySn(URI hostUri, String sysSn, String evchargerSn) throws URISyntaxException
    {
        super(buildRequestUri(hostUri, sysSn, evchargerSn));
        
        this.sysSn = sysSn;
        this.evchargerSn = evchargerSn;
    }
    
    @Override
    public String toString()
    {
        return String.format("GET %s", super.getURI().toString());
    }
    
    private static URI buildRequestUri(URI hostUri, String sysSn, String evchargerSn) throws URISyntaxException
    {
        URIBuilder builder = new URIBuilder(hostUri);
        builder.setPath(PATH);
        builder.addParameter(PARAM_SYS_SN, sysSn);
        builder.addParameter(PARAM_EVCHARGER_SN, evchargerSn);
        
        return builder.build();
    }
}
