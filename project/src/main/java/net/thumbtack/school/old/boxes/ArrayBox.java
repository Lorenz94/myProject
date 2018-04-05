package net.thumbtack.school.old.boxes;

import net.thumbtack.school.old.area.HasArea;

public class ArrayBox<T extends HasArea>{
    private T[] content;

    public ArrayBox(T[] content) {
        this.content = content;
    }

    public T[] getContent() {
        return content;
    }

    public void setContent(T[] content) {
        this.content = content;
    }

    public T getElement(int i){
        return i < content.length ? content[i] : null;
//        try {
//            return getContent[i];
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.err.println("Element is not found");
//            e.printStackTrace();
//            return null;
//        }
    }

    public void setElement(int i, T value){
        if(i < content.length) content[i] = value;
//        try {
//            content[i] = value;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.err.println("Element is not found");
//            e.printStackTrace();
//        }
    }

    public boolean isSameSize(ArrayBox<? extends HasArea> anotherBox){
        return content.length == anotherBox.content.length;
    }

}
