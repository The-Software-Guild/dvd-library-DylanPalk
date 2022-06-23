package dvdCollection.class_roster;

import dvdCollection.controller.dvdLibaryController;
import dvdCollection.dao.dvdLibaryFileImpl;
import dvdCollection.dao.dvdLibary;
import dvdCollection.ui.dvdView;
import dvdCollection.ui.UserIO;
import dvdCollection.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
            UserIO myIo = new UserIOConsoleImpl();
            dvdView myView = new dvdView(myIo);
            dvdLibary myDao = new dvdLibaryFileImpl();
            dvdLibaryController controller =
                    new dvdLibaryController(myDao, myView);
            controller.run();
    }
}
