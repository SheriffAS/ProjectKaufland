package sk.itsovy.ProjectKaufland.Items.Food;


import sk.itsovy.ProjectKaufland.Items.PcsInterface;

public class Pastry extends Food implements PcsInterface {
    private int amount;

    public Pastry(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount = amount;
    }

    public Pastry(String name, double price,int amount) {
        this(name,price,-1,amount);
    }


    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int Amount) {
        amount=Amount;
    }

    @Override
    public double getTotalPrice(){
        return amount*getPrice();
    }

}
