package fpt.edu.vn.koreanbookstore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.BookAdapter;
import fpt.edu.vn.koreanbookstore.book.Book;

public class BookCategory extends AppCompatActivity {
    public static List<Book> allBooks = new ArrayList<>();
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_category);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        String categoryId = getIntent().getStringExtra("categoryId");
        String title = getIntent().getStringExtra("title");

        TextView tvTitle = findViewById(R.id.tv_category_title);
        tvTitle.setText(title);

        RecyclerView recyclerView = findViewById(R.id.rv_category_books);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        List<Book> filtered = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getCategoryId().equals(categoryId)) {
                filtered.add(book);
            }
        }

        BookAdapter adapter = new BookAdapter(this, filtered);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left,0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}