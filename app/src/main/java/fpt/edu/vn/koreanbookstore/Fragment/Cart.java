package fpt.edu.vn.koreanbookstore.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.CartAdapter;
import fpt.edu.vn.koreanbookstore.R;
import fpt.edu.vn.koreanbookstore.cart.CartItem;
import fpt.edu.vn.koreanbookstore.cart.CartManager;

public class Cart extends Fragment {

    private RecyclerView recyclerCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;

    public Cart() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerCart = view.findViewById(R.id.recycler_cart);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        btnCheckout = view.findViewById(R.id.btn_checkout);

        List<CartItem> cartItems = CartManager.getCartItems();

        CartAdapter adapter = new CartAdapter(getContext(), cartItems);
        recyclerCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCart.setAdapter(adapter);

        // Tính tổng tiền (chỉ các item được check)
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.isChecked()) {
                total += item.getDiscountedPrice() * item.getQuantity();
            }
        }

        DecimalFormat formatter = new DecimalFormat("#,###");
        tvTotalPrice.setText("Tổng tiền: " + formatter.format(total).replace(",", ".") + "đ");

        btnCheckout.setOnClickListener(v -> {
            // TODO: Xử lý logic thanh toán sau
        });

        return view;
    }
}
