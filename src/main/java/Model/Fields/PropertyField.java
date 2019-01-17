package Model.Fields;

import Model.Player;

import java.awt.*;

/**
 * This class manages fields that are ownable and able to be built on (houses and hotels)
 * These fields can be bought by a player.
 */
public class PropertyField extends Field {
    private int[] prices;
    private Player owner;
    private Color color;
    private int currentRentIndex;
    private int houses = 0;
    private boolean hotel = false;
    private boolean soldHotel = false;

    /**
     * PropertyFields should be instantiated with a price array of length 10. These array elemented are expected to be
     * {Price to buy, rent, rent with 1 house, 2 houses, 3 houses, 4 houses,
     * a hotel, house price, hotel prisce, price from pawning}
     * the pawn price is not used in any logic yet.
     * The field should have a color too. This is used for some game logic, and for visual purposes
     *
     * @param name
     * @param subText
     * @param description
     * @param prices      {Price to buy, rent, rent with 1 house, 2 houses, 3 houses, 4 houses, a hotel, house price, hotel prisce, price from pawning}
     * @param color
     */
    public PropertyField(String name, String subText, String description, int[] prices, Color color){
        super(name, subText, description);
        this.prices = prices;
        this.color = color;
        this.currentRentIndex = 1;
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public String getSubText(){
        return super.getSubText();
    }

    @Override
    public String getDescription(){
        return super.getDescription();
    }

    /**
     * When a player lands on an owned field, they will pay rent to the owner.
     * If not another part of the game can take care of letting it be bought
     * In both cases, the main player log is written to
     *
     * @param player that has landed on the field
     */
    @Override
    public void fieldAction(Player player){
        if (this.isOwned()){
            payToPlayerLogic(player);
        } else {
            player.setLastAction(player.getLastAction() + "\n - Er landet på " +
                    this.getName());

            System.out.println("[INFO] " + player.getName() + " er landet på " +
                    this.getName());
        }
    }

    /**
     * If the owner is not out of the game (bankrupt) rent is paid.
     * Otherwise the action is ignored
     *
     * @param player the player who will be paying rent
     */
    private void payToPlayerLogic(Player player){
        if (!owner.isBankrupt()){
            player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                    this.getRent() + " kr. til " +
                    this.getOwner().getName() + ".");

            System.out.println("[INFO] " + player.getName() + " Har betalt " +
                    this.getRent() + " kr. til " +
                    this.getOwner().getName() + ".");
            payToPlayerField(player);
        }
    }

    /**
     * The actual rent payment of the house.
     * Simply calcualte the rent, pay the owner, remove money from player.
     *
     * @param player
     */
    private void payToPlayerField(Player player){
        Player owner = this.getOwner();
        int payment = this.getRent();
        player.addMoney(-payment);
        owner.addMoney(payment);
    }

    /**
     * Method to buy a PropertyField from given price array. Also write to log.
     *
     * @param player the player who will buy the property
     */
    public void buyField(Player player){
        if (!this.isOwned()){
            player.setLastAction(player.getLastAction() + "\n - Har købt " +
                    this.getName() + " for " +
                    this.getPrice() + " kr.");

            System.out.println("[INFO] " + player.getName() + " Har købt " +
                    this.getName() + " for " +
                    this.getPrice() + " kr.");

            int payment = this.getPrice();
            player.addMoney(-payment);
            this.setOwner(player);
        }
    }

    /**
     * Expand property with houses. A player can have a maximum of 4 houses.
     * Player cannot buy houses on a property if they can't afford it.
     */
    public void buyHouse(){
        if (houses >= 0 && houses < 4 && owner.getMoney() >= getHousePrice()){
            houses++;
            owner.addMoney(-getHousePrice());

            calcRentIndex();

            owner.setLastAction(owner.getLastAction() + "\n - Har købt et hus på " + this.getName()
                    + "til " + this.getHousePrice() + " kr.");

            System.out.println("[INFO] " + owner.getName() + " Har købt et hus på " + this.getName() +
                    " til " + this.getHousePrice() + " kr.");
        }
    }

    /**
     * Expand property with a hotel. A player can have a maximum of 1 hotel AFTER BUYING 4 HOUSES.
     * Player cannot buy a hotel on a property if they can't afford it.
     */
    public void buyHotel(){
        if (!hotel && houses == 4 && owner.getMoney() >= getHotelPrice()){
            hotel = true;
            owner.addMoney(-getHotelPrice());

            calcRentIndex();

            owner.setLastAction(owner.getLastAction() + "\n - Har købt et hotel på " + this.getName()
                    + "til " + this.getHotelPrice() + " kr.");

            System.out.println("[INFO] " + owner.getName() + " Har købt et hotel på " + this.getName() +
                    " til " + this.getHotelPrice() + " kr.");
        }
    }

    /**
     * Sell house if any are owned. Only get back half of the buying price.
     */
    public void sellHouse(){
        if (houses > 0 && houses <= 4){
            houses--;
            owner.addMoney((getHousePrice() / 2));

            calcRentIndex();

            owner.setLastAction(owner.getLastAction() + "\n - Har solgt et hus på " + this.getName()
                    + "til " + (this.getHousePrice() / 2) + " kr.");

            System.out.println("[INFO] " + owner.getName() + " Har solgt et hus på " + this.getName() +
                    " til " + (this.getHousePrice() / 2) + " kr.");
        }
    }

    /**
     * Sell hotel if one is owned. Only get back half of the buying price.
     */
    public void sellHotel(){
        if (hotel && houses == 4){
            hotel = false;
            soldHotel = true;
            owner.addMoney((getHotelPrice() / 2));

            calcRentIndex();


            owner.setLastAction(owner.getLastAction() + "\n - Har solgt et hotel på " + this.getName()
                    + "til " + (this.getHotelPrice() / 2) + " kr.");

            System.out.println("[INFO] " + owner.getName() + " Har solgt et hotel på " + this.getName() +
                    " til " + (this.getHotelPrice() / 2) + " kr.");
        }
    }

    public int getHousePrice(){
        return prices[7];
    }

    public int getHotelPrice(){
        return prices[8];
    }

    public int getPrice(){
        return prices[0];
    }

    public int getRent(){
        return prices[currentRentIndex];
    }

    public Player getOwner(){
        return owner;
    }

    public boolean isOwned(){
        Player owner = this.getOwner();
        return owner != null;
    }

    public Color getColor(){
        return color;
    }


    /**
     * Calculate rent from the currentRentIndex. This index is incremented every time a house or hotel is bought.
     */
    public void calcRentIndex(){
        if (hotel){
            currentRentIndex = 6;

        } else if (houses > 0 && houses <= 4){
            currentRentIndex = houses + 1;
        }
    }

    private void setOwner(Player owner){
        this.owner = owner;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public int[] getPrices(){
        return prices;
    }

    public void setPrices(int[] prices){
        this.prices = prices;
    }

    public int getCurrentRentIndex(){
        return currentRentIndex;
    }

    public void setCurrentRentIndex(int currentRentIndex){
        this.currentRentIndex = currentRentIndex;
    }

    public int getHouses(){
        return houses;
    }

    public void setHouses(int houses){
        this.houses = houses;
    }

    public boolean isHotel(){
        return hotel;
    }

    public void setHotel(boolean hotel){
        this.hotel = hotel;
    }

    public boolean isSoldHotel(){
        return soldHotel;
    }

    public void setSoldHotel(boolean soldHotel){
        this.soldHotel = soldHotel;
    }
}
