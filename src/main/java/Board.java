import Fields.*;

public class Board {

    private Fields.SuperField[] fields;
    private int totalFields;
    private Object SuperField;

    public Board(){
        this.fields = setupField();
    }

    public SuperField getFielobject(int id) {

        while (id > 40) {
            id = id - 41;
        }

        return fields[id];
    }
    public SuperField[] setupField(){
        fields = new SuperField[40];
        fields[0] = new SpecialField("START", "", 1,false,false,false, 0);
        fields[1] = new VacantField("Rødovrevej", "", 2, 1200, 0, 50, 250,750, 2250, 4000, 6000, 1000);
        fields[2] = new ChanceField("chancefield", "", 3, true);
        fields[3] = new VacantField("Hvidovrevej", "", 4, 1200, 0, 50, 250, 750, 2250, 4000, 6000, 1000);
        fields[4] = new SpecialField("Skat", "", 5, false, true, false, 4000);
        fields[5] = new ShippingField("Scandlines", "", 6, 4000,   500);
        fields[6] = new VacantField("Roskildevej", "", 7, 2000, 1, 100, 600, 1800, 5400, 8000, 11000, 1000);
        fields[7] = new ChanceField("chancefield", "", 8, true);
        fields[8] = new VacantField("Valby Langgade", "", 9, 2000,1, 100, 600, 1800, 5400, 8000, 11000, 1000);
        fields[9] = new VacantField("Allégade", "", 10, 2400,1, 150, 800, 2000, 6000, 9000, 12000, 1000);
        fields[10] = new SpecialField("På besøg", "", 11, false, false, false, 0);
        fields[11] = new VacantField("FrederiksbergAllé", "", 12, 2800, 7, 200, 1000, 3000, 9000, 12500, 15000, 2000);
        fields[12] = new CoorporationField("Squash", "", 13, 3000,  100 );
        fields[13] = new VacantField("Bülowsvej", "", 14, 2800, 7, 200, 1000, 3000, 9000, 12000, 15000, 2000);
        fields[14] = new VacantField("GL. Kongevej", "", 15, 3200, 7, 250, 1250, 3750, 10000, 14000, 18000, 2000);
        fields[15] = new ShippingField("Mols-linien", "", 16, 4000,   500);
        fields[16] = new VacantField("Bernstorffsvej", "", 17, 3600, 4, 300,1400,4000,11000,15000,19000,2000);
        fields[17] = new ChanceField("chancefield", "", 18, true);
        fields[18] = new VacantField("Hellerupvej", "", 19, 3600, 4, 300, 1400,4000,11000, 15000, 19000, 2000);
        fields[19] = new VacantField("Strandvejen", "", 20, 4000, 4, 350, 1600, 4400, 12000, 16000, 20000, 2000);
        fields[20] = new SpecialField("parkering", "",21, false, false, false, 0);
        fields[21] = new VacantField("Trianglen", "", 22, 4400, 5, 350, 1800, 5000, 14000, 17500, 21000, 3000);
        fields[22] = new ChanceField("chancefield", "", 23, true);
        fields[23] = new VacantField("Østerbrogade", "", 24, 4400, 5, 350, 1800, 5000, 14000, 17500, 21000, 3000);
        fields[24] = new VacantField("Grøningen", "", 25, 4800, 5, 400, 2000, 6000, 15000, 18500, 22000, 3000);
        fields[25] = new ShippingField("Scandlines", "", 26, 4000,   500);
        fields[26] = new VacantField("Bredgade", "", 27, 5200, 6, 450, 2200, 6600, 16000, 19500,23000, 3000);
        fields[27] = new VacantField("Kgs. Nytorv", "", 28, 5200, 6, 450, 2200, 6600, 16000, 19500,23000,3000);
        fields[28] = new CoorporationField("Coca-cola", "", 29, 3000,  100);
        fields[29] = new VacantField("Østergade", "", 30, 5600, 6, 500, 2400, 7200, 17000, 20500, 24000, 3000);
        fields[30] = new SpecialField("de fængsles", "", 31, true, false, false, 0);
        fields[31] = new VacantField("Amagertorv", "", 32, 6000, 2, 550, 2600, 7800, 18000, 22000, 25000, 4000);
        fields[32] = new VacantField("Vimmelskaffet", "", 33, 6000, 2, 550, 2600, 7800, 18000, 22000,25000, 4000);
        fields[33] = new ChanceField("chancefield", "", 34, true);
        fields[34] = new VacantField("Nygade", "", 35, 6400, 2, 600, 3000, 9000, 20000, 24000, 28000, 4000);
        fields[35] = new ShippingField("Scandlines", "", 26, 4000,   500);
        fields[36] = new ChanceField("chancefield", "", 37, true);
        fields[37] = new VacantField("Frederiksberggade", "", 38, 7000, 3, 700, 3500, 10000, 22000, 26000, 30000, 4000);
        fields[38] = new SpecialField("Skat", "", 39, false, true, false, 2000);
        fields[39] = new VacantField("Rådhuspladsen", "", 40, 8000, 3,1000, 4000, 12000, 28000, 34000, 40000, 4000);

        return fields;
    }

}
