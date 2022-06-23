package dvdCollection.dto;

public class DVD {
    private String title;
    private String releaseDate;
    private String dvdID;
    private String directorName;
    private String studio;
    private String userOpionion;

    private String mpaaRating;
    public DVD(String dvdID) {
        this.dvdID = dvdID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDvdID() {
        return dvdID;
    }
    public void setDvdID(String dvdID) {
        this.dvdID = dvdID;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserOpionion() {
        return userOpionion;
    }

    public void setUserOpionion(String userOpionion) {
        this.userOpionion = userOpionion;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

}
