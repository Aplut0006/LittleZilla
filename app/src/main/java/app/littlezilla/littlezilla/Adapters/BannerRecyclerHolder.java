package app.littlezilla.littlezilla.Adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.PortUnreachableException;

import app.littlezilla.littlezilla.R;

public class BannerRecyclerHolder extends RecyclerView.ViewHolder {

    public ImageView bannerImageView;
    public BannerRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        bannerImageView = itemView.findViewById(R.id.banner_imageView);

    }
}
