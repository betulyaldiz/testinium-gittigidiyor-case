package com.gittigidiyor.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    protected WebDriver webDriver;

    public Helper(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void click(By byElement, int timeOut) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(byElement)).click();
    }
     //aktif sayfada klavyeden tuşa basılırmış gibi mesaj gönderme
    public void sendKeys(By byElement, String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(byElement)).sendKeys(text);
    }

    public String getText(By byElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byElement)).getText();
    }
    public void moveToElement(By byElement) {
        WebElement element = webDriver.findElement(byElement);
        String strJavaScript = "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);";
        ((JavascriptExecutor) webDriver).executeScript(strJavaScript, element);
    }

    public void scrollToPage(String pixel){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0," + pixel + ")");
    }


}
