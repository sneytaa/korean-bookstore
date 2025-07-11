package fpt.edu.vn.koreanbookstore.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fpt.edu.vn.koreanbookstore.LayoutScreen;
import fpt.edu.vn.koreanbookstore.cart.CartItem;
import fpt.edu.vn.koreanbookstore.cart.CartManager;
import fpt.edu.vn.koreanbookstore.Fragment.Cart;
import fpt.edu.vn.koreanbookstore.R;

public class DetailBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_book);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        int price = intent.getIntExtra("price", 0);
        int imageResId = intent.getIntExtra("imageResId", 0);
        String description = intent.getStringExtra("description");

        TextView tvOldPrice = findViewById(R.id.tv_old_price);
        TextView tvDiscount = findViewById(R.id.tv_discount);
        TextView tvPrice = findViewById(R.id.tv_price);
        ImageView imgBook = findViewById(R.id.img_book);
        TextView tvTitle = findViewById(R.id.tv_book_full_title);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvDeliveryDate = findViewById(R.id.tv_deliveryDate);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String deliveryDate = sdf.format(calendar.getTime());
        tvDeliveryDate.setText("Nhận hàng vào ngày: " + deliveryDate);

        tvDescription.setText(description);
        tvTitle.setText(title + " - " + author);
        imgBook.setImageResource(imageResId);

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedOldPrice = formatter.format(price).replace(",", ".") + "đ";
        tvOldPrice.setText(formattedOldPrice);

        int discountPercent = 20;
        tvDiscount.setText("-" + discountPercent + "%");

        try {
            int discountedPrice = price * (100 - discountPercent) / 100;
            String formattedPrice = formatter.format(discountedPrice).replace(",", ".") + "đ";
            tvPrice.setText(formattedPrice);
        } catch (Exception e) {
            e.printStackTrace();
            tvPrice.setText("Lỗi giá");
        }

        Book book = new Book(imageResId, title, price, description, "", author);
        Button btnAddCart = findViewById(R.id.btn_add_cart);
        btnAddCart.setOnClickListener(v -> {
            CartItem item = new CartItem(
                    imageResId,
                    title,
                    price,
                    1,
                    price * 80 / 100,
                    true,
                    calendar.getTime()
            );

            CartManager.addToCart(item);
            Toast.makeText(DetailBook.this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
            invalidateOptionsMenu();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_book, menu);

        MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();

        TextView cartBadge = actionView.findViewById(R.id.cart_badge);
        ImageView cartIcon = actionView.findViewById(R.id.ic_cart);

        int cartCount = CartManager.getCartCount(); //
        if (cartCount > 0) {
            cartBadge.setText(String.valueOf(cartCount));
            cartBadge.setVisibility(View.VISIBLE);
        } else {
            cartBadge.setVisibility(View.GONE);
        }

        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            Intent intent = new Intent(this, LayoutScreen.class);
            intent.putExtra("navigateTo", "cart");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.cart_container).getVisibility() == View.VISIBLE) {
            getSupportFragmentManager().popBackStack();
            findViewById(R.id.cart_container).setVisibility(View.GONE);
            findViewById(R.id.main).setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

}
