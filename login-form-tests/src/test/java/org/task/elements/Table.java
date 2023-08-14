package org.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.task.utils.DriverUtils;

import java.util.List;

public class Table extends BaseElement{
    public Table(String elementName, By locator) {
        super(elementName, locator);
    }

    public List<WebElement> getElements() {
        List<WebElement> elements = null;
        try {
            elements = DriverUtils.findElements(getLocator());
        } catch (NoSuchElementException e) {
            logger.error("Can not find element - '" + getElementName() + "' \nby locator " + getLocator());
        }
        return elements;
    }
}
