package davis.c196.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Courses")
public class Course {
    @PrimaryKey(autoGenerate = true)

    private int CourseID;

    private int TermID;
    private String Title;
    private String Start;
    private String End;
    private String Status;
    private String MentorName;
    private String MentorPhone;
    private String MentorEmail;

    private String Note;


public Course (int TermID, int CourseID, String Title, String Start, String End, String Status, String MentorName, String MentorPhone, String MentorEmail, String Note){


    this.TermID=TermID;
    this.CourseID=CourseID;
    this.Title=Title;
    this.Start=Start;
    this.End=End;
    this.Status=Status;
    this.MentorName=MentorName;
    this.MentorPhone=MentorPhone;
    this.MentorEmail=MentorEmail;
    this.Note=Note;

}

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        TermID = termID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMentorName() {
        return MentorName;
    }

    public void setMentorName(String mentorName) {
        MentorName = mentorName;
    }

    public String getMentorPhone() {
        return MentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        MentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return MentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        MentorEmail = mentorEmail;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public String toString() {
    return "Course{" + "CourseID=" + CourseID + ", Title='" + Title + ", Start='" + Start + ", End='" + End  + ", Status='" + Status + ", MentorName='" +
    MentorName + ", MentorEmail='" + MentorEmail + ", MentorPhone='" + MentorPhone + ", Note='" + Note + '\'' + '}'; }

}
