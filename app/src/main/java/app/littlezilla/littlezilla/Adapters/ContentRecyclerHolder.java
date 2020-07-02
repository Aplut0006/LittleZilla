package app.littlezilla.littlezilla.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.littlezilla.littlezilla.R;

public class ContentRecyclerHolder extends RecyclerView.ViewHolder {

    public ImageView imageViewContent;
    public TextView textViewContent;
    public TextView buttonViewOption;

    public ProgressBar progressBarContent;
    public ContentRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        imageViewContent = itemView.findViewById(R.id.content_imageView);
        textViewContent = itemView.findViewById(R.id.content_textView);
        buttonViewOption = itemView.findViewById(R.id.textViewOptions);


        progressBarContent = itemView.findViewById(R.id.progressBarContent);


    }
}
