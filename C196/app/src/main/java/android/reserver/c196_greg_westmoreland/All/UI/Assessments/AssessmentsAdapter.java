package android.reserver.c196_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.Terms_Edit_Existing_Term;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;
import java.text.BreakIterator;
import java.util.List;

//public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentViewHolder> {
    /*

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;
        private final LayoutInflater mInflater;
        public BreakIterator assessmentViewItem;

        private AssessmentViewHolder(View itemView, LayoutInflater mInflater){
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessment_item_text_view);
            this.mInflater = mInflater;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final AssessmentsEntity currentAssessment = mAssessments.get(position);
                    Intent intent = new Intent(context, Terms_Edit_Existing_Term.class);
                    intent.putExtra("position", position);
                    intent.putExtra("assessmentID", currentAssessment.getAssessmentID());
                    intent.putExtra("courseID", currentAssessment.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
}

    private final LayoutInflater mInflater;
    private Context context = null;
    public List<AssessmentsEntity> mAssessments; // Cached copy of words

    public AssessmentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public AssessmentsAdapter.AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);

        return new AssessmentsAdapter().AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentsAdapter.AssessmentViewHolder holder, int position) {
        CircularArray mAssessments = null;
        if(mAssessments != null) {
            AssessmentsEntity currentAssessment = (AssessmentsEntity) mAssessments.get(position);
            holder.assessmentViewItem.setText((currentAssessment.getAssessmentName()));
        } else {
            holder.assessmentViewItem.setText("no title");
        }
    }

    /**
     *
     * @param words
     */

    /*
    public void setWords(List<AssessmentsEntity> words) {
        mAssessments = words;
        notifyDataSetChanged();
    }

    /**
     * This method returns the Item Count for assessments
     * @return
     */
    /*
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }

    public AssessmentsEntity getAssessmentsAt(int position) {
        return mAssessments.get(position);
    }


    /**
     * Set Terms method
     * @param assessments
     */
    /*
    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

}*/
