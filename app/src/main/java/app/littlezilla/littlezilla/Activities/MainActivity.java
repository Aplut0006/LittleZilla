package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.littlezilla.littlezilla.Adapters.BannerRecyclerHolder;
import app.littlezilla.littlezilla.Models.BannerModel;
import app.littlezilla.littlezilla.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBanner;
    private FirebaseRecyclerOptions<BannerModel> options;
    private FirebaseRecyclerAdapter<BannerModel, BannerRecyclerHolder> adapter;
    private DatabaseReference databaseReference;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Little Zilla");
        setSupportActionBar(toolbar);
        recyclerViewBanner = findViewById(R.id.recyclerViewBanner);
        recyclerViewBanner.setHasFixedSize(true);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("BannerImages");

        databaseReference.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<BannerModel>()
                .setQuery(databaseReference, BannerModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<BannerModel, BannerRecyclerHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final BannerRecyclerHolder holder, int position, @NonNull final BannerModel model) {
                holder.progressBarBannerView.setVisibility(View.VISIBLE);

                    Picasso.get().load(model.getBannerImages())
                            .fit()
                            .centerCrop()
                            .into(holder.bannerImageView, new Callback() {
                                @Override
                                public void onSuccess() {
                                    holder.progressBarBannerView.setVisibility(View.GONE);
                                }
                                @Override
                                public void onError(Exception e) {

                                }
                            });
            }
            @NonNull
            @Override
            public BannerRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.banner_images_view, parent, false);
                return new BannerRecyclerHolder(view);
            }
        };
        recyclerViewBanner.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}