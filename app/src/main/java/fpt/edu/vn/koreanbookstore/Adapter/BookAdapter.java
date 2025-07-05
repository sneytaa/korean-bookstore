package fpt.edu.vn.koreanbookstore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpt.edu.vn.koreanbookstore.Book;
import fpt.edu.vn.koreanbookstore.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;

    public BookAdapter(List<Book> books) {
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
        holder.tvPrice.setText(book.getPrice());
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