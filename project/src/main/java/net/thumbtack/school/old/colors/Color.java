package net.thumbtack.school.old.colors;

import java.util.Arrays;

public enum Color {
    RED, GREEN, BLUE;

    public static Color colorNotNull(Color color) throws ColorException {
        if(color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        return color;
    }
    public static Color colorFromString(String colorString) throws ColorException{
        try {
            return Color.valueOf(colorString);
        } catch (IllegalArgumentException e) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING, colorString);
        } catch (NullPointerException ex){
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
    }
}
