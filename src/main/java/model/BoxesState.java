package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.time.LocalDateTime;

/**
 * Class representing the state of the boxes.
 */
public class BoxesState {

    public ArrayList<Integer> boxes = new ArrayList<>(15);
    public LocalDateTime startDateAndTime;

    /**
     * Initializes the ArrayList of boxes.
     */
    public void initializeBoxes(){
        Random random = new Random();
        int emptyBoxIndex = random.nextInt(14);
        for (int i = 0; i < 15; i++){
            if (i == emptyBoxIndex){
                boxes.add(0);
            }else{
                boxes.add(1);
            }
        }
        startDateAndTime = LocalDateTime.now();

    }

    /**
     * Determines wether the game has reached the goal state
     * @return true or false depending on wether the game has reached the goal state
     */
    public boolean isGoalState(){
        if (boxes.contains(1)){
            return false;
        }
        return true;
    }

}
