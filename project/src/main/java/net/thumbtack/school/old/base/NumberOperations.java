package net.thumbtack.school.old.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberOperations {

    public static Integer find(int[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value) return i;
        }
        return null;
    }

    public static Integer find(double[] array, double value, double eps){
        for (int i = 0; i < array.length; i++) {
            if(Math.abs(array[i] - value) < eps) return i;
        }
        return null;
    }
    public static Double calculateDensity(double weight, double volume, double min, double max){
        if(min < weight/volume && weight/volume < max) return weight/volume;
        return null;
    }
    public static Integer find(BigInteger[] array, BigInteger value){
        for (int i = 0; i < array.length; i++) {
            if(array[i].compareTo(value) == 0) return i;
        }
        return null;
    }

    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max){
        if(min.compareTo(weight.divide(volume)) == -1 && weight.divide(volume).compareTo(max) == -1)
            return weight.divide(volume);
        else return null;
    }

}

