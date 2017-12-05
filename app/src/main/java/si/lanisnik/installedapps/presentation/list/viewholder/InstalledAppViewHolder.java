package si.lanisnik.installedapps.presentation.list.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import si.lanisnik.installedapps.R;

/**
 * Created by Domen Lanišnik on 04/12/2017.
 * domen.lanisnik@gmail.com
 */
public class InstalledAppViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.app_name_text_view)
    public TextView nameTextView;

    @BindView(R.id.app_icon_image_view)
    public ImageView iconImageView;

    public InstalledAppViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
