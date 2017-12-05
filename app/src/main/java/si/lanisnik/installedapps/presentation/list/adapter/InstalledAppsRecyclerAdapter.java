package si.lanisnik.installedapps.presentation.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import si.lanisnik.installedapps.R;
import si.lanisnik.installedapps.data.model.InstalledApp;
import si.lanisnik.installedapps.presentation.list.viewholder.InstalledAppViewHolder;

/**
 * Created by Domen Lanišnik on 04/12/2017.
 * domen.lanisnik@gmail.com
 */
public class InstalledAppsRecyclerAdapter extends RecyclerView.Adapter<InstalledAppViewHolder> {

    private List<InstalledApp> items;
    private OnAppSelectedListener listener;

    public InstalledAppsRecyclerAdapter(OnAppSelectedListener listener) {
        this.listener = listener;
        this.items = new ArrayList<>();
    }

    @Override
    public InstalledAppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflatedView = layoutInflater.inflate(R.layout.recycler_item_app, parent, false);
        return new InstalledAppViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(InstalledAppViewHolder holder, int position) {
        InstalledApp installedApp = items.get(position);
        holder.nameTextView.setText(installedApp.getName());
        holder.iconImageView.setImageDrawable(installedApp.getIcon());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAppSelected(installedApp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<InstalledApp> installedApps) {
        this.items = installedApps;
        notifyDataSetChanged();
    }

    public interface OnAppSelectedListener {
        void onAppSelected(InstalledApp app);
    }
}
