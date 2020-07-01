package app.littlezilla.littlezilla.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.net.PortUnreachableException;

import app.littlezilla.littlezilla.R;

public class BannerRecyclerHolder extends RecyclerView.ViewHolder {

    public ImageView bannerImageView;
    public ProgressBar progressBarBannerView;
    public ShimmerFrameLayout shimmerFrameLayout;
    public BannerRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        bannerImageView = itemView.findViewById(R.id.banner_imageView);
        progressBarBannerView = itemView.findViewById(R.id.progressBarBanner);
    }
}
