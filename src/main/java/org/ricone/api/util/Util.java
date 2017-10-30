package org.ricone.api.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static boolean isGradeGreater(String grade, String grade2)
    {
        if(getGradeAsInt(grade) > getGradeAsInt(grade2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int getGradeAsInt(String grade)
    {
        if(grade.equalsIgnoreCase("IT")) //Infant/toddler
        {
            return -4;
        }
        else if(grade.equalsIgnoreCase("PR")) //Preschool
        {
            return -3;
        }
        else if(grade.equalsIgnoreCase("PK"))
        {
            return -2;
        }
        else if(grade.equalsIgnoreCase("TK")) //Transitional Kindergarten
        {
            return -1;
        }
        else if(grade.equalsIgnoreCase("KG"))
        {
            return 0;
        }
        else if(grade.equalsIgnoreCase("PS")) //Postsecondary
        {
            return 14;
        }
        else if(grade.equalsIgnoreCase("UG")) //Ungraded
        {
            return 15;
        }
        else if(isInteger(grade)) // String int to int int :P
        {
            return Integer.parseInt(grade);
        }
        else
        {
            return 16;
        }
    }

    public static boolean isInteger(String s)
    {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix)
    {
        if(s.isEmpty())
        {
            return false;
        }

        for(int i = 0; i < s.length(); i++)
        {
            if(i == 0 && s.charAt(i) == '-')
            {
                if(s.length() == 1)
                {
                    return false;
                }
                else
                {
                    continue;
                }
            }

            if(Character.digit(s.charAt(i),radix) < 0)
            {
                return false;
            }
        }
        return true;
    }

    public static String get2DigitYear(Date d)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(d).substring(2, 4);
    }
}
