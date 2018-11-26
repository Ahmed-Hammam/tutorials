package com.example.apigateway.filter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//https://stackoverflow.com/questions/36461493/customizing-zuul-exception

@Component
public class UppercaseRequestEntityFilter extends ZuulFilter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String filterType() {
		return "pre";
	}

	public int filterOrder() {
		return 6;
	}

	public boolean shouldFilter() {
		/*RequestContext context = RequestContext.getCurrentContext();
		return context.get("proxy").equals("service-one");*/
		return true;
	}
	
	private String getBody(final RequestContext context) throws Exception {
		InputStream in = (InputStream) context.get("requestEntity");
		if (in == null) {
			in = context.getRequest().getInputStream();
		}
		return StreamUtils.copyToString(in, Charset.forName("UTF-8"));
	}

	public Object run() {
		try {
			RequestContext context = RequestContext.getCurrentContext();			
			HttpServletResponse response = context.getResponse();
			if (context.getRequest().getMethod().equals(HttpMethod.OPTIONS.toString())) {
				return null;
			}
			if(context.getRequest().getHeader("Authorization")==null) {
//				return new ResponseEntity<>("you are not authorized to access this resource!",HttpStatus.UNAUTHORIZED);
				response.sendError(401,"you are not authorized to access this resource!");
				return response;
			}else {
				
			}
			
//			String body = getBody(context);
			
//			JsonParser parser = new JsonParser();
//			JsonObject json = parser.parse(body).getAsJsonObject();
//			json.addProperty("firstname", json.get("firstname").toString().toUpperCase().replace("\"", StringUtils.EMPTY));
//			json.addProperty("lastname", json.get("lastname").toString().toUpperCase().replace("\"", StringUtils.EMPTY));
			
//			context.set("requestEntity", new ByteArrayInputStream(json.toString().getBytes("UTF-8")));
			
		}
		catch (Exception ex) {
			logger.error("Error", ex);
		}
		return null;
	}
}
