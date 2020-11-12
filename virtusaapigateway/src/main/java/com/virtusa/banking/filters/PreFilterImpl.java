package com.virtusa.banking.filters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Component
public class PreFilterImpl extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(PreFilterImpl.class);
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${authUrl}")
	private String authUrl;
	
    @Override
    public String filterType() {
        return "pre";
    }
   
    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
    	
    	RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = ctx.getRequest();
        log.info("Entering pre filter........");
        log.info( servletRequest.getRemoteAddr());
        log.info("PreFilter: " + String.format("%s request to %s",  servletRequest.getMethod(), servletRequest.getRequestURL().toString()));
        //http://localhost:8765/api/customer?userName=luke&userPwd=admin@123
        Map<String,List<String>> params=ctx.getRequestQueryParams();
        List<String> data =params.values().stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());  
        
        log.info(data.get(0),data.get(1));
        String token=null;      
        String trimmedToken=null;
    	//Redirect to JWT token
    	JwtRequest jwtRequest=new JwtRequest();
    	jwtRequest.setUserName(data.get(0));
    	jwtRequest.setUserPwd(data.get(1));
    	log.info(authUrl);
    	try
    	{
    	HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity request = new HttpEntity<>(jwtRequest,headers);
 	        //phase 1 get jwt token
 	    ResponseEntity<?> authResponse=restTemplate.
 		      postForEntity(authUrl+"signin",request, String.class);
 	    log.info(authResponse.getBody().toString());
 	     
 	   JsonObject jsonObject = JsonParser.parseString(authResponse.getBody().toString()).getAsJsonObject();
 	  Set<Map.Entry<String, JsonElement>> entries  = jsonObject.entrySet();
		for (Map.Entry<String, JsonElement> entry : entries) {
			System.out.println(entry.getKey() + "," + entry.getValue());
			if(entry.getKey().equals("token"))
			{
				token=entry.getValue().toString();
				break;
			}
		}
 	    
 	    
 	       log.info("Token:"+token); 	       
 	       
 	      trimmedToken = token.replaceAll("^\"|\"$", "");
 			//phase 2
 	       //Verification
 	       
 	       headers = new HttpHeaders();
 	       headers.setContentType(MediaType.APPLICATION_JSON);
 	      headers.set("Authorization", "Bearer "+trimmedToken);
 	 	
 		 request = new HttpEntity<String>(null,headers);
	 
		 ResponseEntity<String> responseEntityStr = restTemplate.
          exchange(authUrl+"greet/user",HttpMethod.GET, request,
		  String.class); 
		 System.out.println(responseEntityStr.getBody());
		 log.info("token : {} Verification Passed", trimmedToken);
         
         //Routing requests
         ctx.setSendZuulResponse(true);
    	}
    	catch(Exception exception)
    	{
    		log.error("token : {} Validation failed" , trimmedToken );
            //Do not route requests
            ctx.setSendZuulResponse(false);
            responseError(ctx, -403, "invalid token");
    	}
    	
             
                
               
        return null;
    }
    
    /**
     * Response Exception Information to Front End
     */
    private void responseError(RequestContext ctx, int code, String message) {
        HttpServletResponse response = ctx.getResponse();
        JwtResponse errResult = new JwtResponse(code+"->"+message);
       
        ctx.setResponseBody(toJsonString(errResult));
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
    }

    private String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("json Serialization failed", e);
            return null;
        }
    }
    



}