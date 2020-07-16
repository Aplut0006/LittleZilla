package app.littlezilla.littlezilla.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.littlezilla.littlezilla.Adapters.BannerRecyclerHolder;
import app.littlezilla.littlezilla.Adapters.ContentRecyclerHolder;
import app.littlezilla.littlezilla.Adapters.SliderAdapter;
import app.littlezilla.littlezilla.Models.BannerModel;
import app.littlezilla.littlezilla.Models.ContentModel;
import app.littlezilla.littlezilla.R;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private RecyclerView recyclerViewBanner, recyclerViewContent;
//    private FirebaseRecyclerOptions<BannerModel> options;
    private FirebaseRecyclerOptions<ContentModel> options1;
//    private FirebaseRecyclerAdapter<BannerModel, BannerRecyclerHolder> adapter;
    private FirebaseRecyclerAdapter<ContentModel, ContentRecyclerHolder> adapter1;
    private DatabaseReference databaseReferenceBanner, databaseReferenceContent;
    private Toolbar toolbar;
    private List<BannerModel> bannerModelList = new ArrayList<>();
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Little Zilla");
        setSupportActionBar(toolbar);
        navigationView=findViewById(R.id.navmenu);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        Toast.makeText(MainActivity.this,"HOME",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.share:
                        Toast.makeText(MainActivity.this,"Share",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Updating Data...");
//        progressDialog.setCancelable(false);

//        databaseReferenceBanner = FirebaseDatabase.getInstance().getReference().child("BannerImages");
//
//        databaseReferenceBanner.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                BannerModel data = snapshot.getValue(BannerModel.class);
//                bannerModelList.add(data);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        bannerModelList.add(new BannerModel("https://codecanyon.img.customer.envatousercontent.com/files/255464710/Banner.png?auto=compress%2Cformat&q=80&fit=crop&crop=top&max-h=8000&max-w=590&s=4de7d5607a3a1959e0980094342fba87"));
        bannerModelList.add(new BannerModel("https://i.pinimg.com/originals/f2/d6/37/f2d637801238bb720bd360dee49f1019.jpg"));
        bannerModelList.add(new BannerModel("https://i.pinimg.com/originals/e2/c4/35/e2c4353d8d53a1a47ab6de3e46d6b0f9.jpg"));
//        recyclerViewBanner = findViewById(R.id.recyclerViewBanner);
        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapter adapterSlider = new SliderAdapter(this,bannerModelList);

        sliderView.setSliderAdapter(adapterSlider);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        recyclerViewContent = findViewById(R.id.recyclerViewContent);

//        recyclerViewBanner.setHasFixedSize(true);
//        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewContent.setHasFixedSize(true);
        recyclerViewContent.setLayoutManager(new GridLayoutManager(this, 2));
//        databaseReferenceBanner = FirebaseDatabase.getInstance().getReference().child("BannerImages");
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();

//        databaseReferenceBanner.keepSynced(true);

//        options = new FirebaseRecyclerOptions.Builder<BannerModel>()
//                .setQuery(databaseReferenceBanner, BannerModel.class)
//                .build();
//
//        adapter = new FirebaseRecyclerAdapter<BannerModel, BannerRecyclerHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull final BannerRecyclerHolder holder, int position, @NonNull final BannerModel model) {
//                holder.progressBarBannerView.setVisibility(View.VISIBLE);
//
//                Picasso.get().load(model.getBannerImages())
//                        .fit()
//                        .centerCrop()
//                        .into(holder.bannerImageView, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                progressDialog.cancel();
//                                holder.progressBarBannerView.setVisibility(View.GONE);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                progressDialog.cancel();
//                            }
//                        });
//            }
//
//            @NonNull
//            @Override
//            public BannerRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(MainActivity.this)
//                        .inflate(R.layout.banner_images_view, parent, false);
//                return new BannerRecyclerHolder(view);
//            }
//        };
//        recyclerViewBanner.setAdapter(adapter);
//        //
//        //
//        //
        databaseReferenceContent = FirebaseDatabase.getInstance().getReference().child("content");

        databaseReferenceContent.keepSynced(true);

        options1 = new FirebaseRecyclerOptions.Builder<ContentModel>()
                .setQuery(databaseReferenceContent, ContentModel.class)
                .build();

        adapter1 = new FirebaseRecyclerAdapter<ContentModel, ContentRecyclerHolder>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull final ContentRecyclerHolder holder, final int position, @NonNull final ContentModel model) {
                holder.progressBarContent.setVisibility(View.VISIBLE);
                holder.textViewContent.setText(model.getTitle());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position ==0) {
                            Intent intent = new Intent(MainActivity.this,HindiActivity.class);
                            startActivity(intent);
                        }else if (position ==1){
                            Intent intent = new Intent(MainActivity.this,EnglishActivity.class);
                            startActivity(intent);
                        }
                        else if (position ==2){
                            Intent intent = new Intent(MainActivity.this,PoemsActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this,MathsActivity.class);
                            startActivity(intent);
                        }
                    }
                });

                holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        PopupMenu popup = new PopupMenu(MainActivity.this, holder.buttonViewOption);
                        //inflating menu from xml resource
                        popup.inflate(R.menu.menu_card);
                        //adding click listener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.practice:
                                        Intent intent = new Intent(MainActivity.this,PracticeActivity.class);
                                        startActivity(intent);
                                        break;
                                    case R.id.menu2:
                                        Toast.makeText(MainActivity.this, "2 clicked", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu3:
                                        Toast.makeText(MainActivity.this, "3 clicked", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return false;
                            }
                        });
                        //displaying the popup
                        popup.show();

                    }
                });

                Picasso.get().load(model.getImage())
                        .fit()
                        .centerCrop()
                        .into(holder.imageViewContent, new Callback() {
                            @Override
                            public void onSuccess() {
//                                progressDialog.cancel();
                                holder.progressBarContent.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
//                                progressDialog.cancel();

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
//        adapter.startListening();
        adapter1.startListening();
        if(mAuth.getCurrentUser()==null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter.stopListening();
        adapter1.startListening();

    }

}