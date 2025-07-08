package fpt.edu.vn.koreanbookstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import fpt.edu.vn.koreanbookstore.book.Book;
import fpt.edu.vn.koreanbookstore.book.DetailBook;
import fpt.edu.vn.koreanbookstore.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;

    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.bookList = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.imgBook.setImageResource(book.getImageResId());

        String title = book.getTitle();
        if (title.length() > 7) {
            title = title.substring(0, 7) + "...";
        }
        holder.tvTitle.setText(title);
        holder.tvAuthor.setText(book.getAuthor());

        int originalPrice = book.getPrice();
        int discountPercent = 20;
        int discountedPrice = originalPrice * (100 - discountPercent) / 100;

        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = formatter.format(discountedPrice).replace(",", ".") + "đ";
        holder.tvPrice.setText(formattedPrice);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailBook.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("price", book.getPrice());
            intent.putExtra("imageResId", book.getImageResId());
            intent.putExtra("description", book.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView tvTitle, tvAuthor, tvPrice;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_book_title);
            tvAuthor = itemView.findViewById(R.id.tv_book_author);
            tvPrice = itemView.findViewById(R.id.tv_book_price);
        }
    }
}