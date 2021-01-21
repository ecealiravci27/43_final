// This class contains the board with the 40 different fields.
package Model;

import Model.Fields.*;

public class Board {

    private SuperField[] fields;

    public Board(){
        this.fields = setupField();
    }

    public SuperField[] getField() {
        return fields;
    }

    public SuperField getFielobject(int id) {
        return fields[id];
    }

    //The Fields specified in a Field array
    private SuperField[] setupField(){
        fields = new SuperField[40];
        fields[0] = new SpecialField("START", "Tjen 4000 hver gang du passere dette felt", 0, 0, "start");
        fields[1] = new VacantField("Rødovrevej", "", 1, 1200, 0, 50, 250,750, 2250, 4000,1000);
        fields[2] = new ChanceField("Prøv Lykken", "Træk et tilfældigt kort. Hvad som helst kan ske.", 2);
        fields[3] = new VacantField("Hvidovrevej", "", 3, 1200, 0, 50, 250, 750, 2250,  6000, 1000);
        fields[4] = new SpecialField("Betal\nindkomst-\nskat", "4000", 4, 4000, "tax");
        fields[5] = new ShippingField("Scandlines", "", 5, 4000,   500);
        fields[6] = new VacantField("Roskildevej", "", 6, 2000, 1, 100, 600, 1800, 5400,  11000, 1000);
        fields[7] = new ChanceField("Prøv Lykken", "Træk et tilfældigt kort. Hvad som helst kan ske.", 7);
        fields[8] = new VacantField("Valby Langgade", "", 8, 2000,1, 100, 600, 1800, 5400, 8000, 1000);
        fields[9] = new VacantField("Allégade", "", 9, 2400,1, 150, 800, 2000, 6000, 9000, 1000);
        fields[10] = new SpecialField("Fængsel", "På besøg i fængslet", 10,0, "visitprison");
        fields[11] = new VacantField("FrederiksbergAllé", "", 11, 2800, 7, 200, 1000, 3000, 9000, 12500, 2000);
        fields[12] = new CoorporationField("Tuborg", "", 12, 3000,  100 );
        fields[13] = new VacantField("Bülowsvej", "", 13, 2800, 7, 200, 1000, 3000, 9000, 12000, 2000);
        fields[14] = new VacantField("GL. Kongevej", "", 14, 3200, 7, 250, 1250, 3750, 10000, 14000, 2000);
        fields[15] = new ShippingField("Scandlines", "", 15, 4000,   500);
        fields[16] = new VacantField("Bernstorffsvej", "", 16, 3600, 4, 300,1400,4000,11000,15000,2000);
        fields[17] = new ChanceField("Prøv Lykken", "Træk et tilfældigt kort. Hvad som helst kan ske.", 17);
        fields[18] = new VacantField("Hellerupvej", "", 18, 3600, 4, 300, 1400,4000,11000, 15000, 2000);
        fields[19] = new VacantField("Strandvejen", "", 19, 4000, 4, 350, 1600, 4400, 12000, 16000, 2000);
        fields[20] = new SpecialField("parkering", "Fri Parkering",20,0, "parking");
        fields[21] = new VacantField("Trianglen", "", 21, 4400, 5, 350, 1800, 5000, 14000, 17500, 3000);
        fields[22] = new ChanceField("Prøv Lykken", "Træk et tilfældigt kort. Hvad som helst kan ske.", 22);
        fields[23] = new VacantField("Østerbrogade", "", 23, 4400, 5, 350, 1800, 5000, 14000, 17500, 3000);
        fields[24] = new VacantField("Grøningen", "", 24, 4800, 5, 400, 2000, 6000, 15000, 18500, 3000);
        fields[25] = new ShippingField("Scandlines", "", 25, 4000,   500);
        fields[26] = new VacantField("Bredgade", "", 26, 5200, 6, 450, 2200, 6600, 16000, 19500,3000);
        fields[27] = new VacantField("Kgs. Nytorv", "", 27, 5200, 6, 450, 2200, 6600, 16000, 19500,3000);
        fields[28] = new CoorporationField("Carlsberg", "", 28, 3000,  100);
        fields[29] = new VacantField("Østergade", "", 29, 5600, 6, 500, 2400, 7200, 17000, 20500, 3000);
        fields[30] = new SpecialField("Gå i fængsel", "Du sendes i fængsel og mister en tur", 30, 0, "prison");
        fields[31] = new VacantField("Amagertorv", "", 31, 6000, 2, 550, 2600, 7800, 18000, 22000, 4000);
        fields[32] = new VacantField("Vimmelskaffet", "", 32, 6000, 2, 550, 2600, 7800, 18000, 22000,4000);
        fields[33] = new ChanceField("Prøv Lykken", "Træk et tilfældigt kort. Hvad som helst kan ske.", 33);
        fields[34] = new VacantField("Nygade", "", 34, 6400, 2, 600, 3000, 9000, 20000, 24000, 4000);
        fields[35] = new ShippingField("Scandlines", "", 35, 4000,   500);
        fields[36] = new ChanceField("Prøv Lykken", "Træk et tilfældigt kort. Hvad som helst kan ske.", 36);
        fields[37] = new VacantField("Frederiksberggade", "", 37, 7000, 3, 700, 3500, 10000, 22000, 26000, 4000);
        fields[38] = new SpecialField("Betal\nekstraordinær-\nstatsskat", "2000", 38, 2000, "tax");
        fields[39] = new VacantField("Rådhuspladsen", "", 39, 8000, 3,1000, 4000, 12000, 28000, 34000, 4000);

        return fields;
    }
}
