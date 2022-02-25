package com.example.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.ebook.Ebook1;
import com.example.collegeapp.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private int[] images;
    private String[] text;
    private SliderAdapter adapter;
    private SliderView sliderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.sliderView);
        images = new int[]{R.drawable.c1,R.drawable.c2,R.drawable.c3};
        text = new String[]{"First Image", "Second Image", "Third Image"};

        adapter = new SliderAdapter(images, text);
        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.startAutoCycle();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,R.id.frame_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_Ebook:
                startActivity(new Intent(this, Ebook1.class));
                break;
            case R.id.navigation_result:
                Toast.makeText(this,"Results",Toast.LENGTH_SHORT).show();
                result();

                /*TextView linkTextView = findViewById(R.id.navigation_result);
                linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
                linkTextView.setLinkTextColor(Color.BLACK);
                break;*/
            case R.id.navigation_fees:
                Toast.makeText(this,"Fees",Toast.LENGTH_SHORT).show();
                fees();
//                TextView linkTextView1 = findViewById(R.id.navigation_fees);
//                linkTextView1.setMovementMethod(LinkMovementMethod.getInstance());
//                linkTextView1.setLinkTextColor(Color.BLACK);
                break;
            case R.id.navigation_website:
                Toast.makeText(this,"Website",Toast.LENGTH_SHORT).show();
                website();

               /* TextView linkTextView2 = findViewById(R.id.navigation_website);
                linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());
                linkTextView2.setLinkTextColor(Color.BLACK);
*/
                break;
            case R.id.navigation_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_rate:
                Toast.makeText(this,"Rate Us",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void fees(){
    try {
        Intent intent = new Intent("android.intent.action.VIEW");
        Bundle bundle = new Bundle();
        int i = Build.VERSION.SDK_INT;
        bundle.putBinder("android.support.customtabs.extra.SESSION", null);
        intent.putExtras(bundle);
        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", R.color.darkblue);
        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
        intent.setPackage("com.android.chrome");
        intent.setData(Uri.parse("https://charusat.edu.in:912/FeesPaymentApp/"));
        startActivity(intent, bundle);

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    public void result(){
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Bundle bundle = new Bundle();
            int i = Build.VERSION.SDK_INT;
            bundle.putBinder("android.support.customtabs.extra.SESSION", null);
            intent.putExtras(bundle);
            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", R.color.darkblue);
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
            intent.setPackage("com.android.chrome");
            intent.setData(Uri.parse("https://charusat.edu.in:912/UniExamResult/"));
            startActivity(intent, bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void website(){
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Bundle bundle = new Bundle();
            int i = Build.VERSION.SDK_INT;
            bundle.putBinder("android.support.customtabs.extra.SESSION", null);
            intent.putExtras(bundle);
            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", R.color.darkblue);
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
            intent.setPackage("com.android.chrome");
            intent.setData(Uri.parse("https://www.charusat.ac.in/"));
            startActivity(intent, bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}