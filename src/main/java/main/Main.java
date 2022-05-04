package main;

import controllers.GamePlayController;

public class Main {


    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {

        /*
        ResultState resultState = new ResultState();
        BoxesState boxesState = new BoxesState();
        PlayerState playerState = new PlayerState();
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);

        boxesState.initializeBoxes();
        System.out.println("Enter Player One Name:");
        playerState.setPlayerOneName(sc.nextLine());
        System.out.println("Enter Player Two Name: ");
        playerState.setPlayerTwoName(sc.nextLine());
        controller.printInstructions(playerState.getIsPlayerOnesTurn(),boxesState);
        while(controller.isGoalState(boxesState,resultState,playerState) == false) {
            //controller.waitForCommands(playerState, boxesState, resultState);
            String command;
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

        switch(boolean array index)
        case 0:
           command = 0
           break;
         so on



            if (command.matches("[0-9]+")) {
                Logger.info("Input was number: {}", command);
                playerMove(playerState, boxesState, Integer.parseInt(command), resultState);
                //throw error if command is less than 0

            }else if (command.contains("Done") && playerState.getAvailableTurns() != 2){
                //change player
                Logger.info("Input was done: {}", command);
                changePlayer(playerState,resultState,boxesState);
                if (isGoalState(boxesState,resultState,playerState) == false) {
                    printInstructions(playerState.getIsPlayerOnesTurn(), boxesState);
                }
            }
        }
        System.out.println("Game is over!");
        System.out.println(resultState.getNumberOfMoves());
        System.out.println(resultState.getWinner());

        GameData gameData = new GameData(boxesState.startDateAndTime,playerState.getPlayerOneName(),playerState.getPlayerTwoName(), resultState.getWinner(), resultState.getNumberOfMoves());
        JsonHelper.write(gameData);

        */

        GamePlayController gamePlayController = new GamePlayController();
        gamePlayController.appLaunch();

    }
}
