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
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Courses_Adapter extends RecyclerView.Adapter<Courses_Adapter.Course_View_Holder> {

    class Course_View_Holder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;

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

    private final LayoutInflater mInflater;
    private Context context = null;
    public List<CoursesEntity> mCourses; // Cached copy of words

    public Courses_Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public Courses_Adapter() {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    
    @Override
    public Course_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);

        return new Course_View_Holder(itemView);
    }

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
     *
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
