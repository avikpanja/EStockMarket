package com.estock.company.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class Util {

	public static Object convertObject(Object object, Class cls) throws InstantiationException, IllegalAccessException {
		ObjectMapper mapper = new ObjectMapper();
		Object obj = mapper.convertValue(object, cls);
		return obj;
	}
}
