package com.bluenile.testkit.pages.desktop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static com.bluenile.testkit.base.BrowserDriverFactory.getDriver;
import static com.bluenile.testkit.locators.YoutubeLocators.*;

public class YoutubePage {


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