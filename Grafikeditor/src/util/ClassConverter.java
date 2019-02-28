package util;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ClassConverter {

	public static Object createInstance(Class<?> type, String content) {
		if (content.equals("null"))
			return null;
		if (type.equals(Integer.class))
			return new Integer(content);
		if (type.equals(Color.class))
			return stringToColor(content);
		if (type.getName().equals("boolean")) {
			return content == "true";
		}
		return content;
	}

	public static Color stringToColor(String content) {
		String[] spliceVals = content.split(",");
		int r = Integer.parseInt(spliceVals[0].split("=")[1]);
		int g = Integer.parseInt(spliceVals[1].split("=")[1]);
		int b = Integer.parseInt(spliceVals[2].split("=")[1].split("]")[0]);

		return new Color(r, g, b);
	}

	public static Field getDeclaredFieldOfClassAndSuperclass(String field, Class className) throws Exception {
		ArrayList<Field> fields = new ArrayList<>();
		for (Class<?> c = className; c != null; c = c.getSuperclass()) {
			for (Field fieldOfClass : c.getDeclaredFields()) {
				fields.add(fieldOfClass);
			}
		}
		for (Field fieldOfFields : fields) {
			if (fieldOfFields.getName().equals(field)) {
				return fieldOfFields;
			}
			;
		}
		return null;
	}

}
