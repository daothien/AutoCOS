package tool.autocos.function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.Request;
import tool.autocos.MainFrame;
import tool.autocos.function.acc.Account;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewCheat {
    public static void runAuto(String link) {
        Account account = new Account("","");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1000,900");
        WebDriver driver = new ChromeDriver(options);
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), load -> {
            Request request = load.getRequest();
            String url = request.getUrl();
            if (url.contains("play_action_report")) {
                String postData = request.getPostData();
                if (postData.length() > 100) {
                    String authorization = (String) request.getHeaders().get("Authorization");
                    for (int i = 0; i <= 9; i++){
                        account.view2("25962894543851520", authorization, postData);
                    }
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                    String query = "localStorage.clear();";
                    jsExecutor.executeScript(query);
                    driver.navigate().refresh();
                }else {
                    try {
                        driver.findElement(By.className("vjs-big-play-button")).click();
                    }catch (Exception ex){
                        driver.navigate().refresh();
                    }
                }
            }

        });
        driver.get(link);
    }
}
