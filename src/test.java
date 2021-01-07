import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class test {

    //Testing CoorporationField getRent method below for when owned coorporations are 2 and eyesum is 2:
    @Test
    public void changeRentOwnedCorpTwo() {
        CoorporationField coorporationField = new CoorporationField("test", "test", 1, 2, 3, 4);
        assertEquals(400, coorporationField.getRent(2,2));
    }

    //when owned coorporation is 1 and eyesum is 5:
    @Test
    public void changeRentOwnedCorpOne() {
        CoorporationField coorporationField = new CoorporationField("test", "test", 1, 2, 3, 4);
        assertEquals(500, coorporationField.getRent(5,1));
    }


    //Testing VacantField getRent method below when tier is 0 and rent is 4:
    @Test
    public void changeRentByTierZero() {
        VacantField vacantField = new VacantField("test", "test", 1, 2, 3, 4,5,6,7,8,9,1,2);
        assertEquals(4, vacantField.getRent(0));
    }

    //when tier is 3 and rent is 8:
    @Test
    public void changeRentByTierThree() {
        VacantField vacantField = new VacantField("test", "test", 1, 2, 3, 8,5,6,7,8,9,1,2);
        assertEquals(7, vacantField.getRent(3));
    }

    //Testing ShippingField getRent method when owned shipping fields are 2 and rent is 4
    @Test
    public void changeRentOwnedShippingTwo() {
        ShippingField shippingField = new ShippingField("test", "test", 1, 2,3,4,5,6,7);
        assertEquals(8, shippingField.getRent(2));
    }

    //Testing ShippingField getRent method when owned shipping fields are 4 and rent is 8
    @Test
    public void changeRentOwnedShippingFour() {
        ShippingField shippingField = new ShippingField("test", "test", 1, 2,3,8,5,6,7);
        assertEquals(32, shippingField.getRent(4));
    }

}
