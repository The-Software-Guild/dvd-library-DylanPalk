package dvdCollection.Runner;

import dvdCollection.controller.dvdLibaryController;
import dvdCollection.Model.dvdLibaryFileImpl;
import dvdCollection.Model.dvdLibary;
import dvdCollection.view.dvdView;
import dvdCollection.view.UserIO;
import dvdCollection.view.UserIOConsoleImpl;

public class dvdRunner {

    public static void main(String[] args) {
            UserIO myIo = new UserIOConsoleImpl();
            dvdView myView = new dvdView(myIo);
            dvdLibary myDao = new dvdLibaryFileImpl();
            dvdLibaryController controller =
                    new dvdLibaryController(myDao, myView);
            controller.run();
    }
}
