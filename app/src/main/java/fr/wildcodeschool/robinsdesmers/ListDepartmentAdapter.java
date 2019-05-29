package fr.wildcodeschool.robinsdesmers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListDepartmentAdapter extends RecyclerView.Adapter<ListDepartmentAdapter.DepartmentViewHolder> {

    private ArrayList<Departments> departments;

    public ListDepartmentAdapter(ArrayList<Departments> departments) {
        this.departments = departments;
    }

    @Override
    public DepartmentViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_departments, parent, false);
        return new DepartmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DepartmentViewHolder holder, int position) {
        Departments department = departments.get(position);
        holder.tvName.setText(department.getNameDepartment());
        holder.tvNumber.setText(Integer.toString(department.getNumber()));
        holder.container.setSelected(department.isSelected());
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View container;
        private TextView tvName;
        private TextView tvNumber;

        public DepartmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            container = itemView;
            tvName = itemView.findViewById(R.id.tv_name_department);
            tvNumber = itemView.findViewById(R.id.tv_number_department);
        }

        @Override
        public void onClick(View view) {
            for (Departments departement : departments) {
                departement.setSelected(false);
            }
            departments.get(getAdapterPosition()).setSelected(true);
            notifyDataSetChanged();
        }
    }
}
