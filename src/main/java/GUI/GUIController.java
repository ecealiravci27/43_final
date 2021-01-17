package GUI;

import Model.Board;
import Model.Fields.*;
import Model.Player;
import gui_fields.*;
import gui_main.GUI;
import java.awt.*;
import java.util.HashMap;

public class GUIController {
    private final GUI GUI;
    private GUI_Player[] gPlayers;
    private HashMap<String, Color> playerColors;
    String[] options;

    public GUIController(SuperField[] board) {
        Color bg = new Color(141, 138, 138);
        this.GUI = new GUI(setupBoard(board), bg);
        this.playerColors = setupPlayerColors();
        options = playerColors.keySet().toArray(new String[0]);
    }

    public GUI_Field[] setupBoard(SuperField[] board) {
        GUI_Field[] gfields = new GUI_Field[board.length];
        SuperField field;

        HashMap <String, FieldColor> colors = new HashMap<>();

        Color cRed = new Color(229, 62,62);
        Color cBlack = new Color(0, 0, 0);
        Color cGray = new Color(128, 128, 128);
        Color cOrange = new Color(221, 107, 32);
        Color cWhite = new Color(255, 255, 255);
        Color cBrew = new Color(39, 103, 73);
        Color cChance = new Color(204, 204, 204);
        Color cYellow = new Color(246, 224, 94);
        Color cPurple = new Color(48, 25, 52);
        Color cBlue = new Color(49, 130, 209);
        Color cTeal = new Color(0, 128, 128);
        Color cCyan = new Color(0, 255, 255, 100);
        Color cBeige = new Color(245,245,220);
        Color cOlive = new Color(85,107,47);
        Color cSlate = new Color(112,128,144);

        // Specialfield
        colors.put("start", new FieldColor(cRed, cBlack));
        colors.put("tax", new FieldColor(cGray, cBlack));
        colors.put("prison", new FieldColor(cGray, cBlack));
        colors.put("visitprison", new FieldColor(cGray, cBlack));
        colors.put("parking", new FieldColor(cWhite, cBlack));

        // Vacantfield
        colors.put("vacant", new FieldColor(cWhite, cBlack));

        // chance
        colors.put("chance", new FieldColor(cChance, cBlack));

        // brew
        colors.put("brew", new FieldColor(cBrew, cWhite));

        // Shipping
        colors.put("shipping", new FieldColor(cWhite, cBlack));

        colors.put("vacant.0", new FieldColor(cBlue, cBlack));
        colors.put("vacant.1", new FieldColor(cOrange, cBlack));
        colors.put("vacant.2", new FieldColor(cSlate, cWhite));
        colors.put("vacant.3", new FieldColor(cCyan, cBlack));
        colors.put("vacant.4", new FieldColor(cPurple, cWhite));
        colors.put("vacant.5", new FieldColor(cOlive, cWhite));
        colors.put("vacant.6", new FieldColor(cBeige, cBlack));
        colors.put("vacant.7", new FieldColor(cTeal, cBlack));

        //colors.put()
        for (int i = 0; i < board.length; i++) {
            field = board[i];
            if(field instanceof Model.Fields.SpecialField) {
                System.out.println(((SpecialField) field).getType());
                FieldColor color = colors.get(((SpecialField) field).getType());
                if(((SpecialField) field).getType().equals("start")) {
                    gfields[i] = new GUI_Start(field.getFieldName(), "", field.getFieldDescription(), color.FG, color.BG);
                } else if(((SpecialField) field).getType().equals("tax")) {
                    gfields[i] = new GUI_Tax(field.getFieldName(), field.getFieldDescription(), field.getFieldDescription(), color.FG, color.BG);
                } else if(((SpecialField) field).getType().equals("visitprison") || ((SpecialField) field).getType().equals("prison")) {
                    gfields[i] = new GUI_Jail("default", field.getFieldName(), field.getFieldName(), field.getFieldDescription(), color.FG, color.BG);
                } else if(((SpecialField) field).getType().equals("parking")) {
                    gfields[i] = new GUI_Refuge("default", field.getFieldName(), field.getFieldDescription(), "Her kan der parkeres frit", color.FG, color.BG);
                }

            } else if(field instanceof Model.Fields.VacantField) {
                int rent = ((VacantField) field).getFieldRent();
                int price = ((VacantField) field).getFieldPrice();
                int housePrice = ((VacantField) field).getHouse_price();
                //int houseRent = ((VacantField) field).get
                String desc = "Leje: " + rent + "\nHus Pris:" + housePrice;
                for (int a = 0; a < 4; a++) {
                    desc = desc + "\n" + (a+1) + "xHus Leje: " + ((VacantField) field).getRent(0, (a+1));;
                }
                //String desc = "Rent: " + rent + "\nHouse Price: " + housePrice + "\nHouse Rent: 10";

                FieldColor color = colors.get("vacant." + ((VacantField) field).getTypeIndex());
                System.out.println(((VacantField) field).getColor());
                gfields[i] = new GUI_Street(field.getFieldName(), "Pris: " + price , desc , String.valueOf(rent), color.FG, color.BG);
                //new GUI_Street(,)
            } else if(field instanceof Model.Fields.ChanceField) {
                FieldColor color = colors.get("chance");
                gfields[i] = new GUI_Chance("?", field.getFieldName(), field.getFieldDescription(), color.FG, color.BG);
            } else if(field instanceof Model.Fields.ShippingField) {
                FieldColor color = colors.get("shipping");
                int rent = ((ShippingField) field).getRent(0, 1);
                System.out.println("RENT = " + rent);
                int price = ((ShippingField) field).getFieldPrice();
                String desc = "";
                for (int a = 0; a < 4; a++) {
                    desc = desc + "\n" + (a+1) + "x" + field.getFieldName() + " Leje:"  + ((ShippingField) field).getRent(0, (a+1));;
                }

                gfields[i] = new GUI_Shipping("default", field.getFieldName(), "Pris: " + price, desc, String.valueOf(rent), color.FG, color.BG);
            } else if(field instanceof Model.Fields.CoorporationField) {
                FieldColor color = colors.get("brew");
                int rent = ((CoorporationField) field).getFieldRent();
                int price = ((CoorporationField) field).getFieldPrice();
                String desc = "Lejen er 100 gange øjesummen hvis du kun ejer enten tuborg eller carlsberg.\nLejen er det dobbelte hvis du ejer begge";

                gfields[i] = new GUI_Brewery("default", field.getFieldName(), "Pris: " + price, desc, String.valueOf(rent), color.FG, color.BG);
            }
        }

        setupPlayerColors();
        return gfields;
    }

    public void showDice(int roll, int roll2) {
        GUI.setDice(roll, roll2);
    }

//    public static void main(String[] args) {
//        SuperField[] board = new Board().setupField();
//        //System.out.println(setupBoard(board));
//        GUI gui = new GUI(setupBoard(board));
//        //GUI gui = new GUI();
//    }
    public int totalplayers(int min, int max) {
        String[] options = new String[max - min + 1];

        for (int i = min; i <= max; i++) {
            options[i-min] = String.valueOf(i);
        }

        String chosenElement = GUI.getUserSelection(
                "Vælg antal spillere",
                options
        );
        return Integer.parseInt(chosenElement);
    }

    public GUI_Car setupCar(Color color) {
        return new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
    }

    public String[] popElement(String element, String[] array) {
        int counter = 0;
        boolean found = false;

        // if element doesn't exist
        for (String string: array) {
            if(string == element) {
                found = true;
            }
        }

        if (!found) return array;

        String[] newArray = new String[array.length - 1];
        for (String string: array) {
            if(string != element) {
                newArray[counter] = string;
                counter++;
            }
        }
        return newArray;
    }

    public GUI_Player[] GUIPlayers(Player[] players) {
        gPlayers = new GUI_Player[players.length];
        for (int i = 0; i < players.length; i++) {
            Color color = choosePlayerColor();
            gPlayers[i] = new GUI_Player("test" + i, players[i].getBalance(), setupCar(color));
            GUI.addPlayer(gPlayers[i]);
            GUI.getFields()[0].setCar(gPlayers[i], true);
        }

        return gPlayers;
    }

    public void changePlayerGUIPos(int playerID, int newpos, int oldpos) {
        GUI.getFields()[oldpos].setCar(gPlayers[playerID], false);
        GUI.getFields()[newpos].setCar(gPlayers[playerID], true);

        System.out.printf("Changed guipos from: " + oldpos + " to newpos: " +newpos);
    }

    public HashMap<String, Color> setupPlayerColors() {
        HashMap <String, Color> colors = new HashMap<>();

        colors.put("rød", Color.red);
        colors.put("gul", Color.yellow);
        colors.put("grøn", Color.green);
        colors.put("blå", Color.blue);
        colors.put("orange", Color.orange);
        colors.put("lilla", new Color(128, 0, 128));
        
        return colors;
//        playerColors = new String[6];
//        int i = 0;
//        playerColors[i++] = "rød";
//        playerColors[i++] = "gul";
//        playerColors[i++] = "grøn";
//        playerColors[i++] = "blå";
//        playerColors[i++] = "orange";
//        playerColors[i++] = "lilla";
    }

    public Color choosePlayerColor() {
        String chosenElement = GUI.getUserSelection(
                "Vælg farve",
                options
        );
        options = popElement(chosenElement, options);
        return playerColors.get(chosenElement);
    }

//    public void setPlayerColor(String color, int ID) {
//        return playerColors.get(color);
//        gPlayers[ID].getCar().setPrimaryColor(playerColors.get(color));
//    }

    public boolean wantToBuy(String propertyname) {
        String chosenButton = GUI.getUserButtonPressed(
                "Vil du gerne købe: " + propertyname,
                "Ja", "Nej"
        );
        return chosenButton.equals("Ja");
    }

    public void wantToRoll(String name) {
        GUI.getUserButtonPressed(
                 name + "'s tur",
                "Roll"
        );
    }

    public void readCard(String desc) {
        GUI.getUserButtonPressed(
                desc,
                "Ok"
        );
    }

    public void setPropertyBorder(int playerID, int fieldID) {
        GUI_Field field = GUI.getFields()[fieldID];
        if (field instanceof GUI_Shipping) {
            ((GUI_Shipping) field).setBorder(gPlayers[playerID].getPrimaryColor());
        } else if (field instanceof GUI_Street) {
            ((GUI_Street) field).setBorder(gPlayers[playerID].getPrimaryColor());
        } else if (field instanceof GUI_Brewery) {
            ((GUI_Brewery) field).setBorder(gPlayers[playerID].getPrimaryColor());
        }
    }

    public void updateBalance(int playerID, int balance) {
        gPlayers[playerID].setBalance(balance);
    }

    public VacantField wantToBuildHouse(VacantField[] fields) {
        String[] names = new String[fields.length + 1];
        VacantField chosenField = null;

        for (int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getFieldName();
        }

        names[fields.length + 1] = "Afbryd";

        String chosenElement = GUI.getUserSelection(
                "Vælg hvor du gerne vil bygge et hus",
                names
        );

        for (int i = 0; i < fields.length; i++) {
            if (chosenElement == fields[i].getFieldName()) {
                chosenField = fields[i];
                break;
            }
        }
        return chosenField;
    }

    public void buildHouse(int ID, int amt) {
        GUI_Field field = GUI.getFields()[ID];
        if (field instanceof GUI_Street) {
            ((GUI_Street) field).setHouses(amt);
        }
    }
}