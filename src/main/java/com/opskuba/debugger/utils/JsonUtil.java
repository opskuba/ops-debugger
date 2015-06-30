package com.opskuba.debugger.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public abstract class JsonUtil {

	private static Gson gson = new GsonBuilder()
			.registerTypeAdapter(java.util.Date.class, new UtilDateSerializer())
			.registerTypeAdapter(java.sql.Timestamp.class, new UtilTimeStampSerializer())
			.registerTypeAdapter(java.sql.Date.class, new UtilSqlDateSerializer()).setDateFormat(DateFormat.LONG)
			.disableHtmlEscaping()
			.create();

	public static String toJson(Object protocolObj) {
		return gson.toJson(protocolObj);
	}

	public static Object fromJson(String json, Class<?> cls) {
		return gson.fromJson(json, cls);
	}

	public static Object fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

	private static class UtilDateSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {
		
		@Override
		public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
			return new JsonPrimitive(date.getTime());
		}

		@Override
		public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			return new Date(element.getAsJsonPrimitive().getAsLong());
		}
		
	}

	private static class UtilSqlDateSerializer implements JsonSerializer<java.sql.Date>,
			JsonDeserializer<java.sql.Date> {
		
		@Override
		public JsonElement serialize(java.sql.Date date, Type type, JsonSerializationContext context) {
			return new JsonPrimitive(date.getTime());
		}

		@Override
		public java.sql.Date deserialize(JsonElement element, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			return new java.sql.Date(element.getAsJsonPrimitive().getAsLong());
		}
		
	}

	private static class UtilTimeStampSerializer implements JsonSerializer<java.sql.Timestamp>,
			JsonDeserializer<java.sql.Timestamp> {
		
		@Override
		public JsonElement serialize(java.sql.Timestamp date, Type type, JsonSerializationContext context) {
			return new JsonPrimitive(date.getTime());
		}

		@Override
		public java.sql.Timestamp deserialize(JsonElement element, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			return new java.sql.Timestamp(element.getAsJsonPrimitive().getAsLong());
		}
		
	}
}
