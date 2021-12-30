package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.Edit_Existing_Course;
import android.reserver.C868_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Edit_Existing_Term_Adapter extends RecyclerView.Adapter<Edit_Existing_Term_Adapter.Course_View_Holder> {

    // Declare variables
    private final LayoutInflater mInflater;
    private final Context context;
    public List<CoursesEntity> mCourses;
    private List<AssessmentsEntity> mAssessments;

    /**
     * This method holds the term adapter and inflats the layout for existing terms
     * @param context
     */
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
            assessmentItemView = itemView.findViewById(R.id.assessment_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final CoursesEntity currentCourse = mCourses.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Course.class);
                    intent.putExtra("courseID", currentCourse.getCourseID());
                    intent.putExtra("termID", currentCourse.getTermID());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * This method on crete view holder inflates the course list item for editing an exsting course
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Course_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new Course_View_Holder(itemView);
    }

    /**
     * On bind view holder filters out the courses that assigned to an existing term
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Course_View_Holder holder, int position) {
        if(mCourses != null) {
            final CoursesEntity currentCourse = mCourses.get(position);
            holder.courseItemView.setText((currentCourse.getCourseName()));
            String filteredCourseList = "";
            for (CoursesEntity course: mCourses){
                if (course.getTermID() == Edit_Existing_Term.termID)
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

    /**
     * This method get item count gives the amount of courses that are assigned to an existing term
     * @return
     */
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    /**
     * This method sets all courses from the courses entity array list
     * @param courses
     */
    public void setCourses(List<CoursesEntity> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    /**
     * This method sets assessments from the assessments entity array list
     * @param assessments
     */
    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    /**
     * This method gets the course at position
     * @param position
     * @return
     */
    public CoursesEntity getCourseAt(int position) {
        return mCourses.get(position);
    }
}
