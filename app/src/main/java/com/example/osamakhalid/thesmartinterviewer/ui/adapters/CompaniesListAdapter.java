package com.example.osamakhalid.thesmartinterviewer.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.presenters.CompaniesListPresenter;

/**
 * Created by Osama Khalid on 1/3/2019.
 */

public class CompaniesListAdapter extends RecyclerView.Adapter<CompaniesListAdapter.CompanyViewHolder>{

    private final CompaniesListPresenter presenter;

    public CompaniesListAdapter(CompaniesListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompanyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        presenter.onBindCompaniesRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getSize();
    }


    public class CompanyViewHolder extends RecyclerView.ViewHolder implements CompaniesRowView {
        public TextView name, location;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
        }

        @Override
        public void setName(String mName) {
            name.setText(mName);
        }

        @Override
        public void setLocation(String mLocation) {
            location.setText(mLocation);
        }
    }

    public interface CompaniesRowView {

        void setName(String name);

        void setLocation(String location);
    }
}
