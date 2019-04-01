package sk.itsovy.ProjectKaufland.Items.Goods;


import sk.itsovy.ProjectKaufland.Items.Item;
import sk.itsovy.ProjectKaufland.Items.PcsInterface;

public class Goods extends Item implements PcsInterface {
    private int amount;
    private Category type;

    public Goods(String name, double price, int amount, Category type) {
        super(name, price);
        this.amount=amount;
        this.type=type;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public Category getType() {
        return type;
    }

    @Override
    public void setAmount(int Amount){
        amount=Amount;
    }
}