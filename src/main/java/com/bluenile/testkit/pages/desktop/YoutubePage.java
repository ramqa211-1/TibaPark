package com.bluenile.testkit.pages.desktop;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bluenile.testkit.locators.YoutubeLocators.*;

public class YoutubePage extends BasePageObject {

    public static String getUserChannelName() {
        WebElement text = getDriver().findElement(userChannelName);
        return text.getAttribute("innerHTML");
    }
    public static void clickShowMoreButton() throws InterruptedException {
        click(showMoreButton, 20);
    }

    public static String getArtistsName() {
        WebElement artistElement = getDriver().findElement(artistName);
        String artistName = artistElement.getAttribute("innerHTML");
        return artistName;
    }

    public static void clickSkipAdIfPresentIfSoClickOnIt() {
        List<WebElement> skipAdElements = getDriver().findElements(skidAdButton);
        for (WebElement element : skipAdElements) {
            if (element.isDisplayed()) {
                element.click();
            }
        }
    }
}