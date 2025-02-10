
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsButtonPage {
    public WebDriverWait wait;

    public ContactUsButtonPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    public By ContactUsButton = By.xpath("(//span[text()='CONTACT US']/parent::a)[1]");

    public void test() throws InterruptedException {
        WebElement contactUsButton = this.wait.until(ExpectedConditions.elementToBeClickable(ContactUsButton));
        contactUsButton.click();
        Thread.sleep(2000);
    }
}
