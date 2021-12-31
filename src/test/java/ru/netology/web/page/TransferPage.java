package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static java.awt.SystemColor.text;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement pushButton = $("[data-test-id='action-transfer']");
    private SelenideElement error = $(".notification__icon");

    public TransferPage() {
        amountField.shouldBe(visible);
    }

    public DashboardPage updateBalance(int amountValue, DataHelper.CardInfo cardInfo) {
        amountField.setValue(String.valueOf(amountValue));
        fromField.setValue(cardInfo.getCardNumber());
        pushButton.click();
        return new DashboardPage();
    }

    //Не понимаю, как его реализовать
    public boolean error() {
        if (amountField > dashboardPage.extractBalance(String text) ) {
            error.shouldBe(appear).shouldHave(text("Ошибка"));
        }
        return false;
    }

}

