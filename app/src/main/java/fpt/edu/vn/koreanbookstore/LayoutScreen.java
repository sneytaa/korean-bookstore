package fpt.edu.vn.koreanbookstore;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpt.edu.vn.koreanbookstore.Fragment.Cart;
import fpt.edu.vn.koreanbookstore.Fragment.Category;
import fpt.edu.vn.koreanbookstore.Fragment.Notify;
import fpt.edu.vn.koreanbookstore.Fragment.Settings;
import fpt.edu.vn.koreanbookstore.Fragment.UserHome;

public class LayoutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_layout_screen);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment selectedFragment = null;

                if (itemId == R.id.nav_home) {
                    selectedFragment = new UserHome();
                } else if (itemId == R.id.nav_category) {
                    selectedFragment = new Category();
                } else if (itemId == R.id.nav_notify) {
                    selectedFragment = new Notify();
                } else if (itemId == R.id.nav_cart) {
                    selectedFragment = new Cart();
                }else if (itemId == R.id.nav_settings) {
                    selectedFragment = new Settings();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }

                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNav.setSelectedItemId(R.id.nav_home);
    }
}