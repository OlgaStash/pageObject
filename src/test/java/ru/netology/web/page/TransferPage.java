package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement pushButton = $("[data-test-id='action-transfer']");

    public TransferPage() {
        amountField.shouldBe(visible);
    }

    public DashboardPage updateBalance(int amountValue, DataHelper.CardInfo cardInfo){
        amountField.setValue(String.valueOf(amountValue));
        fromField.setValue(cardInfo.getCardNumber());
        pushButton.click();
        return new DashboardPage();
    }

}

