package com.common.helper;

import java.util.HashMap;
import java.util.Map;

public class StoredValues {

	private static Map<String, Object> values;

	static  
	{
		values = new HashMap<String, Object>();
	}

	public void store(String key, String value) 
	{
		values.put(key.toLowerCase().trim(), value);
	}

	public void store(String key, Object value) 
	{
		values.put(key.toLowerCase().trim(), value);
	}

	public String get(String key) 
	{
		return values.get(key.toLowerCase().trim()).toString();
	}
	
	public boolean containsKey(String key) 
	{
		return values.containsKey(key.toLowerCase().trim());
	}

	public Object getObject(String key) 
	{
		return values.get(key.toLowerCase().trim());
	}

}
