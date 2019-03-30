package sk.itsovy.ProjectKaufland.Main;

import sk.itsovy.ProjectKaufland.Items.Exceptions.BillException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] Args) throws BillException, ParseException, IOException, TransformerException, ParserConfigurationException, SQLException {



        {
            Application app=Application.getInstance();
            app.example();

        }
//        {
//            Application app = new Application();
//            app.example();
//        }
    }
}
