package fpt.edu.vn.koreanbookstore.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import fpt.edu.vn.koreanbookstore.R;
import fpt.edu.vn.koreanbookstore.cart.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final Context context;
    private final List<CartItem> cartItems;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.imgBook.setImageResource(item.getImageResId());
        holder.tvOriginalPrice.setText(item.getOriginalPrice() + "₫");
        holder.tvDiscountedPrice.setText(item.getDiscountedPrice() + "₫");
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        SimpleDateFormat sdf = new SimpleDateFormat("오늘(M/d,EEE) 도착", Locale.KOREA);
        holder.tvDelivery.setText(sdf.format(item.getDeliveryDate()));

        holder.btnRemove.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Đã xóa khỏi giỏ", Toast.LENGTH_SHORT).show();
        });

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
            }
        });

        holder.btnChoose.setImageResource(R.drawable.ic_tick);
        holder.btnChoose.setColorFilter(
                ContextCompat.getColor(context, item.isChecked() ? R.color.bluemain : R.color.pinkmain),
                PorterDuff.Mode.SRC_IN
        );

        holder.btnChoose.setOnClickListener(v -> {
            item.setChecked(!item.isChecked());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook, btnRemove, btnChoose;
        TextView tvTitle, tvOriginalPrice, tvDiscountedPrice, tvQuantity, btnIncrease, btnDecrease, tvDelivery;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_cart_book);
            btnRemove = itemView.findViewById(R.id.btn_remove);
            btnChoose = itemView.findViewById(R.id.btn_choose);
            tvTitle = itemView.findViewById(R.id.tv_cart_book_title);
            tvOriginalPrice = itemView.findViewById(R.id.tv_cart_original_price);
            tvDiscountedPrice = itemView.findViewById(R.id.tv_cart_discounted_price);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            tvDelivery = itemView.findViewById(R.id.tv_cart_delivery);
        }
    }
}
