package fpt.edu.vn.koreanbookstore.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.CartAdapter;
import fpt.edu.vn.koreanbookstore.chat.Chat;
import fpt.edu.vn.koreanbookstore.R;
import fpt.edu.vn.koreanbookstore.cart.CartItem;
import fpt.edu.vn.koreanbookstore.cart.CartManager;

public class Cart extends Fragment implements CartAdapter.OnCartChangedListener {

    private RecyclerView recyclerCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private ImageView tickAll, btnChat;
    private CartAdapter adapter;
    private List<CartItem> cartItems;

    public Cart() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerCart = view.findViewById(R.id.recycler_cart);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        btnChat = view.findViewById(R.id.chat);
        tickAll = view.findViewById(R.id.tick_all);

        cartItems = CartManager.getCartItems();
        adapter = new CartAdapter(getContext(), cartItems, this);
        recyclerCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCart.setAdapter(adapter);

        updateTotalPrice();
        tickAll.setOnClickListener(v -> toggleSelectAll());

        btnCheckout.setOnClickListener(v -> {
            // TODO: Xử lý thanh toán
        });

        btnChat.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Chat.class);
            startActivity(intent);
        });

        return view;
    }
    private void toggleSelectAll() {
        boolean allChecked = true;
        for (CartItem item : cartItems) {
            if (!item.isChecked()) {
                allChecked = false;
                break;
            }
        }

        boolean newCheckState = !allChecked;

        for (CartItem item : cartItems) {
            item.setChecked(newCheckState);
        }

        tickAll.setImageResource(newCheckState ? R.drawable.ic_ticked : R.drawable.ic_tick);
        tickAll.setColorFilter(
                ContextCompat.getColor(requireContext(), newCheckState ? R.color.bluemain : R.color.white),
                PorterDuff.Mode.SRC_IN
        );


        adapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.isChecked()) {
                total += item.getDiscountedPrice() * item.getQuantity();
            }
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatted = formatter.format(total).replace(",", ".") + "đ";
        String text = "Tổng tiền: " + formatted;

        SpannableString spannable = new SpannableString(text);

        int start = text.indexOf(formatted);
        int end = text.length();

        spannable.setSpan(
                new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannable.setSpan(
                new android.text.style.RelativeSizeSpan(1.2f),
                start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        tvTotalPrice.setText(spannable);
    }

    @Override
    public void onCartChanged() {
        updateTotalPrice();
    }
}
