package android.reserver.C868_greg_westmoreland.All.UI.Reports;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.Edit_Existing_Term;
import android.reserver.C868_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Reports_Terms_Adapter extends RecyclerView.Adapter<Reports_Terms_Adapter.TermsViewHolder> {
    /**
     * This class extends the recycle view adapter for terms
     */
    class TermsViewHolder extends RecyclerView.ViewHolder {
        private final TextView termNameView;
        private final TextView termStartDateView;
        private final TextView termEndDateView;

        /**
         * This method holds the terms view for the reports page
         * @param itemView
         */
        private TermsViewHolder(View itemView){
            super(itemView);
            termNameView = itemView.findViewById(R.id.report_term_name);
            termStartDateView=itemView.findViewById(R.id.report_term_start_date);
            termEndDateView=itemView.findViewById(R.id.report_term_end_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method loads term data check clicked
                 * @param v
                 */
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    final TermsEntity current = mTerms.get(position);
                    Intent intent = new Intent(context, Edit_Existing_Term.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("termStartDate", current.getTermStartDate());
                    intent.putExtra("termEndDate", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    // Declare variables
    private final LayoutInflater mInflater;
    private Context context = null;
    public List<TermsEntity> mTerms; // Cached copy of words

    /**
     * This is the assessments adapter method that inflates the screen layout
     * @param context
     */
    public Reports_Terms_Adapter(Context context) {
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
    public Reports_Terms_Adapter.TermsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_terms_list_item, parent, false);
        return new Reports_Terms_Adapter.TermsViewHolder(itemView);
    }

    /**
     * This method filters the assessments list
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Reports_Terms_Adapter.TermsViewHolder holder, int position) {
        if(mTerms != null) {
            final TermsEntity current = mTerms.get(position);
            holder.termNameView.setText(current.getTermName());
            holder.termStartDateView.setText(String.valueOf(current.getTermStartDate()));
            holder.termEndDateView.setText(String.valueOf(current.getTermEndDate()));
        } else {
            // Covers the case of data not being ready yet.
            holder.termNameView.setText("No Terms Name");
            holder.termStartDateView.setText("No Terms Start Date");
            holder.termEndDateView.setText("No Terms End Date");
        }
    }

    /**
     * This method sets the words for the assessments list array list
     * @param words
     */
    public void setWords(List<TermsEntity> words) {
        mTerms = words;
        notifyDataSetChanged();
    }

    /**
     * This method returns the Item Count for assessments
     * @return
     */
    public int getItemCount() {
        if (mTerms != null)
            return mTerms.size();
        else return 0;
    }

    /**
     * This method gets the assessments position in the database
     * @param position
     * @return
     */
    public TermsEntity getTermsAt(int position) {
        return mTerms.get(position);
    }

    /**
     * Set Terms method
     * @param terms
     */
    public void setTerms(List<TermsEntity> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }
}
