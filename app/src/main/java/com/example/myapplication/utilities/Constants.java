package com.example.myapplication.utilities;

import java.util.HashMap;

public class Constants {
    public static final String KEY_COLLECTION_USERS = "users";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PREFERENCE_NAME = "chatAppPreferemce";
    public static final String KEY_IS_SIGNED_IN = "isSignedIn";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_FCM_TOKEN ="fcmToken";
    public static final String KEY_USER = "user";
    public static final String KEY_COLLECTION_CHAT = "chat";
    public static final String KEY_SENDER_ID ="senderId";
    public static final String KEY_RECEIVER_ID = "receiverId";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_COLLECTION_CONVERSATIONS = "conversations";
    public static final String KEY_SENDER_NAME = "senderName";
    public static final String KEY_RECEIVER_NAME = "receiverName";
    public static final String KEY_SENDER_IMAGE = "senderImage";
    public static final String KEY_RECEIVER_IMAGE = "receiverImage";
    public static final String KEY_LAST_MESSAGE = "lastMessage";
    public static final String KEY_AVAILABILITY = "avilability";
    public static final String KEY_REMOTE_MSG_AUTH ="Authorization";
    public static final String KEY_REMOTE_CONTENT_TYPE = "Content-Type";
    public static final String KEY_REMOTE_MSG_DATA = "data";
    public static final String KEY_REMOTE_REGISTRATION_IDS = "registration_ids";


    public static HashMap<String,String> remoteMsgHeaders = null;

    public static HashMap<String,String>  getRemoteMsgHeaders()
    {
        if(remoteMsgHeaders == null)
        {
            remoteMsgHeaders = new HashMap<>();
            remoteMsgHeaders.put(
                    KEY_REMOTE_MSG_AUTH,
                    "key=AAAAxoe7V0E:APA91bELOIv4JSpOy2xlVLWhKrFf8mP2q0PBE4TeBlyjBNsXlpAgkhHBpGTsvwT5JYttln3J-4hqg5Di_aQ-HtvR_PIEdlXMlTlsuHuvYwROXBk4qvGvmQZrajoT2k37NqYsSBs9H-Rt"
            );
            remoteMsgHeaders.put(
                    KEY_REMOTE_CONTENT_TYPE,
                    "application/json");
        }
        return remoteMsgHeaders;
    }
}
