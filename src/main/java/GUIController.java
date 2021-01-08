import Fields.PropertyField;
import Fields.SuperField;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

public class GUIController {
    public static GUI_Field[] setupBoard(SuperField[] board) {
        GUI_Field[] gfields = new GUI_Field[board.length];
        SuperField field;

        for (int i = 0; i < board.length; i++) {
            field = board[i];
            if(field instanceof Fields.SpecialField) {
                //gfields[i] = new GUI_
            } else if(field instanceof Fields.PropertyField) {
                int rent = ((PropertyField) field).getFieldRent();
                gfields[i] = new GUI_Street(field.getFieldName(), "subtext", field.getFieldDescription(), String.valueOf(rent), Color.BLUE, Color.BLACK);
                //new GUI_Street(,)
            }
        }
        return gfields;
    }

    public static void main(String[] args) {
        SuperField[] board = new Board().setupField();
        GUI gui = new GUI(setupBoard(board));
    }
}