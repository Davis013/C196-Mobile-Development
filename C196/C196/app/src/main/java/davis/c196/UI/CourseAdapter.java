package davis.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import davis.c196.Entities.Course;
import davis.c196.Entities.Term;
import davis.c196.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView courseItemView;
        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Course current = mCourse.get(position);
                        Intent intent = new Intent(context, Courseaddedit.class);
                        intent.putExtra("CourseID", current.getCourseID());
                        intent.putExtra("TermID", current.getTermID());
                        intent.putExtra("Title", current.getTitle());
                        intent.putExtra("Start", current.getStart());
                        intent.putExtra("End", current.getEnd());
                        intent.putExtra("Status", current.getStatus());
                        intent.putExtra("MentorName", current.getMentorName());
                        intent.putExtra("MentorEmail", current.getMentorEmail());
                        intent.putExtra("MentorPhone", current.getMentorPhone());
                        intent.putExtra("Note", current.getNote());
                        context.startActivity(intent);
                    }
                }
            });
        }


    }



    public interface OnItemClickListener {
        void onItemClick(Course course);
    }

    private List<Course> mCourse;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }


    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView=mInflater.inflate(R.layout.activity_course_list,parent,false);
        return new CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position){
        if(mCourse!=null){
            Course current=mCourse.get(position);
            String title=current.getTitle();
            holder.courseItemView.setText(title);
        }
        else {
            holder.courseItemView.setText("No Course Title");
        }

    }
    public void setCourses(List<Course> courses){
        mCourse=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCourse!=null) {
            return mCourse.size();
        }
        else return 0;
    }


}

