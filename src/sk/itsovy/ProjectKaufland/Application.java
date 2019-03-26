package sk.itsovy.ProjectKaufland;

import sk.itsovy.ProjectKaufland.Items.Drinks.Bottle;
import sk.itsovy.ProjectKaufland.Items.Drinks.Draft;
import sk.itsovy.ProjectKaufland.Items.Drinks.Drink;
import sk.itsovy.ProjectKaufland.Items.Food.Food;
import sk.itsovy.ProjectKaufland.Items.Food.Fruit;
import sk.itsovy.ProjectKaufland.Items.Food.Pastry;
import sk.itsovy.ProjectKaufland.Items.Goods.Goods;
import sk.itsovy.ProjectKaufland.Items.Item;
import sk.itsovy.ProjectKaufland.Main.Bill;

import static sk.itsovy.ProjectKaufland.Items.Goods.Category.SCHOOL;

public class Application {
    private static Application app=new Application();

    private Application(){ }

    public static Application getInstance(){
        return app;
    }

    public void example() {

        Bill bill = new Bill();
        Bottle milk = new Bottle("Milk 3,5", 0.80, 2);
        bill.addItem(milk);

        Item pizza = new Pastry("Gazdovska",5.50,200,2);
        bill.addItem(pizza);

        Food jablko = new Fruit("Golden apple",0.59,37,0.80);
        bill.addItem(jablko);

        Goods pencil= new Goods("Pencil 007",1.20,3,SCHOOL);
        bill.addItem(pencil);

        Drink juice= new Draft("Relax",1.30,true,3);
        bill.addItem(juice);

        Drink beer= new Draft("Birell nealko",1,false,1);
        bill.addItem(beer);
        bill.removeItem(beer);
        bill.getCount();
        bill.printBill();
        bill.end();
    }
}
