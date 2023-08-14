package org.task.utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.task.elements.BaseElement;

import java.time.Duration;

public class Waiter {
    private static final Logger logger = LoggerFactory.getLogger(Waiter.class);
    private final WebDriverWait wait;
    private static Waiter waiter;

    private Waiter(WebDriver driver, int timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public static Waiter getWaiter(WebDriver driver, int timeoutInSeconds) {
        if (waiter == null) {
            waiter = new Waiter(driver, timeoutInSeconds);
        }
        return waiter;
    }

    public void waitForElementToBeClickable(BaseElement element) {
        waitUntil(element, ExpectedConditions.elementToBeClickable(element.getLocator()));
    }

    public void waitForElementToBeVisible(BaseElement element) {
        waitUntil(element, ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
    }

    public void waitForElementsToBeVisible(BaseElement element) {
        waitUntil(element, ExpectedConditions.visibilityOfAllElementsLocatedBy(element.getLocator()));
    }

    private void waitUntil(BaseElement element, ExpectedCondition<?> condition) {
        try {
            wait.until(condition);
        } catch (TimeoutException e) {
            logger.error("Can not find '" + element.getElementName() +
                    "' condition \nby locator : " + element.getLocator());
        }
    }

    public static void quitWaiter() {
        waiter = null;
    }
}