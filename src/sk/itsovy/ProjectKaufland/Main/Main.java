package sk.itsovy.ProjectKaufland.Main;

import sk.itsovy.ProjectKaufland.Application;
import sk.itsovy.ProjectKaufland.Items.Exceptions.BillException;

public class Main {
    public static void main(String[] Args) throws BillException {



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
