import Fields.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIController {
    public static GUI_Field[] setupBoard(SuperField[] board) {
        GUI_Field[] gfields = new GUI_Field[board.length];
        SuperField field;

        for (int i = 0; i < board.length; i++) {
            field = board[i];
            if(field instanceof Fields.SpecialField) {
                if(((SpecialField) field).getType() == "start") {
                    gfields[i] = new GUI_Start(field.getFieldName(), "subtext", field.getFieldDescription(), Color.ORANGE, Color.WHITE);
                } else if(((SpecialField) field).getType() == "tax") {
                    gfields[i] = new GUI_Tax(field.getFieldName(), "subtext", field.getFieldDescription(), Color.BLACK, Color.WHITE);
                } else if(((SpecialField) field).getType() == "visitprison" || ((SpecialField) field).getType() == "prison") {
                    gfields[i] = new GUI_Jail("default", field.getFieldName(), "subtext", field.getFieldDescription(), Color.BLACK, Color.WHITE);
                } else if(((SpecialField) field).getType() == "parking") {
                    gfields[i] = new GUI_Refuge("default", field.getFieldName(), "subtext", field.getFieldDescription(), Color.BLACK, Color.BLUE);
                }

            } else if(field instanceof Fields.VacantField) {
                int rent = ((VacantField) field).getFieldRent();
                System.out.println(String.valueOf(rent));
                gfields[i] = new GUI_Street(field.getFieldName(), "subtext", field.getFieldDescription(), String.valueOf(rent), Color.LIGHT_GRAY, Color.BLACK);
                //new GUI_Street(,)
            } else if(field instanceof Fields.ChanceField) {
                gfields[i] = new GUI_Chance(field.getFieldName(), "subtext", field.getFieldDescription(), Color.GREEN, Color.black);
            } else if(field instanceof Fields.ShippingField) {
                int rent = ((ShippingField) field).getFieldRent();
                gfields[i] = new GUI_Shipping("default", field.getFieldName(), "subtext", field.getFieldDescription(), String.valueOf(rent), Color.ORANGE, Color.BLACK);
            } else if(field instanceof Fields.CoorporationField) {
                int rent = ((CoorporationField) field).getFieldRent();
                gfields[i] = new GUI_Brewery("default", field.getFieldName(), "subtext", field.getFieldDescription(), String.valueOf(rent), Color.blue, Color.orange);
            }
        }
        return gfields;
    }

    public static void main(String[] args) {
        SuperField[] board = new Board().setupField();
        //System.out.println(setupBoard(board));
        GUI gui = new GUI(setupBoard(board));
    }
}