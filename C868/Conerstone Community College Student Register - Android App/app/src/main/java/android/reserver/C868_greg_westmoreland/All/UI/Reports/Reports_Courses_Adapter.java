package android.reserver.C868_greg_westmoreland.All.UI.Reports;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.Edit_Existing_Course;
import android.reserver.C868_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Reports_Courses_Adapter extends RecyclerView.Adapter<Reports_Courses_Adapter.CoursesViewHolder>{

    /**
     * This class extends the recycle view adapter for assessments
     */
    class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseNameView;
        private final TextView courseStartDateView;
        private final TextView courseEndDateView;

        /**
         * Courses View Holder loads data
         * @param itemView
         */
        private CoursesViewHolder(View itemView){
            super(itemView);
            courseNameView = itemView.findViewById(R.id.report_course_name);
            courseStartDateView=itemView.findViewById(R.id.report_course_start_date);
            courseEndDateView=itemView.findViewById(R.id.report_course_end_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method loads course information when clicked
                 * @param v
                 */
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final CoursesEntity current = mCourses.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Course.class);
                    intent.putExtra("courseID", current.getTermID());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("courseStartDate", current.getCourseStartDate());
                    intent.putExtra("courseEndDate", current.getCourseEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    // Declare variables
    private final LayoutInflater mInflater;
    private Context context = null;
    public List<CoursesEntity> mCourses; // Cached copy of words

    /**
     * This is the assessments adapter method that inflates the screen layout
     * @param context
     */
    public Reports_Courses_Adapter(Context context) {
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
    public Reports_Courses_Adapter.CoursesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_courses_list_item, parent, false);
        return new Reports_Courses_Adapter.CoursesViewHolder(itemView);
    }

    /**
     * This method filters the assessments list
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Reports_Courses_Adapter.CoursesViewHolder holder, int position) {
        if(mCourses != null) {
            final CoursesEntity current = mCourses.get(position);
            holder.courseNameView.setText(current.getCourseName());
            holder.courseStartDateView.setText(String.valueOf(current.getCourseStartDate()));
            holder.courseEndDateView.setText(String.valueOf(current.getCourseEndDate()));
        } else {
            // Covers the case of data not being ready yet.
            holder.courseNameView.setText("No Course Name");
            holder.courseStartDateView.setText("No Course Start Date");
            holder.courseEndDateView.setText("No Course End Date");
        }
    }

    /**
     * This method sets the words for the assessments list array list
     * @param words
     */
    public void setWords(List<CoursesEntity> words) {
        mCourses = words;
        notifyDataSetChanged();
    }

    /**
     * This method returns the Item Count for assessments
     * @return
     */
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    /**
     * This method gets the assessments position in the database
     * @param position
     * @return
     */
    public CoursesEntity getCoursesAt(int position) {
        return mCourses.get(position);
    }


    /**
     * Set Terms method
     * @param courses
     */
    public void setCourses(List<CoursesEntity> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }
}
