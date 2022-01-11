package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import android.content.Context;
import android.content.Intent;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class List_Terms_Adapter extends RecyclerView.Adapter<List_Terms_Adapter.Term_View_Holder> {

    /**
     * This class is the terms view holder for the recycle view
     */
    class Term_View_Holder extends RecyclerView.ViewHolder {
        private final TextView termNameView;
        private final TextView termStartDateView;
        private final TextView termEndDateView;

        /**
         * This method holds the terms information
         * @param itemView
         */
        private Term_View_Holder(View itemView) {
            super(itemView);
            termNameView = itemView.findViewById(R.id.term_name);
            termStartDateView=itemView.findViewById(R.id.term_start_date);
            termEndDateView=itemView.findViewById(R.id.term_end_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * This is used when the user clicks an action on the screen
                 * @param v
                 */
                @Override
                public void onClick(View v) {
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

    /**
     * Declaration of variables and array list of terms
     */
    private final LayoutInflater mInflater;
    private final Context context;
    private List<TermsEntity> mTerms; // Cached copy of words

    /**
     * Terms Adapter method inflater
     * @param context
     */
    public List_Terms_Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    /**
     * Terms Adapter view holder method on create view holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Term_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.terms_list_item,parent, false);
        return new Term_View_Holder(itemView);
    }

    /**
     * On Bind View Holder method
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Term_View_Holder holder, int position) {
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
     * Get Item Count Method
     * @return
     */
    @Override
    public int getItemCount() {
        if (mTerms != null)
            return mTerms.size();
        else
            return 0;
    }

    /**
     * Set Terms method
     * @param terms
     */
    public void setTerms(List<TermsEntity> terms) {
        mTerms=terms;
        notifyDataSetChanged();
    }
}
