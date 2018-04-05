package net.thumbtack.school.old.base;

import java.util.Locale;

public class StringOperations {
    public static int getSummaryLength(String[] strings){
        int length = 0;
        for (String string : strings) {
            length+= string.length();
        }
        return length;
    }
    public static String getFirstAndLastLetterString(String string){
        return String.valueOf(string.charAt(0)) + String.valueOf(string.charAt(string.length()-1));
    }
    public static boolean isSameCharAtPosition(String string1, String string2, int index){
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character){
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character){
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }
    public static boolean isSameFirstStringPosition(String string1, String string2, String str){
        return string1.indexOf(str) == string2.indexOf(str);
    }
    public static boolean isSameLastStringPosition(String string1, String string2, String str){
        return string1.lastIndexOf(str) == string2.indexOf(str);
    }
    public static boolean isEqual(String string1, String string2){
        return string1.equals(string2);
    }
    public static boolean isEqualIgnoreCase(String string1, String string2){
        return string1.equalsIgnoreCase(string2);
    }
    public static boolean isLess(String string1, String string2){
        return string1.compareTo(string2) < 0;
    }
    public static boolean isLessIgnoreCase(String string1, String string2){
        return string1.compareToIgnoreCase(string2) < 0;
    }
    public static String concat(String string1, String string2){
        return string1.concat(string2);
    }
    public static boolean isSamePrefix(String string1, String string2, String prefix){
        return string1.startsWith(prefix) == string2.startsWith(prefix);
    }
    public static boolean isSameSuffix(String string1, String string2, String suffix){
        return string1.endsWith(suffix) == string2.endsWith(suffix);
    }
    public static String getCommonPrefix(String string1, String string2){
        int length = string1.length() < string2.length() ? string1.length() : string2.length();
        for (int i = 0; i < length; i++) {
            if(!(string1.charAt(i) == string2.charAt(i))) {
                return string1.substring(0,i);
            }
        }
        return string1.length() > string2.length() ? string2 : string1;
    }
    public static boolean isPalindrome(String string){
        for (int i = 0; i < string.length()/2; i++) {
            if(!(string.charAt(i) == string.charAt(string.length()-1-i))){
                return false;
            }
        }
        return true;
    }
    public static boolean isPalindromeIgnoreCase(String string){
        return isPalindrome(string.toLowerCase());
    }
    public static String getLongestPalindromeIgnoreCase(String[] strings){
        String palindrome = "";
        for (String string : strings) {
            if(isPalindromeIgnoreCase(string) && palindrome.length() < string.length()){
                palindrome = string;
            }
        }
        return palindrome;
    }
    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        return string1.length() >= length + index && string2.length() >= length + index && string1.substring(index, length).equals(string2.substring(index, length));
    }
    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2){

        return string1.replaceAll(String.valueOf(replaceInStr1),String.valueOf(replaceByInStr1)).
                equals(string2.replaceAll(String.valueOf(replaceInStr2),String.valueOf(replaceByInStr2)));

    }
    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2){

        return string1.replaceAll(replaceInStr1,replaceByInStr1).equals(string2.replaceAll(replaceInStr2,replaceByInStr2));
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string){
        string = string.replaceAll(" ", "");
        return isPalindromeIgnoreCase(string);
    }
    public static boolean isEqualAfterTrimming(String string1, String string2){
        return string1.trim().equals(string2.trim());
    }
    public static String makeCsvStringFromInts(int[] array){
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i : array) {
            stringBuilder.append(i + ",");
        }
        if(stringBuilder.length()>0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array){
        StringBuilder stringBuilder = new StringBuilder("");
        for (double d : array) {
            String s = String.format(Locale.ENGLISH, "%(.2f",d);
            stringBuilder.append(s+",");
        }
        if(stringBuilder.length()>0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
    public static StringBuilder makeCsvStringBuilderFromInts(int[] array){
        return new StringBuilder(makeCsvStringFromInts(array));
    }
    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array){
        return new StringBuilder(makeCsvStringFromDoubles(array));
    }
    public static StringBuilder removeCharacters(String string, int[] positions){
        StringBuilder builder = new StringBuilder(string);
        int index=0;
        for (int position : positions) {
            builder.deleteCharAt(position-index);
            index++;
        }
        return builder;
    }
    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters){
        StringBuilder builder = new StringBuilder(string);
        int index=0;
        for (int i = 0; i < positions.length; i++) {
            builder.insert(positions[i]+index,characters[i]);
            index++;
        }
        return builder;
    }

}
