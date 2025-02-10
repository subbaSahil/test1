
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class ContactUsButtonTest extends BaseTest {
    @Test
    public void contactUsTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        ContactUsButtonPage contactUs = new ContactUsButtonPage(driver);
        contactUs.test();
    }
}
