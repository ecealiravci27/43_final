public class Board {

    private SuperField[] fields;
    private int totalFields;

    public Board(){
        this.fields = setupField();
    }

    public SuperField getFielobject(int id) {

        while (id > 40) {
            id = id - 41;
        }

        return SuperField[id];
    }
    public SuperField[] setupField(){
        fields = new SuperField[40];
        SuperField[0] = new SpecialField("START", "", 1,false,false,false, 0)
        SuperField[1] = new VacantField("Rødovrevej", "", 2, 1200, 0, 50, 250,750, 2250, 4000, 6000, 1000, 1000)
        SuperField[2] = new ChanceField("chancefield", "", 3, true)
        SuperField[3] = new VacantField()
        SuperField[4] = new SpecialField("Skat", "", 5, false, true, false, 4000)
        SuperField[5] = new ShippingField("Scandlines", "", 6, 4000, 0,  500);
        SuperField[6] = new VacantField()
        SuperField[7] = new ChanceField("chancefield", "", 8, true)
        SuperField[8] = new VacantField()
        SuperField[9] = new VacantField()
        SuperField[10] = new SpecialField("På besøg", "", 11, false, false, false, 0)
        SuperField[11] = new VacantField()
        SuperField[12] = new CoorporationField("Squash", "", 13, 3000, 5, 0 )
        SuperField[13] = new VacantField()
        SuperField[14] = new VacantField()
        SuperField[15] = new ShippingField("Mols-linien", "", 16, 4000, 5,  500)
        SuperField[16] = new VacantField()
        SuperField[17] = new ChanceField("chancefield", "", 18, true))
        SuperField[18] = new VacantField()
        SuperField[19] = new VacantField()
        SuperField[20] = new SpecialField("parkering", "",21, false, false, false, 0)
        SuperField[21] = new VacantField()
        SuperField[22] = new ChanceField("chancefield", "", 23, true))
        SuperField[23] = new VacantField()
        SuperField[24] = new VacantField()
        SuperField[25] = new ShippingField("Scandlines", "", 26, 4000, 0,  500)
        SuperField[26] = new VacantField()
        SuperField[27] = new VacantField()
        SuperField[28] = new CoorporationField("Coca-cola", "", 29, 3000, 0)
        SuperField[29] = new VacantField()
        SuperField[30] = new SpecialField("de fængsles", "", 31, true, false, false, 0)
        SuperField[31] = new VacantField()
        SuperField[32] = new VacantField()
        SuperField[33] = new ChanceField("chancefield", "", 34, true)
        SuperField[34] = new VacantField(("Scandlines", "", 26, 4000, 0,  500)
        SuperField[35] = new ShippingField()
        SuperField[36] = new ChanceField("chancefield", "", 37, true)
        SuperField[37] = new VacantField()
        SuperField[38] = new SpecialField("Skat", "", 39, false, true, false, 2000)
        SuperField[39] = new VacantField()
    }

}
