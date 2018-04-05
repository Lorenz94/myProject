package net.thumbtack.school.old.boxes;

import net.thumbtack.school.old.area.HasArea;

public class NamedBox<T extends HasArea> extends Box{

    private String name;

    public NamedBox(T content, String name) {
        super(content);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public T getContent() {
        return (T) super.getContent();
    }
}
