package fpt.edu.vn.koreanbookstore.Adapter;

import android.app.AlertDialog;
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
    private Context context;
    private List<CartItem> cartItems;
    private OnCartChangedListener listener;

    public interface OnCartChangedListener {
        void onCartChanged();
    }

    public CartAdapter(Context context, List<CartItem> cartItems, OnCartChangedListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM (EEEE)", new Locale("vi", "VN"));
        String formattedDate = sdf.format(item.getDeliveryDate());
        holder.tvDelivery.setText("Nhận hàng vào " + formattedDate);


        holder.btnRemove.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Đã xóa khỏi giỏ", Toast.LENGTH_SHORT).show();
        });

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            listener.onCartChanged();

        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                listener.onCartChanged();

            }
        });

        if (item.isChecked()) {
            holder.btnChoose.setImageResource(R.drawable.ic_ticked);
            holder.btnChoose.setColorFilter(
                    ContextCompat.getColor(context, R.color.bluemain),
                    PorterDuff.Mode.SRC_IN
            );
        } else {
            holder.btnChoose.setImageResource(R.drawable.ic_tick);
            holder.btnChoose.setColorFilter(
                    ContextCompat.getColor(context, R.color.pinkmain1),
                    PorterDuff.Mode.SRC_IN
            );
        }

        holder.btnChoose.setOnClickListener(v -> {
            item.setChecked(!item.isChecked());
            notifyItemChanged(position);
            listener.onCartChanged();

        });
        holder.btnRemove.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xóa sách")
                    .setMessage("Bạn có chắc muốn xóa sách này khỏi giỏ?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        cartItems.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cartItems.size());
                        listener.onCartChanged();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
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
