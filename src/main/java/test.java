
import Model.Cards.MoneyCard;
import Model.Cards.MoveCard;
import Model.Board;
import Model.Piece;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.Fields.*;


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


    //Testing VacantField getRent method below when owned_houses 0 and rent is 4:
    @Test
    public void changeRentByTierZero() {
        VacantField vacantField = new VacantField("test", "test", 1, 2, 3, 4,5,6,7,8,9,1);
        assertEquals(4, vacantField.getRent(0,0));
    }

    //when owned_houses is 8 and rent is 8:
    @Test
    public void changeRentByTierThree() {
        VacantField vacantField = new VacantField("test", "test", 1, 2, 3, 8,5,6,7,8,9,1);
        assertEquals(9, vacantField.getRent(3,8));
    }

    //Testing ShippingField getRent method when owned shipping fields are 2 and rent is 4
    @Test
    public void changeRentOwnedShippingTwo() {
        ShippingField shippingField = new ShippingField("test", "test", 1, 2,4);
        assertEquals(8, shippingField.getRent(2,2));
    }

    //Testing ShippingField getRent method when owned shipping fields are 4 and rent is 8
    @Test
    public void changeRentOwnedShippingFour() {
        ShippingField shippingField = new ShippingField("test", "test", 1, 2,8);
        assertEquals(32, shippingField.getRent(3,4));
    }

    //Model.Board class, testing getFieldObject method when ID is above 40
    @Test
    public void returnFieldID() {
        Board board = new Board();
        assertEquals(20, board.getFielobject(60).getID());
    }

    //Model.Board class, testing getFieldObject method when ID is below 40, should return a value +1 from the field ID:
    @Test
    public void returnFieldIDNewValue() {
        Board board = new Board();
        assertEquals(11, board.getFielobject(10).getID());
    }


    //when typeIndex = 2
    @Test
    public void returnTotalMoves() {
        Piece piece = new Piece();
        assertEquals(0, piece.getMoves());
    }


    //PropertyField class, testing getFieldPrice method, returning price
    @Test
    public void returnPrice() {
        OwnableField ownableField = new OwnableField("test", "test", 1,2,3) {
            @Override
            public int getRent(int eyesum, int owned_coorporations) {
                return 0;
            }
        };
        assertEquals(2, ownableField.getFieldPrice());
    }

    //testing the same method with another value set
    @Test
    public void returnPriceNewValue() {
        OwnableField ownableField = new OwnableField("test", "test", 1,2000,3) {
            @Override
            public int getRent(int eyesum, int owned_coorporations) {
                return 0;
            }
        };
        assertEquals(2000, ownableField.getFieldPrice());
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
        assertEquals("test", superField.getFieldDescription());
    }

    //Model.Cards.MoveCard class, testing getMovePiece, getMoveToField and getType methods, returning movePiece, moveToField and type respectively
    //moveType set to 1, returning 5
    @Test
    public void returnPieceMoveTypeOne() {
        MoveCard moveCard = new MoveCard("test", 1, 5);
        assertEquals(5, moveCard.getMovePiece());
    }

    //moveType set to 5, returning 0
    @Test
    public void returnPieceMoveTypeFive() {
        MoveCard moveCard = new MoveCard("test", 5, 5);
        assertEquals(0, moveCard.getMovePiece());
    }

    //getMoveToField, amt set to 8, returning 8
    @Test
    public void returnPieceMoveToFieldEight() {
        MoveCard moveCard = new MoveCard("test", 5, 8);
        assertEquals(8, moveCard.getMoveToField());
    }

    //amt set to 1, returning 1
    @Test
    public void returnPieceMoveToFieldOne() {
        MoveCard moveCard = new MoveCard("test", 5, 1);
        assertEquals(1, moveCard.getMoveToField());
    }

    //getType method, moveType set to 3
    @Test
    public void returnTypeThree() {
        MoveCard moveCard = new MoveCard("test", 3, 5);
        assertEquals(3, moveCard.getType());
    }

    //moveType set to 10
    @Test
    public void returnTypeTen() {
        MoveCard moveCard = new MoveCard("test", 10, 5);
        assertEquals(10, moveCard.getType());
    }

    //Money card class, testing getChangeMoney method
    //amt set to 1
    @Test
    public void returnChangeMoneyOne() {
        MoneyCard moneyCard = new MoneyCard("test", 1);
        assertEquals(1, moneyCard.getChangeMoney());
    }

    //amt set to 10
    @Test
    public void returnChangeMoneyTen() {
        MoneyCard moneyCard = new MoneyCard("test", 10);
        assertEquals(10, moneyCard.getChangeMoney());
    }

    @Test
    public void returNnumberOfOwnedTwo() {

    }

}
