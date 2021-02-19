package tool.autocos.function.acc;

import tool.autocos.utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class AccManager {
    static Map<String, Account> ALL_ACCOUNT;
    static {
        ALL_ACCOUNT = new HashMap<>();
    }
    public static void getAllACC(int fileIndex){
        final String path = "files/accc"+fileIndex+".csv";
        ALL_ACCOUNT = FileUtils.getAccount(path);
        System.out.println("Get acc done!");
    }
}
