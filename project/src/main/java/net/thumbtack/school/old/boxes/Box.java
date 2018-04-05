package net.thumbtack.school.old.boxes;

import net.thumbtack.school.old.area.HasArea;

public class Box<T extends HasArea>{
    private static final double EPS = 1E-6;
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isAreaEqual(Box<? extends HasArea> another){
        return (Math.abs(getArea() - another.getArea()) < EPS);
    }

    public static boolean isAreaEqual(Box<? extends HasArea> first, Box<? extends HasArea> second){
        return (Math.abs(first.getArea() - second.getArea()) < EPS);
    }

    public double getArea(){
        return content.getArea();
    }
}
