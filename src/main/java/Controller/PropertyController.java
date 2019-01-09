package Controller;

public class PropertyController {
    public enum PropertyActions {
        BuyProperty ("Køb ejendom"),
        BuyBuildings ("Køb bygninger"),
        SellBuildings ("Sælg bygninger");

        private final String label;

        PropertyActions(String s) {
            label = s;
        }

    }
}
