package Model.Controller;
import GUI.GUIController;
import Model.Board;
import Model.Cards.*;
import Model.Dice;
import Model.Fields.*;

//controls several parts of the game
public class Controller {
    private Dice dice;
    private CardPile cardPile;
    private PropertyPlayerController propertyPlayerController;
    private GUIController guiController;
    private int totalPlayers = 0;
    Board board;
    SuperField[] field;

    //extracts methods from several classes
    public Controller(){
        this.cardPile = new CardPile();
        this.board = new Board();
        this.field = board.getField();
        this.guiController = new GUIController(field);
        this.propertyPlayerController = setupPropertyPlayerController(board);
        this.dice = new Dice();
    }

    //allows the game to start by calling the play() method
    public void startGame() {
        guiController.GUIPlayers(propertyPlayerController.getPlayerArray());
        play();
    }

    //sets conditions for when the game starts and ends
    private void play() {
        boolean endgame = false;
        //number of rounds
        while (!endgame) {
            int counter = 0;
            for (int i = 0; i < 1000; i++) {
                for (int k = 0; k < totalPlayers; k++) {
                    if (propertyPlayerController.isBankrupt(k)) {
                        counter++;
                    }
                    if (counter == (totalPlayers - 1)) {
                        guiController.message(" Game ends ");
                        endgame = true;
                        break;
                    }
                    doTurn(k);
                }
            }
        }
    }
    //creates the turn for a player including jail conditions
    private void doTurn(int playerID) {
        if (!propertyPlayerController.isBankrupt(playerID)) {
            if (propertyPlayerController.getPlayerArray()[playerID].isJailed()) {
                //message to jailed player
                if (!propertyPlayerController.getPlayerArray()[playerID].hasFreeCard()) {
                    guiController.message("Du er fængslet. vent en tur!");
                    propertyPlayerController.getPlayerArray()[playerID].setFree();
                }

                //message to freed player
                if (propertyPlayerController.getPlayerArray()[playerID].hasFreeCard()) {
                    guiController.message("Du brugte dit frikort til at komme ud af fængslet");
                    propertyPlayerController.getPlayerArray()[playerID].setFree();
                    propertyPlayerController.getPlayerArray()[playerID].spendFreeCard();
                    normalExecution(playerID);
                }
            }

            else if (!propertyPlayerController.getPlayerArray()[playerID].isJailed()) {
                normalExecution(playerID);
            }
        }

        if(propertyPlayerController.isBankrupt(playerID)){
            bankruptExecution(playerID);
        }

    }

    //removes ownership of a property by removing the border on the GUI when a player goes bankrupt
    private void bankruptExecution(int playerID){
        int[] owned = propertyPlayerController.getOwned(playerID);
        propertyPlayerController.removeOwnerShip(playerID);
        for (int i = 0; i < owned.length; i++) {
            guiController.removePlayerBorder(playerID, owned[i]);
        }
    }

    //sets the player positions and changes the positons
    private void normalExecution(int playerID) {
        guiController.wantToRoll(playerID);
        movePlayer(playerID);
        int pos1 = propertyPlayerController.getPlayerPosition(playerID);
        SuperField landedField = field[pos1];
        doField(landedField, playerID);

        // In some cases the player pos gets changed by doField
        int pos2 = propertyPlayerController.getPlayerPosition(playerID);
        guiController.changePlayerGUIPos(playerID, pos2, pos1);

        // updates every players balance
        for (int i = 0; i < totalPlayers; i++) {
            guiController.updateBalance(i, propertyPlayerController.getPlayerMoney(i));
        }
    }

    //allows player to receive 4000 kr. when passing by the start field
    private void passStart(int oldpos, int newpos, int playerID){
        if(newpos <= 12 && oldpos > field.length - 12){
            propertyPlayerController.changeAccount(4000, playerID);
            guiController.updateBalance(playerID, propertyPlayerController.getPlayerMoney(playerID));
        }
    }

    //moves the player from old position to a new position based on a dice roll
    private void movePlayer(int ID) {
        int dice_1 = dice.rollDice();
        int dice_2 = dice.rollDice();
        int eyesum = dice_1 + dice_2;
        dice.setDice(eyesum);
        guiController.showDice(dice_1, dice_2);
        propertyPlayerController.movePiece(eyesum, ID);
        int pos_1 = propertyPlayerController.getOldPlayerPosition(ID);
        int pos_2 = propertyPlayerController.getPlayerPosition(ID);
        guiController.changePlayerGUIPos(ID, pos_2, pos_1);
        passStart(pos_1, pos_2, ID);
    }

    //conditions for what happens when landing on a specific type of field
    private void doField(SuperField landedField, int playerID){
        int fieldID = landedField.getID();
        int EyeSum = dice.getRememberDice();
        if (landedField instanceof OwnableField) {
            doPropertyField((OwnableField) landedField,playerID,fieldID);
        }
        if (landedField instanceof ChanceField) {
            doCard(playerID);
        }
        if (landedField instanceof SpecialField){
            doSpecialField((SpecialField) landedField,playerID);
        }
        if (landedField instanceof VacantField) {
            doVacantField(playerID);
        }
    }

    //conditions for what happens if you land on a vacant field (fields you can build houses on)
    public void doVacantField(int playerID) {
        if (propertyPlayerController.getCanBuildArray(playerID).length > 0) {
            VacantField chosenfield = guiController.wantToBuildHouse(propertyPlayerController.getCanBuildArray(playerID));
            if(chosenfield != null) {
                propertyPlayerController.buyHouse(playerID, chosenfield);
                guiController.buildHouse(chosenfield.getID(), propertyPlayerController.getHouses(chosenfield.getID()));
            }
        }
    }
    //conditions for what happens if you land on a special field (non-ownable fields such as chance field)
    private void doSpecialField(SpecialField landedField, int playerID){
        propertyPlayerController.doSpecialField(landedField, playerID);
    }

    //conditions for what happens if you land on a property field (ownable fields that you cant build houses on such as shipping fields)
    private  void doPropertyField(OwnableField landedField, int playerID, int fieldID){
        if (!propertyPlayerController.isOwned(landedField.getID())) {
            if(propertyPlayerController.isAffordable(playerID, landedField.getFieldPrice())){
                if (guiController.wantToBuy(landedField.getFieldName())) {
                    propertyPlayerController.purchaseProperty(playerID,landedField);
                    guiController.setPropertyBorder(playerID, fieldID);
                }
            }
        }
        else if (!(propertyPlayerController.getOwnership(landedField.getID()) == playerID)) {
            int owner = propertyPlayerController.getOwnership(fieldID);
            propertyPlayerController.payPlayerRent(landedField,owner, playerID, dice.getRememberDice());
        }
    }

    //conditions for what happens depending on which card is drawn
    private void doCard(int playerID){
        SuperCard card = cardPile.drawCard();
        if(card instanceof MoveCard){
            if ((((MoveCard) card).getType()) == 1){
            propertyPlayerController.movePiece(((MoveCard) card).getMovePiece(), playerID);
            }
            if ((((MoveCard) card).getType()) == 2) {
                propertyPlayerController.setPiece(((MoveCard) card).getMovePiece(), playerID);
            }
            guiController.message(card.getCardDescription());
            doField(field[propertyPlayerController.getPlayerPosition(playerID)], playerID);
        } else {
            guiController.message(card.getCardDescription());
            if(card instanceof MoneyCard){
                propertyPlayerController.changeAccount(((MoneyCard) card).getChangeMoney(), playerID);
            }
            if(card instanceof FreeCard) {
                propertyPlayerController.getPlayerArray()[playerID].gainFreeCard();
            }
        }
    }

    //sets minimum and maximum amount of players
    private PropertyPlayerController setupPropertyPlayerController (Board board){
        int maxPlayers = 6;
        int minPlayers = 2;
        totalPlayers = guiController.totalplayers(minPlayers, maxPlayers);
        return new PropertyPlayerController(totalPlayers, board);
    }


}