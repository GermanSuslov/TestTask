package org.task.forms.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.task.elements.Button;
import org.task.elements.Field;
import org.task.elements.Warning;
import org.task.forms.BaseForm;
import org.task.utils.DriverUtils;
import org.task.utils.ResourcesDataProvider;

public class LoginPage extends BaseForm {
    private final Field inputEmailField = new Field("Поле ввода email",
            By.xpath("//input[@id = 'loginEmail']"));
    private final Field inputPasswordField = new Field("Поле ввода пароля",
            By.xpath("//input[@id = 'loginPassword']"));
    private final Button signInButton = new Button("Кнопка вход",
            By.xpath("//button[@id = 'authButton']"));
    private final Warning invalidEmailFormatWarning = new Warning("Окно 'Неверный формат email'",
            By.xpath("//div[@id = 'emailFormatError']"));
    private final Warning invalidCredentialsWarning = new Warning("Окно 'Неверный E-Mail или пароль'",
            By.xpath("//div[@id = 'invalidEmailPassword']"));

    public LoginPage() {
        super(new Field("Поле ввода пароля",
                By.xpath("//input[@id = 'loginPassword']")));
    }

    @Step("Ввести email")
    public void enterEmail(String email) {
        waiter.waitForElementToBeVisible(inputEmailField);
        inputEmailField.enterText(email);
    }

    public boolean isInvalidEmailFormatWarningVisible() {
        waiter.waitForElementToBeVisible(invalidEmailFormatWarning);
        return invalidEmailFormatWarning.isDisplayed();
    }

    public boolean isInvalidCredentialsWarningVisible() {
        waiter.waitForElementToBeVisible(invalidCredentialsWarning);
        return invalidCredentialsWarning.isDisplayed();
    }

    public void enterPassword(String password) {
        waiter.waitForElementToBeVisible(inputPasswordField);
        inputPasswordField.enterText(password);
    }

    public void clickSignInButton() {
        waiter.waitForElementToBeVisible(signInButton);
        signInButton.click();
    }

    public void open() {
        DriverUtils.navigateToFile(ResourcesDataProvider.getStartUrl());
    }
}
