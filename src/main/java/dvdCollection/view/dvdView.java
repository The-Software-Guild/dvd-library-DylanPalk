package dvdCollection.view;

import dvdCollection.Model.DVD;


import java.util.List;

public class dvdView {
    private UserIO io;

    public dvdView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVD");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Search Via Title");
        io.print("6. Edit DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD dvdCollection : DVDList) {
            String dvdInfo = String.format("%s, %s, %s, %s, %s, %s,",
                    dvdCollection.getTitle(),
                    dvdCollection.getReleaseDate(),
                    dvdCollection.getDirectorName(),
                    dvdCollection.getStudio(),
                    dvdCollection.getUserOpionion(),
                    dvdCollection.getDvdID());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD's ===");
    }

    public DVD getDvdInfo() {
        //Needs Updating
        String DVDId = io.readString("Please enter DVD ID");

        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter the release date");
        String directorName = io.readString("Please enter the director name");
        String studioName = io.readString("Please enter the Studio name");
        String mpaaRating = io.readString("Please enter the MPAA rating");
        String userOpinion = io.readString("Please enter a rating or further notes");

        DVD currentDVD = new DVD(DVDId);

        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studioName);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setUserOpionion(userOpinion);
        currentDVD.setDvdID(DVDId);

        return currentDVD;
    }

    public void dvdDisplayBanner() {
        io.print("=== DVD Collection ===");
    }

    public String getDVDIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }

    public String getDvdTitle() {
        return io.readString("Please enter DVD Title.");
    }

    public String getField() {
        return io.readString("Please enter a field to edit. " +
                " 1. title" +
                " 2. release date" +
                " 3. director name" +
                " 4. Studio Name " +
                " 5. MPAA rating" +
                " 6. furtherNotes");
    }


    public void displayDVD(DVD DVD) {
        if (DVD != null) {
            io.print(DVD.getTitle());
            io.print(DVD.getReleaseDate());
            io.print(DVD.getDirectorName());
            io.print(DVD.getStudio());
            io.print(DVD.getUserOpionion());
            io.print(DVD.getDvdID());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDVDTitle(List<DVD> DVDList, String title) {
        if (DVDList != null) {
            for (DVD DVD : DVDList) {
                if (title.equals(DVD.getTitle())) {
                    io.print("DVD ID: " + DVD.getDvdID());
                    io.print("DVD release date: " + DVD.getReleaseDate());
                    io.print("DVD director name: " + DVD.getDirectorName());
                    io.print("DVD studio name: " + DVD.getStudio());
                    io.print("DVD userOpinion/Rating: " + DVD.getUserOpionion() + "\n");
                }
            }
            io.readString("Please hit enter to continue.");
        }
        else{
            System.out.println("No DVDs");
        }
    }

    public void editDVDInformation(DVD DVD, String field, String value){
        if (DVD != null) {
            switch(field) {
                case "1":
                    DVD.setTitle(value);
                    break;
                case "2":
                    DVD.setReleaseDate(value);
                    break;
                case "3":
                    DVD.setDirectorName(value);
                    break;
                case "4":
                    DVD.setStudio(value);
                    break;
                case "5":
                    DVD.setMpaaRating(value);
                    break;
                case "6":
                    DVD.setUserOpionion(value);
                    break;
                default:
                    System.out.println("didn't match any field!");
            }
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD DVDRecord) {
        if(DVDRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayTitleSearchBanner() {
        io.print("=== Searching Title ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public String getValue() {
        return io.readString("Please enter a value to replace.");
    }
}
