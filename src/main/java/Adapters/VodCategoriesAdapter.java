package Adapters;

import Models.Category;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import z.xtreamiptv.player.R;
import z.xtreamiptv.player.MoviesActivity;
import java.util.List;

public class VodCategoriesAdapter extends RecyclerView.Adapter<VodCategoriesAdapter.ViewHolder> {
    private List<Category> categories;
    private Context mContext;

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements OnClickListener {
        CardView category_card;
        TextView category_name;

        ViewHolder(View v) {
            super(v);
            this.category_name = (TextView) v.findViewById(R.id.category_name);
            this.category_card = (CardView) v.findViewById(R.id.category_card);
            v.setOnClickListener(this);
        }

        public void onClick(View v) {
            VodCategoriesAdapter.this.mContext.startActivity(new Intent(v.getContext(), MoviesActivity.class).putExtra("id", ((Category) VodCategoriesAdapter.this.categories.get(getAdapterPosition())).getCategory_id()).putExtra("name", ((Category) VodCategoriesAdapter.this.categories.get(getAdapterPosition())).getCategory_name()));
        }
    }

    public void setVodCategories(List<Category> cats) {
        if (cats.size() == 0) {
            ((Activity) mContext).finish();
            Toast.makeText(mContext, mContext.getResources().getString(R.string.no_categories),
                    Toast.LENGTH_LONG).show();
        }
        this.categories = cats;
        notifyItemRangeChanged(0, this.categories.size());
    }

    public VodCategoriesAdapter(Context context, List<Category> cats) {
        this.mContext = context;
        this.categories = cats;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.category_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.category_name.setText(((Category) this.categories.get(position)).getCategory_name());
    }

    public int getItemCount() {
        if (this.categories == null) {
            return 0;
        }
        return this.categories.size();
    }
}
