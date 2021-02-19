package tool.autocos.function.acc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.json.JSONObject;
import tool.autocos.api.APIConf;
import tool.autocos.utils.PayLoadUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@Getter
@Setter
public class Account implements InteractFunction {
    static String user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
    String cookie;
    String token;
    String email;
    String authorization;
    String postDataClainPop;
    public Account(String email, String cookie) {
        this.email = email;
        this.cookie = cookie;
    }

    @SneakyThrows
    public void like(String postID) {
        action(1, postID);
    }

    @SneakyThrows
    public void comment(String postID, String content) {
        action(2, postID, content);
    }

    @Override
    public void follow(String uid) {
        action(3, uid);
    }

    @Override
    public void getPOP(String vid, String permanent_token, long timestamp) {
//        action(4, vid, permanent_token, timestamp);
    }
    public void view(String postId, String authorization,String permanent_token,String timestamp){
        action(6,postId, authorization, permanent_token,timestamp);
    }
    public void view2(String postId, String authorization,String payload){
        action(7,postId,authorization,payload);
    }

    @SneakyThrows
    @Override
    public boolean getCsrfToken() {
        try {
            URL url = new URL(APIConf.CSRF_TOKEN);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("cookie", cookie);
            con.setRequestProperty("user-agent", user_agent);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer body = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();
            con.disconnect();
            JSONObject jsonObject = new JSONObject(body.toString());
            token = jsonObject.getJSONObject("data").getString("token");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void updateInfo() {

    }
    public void claimPop(String authorization,String payload){
        action(8,"",authorization,payload);
    }
    //interacid: 1:like, 2:cmt, 3:follow, 4:getPOP
    @SneakyThrows
    public void action(int interactID, String... params) {
        String api = "";
        String payload = "";
        switch (interactID) {
            case 1:
                api = APIConf.LIKE;
                String postID1 = params[0];
                payload = PayLoadUtils.buidLikePayLoad(postID1);
                break;
            case 2:
                api = APIConf.CMT;
                String postID2 = params[0];
                String content = params[1];
                payload = PayLoadUtils.buidCmtPayLoad(postID2, content);
                break;
            case 3:
                api = APIConf.FOLLOW;
                String uid = params[0];
                payload = PayLoadUtils.buidFollowPayLoad(uid);
                break;
            case 4:
                api = APIConf.CLAIM_POP;
                String vid = params[0];
                String permanent_token = params[1];
                String str_timestamp = params[2];
                long timestamp = Long.parseLong(str_timestamp);
                payload = PayLoadUtils.buildClaimPOPPlayLoad(vid, permanent_token, timestamp);
            case 5:
                api = APIConf.UPDATE_INFO;
                String avatar = params[0];
                String nickname = params[1];
                String countryCode = params[3];
                payload = PayLoadUtils.buildUpdateInfoPayload(avatar, nickname, countryCode);
                break;
            case 6:
                api = APIConf.VIEW;
                String postid = params[0];
                String pre = params[2];
                String str_timestamp2 = params[3];
                long timestamp2 = Long.parseLong(str_timestamp2);
                payload = PayLoadUtils.buildViewPayload(postid,pre,timestamp2);
                break;
            case 7:
                api = APIConf.VIEW;
                payload = params[2];
                break;
            case 8:
                api = APIConf.CLAIM_POP;
                payload = params[2];
                break;

        }
        URL url = new URL(api);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("accept", "application/json, text/plain, */*");
        con.setRequestProperty("accept-encoding", "gzip, deflate, br");
        con.setRequestProperty("accept-language", "en-US,en;q=0.9");
        con.setRequestProperty("cache-control", "no-cache");
        con.setRequestProperty("content-type", "application/json");
        con.setRequestProperty("cookie", cookie);
        con.setRequestProperty("origin", "https://cos.tv");
        con.setRequestProperty("pragma", "no-cache");
//        con.setRequestProperty("referer", "https://cos.tv/videos/play/25494171646465024");
        con.setRequestProperty("sec-ch-ua-mobile", "?0");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("sec-fetch-site", "same-origin");
        con.setRequestProperty("user-agent", user_agent);
        con.setRequestProperty("x-xsrf-token", token);
        if (interactID==6||interactID==7||interactID==8){
            con.setRequestProperty("authorization",params[1]);
        }
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(payload);
        out.flush();
        out.close();

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer body = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            body.append(inputLine);
        }
        in.close();
        con.disconnect();
        System.out.println("Response status: " + status);
        System.out.println(body.toString());
    }


    public static void main(String[] args) {
        for (int i=0;i<=3;i++)
            run(i);
//        for (int i=0;i<=9;i++)
//            viewCheat();
    }

    private static void run(int fileIndex) {
        AccManager.getAllACC(fileIndex);
        Random rd = new Random();
        String postID = "26010069850427392";
        List<String> liCmt = Arrays.asList("Muito bom parabéns pelo vídeo muito top","Like do Magnata Garantido","bacana","Essa foi a melhor cena do filme","esa musica me fasina muchisimoo","like", "like tt", "amazing", "good job", "Like! like",
                "like sub tt", "Greate Chanel", "nice", "nice video", "video hay", "Excellent video, thanks for sharing my friend", "Like do Magnata Garantido",
                "Nice video!! Like!", "interactuar juntos", "like :D", "^^ good video!,Very good", "Please interact again, and give me a follow-up"
                , "like you", "TT nha bn", "deixo meu like", "top", "haha", ":D", ":))", ":v", "pts :)", "omg", "^^^^^^^^^^^", "kaka", "@@","valeu","parabéns pelo canal muito bom","Salve, Top... tudo belezinha???");

        for (Account acc : AccManager.ALL_ACCOUNT.values()) {
            if (!acc.getCsrfToken()) {
                System.out.println("Fail: " + acc.email);
                continue;
            }
            int index = rd.nextInt(liCmt.size());
            String cmt = liCmt.get(index);
            acc.like(postID);
            int r = rd.nextInt(5);
            if (r == 2)
                acc.comment(postID, cmt);
//            acc.follow("25986322046035968");
            System.out.println("Done: " + acc.email);
        }
    }

}
