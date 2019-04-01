package sk.itsovy.ProjectKaufland.Main;

import sk.itsovy.ProjectKaufland.Items.Database.Database;
import sk.itsovy.ProjectKaufland.Items.Drinks.Bottle;
import sk.itsovy.ProjectKaufland.Items.Drinks.Draft;
import sk.itsovy.ProjectKaufland.Items.Drinks.DraftInterface;
import sk.itsovy.ProjectKaufland.Items.Exceptions.BillException;
import sk.itsovy.ProjectKaufland.Items.Food.Fruit;
import sk.itsovy.ProjectKaufland.Items.Item;
import sk.itsovy.ProjectKaufland.Items.PcsInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static sk.itsovy.ProjectKaufland.Main.Globals.MAXITEMS;

public class Bill {
    private int count;
    private List<Item> list;
    public boolean open;
    private LocalDate date= LocalDate.now();
    private LocalTime time= LocalTime.now();
    private Date datetime = new Date();

    public Bill() {
        this.list = new ArrayList<>();
        count=0;
        open=true;
    }

    public void addItem(Item item) throws BillException {
        Item itemTemp = checkItem(item);
        if (itemTemp == item) {
            if (open == false) {
                String message = "Bill is closed . It is not allowed to add any more items";
                throw new StackOverflowError(message);
            }
            if (count == MAXITEMS) {
                String message = "Bill is pulny, maximum is" + MAXITEMS + "items";
                throw new StackOverflowError(message);
            } else {

                list.add(item);
                count++;
            }

        }
    }

    public Item checkItem(Item item){
        for(Item it :list){
//            System.out.println("item: "+item.getName().toLowerCase());
//            System.out.println("it : "+it.getName().toLowerCase());
            if (item.getName().toLowerCase().equals(it.getName().toLowerCase())){
                System.out.println("PRESLA PODMINKA");
                if ( item.getClass().getName().equals(it.getClass().getName())){
                    updateItem(item,it);
                    return null;
                }
            }
        }
        return item;
    }

    public void updateItem(Item item, Item oldItem){
         if (item instanceof Fruit && oldItem instanceof Fruit){
             ((Fruit) oldItem).setWeight(((Fruit) item).getWeight()+((Fruit) oldItem).getWeight());
         }

         else if(item instanceof DraftInterface && oldItem instanceof DraftInterface){
             ((DraftInterface) oldItem).setVolume(((DraftInterface) item).getVolume()+((DraftInterface) oldItem).getVolume());
         }

         else if(item instanceof PcsInterface && oldItem instanceof PcsInterface){
             ((PcsInterface) oldItem).setAmount(((PcsInterface) item).getAmount()+((PcsInterface) oldItem).getAmount());
         }
    }

    public void removeItem(Item item){
        if(list.contains(item)) {
            list.remove(item);
            count--;
        }
    }

    public void end() throws SQLException {
        if(open) {
            System.out.println("Date:" + date);
            System.out.println("Time:" + time);
            Database db = Database.getInstance();
            db.insertNewBill(this);
        }

        open=false;
        //nech sa cas generuje len raz
    }

    public Date getDatetime() {
        return datetime;
    }
    public int getCount(){
        return count;
    }

    public double getFinalPrice(){
        double finalPrice=0;
        for(Item item : list){
            finalPrice = finalPrice + item.getTotalPrice();
        }
        return finalPrice;
   }


    // get final price in dollars

    public double getFinalPriceDollars() throws IOException {
        double finalUS = getFinalPrice();
        return finalUS * Internet.getUSD();
    }

    public List<Item> getBill() {
        return list;
    }

    public void printBill(){
        if(count==0){
            System.out.println("Nothing to print, Bill is empty");
        }else{
            for(Item item:list){
                if(item instanceof DraftInterface){
                    System.out.print(item.getName()+" "+((DraftInterface) item).getVolume()+" ");
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }else if(item instanceof Fruit){
                    System.out.println(item.getName()+" "+((Fruit) item).getWeight()+" ");
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }else if(item instanceof PcsInterface){
                    System.out.println(item.getName()+" "+((PcsInterface) item).getAmount()+" ");
                    System.out.println(item.getPrice()+" "+item.getTotalPrice());
                }
            }
        }
    }



//    public String billError(){
//      urobit exception
//    }
}
