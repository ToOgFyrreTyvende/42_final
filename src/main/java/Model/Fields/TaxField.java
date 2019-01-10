package Model.Fields;

import Model.Player;

public class TaxField extends Field {

    private int tax;
    private boolean choice;

    public TaxField(String name, String subText, String description, int tax, boolean choice) {
        super(name, subText, description);
        this.tax = tax;
        this.choice = choice;
    }

    @Override
    public void fieldAction(Player player) {
        if (this.isChoice()){

            payTax(player);

            player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                    this.getName() + " kr. i indkomstskat.");
            System.out.println("[INFO] " + player.getName() + " Har betalt " +
                    this.getName() + " kr. i indkomstskat.");
        }else{

            payTax(player);

            player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                    this.getName() + " kr. i ekstraordinær statsskat.");
            System.out.println("[INFO] " + player.getName() + " Har betalt " +
                    this.getName() + " kr. i ekstraordinær statsskat.");
        }
    }

    private void payTax(Player player) {
        int betaling = this.getTax();
        player.addMoney( - betaling);
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}
