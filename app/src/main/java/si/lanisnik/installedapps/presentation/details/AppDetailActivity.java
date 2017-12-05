package si.lanisnik.installedapps.presentation.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import si.lanisnik.installedapps.R;

public class AppDetailActivity extends AppCompatActivity implements AppDetailContract.View {

    private static final String EXTRA_APP_PACKAGE_NAME = "AppPackageName";

    @BindView(R.id.detail_icon_image_view)
    protected ImageView iconImageView;

    @BindView(R.id.detail_name_text_view)
    protected TextView nameTextView;

    @BindView(R.id.detail_package_text_view)
    protected TextView packageNameTextView;

    @BindView(R.id.detail_version_name_text_view)
    protected TextView versionNameTextView;

    @BindView(R.id.detail_version_code_text_view)
    protected TextView versionCodeTextView;

    private AppDetailContract.Presenter presenter;

    /**
     * Constructs an intent for starting this Activity.
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Intent createLaunchIntent(Context context, String packageName) {
        Intent intent = new Intent(context, AppDetailActivity.class);
        intent.putExtra(EXTRA_APP_PACKAGE_NAME, packageName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        setupToolbar();
        ButterKnife.bind(this);
        initPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // only back button is supported
        finish();
        return true;
    }

    @OnClick(R.id.detail_open_button)
    protected void onOpenAppClick() {
        presenter.onStartClicked();
    }

    @Override
    public void showIcon(Drawable drawable) {
        iconImageView.setImageDrawable(drawable);
    }

    @Override
    public void showName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void showPackageName(String packageName) {
        packageNameTextView.setText(packageName);
    }

    @Override
    public void showVersionCode(int versionCode) {
        versionCodeTextView.setText(getString(R.string.detail_version_code, versionCode));
    }

    @Override
    public void showVersionNumber(String versionNumber) {
        versionNameTextView.setText(getString(R.string.detail_version_name, versionNumber));
    }

    @Override
    public void launchApp(String appPackageName) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(appPackageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
        }
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    private void initPresenter() {
        presenter = new AppDetailPresenter();
        presenter.setView(this);
        presenter.initialize(this, getIntent().getStringExtra(EXTRA_APP_PACKAGE_NAME));
    }
}
