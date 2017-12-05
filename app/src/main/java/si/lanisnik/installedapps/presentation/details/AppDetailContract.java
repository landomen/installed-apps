package si.lanisnik.installedapps.presentation.details;

import android.content.Context;
import android.graphics.drawable.Drawable;

import si.lanisnik.installedapps.presentation.base.BasePresenter;
import si.lanisnik.installedapps.presentation.base.BaseView;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public interface AppDetailContract {

    interface View extends BaseView {
        void showIcon(Drawable drawable);

        void showName(String name);

        void showPackageName(String packageName);

        void showVersionCode(String versionCode);

        void showVersionNumber(String versionNumber);

        void launchApp(String appPackageName);
    }

    interface Presenter extends BasePresenter<AppDetailContract.View> {
        void initialize(Context context, String appPackageName);

        void onStartClicked();
    }

}
