package fpt.edu.vn.koreanbookstore.chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.ChatListAdapter;
import fpt.edu.vn.koreanbookstore.R;

public class Chat extends AppCompatActivity implements ChatListAdapter.OnChatItemClickListener {

    private RecyclerView recyclerView;
    private ChatListAdapter adapter;
    private List<ChatUser> chatUsers;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        recyclerView = findViewById(R.id.recycler_chat_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        chatUsers = new ArrayList<>();
        chatUsers.add(new ChatUser("1", "Trang", R.drawable.ic_user, "Xin chào!"));
        chatUsers.add(new ChatUser("2", "Minh", R.drawable.ic_user, "Khi nào giao hàng?"));

        adapter = new ChatListAdapter(chatUsers, this);
        recyclerView.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onChatClick(@NonNull ChatUser user) {
        Intent intent = new Intent(this, ChatDetail.class);
        intent.putExtra("username", user.getName());
        startActivity(intent);
    }
}