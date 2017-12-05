package si.lanisnik.installedapps.presentation.details;

import android.content.Context;
import android.content.pm.PackageManager;

import java.lang.ref.WeakReference;

import si.lanisnik.installedapps.data.InstalledAppsRetriever;
import si.lanisnik.installedapps.data.model.InstalledApp;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public class AppDetailPresenter implements AppDetailContract.Presenter {

    private WeakReference<AppDetailContract.View> view;
    private String appPackageName;

    @Override
    public void initialize(Context context, String appPackageName) {
        this.appPackageName = appPackageName;
        retrieveAppDetails(context.getPackageManager(), appPackageName);
    }

    @Override
    public void setView(AppDetailContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void destroy() {
        view.clear();
        view = null;
    }

    @Override
    public void onStartClicked() {
        view.get().launchApp(appPackageName);
    }

    private void retrieveAppDetails(PackageManager packageManager, String appPackageName) {
        InstalledAppsRetriever appDetailRetriever = new InstalledAppsRetriever();
        try {
            InstalledApp appDetails = appDetailRetriever.retrieveInstalledApp(appPackageName, packageManager);
            view.get().showIcon(appDetails.getIcon());
            view.get().showName(appDetails.getName());
            view.get().showPackageName(appDetails.getPackageName());
            view.get().showVersionCode(String.valueOf(appDetails.getVersionCode()));
            view.get().showVersionNumber(appDetails.getVersionName());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
