package org.task.elements;

import org.openqa.selenium.By;

public class Warning extends BaseElement {
    public Warning(String elementName, By locator) {
        super(elementName, locator);
    }

    public String getText() {
        return getElement().getText();
    }
}
