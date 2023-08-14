package org.task.forms;

import lombok.Getter;
import org.task.elements.BaseElement;
import org.task.utils.Browser;
import org.task.utils.ResourcesDataProvider;
import org.task.utils.Waiter;

public class BaseForm {
    protected Waiter waiter;
    @Getter
    private final BaseElement uniqueElement;

    public BaseForm(BaseElement uniqueElement) {
        this.waiter = Waiter.getWaiter(Browser.getDriver(), ResourcesDataProvider.getWaitTime());
        this.uniqueElement = uniqueElement;
    }

    public boolean isOpened() {
        waiter.waitForElementToBeVisible(uniqueElement);
        return uniqueElement.isDisplayed();
    }
}
