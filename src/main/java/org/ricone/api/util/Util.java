package org.ricone.api.util;

import java.math.BigDecimal;

/**
 * @project: ricone
 * @author: Dan on 9/22/2017.
 */
public class Util
{
    public static String bigDecimalConverter(BigDecimal bigDecimal)
    {
        if(bigDecimal != null)
        {
            return bigDecimal.toString();
        }
        return null;
    }
}
