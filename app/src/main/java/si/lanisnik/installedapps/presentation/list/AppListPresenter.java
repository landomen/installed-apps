package si.lanisnik.installedapps.presentation.list;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import si.lanisnik.installedapps.data.Constants;
import si.lanisnik.installedapps.data.InstalledAppsRetriever;
import si.lanisnik.installedapps.data.model.InstalledApp;


/**
 * Created by Domen Lanišnik on 04/12/2017.
 * domen.lanisnik@gmail.com
 */
public class AppListPresenter implements AppListContract.Presenter {

    private WeakReference<AppListContract.View> view;
    private AppsRetrieveTask appsRetrieveTask;

    @Override
    public void initialize(Context context) {
        view.get().setupRecyclerView();
        retrieveInstalledApps(context);
    }

    @Override
    public void onAppSelected(InstalledApp app) {
        view.get().openDetails(app.getPackageName());
    }

    @Override
    public void setView(AppListContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void destroy() {
        appsRetrieveTask.cancel(true);
        view.clear();
        view = null;
    }

    private void retrieveInstalledApps(Context context) {
        InstalledAppsRetriever installedAppsRetriever = new InstalledAppsRetriever();
        appsRetrieveTask = new AppsRetrieveTask(installedAppsRetriever, context, view);
        appsRetrieveTask.execute();
    }

    private static class AppsRetrieveTask extends AsyncTask<Void, Void, List<InstalledApp>> {

        private InstalledAppsRetriever installedAppsRetriever;
        private WeakReference<Context> context;
        private WeakReference<AppListContract.View> view;

        public AppsRetrieveTask(InstalledAppsRetriever installedAppsRetriever,
                                Context context,
                                WeakReference<AppListContract.View> view) {
            this.installedAppsRetriever = installedAppsRetriever;
            this.context = new WeakReference<>(context);
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<InstalledApp> doInBackground(Void... voids) {
            try {
                return installedAppsRetriever.retrieveInstalledApps(Constants.PACKAGE_OUTFIT7, context.get().getPackageManager());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<InstalledApp> installedApps) {
            super.onPostExecute(installedApps);
            if (view != null && view.get() != null) {
                displayResult(installedApps);
            }
        }

        private void displayResult(List<InstalledApp> installedApps) {
            if (installedApps != null && !installedApps.isEmpty()) {
                view.get().showInstalledApps(installedApps);
            } else if (installedApps != null && installedApps.isEmpty()) {
                view.get().showEmptyMessage();
            } else {
                view.get().showError();
            }
            view.get().hideLoadingView();
        }
    }

}
