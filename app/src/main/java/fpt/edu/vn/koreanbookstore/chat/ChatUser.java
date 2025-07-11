package fpt.edu.vn.koreanbookstore.chat;

public class ChatUser {
    private String userId;
    private String userName;
    private int avatarResId;
    private String lastMessage;

    public ChatUser(String userId, String userName, int avatarResId, String lastMessage) {
        this.userId = userId;
        this.userName = userName;
        this.avatarResId = avatarResId;
        this.lastMessage = lastMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getName() {
        return userName;
    }


    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getAvatarResId() {
        return avatarResId;
    }
}
