package sg.edu.rp.c346.id20022678.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView totalBillDisplay;
    TextView eachPaysDisplay;
    Button splitBtn;
    Button resetBtn;
    EditText amtDollarInput;
    EditText amtPaxInput;
    EditText discountInput;
    ToggleButton svstbtn;
    ToggleButton gsttbtn;
    RadioGroup rgMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalBillDisplay = findViewById(R.id.totalbill);
        eachPaysDisplay = findViewById(R.id.eachpays);
        splitBtn = findViewById(R.id.split);
        resetBtn = findViewById(R.id.reset);
        amtDollarInput = findViewById(R.id.AmountEdit);
        amtPaxInput = findViewById(R.id.PaxEdit);
        discountInput = findViewById(R.id.DiscountEdit);
        svstbtn = findViewById(R.id.Sys);
        gsttbtn = findViewById(R.id.GSt);
        rgMethod = findViewById(R.id.RadioGroupPaymentMethond);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalBillDisplay.setText("");
                eachPaysDisplay.setText("");
                amtDollarInput.setText("");
                amtPaxInput.setText("");
                svstbtn.setChecked(false);
                gsttbtn.setChecked(false);
                rgMethod.clearCheck();
            }
        });

        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalAmount = amtDollarInput.getText().toString();
                double finalTotalAmount = Double.parseDouble(totalAmount);
                double endTotalBill = finalTotalAmount;
                double splitAmount = 0;
                String splitAmounts = "";
                int checkRadioId = rgMethod.getCheckedRadioButtonId();
                if ((svstbtn.isChecked() == true) && (gsttbtn.isChecked() == false)) {
                    endTotalBill = finalTotalAmount * 1.1;
                } else if ((gsttbtn.isChecked() == true) && (svstbtn.isChecked() == false)) {
                    endTotalBill = finalTotalAmount * 1.07;
                } else if ((gsttbtn.isChecked() == true) && (svstbtn.isChecked() == true)) {
                    endTotalBill = (finalTotalAmount * 1.1) * 1.07;
                }
                if (discountInput.getText().toString().isEmpty()){
                }
                else {
                    String dicountAmount = discountInput.getText().toString();
                    double finalDiscountAmount = Double.parseDouble(dicountAmount);
                    endTotalBill = endTotalBill - ((endTotalBill * finalDiscountAmount) /100);
                }
                if (amtPaxInput.getText().toString().isEmpty() == true){
                    splitAmount = (endTotalBill/1);
                }
                else {
                    String paxAmount= amtPaxInput.getText().toString();
                    double finalPaxAmount=Double.parseDouble(paxAmount);
                    splitAmount = (endTotalBill/finalPaxAmount);
                }
                String finalSplit = String.format("%.2f", splitAmount);
                String finalTotalBill = String.format("%.2f",endTotalBill);
                if (checkRadioId == R.id.Paynow){
                    splitAmounts = finalSplit + " via PayNow to 912345678";
                }
                else {
                    splitAmounts = finalSplit + " in cash";
                }
                totalBillDisplay.setText(finalTotalBill);
                eachPaysDisplay.setText(splitAmounts);
            }
        });
    }
}