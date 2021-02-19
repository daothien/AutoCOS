package tool.autocos.function.mail;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tool.autocos.api.APIConf;
import tool.autocos.api.MailConf;
import tool.autocos.function.WriteFile;
import tool.autocos.utils.NameGenerator;
import tool.autocos.utils.PayLoadUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Mail implements IMail {
    enum METHOD {POST, GET}

    String email;
    String pass;
    String token;
    String idMessActive;
    String codeActive;

    @Override
    public void creat(String email, String pass) {
        boolean ok = action(1, email, pass);
        if (ok) {
            WriteFile.write(email, pass);
        }
    }

    @Override
    public boolean getToken(String email, String pass) {
        return action(2, email, pass);
    }

    @Override
    public void getIdActive() {
        if (null != token) {
            while (null == idMessActive)
                action(3);
        }
    }

    @Override
    public void activeCOS() {
        if (null != idMessActive) {
            action(4);
        }
    }

    public boolean action(Integer interactID, String... params) {
        try {
            String api = "";
            String payload = "";
            String method = METHOD.POST.toString();
            switch (interactID) {
                case 1:
                    api = MailConf.CREAT;
                    String email = params[0];
                    String pass = params[1];
                    payload = PayLoadUtils.buildCreateMailPayload(email, pass);
                    break;
                case 2:
                    api = MailConf.TOKEN;
                    String email2 = params[0];
                    String pass2 = params[1];
                    payload = PayLoadUtils.buildCreateMailPayload(email2, pass2);
                    break;
                case 3:
                    api = MailConf.MAIL;
                    method = METHOD.GET.toString();
                    break;
                case 4:
                    api = MailConf.MAIL + "/" + idMessActive;
                    method = METHOD.GET.toString();
                    break;
            }
            URL url = new URL(api);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod(method);
            if (interactID == 3 || interactID == 4) {
                con.setRequestProperty("authorization", "Bearer " + token);
            }
            con.setRequestProperty("accept", "application/json, text/plain, */*");
            con.setRequestProperty("accept-encoding", "gzip, deflate, br");
            con.setRequestProperty("accept-language", "vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,zh-CN;q=0.5,zh-TW;q=0.4,zh;q=0.3");
            con.setRequestProperty("cache-control", "no-cache");
            con.setRequestProperty("content-type", "application/json;charset=UTF-8");
            con.setRequestProperty("origin", "https://mail.tm");
            con.setRequestProperty("pragma", "no-cache");
            con.setRequestProperty("referer", "https://mail.tm/");
            con.setRequestProperty("sec-ch-ua-mobile", "?0");
            con.setRequestProperty("sec-fetch-dest", "empty");
            con.setRequestProperty("sec-fetch-mode", "cors");
            con.setRequestProperty("sec-fetch-site", "same-site");
            con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36");

            /* Payload support */
            if (interactID != 3 && interactID != 4) {
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(payload);
                out.flush();
                out.close();
            }
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            if (interactID != 3)
                System.out.println("Action: " + interactID + "\tcode" + status);
//            System.out.println(content.toString());
            if (interactID == 2) {
                JSONObject jsonObject = new JSONObject(content.toString());
                token = jsonObject.get("token").toString();
            }
            if (interactID == 3) {
                JSONObject jsonObject = new JSONObject(content.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("hydra:member");
                JSONObject js2 = jsonArray.getJSONObject(0);
                idMessActive = js2.getString("id");
            }
            if (interactID == 4) {
                String regex = "code=([\\w]+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(content.toString());
                if (matcher.find()) {
                    System.out.println(matcher.group(1));
                    codeActive = matcher.group(1);
                }
            }
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        NameGenerator nameGenerator = new NameGenerator(7);
        for (int i=0;i<=69;i++){
            String name = nameGenerator.getName();
            String domain = "@baybabes.com";
            Mail mail = new Mail();
            String email = name + domain;
//            String email = "ejajefe@baybabes.com";
            System.out.println(email);
            mail.creat(email, "dvt");
            mail.getToken(email, "dvt");
            mail.getIdActive();
            mail.activeCOS();
            String url = APIConf.URL_ACTIVE + mail.getCodeActive();
            webDriver.get(url);
            System.out.println("----------------------");
        }
    }
}
