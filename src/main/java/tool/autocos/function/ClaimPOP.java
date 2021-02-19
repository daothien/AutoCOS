package tool.autocos.function;

import tool.autocos.function.acc.Account;
import tool.autocos.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ClaimPOP {
    public static void main(String[] args) {
        List<Account> accountList = FileUtils.getAccountPOP("files/accpopnew.csv");
        for (Account account: accountList) {
            Thread thread = new Thread(() -> {
                for (int i = 0; i <= 25; i++) {
                    account.claimPop(account.getAuthorization(), account.getPostDataClainPop());
                    try {
                        Thread.sleep(61000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

}
