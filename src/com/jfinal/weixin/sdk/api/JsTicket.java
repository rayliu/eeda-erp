/**
 * Copyright (c) 2011-2015, Ray Liu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 封装 ticket
 */
public class JsTicket {
	
	private String ticket;	        // 正确获取到 ticket 时有值
	private Integer expires_in;		// 正确获取到 access_token 时有值
	private Integer errcode;		// 出错时有值
	private String errmsg;			// 出错时有值
	
	private Long expiredTime;		// 正确获取到 ticket 时有值，存放过期时间
	private String json;
	
	public JsTicket(String jsonStr) {
		this.json = jsonStr;
		
		try {
			@SuppressWarnings("rawtypes")
			Map map = new ObjectMapper().readValue(jsonStr, Map.class);
			ticket = (String)map.get("ticket");
			expires_in = (Integer)map.get("expires_in");
			errcode = (Integer)map.get("errcode");
			errmsg = (String)map.get("errmsg");
			
			if (expires_in != null)
				expiredTime = System.currentTimeMillis() + ((expires_in -5) * 1000);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getJson() {
		return json;
	}
	
	public boolean isAvailable() {
		if (expiredTime == null)
			return false;
		if (errcode != null && errcode>0)
			return false;
		if (expiredTime < System.currentTimeMillis())
			return false;
		return ticket != null;
	}
	
	public String getJsTicket() {
		return ticket;
	}
	
	public Integer getExpiresIn() {
		return expires_in;
	}
	
	public Integer getErrorCode() {
		return errcode;
	}
	
	public String getErrorMsg() {
		if (errcode != null) {
			String result = ReturnCode.get(errcode);
			if (result != null)
				return result;
		}
		return errmsg;
	}
}
