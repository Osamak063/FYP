package com.example.osamakhalid.thesmartinterviewer.presenters;

import android.widget.Filter;
import android.widget.Filterable;

import com.example.osamakhalid.thesmartinterviewer.models.Company;
import com.example.osamakhalid.thesmartinterviewer.ui.adapters.CompaniesListAdapter;
import com.example.osamakhalid.thesmartinterviewer.ui.adapters.CompaniesListAdapter.CompaniesRowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 1/3/2019.
 */

public class CompaniesListPresenter implements Filterable{
    private List<Company> companies = new ArrayList<Company>();
    private List<Company> backup = new ArrayList<Company>();
    private CompaniesListAdapter adapter;
    public CompaniesListPresenter() {
        companies.add(new Company("Venture Dive", "Karachi, Pakistan", "IT"));
        companies.add(new Company("10 Pearls", "Karachi, Pakistan", "IT"));
        companies.add(new Company("Systems Limited", "Karachi, Pakistan", "IT"));
        companies.add(new Company("Folio3", "Karachi, Pakistan", "IT"));
        companies.add(new Company("Avanza", "Karachi, Pakistan", "IT"));
        backup.addAll(companies);
    }

    public void onBindCompaniesRowViewAtPosition(int position, CompaniesRowView holder) {
        Company company = companies.get(position);
        holder.setName(company.getName());
        holder.setLocation(company.getLocation());
    }

    public int getSize() {
        return companies.size();
    }

    public CompaniesListAdapter getAdapter() {
        adapter = new CompaniesListAdapter(this);
        return adapter;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    companies = backup;
                } else {
                    List<Company> filteredList = new ArrayList<>();
                    for (Company row : backup) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    companies = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = companies;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                companies = (ArrayList<Company>) filterResults.values;
                adapter.notifyDataSetChanged();
            }
        };
    }
}
