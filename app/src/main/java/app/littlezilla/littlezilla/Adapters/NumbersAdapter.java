package app.littlezilla.littlezilla.Adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import app.littlezilla.littlezilla.Models.HindiModel;
import app.littlezilla.littlezilla.Models.NumbersModel;
import app.littlezilla.littlezilla.R;

public class NumbersAdapter extends PagerAdapter {
    private Context context;
    private List<NumbersModel> modelList;
    private LayoutInflater inflater;
    private TextToSpeech tts;
    public NumbersAdapter(Context context, List<NumbersModel> modelList) {
        this.context = context;
        this.modelList = modelList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_numbers,container,false);
        final TextView textViewtitle = view.findViewById(R.id.textone);
        final TextView textViewdescription = view.findViewById(R.id.textDescription);
        ImageView imageViewLink = view.findViewById(R.id.imageNumbers);
        final Button buttonPlayLetter = view.findViewById(R.id.buttonPlay);
        final Button buttonPlayDescription = view.findViewById(R.id.buttonPlayDescription);
        textViewtitle.setText(modelList.get(position).getTitle());
       textViewdescription.setText(modelList.get(position).getDescription());

        Picasso.get().load(modelList.get(position).getImageLink())
                .fit()
                .centerInside()
                .into(imageViewLink);

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    int result = tts.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    } else {
                        buttonPlayLetter.setEnabled(true);
                        buttonPlayDescription.setEnabled(true);
                    }
                }
            }
        });
        buttonPlayLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewtitle.getText().toString();
                tts.setLanguage(Locale.UK);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        buttonPlayDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewdescription.getText().toString();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               textViewdescription.setVisibility(View.VISIBLE);
                buttonPlayDescription.setVisibility(View.VISIBLE);
            }
        });

        container.addView(view);
        return view;
    }

}
