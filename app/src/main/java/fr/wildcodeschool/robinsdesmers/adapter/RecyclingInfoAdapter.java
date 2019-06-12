package fr.wildcodeschool.robinsdesmers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.robinsdesmers.model.RecyclingInfoItem;

public class RecyclingInfoAdapter extends RecyclerView.Adapter<RecyclingInfoAdapter.RecyclingInfoViewHolder> {

    List<RecyclingInfoItem> recyclingInfoItems;

    public RecyclingInfoAdapter(List<RecyclingInfoItem> recyclingInfoItems) {
        this.recyclingInfoItems = recyclingInfoItems;
    }

    @NonNull
    @Override
    public RecyclingInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycling_info, viewGroup, false);
        RecyclingInfoViewHolder recyclingInfoViewHolder = new RecyclingInfoViewHolder(view);
        return recyclingInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclingInfoViewHolder recyclingInfoViewHolder, int i) {

        RecyclingInfoItem recyclingInfoItem = recyclingInfoItems.get(i);
        recyclingInfoViewHolder.tvRecyclingInfoTitle.setText(recyclingInfoItem.getTitleRecyclingInfo());
        recyclingInfoViewHolder.tvRecyclingInfoDescription.setText(recyclingInfoItem.getDescriptionRecyclingInfo());
        recyclingInfoViewHolder.ivRecyclingLogo.setImageResource(recyclingInfoItem.getLogoRecycling());
    }

    @Override
    public int getItemCount() {
        return recyclingInfoItems.size();
    }

    public class RecyclingInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRecyclingInfoTitle;
        public TextView tvRecyclingInfoDescription;
        public ImageView ivRecyclingLogo;

        public RecyclingInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecyclingInfoTitle = itemView.findViewById(R.id.tvSecurityInfoTitle);
            tvRecyclingInfoDescription = itemView.findViewById(R.id.tvRecyclingInfoDescription);
            ivRecyclingLogo = itemView.findViewById(R.id.ivRecyclingLogo);
        }
    }
}
