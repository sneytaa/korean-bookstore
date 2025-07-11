package fpt.edu.vn.koreanbookstore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import fpt.edu.vn.koreanbookstore.R;
import fpt.edu.vn.koreanbookstore.chat.ChatUser;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {

    public interface OnChatItemClickListener {
        void onChatClick(ChatUser user);
    }

    private List<ChatUser> chatList;
    private OnChatItemClickListener listener;

    public ChatListAdapter(List<ChatUser> chatList, OnChatItemClickListener listener) {
        this.chatList = chatList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_list, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatUser user = chatList.get(position);
        holder.tvName.setText(user.getName());
        holder.tvLastMessage.setText(user.getLastMessage());
        holder.avatar.setImageResource(user.getAvatarResId());

        holder.itemView.setOnClickListener(v -> listener.onChatClick(user));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLastMessage;
        ImageView avatar;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_username);
            tvLastMessage = itemView.findViewById(R.id.tv_last_message);
            avatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
