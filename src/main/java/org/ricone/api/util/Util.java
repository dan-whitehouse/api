package org.ricone.api.util;

/**
 * @project: ricone
 * @author: Dan on 9/27/2017.
 */
public class Util
{
    public static boolean isRefId(String refId)
    {
        return refId.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}");
    }
}
