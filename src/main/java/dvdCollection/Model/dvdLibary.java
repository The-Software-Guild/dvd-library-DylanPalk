package dvdCollection.Model;

import java.util.List;

public interface dvdLibary {
        /**
         * Adds the given Student to the roster and associates it with the given
         * student id. If there is already a student associated with the given
         * student id it will return that student object, otherwise it will
         * return null.
         *
         * @param dvdID id with which student is to be associated
         * @param DVD student to be added to the roster
         * @return the Student object previously associated with the given
         * student id if it exists, null otherwise
         * @throws dvdLibaryException
         */
        DVD addDVD(String dvdID, DVD DVD)
                throws dvdLibaryException;

        /**
         * Returns a List of all Students on the roster.
         *
         * @return Student List containing all students on the roster.
         * @throws dvdLibaryException
         */
        List<DVD> getAllDVD()
                throws dvdLibaryException;

        /**
         * Returns the student object associated with the given student id.
         * Returns null if no such student exists
         *
         * @param studentId ID of the student to retrieve
         * @return the Student object associated with the given student id,
         * null if no such student exists
         * @throws dvdLibaryException
         */
        DVD getStudent(String studentId)
                throws dvdLibaryException;

        /**
         * Removes from the roster the student associated with the given id.
         * Returns the student object that is being removed or null if
         * there is no student associated with the given id
         *
         * @param dvdID id of student to be removed
         * @return Student object that was removed or null if no student
         * was associated with the given student id
         * @throws dvdLibaryException
         */
        DVD removeDVD(String dvdID)
                throws dvdLibaryException;

        void updateFile(String dvdID, DVD dvd)
                throws dvdLibaryException;
}
