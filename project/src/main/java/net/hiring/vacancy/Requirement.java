package net.thumbtack.school.hiring.vacancy;

import net.thumbtack.school.hiring.exception.ServerException;

import java.util.Objects;

public class Requirement extends Skill{

    private boolean mandatory;

    public Requirement(String name, int score, boolean mandatory) throws ServerException {
        super(name,score);
        setMandatory(mandatory);
    }


    public boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory){
        this.mandatory = mandatory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Requirement that = (Requirement) o;
        return mandatory == that.mandatory;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), mandatory);
    }
}
