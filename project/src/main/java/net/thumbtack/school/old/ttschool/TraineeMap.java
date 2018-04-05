package net.thumbtack.school.old.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private Map<Trainee,String> traineeMap;

    public TraineeMap() {
        this.traineeMap = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(traineeMap.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
        traineeMap.put(trainee,institute);
    }
    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(traineeMap.replace(trainee,institute) == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if(!traineeMap.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        traineeMap.remove(trainee);
    }

    public int getTraineesCount(){
        return traineeMap.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        if(traineeMap.get(trainee) == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }

        return traineeMap.get(trainee);
    }

    public Set<Trainee> getAllTrainees(){
        return traineeMap.keySet();
    }

    public Set<String> getAllInstitutes(){
        Set<String> set = new HashSet<>(traineeMap.values());
        return set;
    }

    public boolean isAnyFromInstitute(String institute){
        for (String value : getAllInstitutes()) {
           if(institute.contains(value)) return true;
        }
        return false;
    }


}
