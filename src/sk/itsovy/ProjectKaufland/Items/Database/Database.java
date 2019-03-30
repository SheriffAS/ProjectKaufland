package sk.itsovy.ProjectKaufland.Items.Database;



import sk.itsovy.ProjectKaufland.Items.Drinks.DraftInterface;
import sk.itsovy.ProjectKaufland.Items.Food.Fruit;
import sk.itsovy.ProjectKaufland.Items.Item;
import sk.itsovy.ProjectKaufland.Items.PcsInterface;
import sk.itsovy.ProjectKaufland.Main.Bill;
import sk.itsovy.ProjectKaufland.Main.Globals;

import java.sql.*;



public class Database {


    private static Database db = new Database();
    private Database(){
    }
    public static Database getInstance(){
        return db;
    }

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName(Globals.databClassforName);
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(Globals.url, Globals.username,Globals.password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void insertNewBill(Bill bill) throws SQLException  {
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO bill (datetime, totalPrice) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, new java.sql.Timestamp(bill.getDatetime().getTime()));
            statement.setDouble(2, bill.getFinalPrice());

            int result = statement.executeUpdate();

            if (result == 0) {
                throw new SQLException("Creating user failed.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println(generatedKeys.getLong(1));
                    for (Item item : bill.getBill()) {
                        PreparedStatement statement1 = conn.prepareStatement("INSERT INTO items (OrderID, Name, Price, Count, Unit) values(?,?,?,?,?)");
                        statement1.setString(1, String.valueOf(generatedKeys.getLong(1)));
                        statement1.setString(2, item.getName());
                        statement1.setDouble(3, item.getPrice());
                        if (item instanceof Fruit) {
                            statement1.setDouble(4, ((Fruit) item).getWeight());
                            statement1.setString(5, "kg");
                        }
                        else  if (item instanceof DraftInterface) {
                            statement1.setDouble(4, ((DraftInterface) item).getVolume());
                            statement1.setString(5, "l");
                        }
                        else if (item instanceof PcsInterface) {
                            statement1.setDouble(4, ((PcsInterface) item).getAmount());
                            statement1.setString(5, "pcs");
                        }


                        int ex = statement1.executeUpdate();
                    }

                } else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }
            conn.commit();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }


    }

}