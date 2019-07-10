package fr.wildcodeschool.robinsdesmers.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.SecurityInfoItem;

public class SecurityInfoAdapter extends RecyclerView.Adapter<SecurityInfoAdapter.SecurityInfoViewHolder> {

    private List<SecurityInfoItem> securityInfoItems;

    public SecurityInfoAdapter(List<SecurityInfoItem> securityInfoItems) {
        this.securityInfoItems = securityInfoItems;
    }

    @NonNull
    @Override
    public SecurityInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_security_info, viewGroup, false);
        SecurityInfoViewHolder securityInfoViewHolder = new SecurityInfoViewHolder(view);
        return securityInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecurityInfoViewHolder securityInfoViewHolder, int i) {
        SecurityInfoItem securityInfoItem = securityInfoItems.get(i);
        securityInfoViewHolder.tvSecurityInfoTitle.setText(securityInfoItem.getSecurityInfoTitle());
    }

    @Override
    public int getItemCount() {
        return securityInfoItems.size();
    }

    public class SecurityInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSecurityInfoTitle;

        public SecurityInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSecurityInfoTitle = itemView.findViewById(R.id.tvSecurityInfoTitle);
        }
    }
}
