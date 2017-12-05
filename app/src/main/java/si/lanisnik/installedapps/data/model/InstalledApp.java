package si.lanisnik.installedapps.data.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Domen Lanišnik on 04/12/2017.
 * domen.lanisnik@gmail.com
 */
public class InstalledApp {
    private String name;
    private String packageName;
    private int versionCode;
    private String versionName;
    private Drawable icon;

    public InstalledApp(String name, String packageName, int versionCode, String versionName, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public Drawable getIcon() {
        return icon;
    }
}
