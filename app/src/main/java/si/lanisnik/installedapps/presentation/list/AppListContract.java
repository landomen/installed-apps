package si.lanisnik.installedapps.presentation.list;

import android.content.Context;

import java.util.List;

import si.lanisnik.installedapps.data.model.InstalledApp;
import si.lanisnik.installedapps.presentation.base.BasePresenter;
import si.lanisnik.installedapps.presentation.base.BaseView;

/**
 * Created by Domen Lanišnik on 04/12/2017.
 * domen.lanisnik@gmail.com
 */
public interface AppListContract {

    interface View extends BaseView {
        void setupRecyclerView();

        void hideLoadingView();

        void showEmptyMessage();

        void hideEmptyMessage();

        void showInstalledApps(List<InstalledApp> installedApps);

        void showError();

        void openDetails(String packageName);

    }

    interface Presenter extends BasePresenter<View> {
        void initialize(Context context);

        void onAppSelected(InstalledApp app);
    }
}
