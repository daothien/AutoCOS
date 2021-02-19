package tool.autocos;

import com.github.kklisura.cdt.launch.ChromeArguments;
import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.protocol.types.network.Request;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import tool.autocos.function.acc.Account;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LogRequestsExample {
    public static void main(String[] args) {
        for (int i=0;i<3;i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    cheat();
                }
            };
            thread.start();
        }
    }

    private static void cheat() {
        // Create chrome launcher.
        final ChromeLauncher launcher = new ChromeLauncher();
        ChromeArguments chromeArguments = ChromeArguments.builder().additionalArguments("window-size","1000,1000").
                disableExtensions().
                disableBackgroundNetworking().
        disableDefaultApps().build();
        final ChromeService chromeService = launcher.launch(chromeArguments);
        List<ChromeTab> tabs =chromeService.getTabs();
        ChromeTab tab = tabs.get(tabs.size()-1);
        final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);
        final Page page = devToolsService.getPage();
        final Network network = devToolsService.getNetwork();
//        page.getResourceTree().getFrame().
        // Log requests with onRequestWillBeSent event handler.
        Account account = new Account("","");
        AtomicInteger count = new AtomicInteger(0);
        network.onRequestWillBeSent(
                event ->{
                    Request request = event.getRequest();
                    String url = request.getUrl();
                    if (url.contains("report"))
                    {
//                        System.out.println(request.getUrl());
//                        System.out.println(request.getHeaders());
//                        System.out.println(request.getPostData());
                        String postData = request.getPostData();
                        if (postData.length()>100){
                            String authorization = (String) request.getHeaders().get("Authorization");
                            for (int i=0;i<=9;i++)
                                account.view2("25940802557744128",authorization,postData);
                            launcher.close();
                            cheat();
                        }else {
                            page.reload();
                        }
                    }}
        );
        network.enable();
        page.navigate("https://cos.tv/videos/play/25940802557744128");
        devToolsService.waitUntilClosed();
    }
}
