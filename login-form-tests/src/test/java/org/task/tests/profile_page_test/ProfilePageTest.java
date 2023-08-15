package org.task.tests.profile_page_test;

import io.qameta.allure.*;
import org.task.forms.pages.LoginPage;
import org.task.forms.pages.ProfilePage;
import org.task.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Owner("German Suslov")
@Feature("Тестирование страницы профиля")
public class ProfilePageTest extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;
    @BeforeMethod
    public void openLoginPage() {
        loginPage = new LoginPage();
        loginPage.open();
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();
        profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.isOpened(), "Страница профиля не открыта");
    }

    @Story("Добавление нового пользователя")
    @Description("1. Перейти на страницу логина - Страница логина открыта.\n" +
            "2. Ввести корректный email (test@protei.ru) и пароль (test), нажать кнопку \"Вход\" " +
            "- Страница профиля открыта.\n" +
            "3. Ввести новый email (new@mail.ru), Ввести имя \"New\", Выбрать гендер \"Женский\", " +
            "Выбрать чекбокс \"Вариант 1.1\", Выбрать чекбокс \"Вариант 1.2\", Выбрать радио \"Вариант 2.2\", " +
            "Нажать кнопку \"Добавить\" - Всплывающее окно отображено.\n" +
            "4. Закрыть всплывающее окно - Всплывающее окно успешно закрыто.\n" +
            "5. Проверить, что список пользователей не пустой - Список пользователей не пустой.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void addNewCustomerTest() {
        profilePage.enterEmail("new@mail.ru");
        profilePage.enterName("New");
        profilePage.clickGenderButton("Женский");
        profilePage.clickCheckBox("Вариант 1.1");
        profilePage.clickCheckBox("Вариант 1.2");
        profilePage.clickRadio("Вариант 2.2");
        profilePage.clickAddButton();
        Assert.assertTrue(profilePage.isPopUpVisible(), "Всплывающее окно не отображено");

        profilePage.clickClosePopUpButton();
        Assert.assertFalse(profilePage.isPopUpVisible(), "Всплывающее окно отображено");
        Assert.assertFalse(profilePage.isCustomersListEmpty(), "Список пользователей пустой");
    }

    @Story("Проверка информации о новом пользователе")
    @Description("1. Перейти на страницу логина - Страница логина открыта.\n" +
            "2. Ввести корректный email (test@protei.ru) и пароль (test), нажать кнопку \"Вход\" - " +
            "Страница профиля открыта.\n" +
            "3. Ввести новый email (new@mail.ru), ввести имя \"New\", выбрать гендер \"Женский\", выбрать " +
            "чекбокс \"Вариант 1.1\", выбрать чекбокс \"Вариант 1.2\", выбрать радио \"Вариант 2.1\", " +
            "нажать кнопку \"Добавить\" - Всплывающее окно отображено.\n" +
            "4. Закрыть всплывающее окно - Всплывающее окно успешно закрыто.\n" +
            "5. Проверить, что список пользователей не пустой - Список пользователей не пустой, проверить, " +
            "что текст содержит \"1.2, 2.1, Женский, New, new@mail\".")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void newCustomerInfoTest() {
        profilePage.enterEmail("new@mail.ru");
        profilePage.enterName("New");
        profilePage.clickGenderButton("Женский");
        profilePage.clickCheckBox("Вариант 1.1");
        profilePage.clickCheckBox("Вариант 1.2");
        profilePage.clickRadio("Вариант 2.1");
        profilePage.clickAddButton();
        Assert.assertTrue(profilePage.isPopUpVisible(), "Всплывающее окно не отображено");

        profilePage.clickClosePopUpButton();
        Assert.assertFalse(profilePage.isCustomersListEmpty(), "Список пользователей пустой");

        String text = profilePage.getCustomersListText();
        Assert.assertTrue(text.contains("1.2"), "Список пользователей не содержит значений 1.2");
        Assert.assertTrue(text.contains("2.1"), "Список пользователей не содержит значений 2.1");
        Assert.assertTrue(text.contains("Женский"), "Список пользователей не содержит значений Женский");
        Assert.assertTrue(text.contains("New"), "Список пользователей не содержит значений New");
        Assert.assertTrue(text.contains("new@mail"), "Список пользователей не содержит значений new@maill");
    }
}
