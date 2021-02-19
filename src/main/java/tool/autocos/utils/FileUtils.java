package tool.autocos.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tool.autocos.function.acc.Account;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {
    public static Map<String,Account> getAccount(String path){
        Map<String,Account> map = new HashMap<>();
        try{
            Reader reader= Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser=new CSVParser(reader, CSVFormat.DEFAULT);
            boolean isFirst=true;
            for (CSVRecord csvRecord: csvParser) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                String email = csvRecord.get(0).trim();
                String cookie = csvRecord.get(1).trim();
                if (cookie.trim().isEmpty())
                    continue;
                Account account = new Account(email, cookie);
                map.put(email, account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    public static List<Account> getAccountPOP(String path){
        List<Account> accountList = new ArrayList<>();
        try{
            Reader reader= Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser=new CSVParser(reader, CSVFormat.DEFAULT);
            boolean isFirst=true;
            for (CSVRecord csvRecord: csvParser) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                String authorization = csvRecord.get(2).trim();
                String payload = csvRecord.get(3).trim();
                String cookie = csvRecord.get(4).trim();

                Account account = new Account();
                account.setAuthorization(authorization);
                account.setPostDataClainPop(payload);
                account.setCookie(cookie);
                accountList.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountList;
    }
}
