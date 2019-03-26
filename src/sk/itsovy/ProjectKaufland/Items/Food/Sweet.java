package sk.itsovy.ProjectKaufland.Items.Food;

import sk.itsovy.ProjectKaufland.Items.Food.Food;
import sk.itsovy.ProjectKaufland.Items.PcsInterface;

public class Sweet extends Food implements PcsInterface {
    private int amount;


    public Sweet(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount = amount;
    }

    @Override
    public double getTotalPrice(){
        return amount*getPrice();
    }

    @Override
    public int getAmount(){
        return amount;
    }

}
