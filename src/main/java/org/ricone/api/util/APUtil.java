package org.ricone.api.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.model.core.extension.userpassword.EntityType;

import java.util.HashMap;

public class APUtil
{
    public static boolean doesUsernameRequireIncrement(EntityType entityType, HashMap<String, String> kv) {
        String key;
        if(entityType.equals(EntityType.STAFF)){
            key = "api.aupp.username.method.staff";
        }
        else {
            key = "api.aupp.username.method.student";
        }
        String value = MapUtils.getString(kv, key);
        return StringUtils.equalsIgnoreCase(value, "FNI+LN") || StringUtils.equalsIgnoreCase(value, "LN+FNI");
    }
}
