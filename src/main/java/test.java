import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;
import Fields.*;



public class test {

    //Testing CoorporationField getRent method below for when owned coorporations are 2 and eyesum is 2:
    @Test
    public void changeRentOwnedCorpTwo() {
        CoorporationField coorporationField = new CoorporationField("test", "test", 1, 2, 3);
        assertEquals(1200, coorporationField.getRent(2,2));
    }

    //when owned coorporation is 1 and eyesum is 5:
    @Test
    public void changeRentOwnedCorpOne() {
        CoorporationField coorporationField = new CoorporationField("test", "test", 1, 2, 3);
        assertEquals(500, coorporationField.getRent(5,1));
    }


    //Testing VacantField getRent method below when tier is 0 and rent is 4:
    @Test
    public void changeRentByTierZero() {
        VacantField vacantField = new VacantField("test", "test", 1, 2, 3, 4,5,6,7,8,9,1);
        assertEquals(4, vacantField.getRent(0));
    }

    //when tier is 3 and rent is 8:
    @Test
    public void changeRentByTierThree() {
        VacantField vacantField = new VacantField("test", "test", 1, 2, 3, 8,5,6,7,8,9,1);
        assertEquals(7, vacantField.getRent(3));
    }

    //Testing ShippingField getRent method when owned shipping fields are 2 and rent is 4
    @Test
    public void changeRentOwnedShippingTwo() {
        ShippingField shippingField = new ShippingField("test", "test", 1, 2,3);
        assertEquals(6, shippingField.getRent(2));
    }

    //Testing ShippingField getRent method when owned shipping fields are 4 and rent is 8
    @Test
    public void changeRentOwnedShippingFour() {
        ShippingField shippingField = new ShippingField("test", "test", 1, 2,3);
        assertEquals(12, shippingField.getRent(4));
    }

    //Board class, testing getFieldObject method when ID is above 40
    @Test
    public void returnFieldID() {
        Board board = new Board();
        assertEquals(20, board.getFielobject(60).getID());
    }

    //Board class, testing getFieldObject method when ID is below 40, should return a value +1 from the field ID:
    @Test
    public void returnFieldIDNewValue() {
        Board board = new Board();
        assertEquals(11, board.getFielobject(10).getID());
    }


    //when typeIndex = 2
    @Test
    public void returnTotalMoves() {
        Piece piece = new Piece(2);
        assertEquals(0, piece.getMoves());
    }

    //Piece class, testing getColor method, returning color depending on selected typeIndex
    //When typeIndex = 5, it should return purple from Color-array
    @Test
    public void returnPieceColorPurple() {
        Piece piece = new Piece(5);
        assertEquals("Purple", piece.getColor());
    }

    //When typeIndex = 1, it should return blue from Color-array
    @Test
    public void returnPieceColorBlue() {
        Piece piece = new Piece(1);
        assertEquals("Blue", piece.getColor());
    }

    //PropertyField class, testing getFieldPrice method, returning price
    @Test
    public void returnPrice() {
        PropertyField propertyField = new PropertyField("test", "test", 1,2,3) {
            @Override
            public int getFieldPrice() {
                return super.getFieldPrice();
            }
        };
        assertEquals(2, propertyField.getFieldPrice());
    }

    //testing the same method with another value set
    @Test
    public void returnPriceNewValue() {
        PropertyField propertyField = new PropertyField("test", "test", 1,2000,3) {
            @Override
            public int getFieldPrice() {
                return super.getFieldPrice();
            }
        };
        assertEquals(2000, propertyField.getFieldPrice());
    }


    //SuperField class, testing getFieldDescription method, returning fieldDescription
    @Test
    public void returnFieldDescription() {
        SuperField superField = new SuperField("test", "test", 1) {
            @Override
            public String getFieldDescription() {
                return super.getFieldDescription();
            }
        };
        assertEquals("", superField.getFieldDescription());
    }
}
