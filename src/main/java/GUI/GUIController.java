package GUI;

import Model.Board;
import Model.Fields.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;

public class GUIController {
    public static GUI_Field[] setupBoard(SuperField[] board) {
        GUI_Field[] gfields = new GUI_Field[board.length];
        SuperField field;
        HashMap <String, FieldColor> colors = new HashMap<>();

        Color cRed = new Color(229, 62,62);
        Color cBlack = new Color(0, 0, 0);
        Color cGray = new Color(128, 128, 128);
        Color cOrange = new Color(221, 107, 32);
        Color cWhite = new Color(255, 255, 255);
        Color cPrison = new Color(113, 128, 150);
        Color cBrew = new Color(39, 103, 73);
        Color cChance = new Color(204, 204, 204);
        Color cYellow = new Color(246, 224, 94);
        Color cPurple = new Color(48, 25, 52);
        Color cBlue = new Color(49, 130, 209);
        Color cGreen = new Color(102,204,0);

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
        //String[] Colors = new String[]{ "Blue","Orange", "light-yellow", "purple", "gray", "red", "white", "yellow"};

        // Shipping
        colors.put("shipping", new FieldColor(cWhite, cBlack));

        colors.put("vacant.0", new FieldColor(cBlue, cBlack));
        colors.put("vacant.1", new FieldColor(cOrange, cBlack));
        colors.put("vacant.2", new FieldColor(cYellow, cBlack));
        colors.put("vacant.3", new FieldColor(cPurple, cWhite));
        colors.put("vacant.4", new FieldColor(cGray, cBlack));
        colors.put("vacant.5", new FieldColor(cRed, cBlack));
        colors.put("vacant.6", new FieldColor(cWhite, cBlack));
        colors.put("vacant.7", new FieldColor(cGreen, cBlack));

        //colors.put()
        for (int i = 0; i < board.length; i++) {
            field = board[i];
            if(field instanceof Model.Fields.SpecialField) {
                System.out.println(((SpecialField) field).getType());
                FieldColor color = colors.get(((SpecialField) field).getType());
                if(((SpecialField) field).getType().equals("start")) {
                    gfields[i] = new GUI_Start(field.getFieldName(), "subtext", field.getFieldDescription(), color.FG, color.BG);
                } else if(((SpecialField) field).getType().equals("tax")) {
                    gfields[i] = new GUI_Tax(field.getFieldName(), field.getFieldDescription(), field.getFieldDescription(), color.FG, color.BG);
                } else if(((SpecialField) field).getType().equals("visitprison") || ((SpecialField) field).getType().equals("prison")) {
                    gfields[i] = new GUI_Jail("default", field.getFieldName(), field.getFieldName(), field.getFieldDescription(), color.FG, color.BG);
                } else if(((SpecialField) field).getType().equals("parking")) {
                    gfields[i] = new GUI_Refuge("default", field.getFieldName(), "subtext", field.getFieldDescription(), color.FG, color.BG);
                }

            } else if(field instanceof Model.Fields.VacantField) {
                int rent = ((VacantField) field).getFieldRent();
                int price = ((VacantField) field).getFieldPrice();
                FieldColor color = colors.get("vacant." + ((VacantField) field).getTypeIndex());
                System.out.println(((VacantField) field).getColor());
                gfields[i] = new GUI_Street(field.getFieldName(), "Pris: " + price , field.getFieldDescription(), String.valueOf(rent), color.FG, color.BG);
                //new GUI_Street(,)
            } else if(field instanceof Model.Fields.ChanceField) {
                FieldColor color = colors.get("chance");
                gfields[i] = new GUI_Chance(field.getFieldName(), field.getFieldDescription(), field.getFieldDescription(), color.FG, color.BG);
            } else if(field instanceof Model.Fields.ShippingField) {
                FieldColor color = colors.get("shipping");
                int rent = ((ShippingField) field).getFieldRent();
                int price = ((ShippingField) field).getFieldPrice();
                gfields[i] = new GUI_Shipping("default", field.getFieldName(), "Pris: " + price, field.getFieldDescription(), String.valueOf(rent), color.FG, color.BG);
            } else if(field instanceof Model.Fields.CoorporationField) {
                FieldColor color = colors.get("brew");
                int rent = ((CoorporationField) field).getFieldRent();
                int price = ((CoorporationField) field).getFieldPrice();
                gfields[i] = new GUI_Brewery("default", field.getFieldName(), "Pris: " + price, field.getFieldDescription(), String.valueOf(rent), color.FG, color.BG);
            }
        }
        return gfields;
    }

    public void showDice(int roll, int roll2) {

    }

    public static void main(String[] args) {
        SuperField[] board = new Board().setupField();
        //System.out.println(setupBoard(board));
        GUI gui = new GUI(setupBoard(board));
        //GUI gui = new GUI();
    }
}