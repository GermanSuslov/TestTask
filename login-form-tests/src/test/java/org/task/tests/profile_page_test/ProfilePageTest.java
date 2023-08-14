package org.task.tests.profile_page_test;

import org.task.forms.pages.LoginPage;
import org.task.forms.pages.ProfilePage;
import org.task.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        Assert.assertFalse(profilePage.isCustomersListEmpty(), "Список пользователей пустой");
    }

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
        Assert.assertTrue(text.contains("1.2"), "Список пользователей не содержит искомой информации");
        Assert.assertTrue(text.contains("Женский"), "Список пользователей не содержит искомой информации");
        Assert.assertTrue(text.contains("New"), "Список пользователей не содержит искомой информации");
        Assert.assertTrue(text.contains("new@maill"), "Список пользователей не содержит искомой информации");
    }
}
