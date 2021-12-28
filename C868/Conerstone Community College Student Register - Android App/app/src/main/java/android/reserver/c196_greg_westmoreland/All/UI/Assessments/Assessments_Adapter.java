package android.reserver.c196_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Assessments_Adapter extends RecyclerView.Adapter<Assessments_Adapter.AssessmentViewHolder> {

    /**
     * This class extends the recylce view adapater for assessments
     */
    class AssessmentViewHolder extends RecyclerView.ViewHolder {
       private final TextView assessmentItemView;

        private AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessment_item_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final AssessmentsEntity currentAssessment = mAssessments.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Assessment.class);
                    intent.putExtra("position", position);
                    intent.putExtra("assessmentID", currentAssessment.getAssessmentID());
                    intent.putExtra("courseID", currentAssessment.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }

    // Declare variables
    private final LayoutInflater mInflater;
    private Context context = null;
    public List<AssessmentsEntity> mAssessments; // Cached copy of words

    /**
     * This is the assessments adapter method that inflates the screen layout
     * @param context
     */
    public Assessments_Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * This method grabs the assessment list layout and inflates it
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    /**
     * This method filters the assessments list
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Assessments_Adapter.AssessmentViewHolder holder, int position) {
        if(mAssessments != null) {
            AssessmentsEntity currentAssessment = (AssessmentsEntity) mAssessments.get(position);
            holder.assessmentItemView.setText((currentAssessment.getAssessmentName()));
        } else {
            holder.assessmentItemView.setText("no title");
        }
    }

    /**
     * This method sets the words for the assessments list array list
     * @param words
     */
    public void setWords(List<AssessmentsEntity> words) {
        mAssessments = words;
        notifyDataSetChanged();
    }

    /**
     * This method returns the Item Count for assessments
     * @return
     */
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }

    /**
     * This method gets the assessments position in the database
     * @param position
     * @return
     */
    public AssessmentsEntity getAssessmentsAt(int position) {
        return mAssessments.get(position);
    }


    /**
     * Set Terms method
     * @param assessments
     */
    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
}
