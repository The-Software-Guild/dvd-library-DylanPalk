package dvdCollection.Model;

import java.io.*;
import java.util.*;

public class dvdLibaryFileImpl implements dvdLibary {

    private Map<String, DVD> dvdCollection = new HashMap<>();

    public static final String ROSTER_FILE = "DVDLibary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDVD(String dvdID, DVD DVD)
            throws dvdLibaryException {
        loadRoster();
        DVD newDVD = dvdCollection.put(dvdID, DVD);
        writeRoster();
        return newDVD;
    }

    @Override
    public void updateFile(String dvdID, DVD DVD)
            throws dvdLibaryException {
        loadRoster();
        DVD newDVD = dvdCollection.put(dvdID, DVD);
        writeRoster();
    }

    @Override
    public DVD getStudent(String dvdID)
            throws dvdLibaryException {
        loadRoster();
        return dvdCollection.get(dvdID);
    }

    @Override
    public List<DVD> getAllDVD()
            throws dvdLibaryException {
        loadRoster();
        return new ArrayList(dvdCollection.values());
    }

    @Override
    public DVD removeDVD(String dvdID)
            throws dvdLibaryException {
        loadRoster();
        DVD removedDVD = dvdCollection.remove(dvdID);
        writeRoster();
        return removedDVD;
    }


    private String marshallDVD(DVD aDVD){
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.

        // Start with the student id, since that's supposed to be first.
        String dvdText = aDVD.getDvdID() + DELIMITER;

        // add the rest of the properties in the correct order:

        // Title
        dvdText += aDVD.getTitle() + DELIMITER;

        // Release Date
        dvdText += aDVD.getReleaseDate() + DELIMITER;

        // Director Name
        dvdText += aDVD.getDirectorName() + DELIMITER;

        // Studio Date
        dvdText += aDVD.getStudio() + DELIMITER;

        //MPAA Rating
        dvdText += aDVD.getMpaaRating() + DELIMITER;

        // Rating + Further Notes - don't forget to skip the DELIMITER here.
        dvdText += aDVD.getUserOpionion();

        // We have now turned a student to text! Return it!
        return dvdText;
    }

    private DVD unmarshallDVD(String studentAsText){
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in textToken.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]
        String[] lineToken = studentAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String dvdID = lineToken[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        DVD DVDFromFile = new DVD(dvdID);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - Title
        DVDFromFile.setTitle(lineToken[1]);

        // Index 2 - ReleaseDate
        DVDFromFile.setReleaseDate(lineToken[2]);

        // Index 3 - DirectorName
        DVDFromFile.setDirectorName(lineToken[3]);

        //Index 4 - Studio
        DVDFromFile.setStudio(lineToken[4]);

        //Index 5 - MpaaRating
        DVDFromFile.setMpaaRating(lineToken[5]);

        //Index 6 - user Opionion
        DVDFromFile.setUserOpionion(lineToken[6]);

        // We have now created a student! Return it!
        return DVDFromFile;
    }

    private void loadRoster() throws dvdLibaryException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new dvdLibaryException(
                    "-_- Could not load roster data into memory.", e);
        }

        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDVD;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            dvdCollection.put(currentDVD.getDvdID(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *
     * @throws dvdLibaryException if an error occurs writing to the file
     */
    private void writeRoster() throws dvdLibaryException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new dvdLibaryException(
                    "Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String lineToken;
        List<DVD> DVDList = this.getAllDVD();
        for (DVD currentDVD : DVDList) {
            // turn a Student into a String
            lineToken = marshallDVD(currentDVD);
            // write the Student object to the file
            out.println(lineToken);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }



}
