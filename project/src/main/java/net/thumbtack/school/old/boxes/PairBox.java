package net.thumbtack.school.old.boxes;

import net.thumbtack.school.old.area.HasArea;

public class PairBox<T extends HasArea,V extends HasArea> {

    private static final double EPS = 1E-6;
    private T contentFirst;
    private V contentSecond;

    public PairBox(T contentFirst, V contentSecond) {
        this.contentFirst = contentFirst;
        this.contentSecond = contentSecond;
    }

    public T getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(T contentFirst) {
        this.contentFirst = contentFirst;
    }

    public V getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(V contentSecond) {
        this.contentSecond = contentSecond;
    }

    public double getArea(){
        return contentFirst.getArea() + contentSecond.getArea();
    }

    public boolean isAreaEqual(PairBox<? extends HasArea,? extends HasArea> another){
        return (Math.abs(getArea() - another.getArea()) < EPS);
    }

    public static boolean isAreaEqual(PairBox<? extends HasArea,? extends HasArea> first, PairBox<? extends HasArea,? extends HasArea> second){
        return (Math.abs(first.getArea() - second.getArea()) < EPS);
    }


}
