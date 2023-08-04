package davis.c196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity for Term information
 * @author Brandon Davis
 */


@Entity(tableName = "Terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int TermID;

    private String title;
    private String start;
    private String end;

public Term (int TermID,String title, String start, String end){
    this.TermID=TermID;
    this.title=title;
    this.start=start;
    this.end=end;


}


    /**
     * Getter for Term ID
     * @return
     */
    public int getTermID() {
        return TermID;
    }

    /**
     * Setter for Term ID
     * @param TermID
     */
    public void setTermID(int TermID) {
        this.TermID = TermID;
    }

    /**
     * Getter for Title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for Title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for Start date
     * @return
     */
    public String getStart() {
        return start;
    }

    /**
     * Setter for Start Date
     * @param start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Getter for End Date
     * @return
     */
    public String getEnd() {
        return end;
    }

    /**
     * Setter for End Date
     * @param end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString () {
        return "Term{" + "TermID=" + TermID + ", Title='" + title + ", Start='" + start + ", End='" + end + '\'' + '}';
    }
}
