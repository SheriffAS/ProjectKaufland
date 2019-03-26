package sk.itsovy.ProjectKaufland.Main;

import sk.itsovy.ProjectKaufland.Items.Drinks.DraftInterface;
import sk.itsovy.ProjectKaufland.Items.Exceptions.BillException;
import sk.itsovy.ProjectKaufland.Items.Food.Fruit;
import sk.itsovy.ProjectKaufland.Items.Item;
import sk.itsovy.ProjectKaufland.Items.PcsInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static sk.itsovy.ProjectKaufland.Main.Globals.MAXITEMS;

public class Bill {
    private int count;
    private List<Item> list;
    public boolean open;
    private LocalDate date;
    private LocalTime time;

    public Bill() {
        this.list = new ArrayList<>();
        count=0;
        open=true;
    }

    public void addItem(Item item){
        if(item!=null) {
            if(open==false){
                String message= "Bill is closed biatch. It is not allowed to add any more items";
            }
            if(count==MAXITEMS){
                String message= "Bill is pulny, maximum is"+MAXITEMS+"items";
                throw new StackOverflowError(message);
            }

            list.add(item);
            count++;
        }
    }

    public void removeItem(Item item){
        if(list.contains(item)) {
            list.remove(item);
            count--;
        }
    }

    public void end(){
        if(open){
            date= LocalDate.now();
            time= LocalTime.now();
            System.out.println("Date:"+date);
            System.out.println("Time:"+time);
        }
        open=false;
        //nech sa cas generuje len raz


    }
//    public  printDate(){
//        java.util.Date date=new java.util.Date();
//        return date;
//    }
//    System.out.println(date);

    public int getCount(){
        return count;
    }
//    public double getFinalPrice(){
//        throw new IllegalThreadStateException("Method doesnt exist");
//    }

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
//
//    }
}
