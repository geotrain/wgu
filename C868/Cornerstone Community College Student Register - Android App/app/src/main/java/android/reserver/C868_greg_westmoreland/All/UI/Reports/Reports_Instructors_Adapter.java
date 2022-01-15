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

public class Reports_Instructors_Adapter extends RecyclerView.Adapter<Reports_Instructors_Adapter.InstructorsViewHolder> {
    public Reports_Instructors_Adapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    /**
     * This class extends the recycle view adapter for assessments
     */
    class InstructorsViewHolder extends RecyclerView.ViewHolder {
        private final TextView instructorNameView;
        private final TextView instructorPhoneView;
        private final TextView instructorEmailView;

        /**
         * This method holds instructors view data
         * @param itemView
         */
        private InstructorsViewHolder(View itemView){
            super(itemView);
            instructorNameView = itemView.findViewById(R.id.report_instructor_name);
            instructorPhoneView = itemView.findViewById(R.id.report_instructor_phone);
            instructorEmailView = itemView.findViewById(R.id.report_instructor_email);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method loads the instructor information when clicked
                 * @param v
                 */
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final CoursesEntity current = mInstructors.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Course.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("instructorName", current.getCourseInstructorName());
                    intent.putExtra("instructorPhone", current.getCourseInstructorPhone());
                    intent.putExtra("instructorEmail", current.getCourseInstructorEmail());
                    context.startActivity(intent);
                }
            });
        }
    }

    // Declare variables
    private final LayoutInflater mInflater;
    private Context context = null;
    public List<CoursesEntity> mInstructors; // Cached copy of words

    /**
     * This is the assessments adapter method that inflates the screen layout
     * @param context
     */
    public Reports_Instructors_Adapter(Context context) {
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
    public Reports_Instructors_Adapter.InstructorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_instructors_list_item, parent, false);
        return new InstructorsViewHolder(itemView);
    }

    /**
     * This method filters the assessments list
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Reports_Instructors_Adapter.InstructorsViewHolder holder, int position) {
        if(mInstructors != null) {
            final CoursesEntity current = mInstructors.get(position);
            holder.instructorNameView.setText(current.getCourseInstructorName());
            holder.instructorPhoneView.setText(current.getCourseInstructorPhone());
            holder.instructorEmailView.setText(current.getCourseInstructorEmail());
        } else {
            // Covers the case of data not being ready yet.
            holder.instructorNameView.setText("No Instructor Name");
            holder.instructorPhoneView.setText("No Instructor Phone");
            holder.instructorEmailView.setText("No Instructor Email");
        }
    }

    /**
     * This method sets the words for the assessments list array list
     * @param words
     */
    public void setWords(List<CoursesEntity> words) {
        mInstructors = words;
        notifyDataSetChanged();
    }

    /**
     * This method returns the Item Count for assessments
     * @return
     */
    public int getItemCount() {
        if (mInstructors != null)
            return mInstructors.size();
        else return 0;
    }

    /**
     * This method gets the assessments position in the database
     * @param position
     * @return
     */
    public CoursesEntity getInstructorsAt(int position) {
        return mInstructors.get(position);
    }


    /**
     * Set Terms method
     * @param instructors
     */
    public void setInstructors(List<CoursesEntity> instructors) {
        mInstructors = instructors;
        notifyDataSetChanged();
    }
}
