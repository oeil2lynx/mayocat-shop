package org.mayocat.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @version $Id$
 */
public enum AddonFieldType
{

    STRING,
    HTML,
    JSON;

    @JsonValue
    public String toJson()
    {
        return name().toLowerCase();
    }

    @JsonCreator
    public static AddonFieldType fromJson(String text)
    {
        return valueOf(text.toUpperCase());
    }
}
