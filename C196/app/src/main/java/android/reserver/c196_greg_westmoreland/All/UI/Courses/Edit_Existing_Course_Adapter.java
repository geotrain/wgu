package android.reserver.c196_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Edit_Existing_Course_Adapter extends RecyclerView.Adapter<Edit_Existing_Course_Adapter.Assessment_View_Holder> {

    // Declare variables
    private final LayoutInflater mInflater;
    private final Context context;
    public List<CoursesEntity> mCourses;
    public List<AssessmentsEntity> mAssessments;

    /**
     * This Edit course adapater is used to load the existing courses and assessments
     * @param context
     */
    public Edit_Existing_Course_Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * This class is the assessment view holder that holds the associated assessments to an existing course
     */
    class Assessment_View_Holder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        private final TextView assessmentItemView;

        private Assessment_View_Holder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.course_item_text_view);
            assessmentItemView = itemView.findViewById(R.id.assessment_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    CircularArray<Object> mAssessments = null;
                    final AssessmentsEntity currentAssessment = (AssessmentsEntity) mAssessments.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Course.class);
                    intent.putExtra("assessmentID", currentAssessment.getAssessmentID());
                    intent.putExtra("courseID", currentAssessment.getCourseID());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * This method on create view holder inflates the assessment item view layout screen
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Edit_Existing_Course_Adapter.Assessment_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new Edit_Existing_Course_Adapter.Assessment_View_Holder(itemView);
    }

    /**
     * This on bind view holder filters a list of assessments assigned to a course
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Edit_Existing_Course_Adapter.Assessment_View_Holder holder, int position) {
        if(mAssessments != null) {
            final AssessmentsEntity currentAssessment = mAssessments.get(position);
            holder.assessmentItemView.setText((currentAssessment.getAssessmentName()));

            String filteredAssessmentList = "";
            for (AssessmentsEntity assessment: mAssessments){
                if (assessment.getCourseID() == Edit_Existing_Course.courseID)
                    filteredAssessmentList = filteredAssessmentList + assessment.getAssessmentName() + "\n";
            }
            if (filteredAssessmentList != "")
                holder.assessmentItemView.setText(filteredAssessmentList);
            else
                holder.assessmentItemView.setVisibility(View.GONE);
        } else {
            holder.assessmentItemView.setText("no name");
        }
    }

    /**
     * This get item count gets the total count of assessments assigned to a course
     * @return
     */
    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }

    /**
     * This set assessments notifies the data set that is changed
     * @param assessments
     */
    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    /**
     * This method gets the assessment at a position from the array list
     * @param position
     * @return
     */
    public AssessmentsEntity getAssessmentAt(int position) {
        CircularArray<Object> mCourses;
        return mAssessments.get(position);
    }
}