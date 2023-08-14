package org.task.tests.login_page;

import org.task.forms.pages.LoginPage;
import org.task.forms.pages.ProfilePage;
import org.task.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;
    @BeforeMethod
    public void openLoginPage() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @Test
    public void validCredentialsSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();

        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.isOpened(), "Страница профиля не открыта");
    }

    @Test
    public void validCredentialsWithSpacesSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail(" test@protei.ru ");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidEmailFormatWarningVisible(),
                "Предупреждение о неверном формате email не отображено");
    }

    @Test
    public void upperCaseValidCredentialsSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("TEST@PROTEI.RU");
        loginPage.enterPassword("TEST");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidCredentialsWarningVisible(),
                "Предупреждение о неверном email или пароле не отображено");
    }

    @Test
    public void invalidPasswordSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("invalid password");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidCredentialsWarningVisible(),
                "Предупреждение о неверном email или пароле не отображено");
    }

    @Test
    public void invalidEmailFormatTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("invalid email format");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidEmailFormatWarningVisible(),
                "Предупреждение о неверном формате email не отображено");
    }

    @Test
    public void emptyCredentialsTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidEmailFormatWarningVisible(),
                "Предупреждение о неверном формате email не отображено");
    }
}
