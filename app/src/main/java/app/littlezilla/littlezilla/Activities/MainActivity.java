package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import app.littlezilla.littlezilla.Adapters.ContentRecyclerHolder;
import app.littlezilla.littlezilla.Models.BannerModel;
import app.littlezilla.littlezilla.Models.ContentModel;
import app.littlezilla.littlezilla.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBanner, recyclerViewContent;
    private FirebaseRecyclerOptions<BannerModel> options;
    private FirebaseRecyclerOptions<ContentModel> options1;
    private FirebaseRecyclerAdapter<BannerModel, BannerRecyclerHolder> adapter;
    private FirebaseRecyclerAdapter<ContentModel, ContentRecyclerHolder> adapter1;
    private DatabaseReference databaseReferenceBanner, databaseReferenceContent;
    private Toolbar toolbar;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Little Zilla");
        setSupportActionBar(toolbar);
        recyclerViewBanner = findViewById(R.id.recyclerViewBanner);
        recyclerViewContent = findViewById(R.id.recyclerViewContent);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Data...");
        progressDialog.setCancelable(false);
        recyclerViewBanner.setHasFixedSize(true);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewContent.setHasFixedSize(true);
        recyclerViewContent.setLayoutManager(new GridLayoutManager(this, 2));
        databaseReferenceBanner = FirebaseDatabase.getInstance().getReference().child("BannerImages");
        
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        
        databaseReferenceBanner.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<BannerModel>()
                .setQuery(databaseReferenceBanner, BannerModel.class)
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
                                progressDialog.cancel();
                                holder.progressBarBannerView.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                progressDialog.cancel();
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
        //
        //
        //
        databaseReferenceContent = FirebaseDatabase.getInstance().getReference().child("content");

        databaseReferenceContent.keepSynced(true);

        options1 = new FirebaseRecyclerOptions.Builder<ContentModel>()
                .setQuery(databaseReferenceContent, ContentModel.class)
                .build();

        adapter1 = new FirebaseRecyclerAdapter<ContentModel, ContentRecyclerHolder>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull final ContentRecyclerHolder holder, int position, @NonNull final ContentModel model) {
                holder.progressBarContent.setVisibility(View.VISIBLE);
                holder.textViewContent.setText(model.getTitle());

                Picasso.get().load(model.getImage())
                        .fit()
                        .centerCrop()
                        .into(holder.imageViewContent, new Callback() {
                            @Override
                            public void onSuccess() {
                                holder.progressBarContent.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                            }
                        });
            }

            @NonNull
            @Override
            public ContentRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.dashboard_content, parent, false);
                return new ContentRecyclerHolder(view);
            }
        };
        recyclerViewContent.setAdapter(adapter1);

    }
//option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.siginout:
                Siginout();
                break;
        }
        return  super.onOptionsItemSelected(item);
    }

    private void Siginout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        Toast.makeText(MainActivity.this, "Signout sucessfull", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.show();
        adapter.startListening();
        adapter1.startListening();
        if(mAuth.getCurrentUser()==null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter1.startListening();

    }
    

}