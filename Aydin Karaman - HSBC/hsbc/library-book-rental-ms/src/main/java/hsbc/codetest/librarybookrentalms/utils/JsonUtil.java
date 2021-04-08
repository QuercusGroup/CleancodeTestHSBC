package hsbc.codetest.librarybookrentalms.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JsonUtil {

	private static final Logger logger = LogManager.getLogger(JsonUtil.class);

	private JsonUtil() {
	}

	public static String mapToString(Object object) {
		String jsonString = null;

		if (object != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return jsonString;
	}

	public static String mapToString(Object object, String filter, String... propertyArray) {
		String jsonString = null;

		if (object != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setFilterProvider(new SimpleFilterProvider().addFilter(filter,
					SimpleBeanPropertyFilter.serializeAllExcept(propertyArray)));
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return jsonString;
	}

	public static String mapToString(Object object, Class<?> jsonView) {
		String jsonString = null;

		if (object != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				jsonString = mapper.writerWithView(jsonView).writeValueAsString(object);
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return jsonString;
	}

	public static <T> T mapToObject(String jsonString, Class<T> clazz) {
		T result = null;
		if (jsonString != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				result = mapper.readValue(jsonString, clazz);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return result;
	}

	public static <T> T mapToTypedObject(String jsonString, final TypeReference<T> type) {
		T result = null;
		if (jsonString != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				result = mapper.readValue(jsonString, type);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return result;
	}

}
