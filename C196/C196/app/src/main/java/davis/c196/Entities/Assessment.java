package davis.c196.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessment")
public class Assessment {
@PrimaryKey(autoGenerate = true)
    private int AssessmentID;

    private int CourseID;
    private String Title;
    private String Type;
    private String Content;
    private String Start;
    private String End;

    public Assessment (int CourseID, int AssessmentID ,String Title, String Type, String Start, String End,  String Content){

        this.CourseID=CourseID;
        this.AssessmentID=AssessmentID;
        this.Title=Title;
        this.Type=Type;
        this.Content=Content;
        this.Start=Start;
        this.End=End;

}

    public int getAssessmentID() {
        return AssessmentID;
    }

    public void setAssessmentID(int AssessmentID) {
        this.AssessmentID = AssessmentID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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

    @Override
    public String toString(){
        return "Assessment{" + "AssessmentID=" + AssessmentID + ", Title='" + Title +  ", Type='" + ", Content='"
        + Content + ", Start='" + Start + ", End='" + End +  '\'' +'}';
    }
}
