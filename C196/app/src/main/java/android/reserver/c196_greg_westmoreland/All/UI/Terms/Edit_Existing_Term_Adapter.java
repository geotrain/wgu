package android.reserver.c196_greg_westmoreland.All.UI.Terms;

import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Edit_Existing_Term_Adapter extends RecyclerView.Adapter<Edit_Existing_Term_Adapter.Course_View_Holder> {

    private final LayoutInflater mInflater;
    private final Context context;
    public List<CoursesEntity> mCourses;
    private List<AssessmentsEntity> mAssessments;

    public Edit_Existing_Term_Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    class Course_View_Holder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        private final TextView assessmentItemView;

        private Course_View_Holder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.course_item_text_view);
            assessmentItemView = itemView.findViewById(R.id.course_assessment_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final CoursesEntity currentCourse = mCourses.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Term.class);
                    intent.putExtra("courseID", currentCourse.getCourseID());
                    intent.putExtra("termID", currentCourse.getTermID());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public Course_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new Course_View_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Course_View_Holder holder, int position) {
        if(mCourses != null) {
            final CoursesEntity currentCourse = mCourses.get(position);
            holder.courseItemView.setText((currentCourse.getCourseName()));

            String filteredCourseList = "";
            for (CoursesEntity course: mCourses){
                if (course.getTermID() == Edit_Existing_Term.termID) // TODO get intent from for termID
                    filteredCourseList = filteredCourseList + course.getCourseName() + "\n";
            }
            if (filteredCourseList != "")
                holder.assessmentItemView.setText(filteredCourseList);
            else
                holder.assessmentItemView.setVisibility(View.GONE);

        } else {
            holder.courseItemView.setText("no name");
        }
    }

    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    public void setCourses(List<CoursesEntity> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
    public CoursesEntity getCourseAt(int position) {
        return mCourses.get(position);
    }

}
