package tool.autocos.function.acc;

public interface InteractFunction {
    void like(String postID);

    void comment(String postID, String content);

    void follow(String uid);

    void getPOP(String vid, String permanent_token, long timestamp);

    boolean getCsrfToken();

    void updateInfo();
}
