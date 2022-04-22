package main;


import model.BoxesState;

public class Main {
    public static void main(String[] args) {
        BoxesState boxesState = new BoxesState();
        boxesState.initializeBoxes();
        System.out.print(boxesState.boxes);
    }
}
