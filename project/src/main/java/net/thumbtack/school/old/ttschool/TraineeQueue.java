package net.thumbtack.school.old.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    private Queue<Trainee> queue;

    public TraineeQueue() {
        this.queue = new LinkedList<>();
    }
    public void addTrainee(Trainee trainee){
        queue.add(trainee);
    }
    public Trainee removeTrainee() throws TrainingException {
        Trainee t = queue.poll();
        if(t == null) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        return t;
    }
}
