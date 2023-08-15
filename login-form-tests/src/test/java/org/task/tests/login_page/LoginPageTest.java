package org.task.tests.login_page;

import io.qameta.allure.*;
import org.task.forms.pages.LoginPage;
import org.task.forms.pages.ProfilePage;
import org.task.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Owner("German Suslov")
@Feature("Тестирование страницы логина")
public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;
    @BeforeMethod
    public void openLoginPage() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @Story("Вход с правильными учетными данными")
    @Description("1. Перейти на страницу логина - Страница логина открыта\n" +
            "2. Ввести корректный email (test@protei.ru) и пароль (test), нажать кнопку \"Вход\" - " +
            "Страница профиля открыта")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void validCredentialsSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();

        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.isOpened(), "Страница профиля не открыта");
    }

    @Story("Вход с корректными данными с пробелами в email")
    @Description("1. Перейти на страницу логина - Страница логина открыта\n" +
            "2. Ввести email с пробелами в начале и конце (\" test@protei.ru \").\n" +
            "3. Ввести корректный пароль (test), нажать кнопку \"Вход\" - " +
            "Появляется предупреждение о неверном формате email")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void validCredentialsWithSpacesSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail(" test@protei.ru ");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidEmailFormatWarningVisible(),
                "Предупреждение о неверном формате email не отображено");
    }

    @Story("Вход с верхним регистром в учетных данных")
    @Description("1. Перейти на страницу логина - Страница логина открыта\n" +
            "2. Ввести email в верхнем регистре (TEST@PROTEI.RU) и пароль в верхнем регистре (TEST), " +
            "нажать кнопку \"Вход\" -Появляется предупреждение о неверных Email или пароле.")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void upperCaseValidCredentialsSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("TEST@PROTEI.RU");
        loginPage.enterPassword("TEST");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidCredentialsWarningVisible(),
                "Предупреждение о неверном email или пароле не отображено");
    }

    @Story("Вход с неверным паролем")
    @Description("1. Перейти на страницу логина - Страница логина открыта\n" +
            "2. Ввести корректный email (test@protei.ru) и неверный пароль (\"invalid password\"), " +
            "нажать кнопку \"Вход\" - Появляется предупреждение о неверных Email или пароле.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void invalidPasswordSignInTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("invalid password");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidCredentialsWarningVisible(),
                "Предупреждение о неверном email или пароле не отображено");
    }

    @Story("Вход с неверным форматом email")
    @Description("1. Перейти на страницу логина - Страница логина открыта\n" +
            "2. Ввести неверный формат email (\"invalid email format\") и корректный пароль (test), " +
            "нажать кнопку \"Вход\" - Появляется предупреждение о неверном формате email.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void invalidEmailFormatTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.enterEmail("invalid email format");
        loginPage.enterPassword("test");
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidEmailFormatWarningVisible(),
                "Предупреждение о неверном формате email не отображено");
    }

    @Story("Вход без заполнения учетных данных")
    @Description("1. Перейти на страницу логина - Страница логина открыта\n" +
            "2. Не заполнять поля email и пароль, нажать кнопку \"Вход\" - " +
            "Появляется предупреждение о неверном формате email.")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void emptyCredentialsTest() {
        Assert.assertTrue(loginPage.isOpened(), "Страница логина не открыта");

        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isInvalidEmailFormatWarningVisible(),
                "Предупреждение о неверном формате email не отображено");
    }
}
