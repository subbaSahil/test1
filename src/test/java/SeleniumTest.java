import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class SeleniumTest extends BaseTest {
    @Test(priority = 1)
    public void helloWorld(){
        HelloPage hp = new HelloPage();
        hp.helloWorld();
    }
    @Test(priority = 2 )
    public void contactUsTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        ContactUsButtonPage contactUs = new ContactUsButtonPage(driver);
        contactUs.test();
    }
}
