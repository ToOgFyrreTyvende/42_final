package Model.Fields;

import Model.Player;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Field;
import gui_fields.GUI_Shipping;
import gui_resources.Attrs;

import java.awt.*;

public class CompanyField extends Field {

    private Color color;
    private boolean isShipping;
    private int price;

    public CompanyField(String name, String subText, String description, int price, Color color, boolean isShipping) {
        super(name, subText, description);
        this.price = price;
        this.color = color;
        this.isShipping = isShipping;
    }

    @Override
    public void fieldAction(Player player) {

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSubText() {
        return super.getSubText();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isShipping() {
        return isShipping;
    }

    public void setShipping(boolean shipping) {
        isShipping = shipping;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
