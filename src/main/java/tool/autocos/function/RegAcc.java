package tool.autocos.function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class RegAcc {
    public static void setupBrowser() {
        String mailtemp = "https://mail.tm/en/view";
        String temp_mail = "https://temp-mail.org/vi/";
        WebDriver driver = new ChromeDriver();
        driver.get(mailtemp);
//        ((JavascriptExecutor) driver).executeScript("window.open('https://cos.tv/account/register','_blank');");
//        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(0));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String query = "function getMail(){\n" +
                "    var value = document.querySelector('#address').value;\n" +
                "    return value;\n" +
                "} " +
                "return getMail()";
        String mail = "";
        while (!mail.contains("@")) {
            mail = (String) jsExecutor.executeScript(query);
        }
//
//        driver.switchTo().window(tabs.get(1));
//        WebElement emailForm = driver.findElement(By.cssSelector("[placeholder='Email']"));
//        WebElement passForm = driver.findElement(By.cssSelector("[placeholder='New password']"));
//        WebElement rePassForm = driver.findElement(By.cssSelector("[placeholder='Repeat password']"));
//        emailForm.sendKeys(mail);
        String pw = "costvdvt";
//        passForm.sendKeys(pw);
//        rePassForm.sendKeys(pw);
//        WebElement btnSignUp = driver.findElement(By.cssSelector("[type='submit']"));
//        btnSignUp.click();
        WriteFile.write(mail,pw);
        System.out.println(mail);
    }
}
