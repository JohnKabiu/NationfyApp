package com.onetouch.tenant.emanyatta;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.apps.nationfy.R;
import com.braintreepayments.cardform.view.CardForm;

public class CardPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .actionLabel("Purchase")
                .setup(this);

        cardForm.getCardNumber();
        cardForm.getExpirationMonth();
        cardForm.getExpirationYear();
        cardForm.getCvv();
        cardForm.getCardholderName();
        cardForm.getPostalCode();
        cardForm.getCountryCode();
        cardForm.getMobileNumber();

        cardForm.isCardScanningAvailable();
        cardForm.scanCard(this);
    }
}
