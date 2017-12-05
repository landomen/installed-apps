package si.lanisnik.installedapps.data;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import si.lanisnik.installedapps.data.model.InstalledApp;

/**
 * Created by Domen Lanišnik on 04/12/2017.
 * domen.lanisnik@gmail.com
 */
public class InstalledAppsRetriever {

    /**
     * Extracts information for all apps matching the constructor passed package.
     *
     * @param packageManager
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public List<InstalledApp> retrieveInstalledApps(String packageToFind, PackageManager packageManager)
            throws PackageManager.NameNotFoundException {
        final List<InstalledApp> installedApps = new ArrayList<>();

        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo appInfo : packages) {
            if (appInfo.packageName.startsWith(packageToFind)) {
                InstalledApp installedApp = extractAppDetails(appInfo, packageManager);
                installedApps.add(installedApp);
            }
        }

        return installedApps;
    }

    /**
     * Extracts information for a specific app based on the package name
     *
     * @param packageName
     * @param packageManager
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public InstalledApp retrieveInstalledApp(String packageName, PackageManager packageManager)
            throws PackageManager.NameNotFoundException {
        ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
        return extractAppDetails(appInfo, packageManager);
    }

    /**
     * Constructs basic information regarding passed application, including its icon and name.
     *
     * @param appInfo
     * @param packageManager
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    private InstalledApp extractAppDetails(ApplicationInfo appInfo, PackageManager packageManager)
            throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = packageManager.getPackageInfo(appInfo.packageName, 0);
        return new InstalledApp(appInfo.loadLabel(packageManager).toString(),
                appInfo.packageName, packageInfo.versionCode,
                packageInfo.versionName, appInfo.loadIcon(packageManager));
    }
}
