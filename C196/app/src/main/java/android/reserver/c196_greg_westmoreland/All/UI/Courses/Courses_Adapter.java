package android.reserver.c196_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Courses_Adapter extends RecyclerView.Adapter<Courses_Adapter.Course_View_Holder> {

    /**
     * This method is the course adapter used with the courses list class
     */
    class Course_View_Holder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;

        /**
         * The Course View Holder has the on click method that is used when the courses list class page is loaded
         * @param itemView
         */
        private Course_View_Holder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.course_item_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final CoursesEntity currentCourse = mCourses.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Course.class);
                    intent.putExtra("position", position);
                    intent.putExtra("courseID", currentCourse.getCourseID());
                    intent.putExtra("termID", currentCourse.getTermID());
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
     * The courses adapter inflates the lay out screen for the courses list
     * @param context
     */
    public Courses_Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * The courses adapter inflates the lay out screen for the courses list
     */
    public Courses_Adapter() {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * On Create View Holder inflates the course list item layout screen
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
     * The on bind view holder loades the courses entity array and filters the courses
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Course_View_Holder holder, int position) {
        if(mCourses != null) {
            CoursesEntity currentCourse = mCourses.get(position);
            holder.courseItemView.setText((currentCourse.getCourseName()));
        } else {
            holder.courseItemView.setText("no title");
        }
    }

    /**
     * This method assignes the notify date set change
     * @param words
     */
    public void setWords(List<CoursesEntity> words) {
        mCourses = words;
        notifyDataSetChanged();
    }

    /**
     * This method returns the Item Count for courses
     * @return
     */
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    /**
     * This method gets the position at the course ID selected
     * @param position
     * @return
     */
    public CoursesEntity getCourseAt(int position) {
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
