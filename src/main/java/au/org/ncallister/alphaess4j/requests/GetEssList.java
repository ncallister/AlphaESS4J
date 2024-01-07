/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j.requests;

import au.org.ncallister.alphaess4j.responses.EssSystem;
import au.org.ncallister.alphaess4j.responses.EssSystemJsonDecoder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;

/**
 *
 */
public class GetEssList extends HttpGet
{
    public static final String PATH = "/api/getEssList";
    
    private final EssSystemJsonDecoder decoder = new EssSystemJsonDecoder();
    
    public GetEssList(URI hostUri) throws URISyntaxException
    {
        super(buildRequestUri(hostUri));
    }
    
    @Override
    public String toString()
    {
        return String.format("GET %s", super.getURI().toString());
    }
    
    private static URI buildRequestUri(URI hostUri) throws URISyntaxException
    {
        URIBuilder builder = new URIBuilder(hostUri);
        builder.setPath(PATH);
        
        return builder.build();
    }
    
    public List<EssSystem> decodeResponseData(JSONArray responseData)
    {
        List<EssSystem> sink = new ArrayList<>();
        
        for (int i = 0 ; i < responseData.length() ; ++i)
        {
            sink.add(decoder.decode(responseData.getJSONObject(i)));
        }
        
        return sink;
    }
}
