package si.lanisnik.installedapps.presentation.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import si.lanisnik.installedapps.R;
import si.lanisnik.installedapps.data.model.InstalledApp;
import si.lanisnik.installedapps.presentation.details.AppDetailActivity;
import si.lanisnik.installedapps.presentation.list.adapter.InstalledAppsRecyclerAdapter;

public class AppListActivity extends AppCompatActivity implements AppListContract.View, InstalledAppsRecyclerAdapter.OnAppSelectedListener {

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.list_progress_bar)
    protected ProgressBar progressBar;

    @BindView(R.id.list_empty_text_view)
    protected TextView emptyTextView;

    private AppListContract.Presenter presenter;
    private InstalledAppsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new InstalledAppsRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideLoadingView() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyMessage() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyMessage() {
        emptyTextView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInstalledApps(List<InstalledApp> installedApps) {
        adapter.setItems(installedApps);
    }

    @Override
    public void onAppSelected(InstalledApp app) {
        presenter.onAppSelected(app);
    }

    @Override
    public void openDetails(String packageName) {
        startActivity(AppDetailActivity.createLaunchIntent(this, packageName));
    }

    private void initPresenter() {
        presenter = new AppListPresenter();
        presenter.setView(this);
        presenter.initialize(getApplicationContext());
    }

}
