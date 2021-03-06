package net.thumbtack.school.old.ttschool;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
        trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if(name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if(room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void  addTrainee(Trainee trainee){
        trainees.add(trainee);
    }

    public void  removeTrainee(Trainee trainee) throws TrainingException {
        if(!trainees.remove(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void  removeTrainee(int index) throws TrainingException {
        if(index < 0 || index >= trainees.size()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(index);
    }

    public Trainee  getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee trainee : trainees) {
            if(trainee.getFirstName().equals(firstName)){
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee  getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee trainee : trainees) {
            if(trainee.getFullName().equals(fullName))
                return trainee;
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  sortTraineeListByFirstNameAscendant(){
        Collections.sort(trainees, Comparator.comparing(Trainee::getFirstName));
    }

    public void  sortTraineeListByRatingDescendant(){
        Collections.sort(trainees, Group::compareSortByRatingDescendant);
    }
    public void  reverseTraineeList(){
        Collections.reverse(trainees);
    }

    public void  rotateTraineeList(int positions){
        Collections.rotate(trainees,positions);
    }

    public List<Trainee>  getTraineesWithMaxRating() throws TrainingException {
        if(getTrainees().isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        List<Trainee> list = new ArrayList<>();
        int tempRating = 1;

        for (Trainee trainee : trainees) {
            if(trainee.getRating() > tempRating) {
                list.clear();
                tempRating = trainee.getRating();
            }
            if(trainee.getRating() == tempRating) list.add(trainee);
        }
        return list;
    }

    public boolean hasDuplicates(){
        Set<Trainee> temp = new HashSet<>(trainees);
        return trainees.size() != temp.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }

    private static int compareSortByRatingDescendant(Trainee t1, Trainee t2) {
        return Integer.compare(t2.getRating(), t1.getRating());
    }

}
