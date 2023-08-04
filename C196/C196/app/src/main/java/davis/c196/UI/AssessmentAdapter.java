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

import davis.c196.Entities.Assessment;
import davis.c196.Entities.Course;
import davis.c196.R;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder{

        private final TextView assessmentItemView;
        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textView4);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Assessment current = mAssessment.get(position);
                        Intent intent = new Intent(context, Assessmentaddedit.class);
                        intent.putExtra("CourseID", current.getCourseID());
                        intent.putExtra("AssessmentID", current.getAssessmentID());
                        intent.putExtra("Title", current.getTitle());
                        intent.putExtra("Type", current.getType());
                        intent.putExtra("Content", current.getContent());
                        intent.putExtra("Start", current.getStart());
                        intent.putExtra("End", current.getEnd());
                        context.startActivity(intent);
                    }
                }
            });
        }


    }




    public interface OnItemClickListener {
        void onItemClick(Assessment assessment);
    }

    private List<Assessment> mAssessment;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }


    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView=mInflater.inflate(R.layout.activity_assessment_list,parent,false);
        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position){
        if(mAssessment!=null){
            Assessment current=mAssessment.get(position);
            String title=current.getTitle();
            holder.assessmentItemView.setText(title);
        }
        else {
            holder.assessmentItemView.setText("No Assessment Title");
        }

    }
    public void setAssessments(List<Assessment> assessments){
        mAssessment=assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAssessment!=null) {
            return mAssessment.size();
        }
        else return 0;
    }


}
