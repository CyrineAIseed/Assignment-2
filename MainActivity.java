package calculator.example.myapplication;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements OnClickListener {

    public TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@###########");

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculatorBrain = new CalculatorBrain();
        mCalculatorDisplay = (TextView) findViewById(R.id.txtView);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);

        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMin).setOnClickListener(this);
        findViewById(R.id.btnMul).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
        findViewById(R.id.btnN).setOnClickListener(this);

        findViewById(R.id.btnEqual).setOnClickListener(this);
        findViewById(R.id.btnC).setOnClickListener(this);
        findViewById(R.id.btnMC).setOnClickListener(this);
        findViewById(R.id.btnMplus).setOnClickListener(this);

        findViewById(R.id.btnMr).setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            if (userIsInTheMiddleOfTypingANumber) {

                mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }

            mCalculatorBrain.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));

        }
        if(buttonPressed.equals("BACK")){


        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("OPERAND", mCalculatorBrain.getResult());
        outState.putDouble("MEMORY", mCalculatorBrain.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCalculatorBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculatorBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
    }
    public void onclick_back(View v){
        Button back= (Button) findViewById(R.id.btnBack);
        TextView text= (TextView) findViewById(R.id.txtView);
        if(text.getText().toString().length()>0){
            text.setText(text.getText().toString().substring(0,text.getText().toString().length()-1));
        }

    }
}