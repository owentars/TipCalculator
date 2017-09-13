package me.owentarswell.tipcalculator;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;



public class MainActivity extends AppCompatActivity implements OnEditorActionListener {

    private EditText billAmount;
    private TextView percentTextView;
    private Button percentUpBtn;
    private Button percentDwnBtn;
    private TextView tipText;
    private TextView totalText;

    private float tipPercent = .15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = (EditText) findViewById(R.id.amtEditText);
        percentTextView = (TextView) findViewById(R.id.tipPerc);
        percentUpBtn = (Button) findViewById(R.id.percentUp);
        percentDwnBtn = (Button) findViewById(R.id.percentDown);
        totalText = (TextView) findViewById(R.id.totalText);
        tipText = (TextView) findViewById(R.id.tipText);


        billAmount.setOnEditorActionListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void percentUpDown(View view) {
        switch (view.getId()){
            case R.id.percentDown:
                tipPercent = tipPercent - 0.01f;
                calculateAndDisplay();
                break;
            case R.id.percentUp:
                tipPercent = tipPercent + 0.01f;
                calculateAndDisplay();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calculateAndDisplay(){

        String billAmountString = billAmount.getText().toString();
        float billAmount;
        if (billAmountString.equals("")){
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;


        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipText.setText(currency.format(tipAmount));
        totalText.setText(currency.format(totalAmount));
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }
}
