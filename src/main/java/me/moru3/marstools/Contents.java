package me.moru3.marstools;

import java.util.List;
import java.util.regex.Pattern;

public class Contents {
    public static boolean contains(List<String> list, Pattern pattern) {
        for (String s : list) {
            if (pattern.matcher(s).matches()) { return true; }
        }
        return false;
    }
}
