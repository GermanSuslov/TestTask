package org.task.elements;

import org.openqa.selenium.By;

public class Field extends BaseElement{
    public Field(String elementName, By locator) {
        super(elementName, locator);
    }

    public void enterText(String text) {
        getElement().sendKeys(text);
    }
}
