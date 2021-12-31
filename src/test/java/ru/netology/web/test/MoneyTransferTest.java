package ru.netology.web.test;

import lombok.val;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromFirstCard() {
        val dashboardPage = new DashboardPage();
        int amountValue = 1000;

        val expectedResultFirstCard = dashboardPage.getFirstCardBalance() - amountValue;
        val expectedResultSecondCard = dashboardPage.getSecondCardBalance() + amountValue;
        // пополнить
        val transferPage = dashboardPage.secondCardDeposit();
        transferPage.updateBalance(amountValue, DataHelper.getFirstCardNumber());
        val actualResultsFirstCard = dashboardPage.getFirstCardBalance();
        val actualResultsSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedResultFirstCard, actualResultsFirstCard);
        assertEquals(expectedResultSecondCard, actualResultsSecondCard);
    }

    @Test
    void shouldTransferMoneyFromSecondCard() {
        val dashboardPage = new DashboardPage();
        int amountValue = 100;

        val expectedResultSecondCard = dashboardPage.getSecondCardBalance() - amountValue;
        val expectedResultFirstCard = dashboardPage.getFirstCardBalance() + amountValue;
        // пополнить
        val transferPage = dashboardPage.firstCardDeposit();
        transferPage.updateBalance(amountValue, DataHelper.getSecondCardNumber());
        val actualResultsFirstCard = dashboardPage.getFirstCardBalance();
        val actualResultsSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedResultFirstCard, actualResultsFirstCard);
        assertEquals(expectedResultSecondCard, actualResultsSecondCard);
    }

    @Test
    void shouldTransferMoneyFromSecondCardMoreLimit() {
        val dashboardPage = new DashboardPage();
        int amountValue = 20000;

        val expectedResultSecondCard = dashboardPage.getSecondCardBalance() - amountValue;
        val expectedResultFirstCard = dashboardPage.getFirstCardBalance() + amountValue;
        // пополнить
        val transferPage = dashboardPage.firstCardDeposit();
        transferPage.updateBalance(amountValue, DataHelper.getSecondCardNumber());
        val actualResultsFirstCard = dashboardPage.getFirstCardBalance();
        val actualResultsSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedResultFirstCard, actualResultsFirstCard);
        assertEquals(expectedResultSecondCard, actualResultsSecondCard);
        transferPage.getError();
    }
}