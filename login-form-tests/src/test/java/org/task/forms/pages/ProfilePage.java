package org.task.forms.pages;

import org.openqa.selenium.By;
import org.task.elements.*;
import org.task.forms.BaseForm;

public class ProfilePage extends BaseForm {
    private final Button addButton = new Button("Кнопка 'добавить'",
            By.xpath("//button[@id = 'dataSend']"));
    private final Field inputEmailField = new Field("Поле ввода email",
            By.xpath("//input[@id = 'dataEmail']"));

    private final Field inputNameField = new Field("Поле ввода имени",
            By.xpath("//input[@id = 'dataName']"));
    private final Dropdown genderDropdown = new Dropdown("Выпадающий список 'пол'",
            By.xpath("//select[@id = 'dataGender']"));
    private final Table customerList = new Table("Список пользователей",
            By.xpath("//tbody//tr"));
    private final Button closePopUpButton = new Button("Кнопка 'Ок' на всплывающем окне",
            By.xpath("//button[contains(@class, 'uk-modal-close')]"));
    private Button genderButton;
    private CheckBox checkBox;
    private Radio radio;

    public ProfilePage() {
        super(new Button("Кнопка 'добавить'",
                By.xpath("//button[@id = 'dataSend']")));
    }

    public void enterEmail(String email) {
        waiter.waitForElementToBeVisible(inputEmailField);
        inputEmailField.enterText(email);
    }

    public void clickRadio(String number) {
        number = number.replaceAll("\\D", "");
        radio = new Radio("Вариант №" + number,
                By.xpath(String.format("//input[@id = 'dataSelect%s']", number)));
        waiter.waitForElementsToBeVisible(radio);
        radio.click();
    }

    public void clickCheckBox(String number) {
        number = number.replaceAll("\\D", "");
        checkBox = new CheckBox("Вариант №" + number,
                By.xpath(String.format("//input[@id = 'dataCheck%s']", number)));
        waiter.waitForElementsToBeVisible(checkBox);
        checkBox.click();
    }

    public String getCustomersListText() {
        waiter.waitForElementsToBeVisible(customerList);
        return customerList.getElement().getText();
    }

    public boolean isCustomersListEmpty() {
        waiter.waitForElementsToBeVisible(customerList);
        return customerList.getElements().isEmpty();
    }

    public void enterName(String name) {
        waiter.waitForElementToBeVisible(inputNameField);
        inputNameField.enterText(name);
    }

    public void clickAddButton() {
        waiter.waitForElementToBeVisible(addButton);
        addButton.click();
    }

    public void clickGenderDropdown() {
        waiter.waitForElementToBeVisible(genderDropdown);
        genderDropdown.click();
    }

    public void clickGenderButton(String gender) {
        genderButton = new Button(gender,
                By.xpath("//select[@id = 'dataGender']//option[contains(text(), 'Женский')]"));
        waiter.waitForElementToBeVisible(genderButton);
        genderButton.click();
    }

    public void clickClosePopUpButton() {
        waiter.waitForElementToBeVisible(closePopUpButton);
        closePopUpButton.click();
    }

    public boolean isPopUpVisible() {
        waiter.waitForElementToBeVisible(closePopUpButton);
        return closePopUpButton.isDisplayed();
    }
}
