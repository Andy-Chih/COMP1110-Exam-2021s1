package comp1110.exam;

import java.util.ArrayList;
import java.util.Locale;

public class Q1FindAllStarting {
    /**
     * Given an array of strings, find all strings that start with the target char
     * and return them in the order that they appear in the original array.
     * If there are no strings starting with the target char, or the input
     * array is null, return an empty array.
     * Uppercase and lowercase letters are not considered distinct.
     * For example:
     * given the input {"hello", "world", "hi", "comp1110"} and target 'h', the result is {"hello", "hi"};
     * given the input {"Hello", "help", "hi"} and target 'H', the result is {"Hello", "help", "hi"};
     * given the input {"hello", "world", "hi", "comp1110"} and target 'b' the result is {};
     *
     * @param in     An array of Strings
     * @param target the target char
     * @return all Strings whose first character is the target char.
     */
    public static String[] findAllStarting(String[] in, char target) {
        // FIXME complete this method

        String[] resultDemo={};
        if (in==null) return resultDemo;
        if (in.length==0) return resultDemo;
        // transfer char target-> lowercase char ----b-----
        String a = (target+"").toLowerCase();
        char b = a.charAt(0);

        ArrayList<String> demo = new ArrayList<>();
        for(String s: in){
            String n = s.toLowerCase();
            char[] charArr = n.toCharArray();
            if(charArr[0]==b){
                demo.add(s);
            }
        }

        String[] strArr = new String[demo.size()];
        for(int i=0; i<strArr.length;i++){
            strArr[i]=demo.get(i);
        }

        return strArr;
    }
}

