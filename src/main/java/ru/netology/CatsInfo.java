package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CatsInfo {
    private final String userId;
    private final String text;

    private final String type;
    private final String user;
    private final int upvotes;


    public CatsInfo(
            @JsonProperty("id") String userId,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") int upvotes
    ) {
        this.userId = userId;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;

    }


    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public int getUpvotes() {
        return upvotes;
    }


    @Override
    public String toString() {
        return "\n \"id\": " + " \"" + userId + "\"" + "," +
                "\n \"text\": " + " \"" + text + "\"" + "," +
                "\n \"type\": " + " \"" + type + "\"" + "," +
                "\n \"user\": " + " \"" + user + "\"" + "," +
                "\n \"upvotes\": "  + upvotes;
    }
}