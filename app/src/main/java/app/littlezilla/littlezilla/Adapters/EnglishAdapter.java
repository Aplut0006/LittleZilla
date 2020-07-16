package app.littlezilla.littlezilla.Adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import app.littlezilla.littlezilla.Activities.PracticeActivity;
import app.littlezilla.littlezilla.Models.EnglishModel;
import app.littlezilla.littlezilla.R;

public class EnglishAdapter extends PagerAdapter {

    private Context context;
    private List<EnglishModel> modelList;
    private LayoutInflater inflater;
    private TextToSpeech tts;


    public EnglishAdapter(Context context, List<EnglishModel> modelList) {
        this.context = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(context);
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



        View view = inflater.inflate(R.layout.item_english,container,false);
        final TextView textViewCapitalLetter = view.findViewById(R.id.textCaptialA);
        TextView textViewSmallLetter = view.findViewById(R.id.textSmalla);
        final TextView textViewDescription = view.findViewById(R.id.textDescription);
        ImageView imageViewEnglish = view.findViewById(R.id.imageEnglish);
        final Button buttonPlayLetter = view.findViewById(R.id.buttonPlay);
        final Button buttonPlayDescription = view.findViewById(R.id.buttonPlayDescription);
        textViewCapitalLetter.setText(modelList.get(position).getCapitalLetter());
        textViewSmallLetter.setText(modelList.get(position).getSmallLetter());
        textViewDescription.setText(modelList.get(position).getTextDescription());

        Picasso.get().load(modelList.get(position).getImage())
                .fit()
                .centerInside()
                .into(imageViewEnglish);

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    int result = tts.setLanguage(new Locale("en","IN"));

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
                String text = textViewCapitalLetter.getText().toString();
                tts.setLanguage(new Locale("en","IN"));
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        buttonPlayDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewDescription.getText().toString();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDescription.setVisibility(View.VISIBLE);
                buttonPlayDescription.setVisibility(View.VISIBLE);
            }
        });
        container.addView(view);
        return view;
    }

}
