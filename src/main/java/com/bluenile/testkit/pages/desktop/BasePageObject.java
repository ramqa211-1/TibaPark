package com.bluenile.testkit.pages.desktop;
import com.bluenile.testkit.base.BrowserDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class BasePageObject extends BrowserDriverFactory {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");



    public static void openUrl(String URL, int... optionalSleep) {
        refreshPage();
        getDriver().get(URL);
        if (optionalSleep.length > 0) {
            try {
                sleep(optionalSleep[0]);
            } catch (Exception e) {
                System.out.println("The Error: " + e + ".");
            }
        }
    }

    //Find element using given locator
    protected static WebElement find(By locator, int... timeout) throws InterruptedException {
        try {
            return getDriver().findElement(locator);
        } catch (Exception e) {
            int count = 0;
            while (count < 10) {
                sleep(200);
                count++;
            }
            return getDriver().findElement(locator);
        }
    }

    //CLICK MAIN METHOD
    public static void click(By locator, int... timeout) throws InterruptedException {
        find(locator).click();
    }


    public static void typeTextOnTextFieldGlobal(By locator, String textToType )  {
        WebElement element = getDriver().findElement(locator);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click().sendKeys(textToType).build().perform();
    }


    public static void refreshPage() {
        getDriver().navigate().refresh();
    }


    //** SCROLL TO ELEMENT CORE METHOD **//
    public static void scrollToElementAction(By locator, String... textToWait) {
        Actions actions = new Actions(getDriver());
        try {
            if (textToWait.length > 0) {
                WebDriverWait mobile = new WebDriverWait(getDriver(), 30);
                mobile.until((WebDriver dr) ->
                        dr.findElement(locator).getText().contains(textToWait[0]));
            }
            WebElement element = BrowserDriverFactory.getDriver().findElement(locator);
            //Get the current position of the element on the current web page
            Point location = element.getLocation();
            //Get the Width and Height of the element
            Dimension size = element.getSize();
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            //Get the width of the visible area of the web page in the browser
            int viewportWidth = ((Number) js.executeScript("return window.innerWidth || document.documentElement.clientWidth;")).intValue();
            //Get the height of the visible area of the web page in the browser
            int viewportHeight = ((Number) js.executeScript("return window.innerHeight || document.documentElement.clientHeight;")).intValue();
            //Determine if the element is outside the visible area of the current web page in the browser
            boolean isOutOfBounds =
                    //Check if the element's left edge is out of the left side of the visible area
                    location.getX() < 0 ||
                            //Check if the element's top edge is out of the top side of the visible area
                            location.getY() < 0 ||
                            //Check if the element's right edge is out the right side of the visible area
                            location.getX() + size.getWidth() > viewportWidth ||
                            //Check if the element's bottom edge is out of the bottom side of the visible area
                            location.getY() + size.getHeight() > viewportHeight;
            //If the element is out the screen Or is not clickable,
            // we'll use JS scrollIntoView to show the element
            //The {block: 'center'} will center the element on the screen
            boolean elementIsClickable;
            //Check if the element is clickable on the current page
            WebDriverWait checkClickable = new WebDriverWait(getDriver(), 30);
            try {
                checkClickable.until(ExpectedConditions.elementToBeClickable(element));
                elementIsClickable = true;
            } catch (TimeoutException e) {
                elementIsClickable = false;
            }
            //If the element is out the screen or is not clickable, we'll use JS scrollIntoView to show the element
            //The {block: 'center'} will center the element on the screen
            if (isOutOfBounds || elementIsClickable) {
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            } else {
                //We'll use action move to element if isOutOfBounds = false and the elementIsClickable = true
                actions.moveToElement(element).perform();
            }
        } catch (Exception e) {
            System.out.println("Element Not Exist Or Page Is not Loaded. The error is: \n" + e + ".");
        }
    }


}