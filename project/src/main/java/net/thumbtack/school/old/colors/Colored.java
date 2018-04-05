package net.thumbtack.school.old.colors;

public interface Colored {
    void setColor(Color color) throws ColorException;
    void setColor(String string) throws ColorException;
    Color getColor();
}
