package com.example.starsgallery.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.ClassStart;
import com.example.starsgallery.service.StarService;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private List<ClassStart> stars;
    private List<ClassStart> starsFilter;
    private Context context;
    private NewFilter mFilter;

    public StarAdapter(Context context, List<ClassStart> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
        this.mFilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        ClassStart s = starsFilter.get(position);

        holder.tvName.setText(s.getName());
        holder.ratingBar.setRating(s.getRating());
        holder.tvRating.setText(String.valueOf(s.getRating()));

        Glide.with(context)
                .load(s.getImg())
                .apply(new RequestOptions().circleCrop().placeholder(R.drawable.ic_launcher_background))
                .into(holder.imgStar);

        holder.itemView.setOnClickListener(v -> showEditDialog(s, holder));
    }

    private void showEditDialog(ClassStart star, StarViewHolder holder) {
        View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null);

        ImageView imgPopup = popup.findViewById(R.id.imgPopup);
        TextView tvPopupName = popup.findViewById(R.id.tvPopupName);
        RatingBar ratingBarPopup = popup.findViewById(R.id.ratingBarPopup);

        tvPopupName.setText(star.getName());
        ratingBarPopup.setRating(star.getRating());
        Glide.with(context)
                .load(star.getImg())
                .apply(new RequestOptions().circleCrop())
                .into(imgPopup);

        new AlertDialog.Builder(context)
                .setTitle("Modifier la note")
                .setView(popup)
                .setPositiveButton("Valider", (dialog, which) -> {
                    star.setRating(ratingBarPopup.getRating());
                    StarService.getInstance().update(star);
                    notifyItemChanged(holder.getAdapterPosition());
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    @Override
    public int getItemCount() { return starsFilter.size(); }

    @Override
    public Filter getFilter() { return mFilter; }

    public static class StarViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgStar;
        TextView tvName, tvRating;
        RatingBar ratingBar;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStar   = itemView.findViewById(R.id.imgStar);
            tvName    = itemView.findViewById(R.id.tvName);
            tvRating  = itemView.findViewById(R.id.tvRating);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    public class NewFilter extends Filter {
        private final RecyclerView.Adapter adapter;

        public NewFilter(RecyclerView.Adapter adapter) { this.adapter = adapter; }

        @Override
        protected FilterResults performFiltering(CharSequence cs) {
            List<ClassStart> filtered = new ArrayList<>();
            if (cs == null || cs.length() == 0) {
                filtered.addAll(stars);
            } else {
                String pattern = cs.toString().toLowerCase().trim();
                for (ClassStart s : stars) {
                    if (s.getName().toLowerCase().startsWith(pattern)) filtered.add(s);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            results.count  = filtered.size();
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence cs, FilterResults results) {
            starsFilter = (List<ClassStart>) results.values;
            adapter.notifyDataSetChanged();
        }
    }
}