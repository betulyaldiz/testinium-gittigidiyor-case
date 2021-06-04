package com.gittigidiyor.testcase;

import com.gittigidiyor.testcase.Users.User;
import com.gittigidiyor.testcase.pages.*;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AppTest extends BaseTest {
    @Test
    public void gittiGidiyorTest() throws InterruptedException {
        String title = "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi";
        String email = "testcaseuser123@gmail.com";
        String password = "test.123";

        HomePage homePage = new HomePage(webDriver);
        String webDriverTitle = homePage.getHomePageTitle();
        homePage.closeCookiesPopUp();

        com.gittigidiyor.testcase.Log4j.info("Title: " + webDriverTitle);
        assertEquals(webDriverTitle, title);

        com.gittigidiyor.testcase.Log4j.info("Giriş Sayfasına Git");
        LoginPage loginPage = homePage.getLoginPage();
        com.gittigidiyor.testcase.Log4j.info("email: " + email + ", password: " + password);
        User user = new User(email, password);
        loginPage.login(user);
        com.gittigidiyor.testcase.Log4j.info("Ana sayfaya git");
        String accountButtonText = homePage.getAccountText();
        com.gittigidiyor.testcase.Log4j.info("Hesabım butonuna yazılan metin: " + accountButtonText);
        assertTrue(accountButtonText.contains("Hesabım"));

        com.gittigidiyor.testcase.Log4j.info("Arama kutusuna 'bilgisayar' kelimesi girilir.");
        SearchResultPage searchResultPage = homePage.search("bilgisayar");
        searchResultPage.scrollToPage("7200");
        com.gittigidiyor.testcase.Log4j.info("Arama sonuçları sayfasından seçilen sayfa açılır.");
        searchResultPage.choosePage("2");
        assertTrue(webDriver.getCurrentUrl().contains("2"));

        com.gittigidiyor.testcase.Log4j.info("Ürün ayrıntıları sayfasına gidin");
        ProductDetailsPage productDetailsPage = searchResultPage.goToProductDetails();

        com.gittigidiyor.testcase.Log4j.info("Ürün fiyat bilgisi alınır.");
        String productPrice = productDetailsPage.productPrice();
        productDetailsPage.scrollToPage("1000");
        com.gittigidiyor.testcase.Log4j.info("Seçilen ürün sepete eklenir.");
        productDetailsPage.addToBasket();

        com.gittigidiyor.testcase.Log4j.info("Sepetim sayfasına gidin.");
        BasketPage basketPage = productDetailsPage.goToBasket();
        com.gittigidiyor.testcase.Log4j.info("Ürün sayfasındaki fiyat, sepetteki ürünün fiyatı ile karşılaştırılır.");
        assertEquals(productPrice, basketPage.priceInTheBasket());

        com.gittigidiyor.testcase.Log4j.info("Ürün sayısı artırıldı.");
        basketPage.setNumberOfProducts();
        Thread.sleep(2000);
        assertTrue(basketPage.getTotalProduct().contains("2 Adet"));
        com.gittigidiyor.testcase.Log4j.info("Ürün sepetten silinmiştir.");
        basketPage.deleteProduct();
        Thread.sleep(2000);

        com.gittigidiyor.testcase.Log4j.info("Sepetin boş olup olmadığını kontrol edin");
        assertEquals("0,00 TL", basketPage.priceInTheBasket());

    }
}
