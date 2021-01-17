package Model.Property;

    public class Ownable {
        public boolean available;
        public int propertyID;
        public int owner;
        public static final int SHIPPING_TYPE = 0;
        public static final int COORP_TYPE = 1;
        public static final int VACANT_TYPE = 2;
        private final int typeInt;

        public Ownable(int ID, int typeInt) {
            this.available = isAvailable();
            this.owner = 10;
            this.propertyID = ID;
            this.typeInt = typeInt;
        }

        public void seizeProperty(int playerNumber) {
            owner = playerNumber;
        }

        public void setOwner(int ID) {
            owner = ID;
        }

        public int getOwner() {
            return owner;
        }

        public int getID() {
            return propertyID;
        }

        public boolean isAvailable() {
            return owner == 0;
        }

        public int getType() {

            return typeInt;
        }
    }
