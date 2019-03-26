package sk.itsovy.ProjectKaufland.Items.Drinks;

import sk.itsovy.ProjectKaufland.Items.Item;

//abstraktna
public abstract class Drink extends Item {
    private boolean sweet;

    public Drink(String name, double price, boolean sweet) {
        super(name,price);
        this.sweet=sweet;
    }

    public boolean isSweet() {
        return sweet;
    }

}
