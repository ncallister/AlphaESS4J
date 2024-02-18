/*
 * Nathan Callister 2024.
 */

package au.org.ncallister.alphaess4j;

import au.org.ncallister.alphaess4j.requests.GetEssList;
import au.org.ncallister.alphaess4j.requests.GetEvChargetStatusBySn;
import au.org.ncallister.alphaess4j.requests.GetLastPowerData;
import au.org.ncallister.alphaess4j.requests.GetOneDateEnergyBySn;
import au.org.ncallister.alphaess4j.requests.GetOneDayPowerBySn;
import au.org.ncallister.alphaess4j.responses.DayPower;
import au.org.ncallister.alphaess4j.responses.EssSystem;
import au.org.ncallister.alphaess4j.responses.PowerData;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.io.EmptyInputStream;
import org.apache.http.message.AbstractHttpMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class AlphaEssClient 
{
    private static final Logger LOG = LoggerFactory.getLogger(AlphaEssClient.class);
    
    public static final String ALPHA_CLOUD = "openapi.alphaess.com";
    public static final String SCHEME = "https";
    
    public static final String HEADER_APP_ID = "appId";
    public static final String HEADER_TIMESTAMP = "timeStamp";
    public static final String HEADER_SIGN = "sign";
    
    /// Response body keys
    public static final String RKEY_CODE = "code";
    public static final String RKEY_MESSAGE = "msg";
    public static final String RKEY_EXP_MESSAGE = "expMsg";
    public static final String RKEY_DATA = "data";
    
    private CloseableHttpClient client;
    private URI hostUri;
    
    private final String appId;
    private final String appSecret;
    
    public AlphaEssClient(String appId, String appSecret) throws URISyntaxException
    {
        this.appId = appId;
        this.appSecret = appSecret;
        
        buildHostUri();
    }
    
    private void buildHostUri() throws URISyntaxException
    {
        URIBuilder builder = new URIBuilder();
        builder.setScheme(SCHEME);
        builder.setHost(ALPHA_CLOUD);
        builder.setPath("/");
        hostUri = builder.build();
    }
    
    private void initClient()
    {
//        CredentialsProvider credProv = new BasicCredentialsProvider();
//        credProv.setCredentials(new AuthScope(new HttpHost(ALPHA_CLOUD, 443, SCHEME)), 
//                                new UsernamePasswordCredentials(username, password));
////        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
////        sslContext.init(null, new TrustManager[]{new NaiveX509TrustManager()}, null);
////        client = HttpClients.custom().setDefaultCredentialsProvider(credProv).setSSLContext(sslContext).build();
//        client = HttpClients.custom().setDefaultCredentialsProvider(credProv).build();
////        client = HttpClients.createDefault();
        
        client = HttpClients.createDefault();
    }
    
    private String getSignHeader(long timeStamp) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        digest.update(String.format("%s%s%d", appId, appSecret, timeStamp).getBytes(StandardCharsets.UTF_8));
        return String.format("%0128x", new BigInteger(1, digest.digest()));
    }
    
    private void addAuthHeaders(AbstractHttpMessage message) throws NoSuchAlgorithmException
    {
        long timeStamp = Instant.now().getEpochSecond();
        message.addHeader(HEADER_APP_ID, appId);
        message.addHeader(HEADER_TIMESTAMP, String.valueOf(timeStamp));
        message.addHeader(HEADER_SIGN, getSignHeader(timeStamp));
    }
    
    private String getResponseBody(String requestString, CloseableHttpResponse response) throws IOException
    {
        int status = response.getStatusLine().getStatusCode();
        String responseBody = "";
        if (response.getEntity() != null && !(response.getEntity().getContent() instanceof EmptyInputStream))
        {
            try (Scanner scanner = new Scanner(response.getEntity().getContent()))
            {
                responseBody = scanner.useDelimiter("\\A").next();
            }
        }
        if (status < 200 || status > 299)
        {
            LOG.debug("~~~~~~");
            LOG.debug("Failed request!");
            LOG.debug("~~~~~~");
            LOG.debug(requestString);
            LOG.debug("~~~~~~");
            LOG.debug("status: {} - {}", status, responseBody);
            LOG.debug("~~~~~~");
            throw new IOException(String.format("%s: %d - %s", requestString, status, responseBody));
        }
        else
        {
            LOG.debug("~~~~~~");
            LOG.debug("Success");
            LOG.debug("~~~~~~");
            LOG.debug(requestString);
            LOG.debug("~~~~~~");
            LOG.debug(responseBody);
            LOG.debug("~~~~~~");
        }
        
        return responseBody;
    }
    
    public List<EssSystem> getEssList() throws URISyntaxException, NoSuchAlgorithmException, IOException
    {
        if (client == null)
        {
            initClient();
        }
        
        GetEssList request = new GetEssList(hostUri);
        addAuthHeaders(request);
        
        try (CloseableHttpResponse response = client.execute(request))
        {
            String responseBody = getResponseBody(request.toString(), response);
            
            JSONObject responseData = new JSONObject(responseBody);
            
            int code = responseData.getInt(RKEY_CODE);
            
            if (code != 200)
            {
                throw new IOException(String.format("%s: %s", request.toString(), responseBody));
            }
            
            return request.decodeResponseData(responseData.getJSONArray(RKEY_DATA));
        }
    }
    
    public void getChargingPileStatusBySn(String sysSn, String evchargerSn) throws URISyntaxException, NoSuchAlgorithmException, IOException
    {
        if (client == null)
        {
            initClient();
        }
        
        GetEvChargetStatusBySn request = new GetEvChargetStatusBySn(hostUri, sysSn, evchargerSn);
        addAuthHeaders(request);
        
        try (CloseableHttpResponse response = client.execute(request))
        {
            String responseBody = getResponseBody(request.toString(), response);
            
            // TODO
//            return new JSONObject(responseBody);
        }
    }
    
    public PowerData getLastPowerData(String sysSn) throws URISyntaxException, NoSuchAlgorithmException, IOException
    {
        if (client == null)
        {
            initClient();
        }
        
        GetLastPowerData request = new GetLastPowerData(hostUri, sysSn);
        addAuthHeaders(request);
        
        try (CloseableHttpResponse response = client.execute(request))
        {
            String responseBody = getResponseBody(request.toString(), response);
            
            JSONObject responseData = new JSONObject(responseBody);
            
            int code = responseData.getInt(RKEY_CODE);
            
            if (code != 200)
            {
                throw new IOException(String.format("%s: %s", request.toString(), responseBody));
            }
            
            return request.decodeResponseData(responseData.getJSONObject(RKEY_DATA));
        }
    }
    
    public void getOneDateEnergyBySn(LocalDate date, String sysSn) throws URISyntaxException, NoSuchAlgorithmException, IOException
    {
        if (client == null)
        {
            initClient();
        }
        
        GetOneDateEnergyBySn request = new GetOneDateEnergyBySn(hostUri, date, sysSn);
        addAuthHeaders(request);
        
        try (CloseableHttpResponse response = client.execute(request))
        {
            String responseBody = getResponseBody(request.toString(), response);
            
            JSONObject responseData = new JSONObject(responseBody);
            
            int code = responseData.getInt(RKEY_CODE);
            
            if (code != 200)
            {
                throw new IOException(String.format("%s: %s", request.toString(), responseBody));
            }
            
            // TODO
//            return request.decodeResponseData(responseData.getJSONObject(RKEY_DATA));
        }
    }
    
    public DayPower getOneDayPowerBySn(LocalDate date, String sysSn) 
            throws URISyntaxException, NoSuchAlgorithmException, IOException, JSONException, ParseException
    {
        if (client == null)
        {
            initClient();
        }
        
        GetOneDayPowerBySn request = new GetOneDayPowerBySn(hostUri, date, sysSn);
        addAuthHeaders(request);
        
        try (CloseableHttpResponse response = client.execute(request))
        {
            String responseBody = getResponseBody(request.toString(), response);
            
            JSONObject responseData = new JSONObject(responseBody);
            
            int code = responseData.getInt(RKEY_CODE);
            
            if (code != 200)
            {
                throw new IOException(String.format("%s: %s", request.toString(), responseBody));
            }
            
            return request.decodeResponseData(responseData.getJSONArray(RKEY_DATA));
        }
    }
}
