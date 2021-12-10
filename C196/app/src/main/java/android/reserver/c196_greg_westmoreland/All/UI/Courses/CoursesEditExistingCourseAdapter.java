package android.reserver.c196_greg_westmoreland.All.UI.Courses;

import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.TermsEditExistingTermAdapter;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//public class CoursesEditExistingCourseAdapter extends RecyclerView.Adapter<CoursesEditExistingCourseAdapter.AssessmentViewHolder> {
/*
    private final LayoutInflater mInflater;
    private final Context context;
    private List<AssessmentsEntity> mAssessments;

    public CoursesEditExistingCourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public CoursesEditExistingCourseAdapter(LayoutInflater mInflater, Context context) {
        this.mInflater = mInflater;
        this.context = context;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        private final TextView assessmentItemView;

        private CourseViewHolder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.course_item_text_view);
            assessmentItemView = itemView.findViewById(R.id.course_assessment_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final CoursesEntity currentCourse = mCourses.get(position);
                    Intent intent = new Intent(context, Courses_Edit_Existing_Course.class);
                    intent.putExtra("courseID", currentCourse.getCourseID());
                    intent.putExtra("termID", currentCourse.getTermID());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public CoursesEditExistingCourseAdapter.AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CoursesEditExistingCourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TermsEditExistingTermAdapter.AssessmentViewHolder holder, int position) {
        if(mCourses != null) {
            final CoursesEntity currentAssessment = mCourses.get(position);
            holder.AssessmentViewHolder.setText((currentAssessment.getCourseName()));

            String filteredAssessmentList = "";
            for (AssessmentsEntity assessment: mAssessments){
                if (assessment.getCourseID() == currentAssessment.getCourseID())
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

    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
    public CoursesEntity getCourseAt(int position) {
        return mCourses.get(position);
    }
/**


}*/