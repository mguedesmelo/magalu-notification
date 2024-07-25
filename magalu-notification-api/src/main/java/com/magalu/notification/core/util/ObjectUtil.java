package com.magalu.notification.core.util;

import org.springframework.util.ObjectUtils;

public class ObjectUtil {
	private ObjectUtil() {
		// Empty
	}

	public static boolean contains(Object object, Object... objectValues) {
		return ObjectUtils.containsElement(objectValues, object);
	}

    public static boolean isNotEmpty(Object object) {
        if (ObjectUtils.isArray(object)) {
            return !ObjectUtils.isEmpty((Object[]) object);
        }
        return !ObjectUtils.isEmpty(object);
    }

    public static boolean isEmpty(Object object) {
        return !isNotEmpty(object);
    }

    public static boolean hasSomeEmptyObject(Object... objects) {
    	for (Object object : objects) {
    		if (ObjectUtil.isEmpty(object)) {
    			return true;
    		}
    	}
    	return false;
    }
}
