package dvdCollection.controller;

import dvdCollection.Model.dvdLibaryException;
import dvdCollection.Model.dvdLibary;
import dvdCollection.Model.DVD;
import dvdCollection.view.dvdView;
import dvdCollection.view.UserIO;
import dvdCollection.view.UserIOConsoleImpl;

import java.util.List;

public class dvdLibaryController {
    private dvdView view;
    private dvdLibary dao;

    public dvdLibaryController(dvdLibary dao, dvdView view) {
        this.dao = dao;
        this.view = view;
    }

    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        searchTitle();
                        break;
                    case 6:
                        editDVD();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (dvdLibaryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws dvdLibaryException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getDvdInfo();
        dao.addDVD(newDVD.getDvdID(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listStudents() throws dvdLibaryException {
        view.displayDisplayAllBanner();
        List<DVD> DVDList = dao.getAllDVD();
        view.displayDVDList(DVDList);
    }

    private void viewStudent() throws dvdLibaryException {
        view.dvdDisplayBanner();
        String dvdID = view.getDVDIdChoice();
        DVD DVD = dao.getStudent(dvdID);
        view.displayDVD(DVD);
    }

    private void removeStudent() throws dvdLibaryException {
        view.displayRemoveDVDBanner();
        String dvdID = view.getDVDIdChoice();
        DVD removedDVD = dao.removeDVD(dvdID);
        view.displayRemoveResult(removedDVD);
    }

    private void searchTitle() throws dvdLibaryException {
        view.displayTitleSearchBanner();
        String title = view.getDvdTitle();
        List<DVD> DVDList = dao.getAllDVD();
        view.displayDVDTitle(DVDList, title);
    }

    private void editDVD() throws dvdLibaryException {
        view.displayEditDVDBanner();
        String dvdId = view.getDVDIdChoice();
        String userField = view.getField();
        String userValue = view.getValue();
        DVD DVD = dao.getStudent(dvdId);
        view.editDVDInformation(DVD, userField.toLowerCase(),userValue);
        dao.updateFile(dvdId, DVD);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
