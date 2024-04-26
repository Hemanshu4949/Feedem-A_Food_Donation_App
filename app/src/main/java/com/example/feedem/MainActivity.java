package com.example.feedem;

        import static android.app.PendingIntent.getActivity;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Looper;
        import android.util.Log;

        import androidx.core.splashscreen.SplashScreen;

        import com.example.feedem.ModelClasses.Donation_Data;
        import com.example.feedem.Service.MyService;
        import com.example.feedem.Service.Profile_retrival;
        import com.example.feedem.ui.Credentials_chacking.login;
        import com.google.android.material.bottomnavigation.BottomNavigationView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.navigation.NavController;
        import androidx.navigation.Navigation;
        import androidx.navigation.ui.AppBarConfiguration;
        import androidx.navigation.ui.NavigationUI;

        import com.example.feedem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
     SharedPreferences credentials;
    private boolean keep = true;
    private final int DELAY = 2000;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = new Intent(getApplicationContext(), MyService.class);
        getApplicationContext().startService(intent);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
            @Override
            public boolean shouldKeepOnScreen() {
                return keep;
            }
        });

        // getting required data from firebase in background


        Boolean flag = false;

        credentials = getSharedPreferences("crendentials" , MODE_PRIVATE);
        String credential = credentials.getString("Userid" , null);
        Log.d("mainactivity" , credential+"hi");
        if (credential == null)
        {
            Handler handler = new Handler();
            handler.postDelayed(runner, DELAY);
            Log.d("ifmain" , "true");
        }
        else
        {
            Donation_Data.setUid(credential);
            Handler handler = new Handler();
            handler.postDelayed(runner1, DELAY);
            Log.d("ifmain" , "false");
            flag = true;
            Intent intent1= new Intent(getApplicationContext(), Profile_retrival.class);
            getApplicationContext().startService(intent1);
        }

        credentials = getSharedPreferences("crendentials" , MODE_PRIVATE);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_donate, R.id.navigation_profile)
                .build();
        getSupportActionBar().hide();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }
    private final Runnable runner = new Runnable() {
        @Override
        public void run() {
            keep = false;
            tologin();
        }
    };
    private final Runnable runner1 = new Runnable() {
        @Override
        public void run() {
            keep = false;
        }
    };

    private void tologin()
    {
        Intent intent = new Intent(getBaseContext(), login.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().stopService(intent);
    }


}