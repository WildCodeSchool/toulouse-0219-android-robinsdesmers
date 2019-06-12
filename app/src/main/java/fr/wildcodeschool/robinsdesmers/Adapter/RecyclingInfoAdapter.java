package fr.wildcodeschool.robinsdesmers.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.wildcodeschool.robinsdesmers.R;

public class RecyclingInfoAdapter extends RecyclerView.Adapter<RecyclingInfoAdapter.RecyclingInfoViewHolder> {
    @NonNull
    @Override
    public RecyclingInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycling_info, viewGroup, false);
        RecyclingInfoViewHolder recyclingInfoViewHolder = new RecyclingInfoViewHolder(view);
        return recyclingInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclingInfoViewHolder recyclingInfoViewHolder, int i) {
        recyclingInfoViewHolder.tvRecyclingInfoTitle.setText(R.string.recycling_info_title);
        recyclingInfoViewHolder.tvRecyclingInfoDescription.setText(R.string.recycling_info_description);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class RecyclingInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRecyclingInfoTitle;
        public TextView tvRecyclingInfoDescription;
        public ImageView ivRecyclingLogo;

        public RecyclingInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecyclingInfoTitle = itemView.findViewById(R.id.tvRecyclingInfoTitle);
            tvRecyclingInfoDescription = itemView.findViewById(R.id.tvRecyclingInfoDescription);
            ivRecyclingLogo = itemView.findViewById(R.id.ivRecyclingLogo);
        }
    }
}
