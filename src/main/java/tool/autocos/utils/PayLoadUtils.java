package tool.autocos.utils;

import org.json.JSONObject;

public class PayLoadUtils {
    public static String buidLikePayLoad(String postID) {
        JSONObject json = new JSONObject();
        json.put("post_id", postID);
        json.put("timestamp", System.currentTimeMillis());
        return json.toString();
    }

    public static String buidCmtPayLoad(String postID, String content) {
        JSONObject json = new JSONObject();
        json.put("post_id", postID);
        json.put("timestamp", System.currentTimeMillis());
        json.put("comment_content", content);
        return json.toString();
    }

    public static String buidFollowPayLoad(String uid) {
        JSONObject json = new JSONObject();
        json.put("fuid", uid);
        json.put("timestamp", System.currentTimeMillis());
        return json.toString();
    }

    public static String buildClaimPOPPlayLoad(String vid,String permanent_token, long timestamp){
        JSONObject json = new JSONObject();
        json.put("integral", 20);
        json.put("permanent_token",permanent_token);
        json.put("vid", vid);
        json.put("activity","videoIntegral");
        json.put("timestamp", timestamp);
        return json.toString();
    }

    public static String buildCreateMailPayload(String email, String pass){
        JSONObject json = new JSONObject();
        json.put("address", email);
        json.put("password", pass);
        return json.toString();
    }

    public static String buildUpdateInfoPayload(String avtLink, String nickname, String countryCode){
        JSONObject json = new JSONObject();
        json.put("avatar",avtLink);
        json.put("nickname",nickname);
        json.put("countryCode",countryCode);
        json.put("fullname", "");
        return json.toString();
    }

    public static String buildViewPayload(String vid,String permanent_token, long timestamp){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"watch\"").append(":").append("\""+vid+"\"").append(",");
        stringBuilder.append("\"permanent_token\"").append(":").append("\""+permanent_token+"\"").append(",");
        stringBuilder.append("\"timestamp\"").append(":").append(timestamp);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
