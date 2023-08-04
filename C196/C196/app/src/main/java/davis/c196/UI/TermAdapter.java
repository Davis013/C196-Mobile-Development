package davis.c196.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import davis.c196.Entities.Term;
import davis.c196.R;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {


    class TermViewHolder extends RecyclerView.ViewHolder{

        private final TextView termItemView;
        private TermViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Term current = mTerms.get(position);
                        onItemClickListener.onItemClick(current);
                    }
                }
            });
        }


    }
    private final OnItemClickListener onItemClickListener;



    public interface OnItemClickListener {
        void onItemClick(Term term);

    }

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context, OnItemClickListener listener){
        mInflater=LayoutInflater.from(context);
        this.context=context;
        this.onItemClickListener = listener;
    }


    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView=mInflater.inflate(R.layout.activity_term_list,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position){
        if(mTerms!=null){
            Term current=mTerms.get(position);
            String title=current.getTitle();
            holder.termItemView.setText(title);
        }
        else {
            holder.termItemView.setText("No Term Title");
        }

    }
    public void setTerms(List<Term> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms!=null) {
            return mTerms.size();
        }
        else return 0;
    }



}