package android.reserver.C868_greg_westmoreland.All.UI.Reports;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.Edit_Existing_Assessment;
import android.reserver.C868_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Reports_Assessments_Adapter extends RecyclerView.Adapter<Reports_Assessments_Adapter.AssessmentsViewHolder> {
    /**
     * This class extends the recycle view adapter for assessments
     */
    class AssessmentsViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentNameView;
        private final TextView assessmentStartDateView;
        private final TextView assessmentEndDateView;

        /**
         * Assessments View Holder for Reports page
         * @param itemView
         */
        private AssessmentsViewHolder(View itemView){
            super(itemView);
            assessmentNameView = itemView.findViewById(R.id.report_assessment_name);
            assessmentStartDateView=itemView.findViewById(R.id.report_assessment_start_date);
            assessmentEndDateView=itemView.findViewById(R.id.report_assessment_end_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * On Click it loads the following
                 * @param v
                 */
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final AssessmentsEntity current = mAssessments.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Assessment.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("assessmentStartDate", current.getAssessmentStartDate());
                    intent.putExtra("assessmentEndDate", current.getAssessmentEndDate());
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
    public Reports_Assessments_Adapter(Context context) {
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
    public Reports_Assessments_Adapter.AssessmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_assessments_list_item, parent, false);
        return new Reports_Assessments_Adapter.AssessmentsViewHolder(itemView);
    }

    /**
     * This method filters the assessments list
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Reports_Assessments_Adapter.AssessmentsViewHolder holder, int position) {
        if(mAssessments != null) {
            final AssessmentsEntity current = mAssessments.get(position);
            holder.assessmentNameView.setText(current.getAssessmentName());
            holder.assessmentStartDateView.setText(String.valueOf(current.getAssessmentStartDate()));
            holder.assessmentEndDateView.setText(String.valueOf(current.getAssessmentEndDate()));
        } else {
            // Covers the case of data not being ready yet.
            holder.assessmentNameView.setText("No Assessments Name");
            holder.assessmentStartDateView.setText("No Assessments Start Date");
            holder.assessmentEndDateView.setText("No Assessments End Date");
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
