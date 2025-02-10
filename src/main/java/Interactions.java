
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;

public class Interactions {
    private WebDriverWait wait;
    private Actions actions;
    private WebDriver driver;
    public Interactions(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        this.driver = driver;
    }

    //normal click on a particular element
    public void click(By element) {
        int retryCount = 3; // Number of retries
        int attempts = 0;
        boolean clicked = false;

        while (attempts < retryCount && !clicked) {
            try {
                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
                clickableElement.click();
                clicked = true; // Successfully clicked
                System.out.println("Element clicked successfully: " + element);
            } catch (Exception e) {
                attempts++;
                System.out.println("Attempt " + attempts + " to click on " + element + " failed: " + e.getMessage());
                if (attempts == retryCount) {
                    System.out.println("Failed to click on the element after " + retryCount + " attempts.");
                }
            }
        }
    }

    //
    public void javascriptClick(By element) {
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(ele).click().perform();

    }

    //overide the existing text
    public void enterText(By element, String text) {
        try {
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            inputField.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Error while entering text in the element: " + element + " - " + e.getMessage());
        }
    }

    //clear and input field and enter new text
    public void clearTextAndEnterText(By element, String newText) {
        try {
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            inputField.clear();
            inputField.sendKeys(newText);
        } catch (Exception e) {
            System.out.println("Error while clearing and entering text in the element: " + element + " - " + e.getMessage());
        }
    }

    //to get text of a particular element
    public String getText(By element) {
        try {
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return inputField.getText();
        } catch (Exception e) {
            System.out.println("Error while retrieving text from the element: " + element + " - " + e.getMessage());
            return null; // Returning null in case of an error
        }
    }



    //to scroll to a particular element for the current page
    public void scroll(By element) {
        try {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement elementToReach = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            js.executeScript("arguments[0].scrollIntoView(true);", elementToReach);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // to get the url for t he current page
    public  void isCurrentUrlCorrect(String expectedUrl) {
        try {
            Thread.sleep(5);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            if (!currentUrl.equals(expectedUrl)) {
                System.out.println("URL mismatch! Redirecting to the base URL: " + expectedUrl);
                driver.navigate().to(expectedUrl);
                Thread.sleep(10);

            }

        } catch (Exception e) {
            System.out.println("Error while retrieving, comparing, or redirecting URLs: " + e.getMessage());
            e.printStackTrace();

        }
    }



    //check the visibility of an element
    public Boolean elementVisibility(By element) {
        try {
            return (element != null);
        } catch (Exception e) {
            System.out.println("Error while checking visibility of the element: " + element + " - " + e.getMessage());
            return false; // Return false if an error occurs
        }
    }

    //to click on a particular element on a list
    public void selectFromDropdown(By element, String clientName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(element));

            List<WebElement> options = dropdown.findElements(By.tagName("li"));

            for (WebElement option : options) {
                String text = option.findElement(By.className("itemTemplateLabel_dqr75c")).getText();
                if (text.equals(clientName)) {
                    option.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while selecting from dropdown: " + element + " - " + e.getMessage());
        }
    }

    public void selectYearDiv(String desiredYear) {
        WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option' and text()='" + desiredYear + "']")));
        yearElement.click();
    }


    //enter multiple inputs on multiple  element of same type
    public void multipleInputs(By locator, String text) {
        // Wait until all matching elements are visible
        List<WebElement> textareas = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

        for (WebElement textarea : textareas) {
            textarea.clear(); // Optional: Clear existing text
            textarea.sendKeys(text);
        }
    }
}
