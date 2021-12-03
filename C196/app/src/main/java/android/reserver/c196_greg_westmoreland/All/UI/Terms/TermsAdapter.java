package android.reserver.c196_greg_westmoreland.All.UI.Terms;

import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.List;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        private final TextView termItemView2;
        private final TextView termItemView3;
        private final TextView termItemView4;
        private TermViewHolder(View itemView){
            super (itemView);
            termItemView=itemView.findViewById(R.id.termView);
            termItemView2=itemView.findViewById(R.id.termView2);
            termItemView3=itemView.findViewById(R.id.termView3);
            termItemView4=itemView.findViewById(R.id.termView4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final TermsEntity current = mTerms.get(position);
                    Intent intent = new Intent(context,TermsList.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("termStartDate",current.getTermStartDate());
                    intent.putExtra("termEndDate",current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    private final Context context;
    private List<TermsEntity> mTerms;
    private final LayoutInflater mInflater;

    public TermsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public TermsAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.terms_list_item,parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermsAdapter.TermViewHolder holder, int position) {
        if(mTerms != null) {
            TermsEntity current = mTerms.get(position);
            holder.termItemView.setText(current.getTermName());
            holder.termItemView2.setText(Integer.toString(current.getTermID()));
            holder.termItemView3.setText((CharSequence) Date.valueOf(String.valueOf(current.getTermStartDate())));
            holder.termItemView4.setText((CharSequence) Date.valueOf(String.valueOf(current.getTermStartDate())));
        } else {
            holder.termItemView.setText("No Terms Name");
            holder.termItemView2.setText("No Terms ID");
            holder.termItemView3.setText("No Terms Start Date");
            holder.termItemView4.setText("No Terms End Date");
        }
    }

    public void setThings(List<TermsEntity> things) {
        mTerms=things;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }
}
