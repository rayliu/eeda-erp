/**
 * Copyright (c) 2011-2014, Ray Liu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;

/**
 * 认证并获取 access_token后，获取js ticket API
 * 
 */
public class JsTicketApi {
	//"https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi"
	private static String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	private static JsTicket ticket;
	
	public static JsTicket getJsTicket() {
		if (ticket != null && ticket.isAvailable())
			return ticket;
		
		refreshJsTicket();
		return ticket;
	}
	
	public static void refreshJsTicket() {
		ticket = requestJsTicket();
	}
	
	private static synchronized JsTicket requestJsTicket() {
		JsTicket result = null;
		for (int i=0; i<3; i++) {
			String accessToken = AccessTokenApi.getAccessToken().getAccessToken();
			Map<String, String> queryParas = ParaMap.create("access_token", accessToken)
					.put("type", "jsapi").getData();
			String json = HttpKit.get(url, queryParas);
			result = new JsTicket(json);
			
			if (result.isAvailable())
				break;
		}
		return result;
	}
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ApiConfig ac = new ApiConfig();
		ac.setAppId("wx04d6e8e83464b0b4");
		ac.setAppSecret("f67d4c549da09ab818f11f9e862b77e7");
		ApiConfigKit.setThreadLocalApiConfig(ac);
		
		JsTicket at = getJsTicket();
		if (at.isAvailable())
			System.out.println("JsTicket : " + at.getJsTicket());
		else
			System.out.println(at.getErrorCode() + " : " + at.getErrorMsg());
	}
}
