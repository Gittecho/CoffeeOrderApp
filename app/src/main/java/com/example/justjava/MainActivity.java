package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*increment and send the no of order*/
    public void increment(View view) {
        if (a==100){
            Toast.makeText(this,
                    R.string.greater,
                    Toast.LENGTH_SHORT).show();
            return ;
        }
        a = a + 1;
        display(a);
    }
    /*decrement and send the no of order*/
    public void decrement(View view) {
        if(a==0){
            Toast.makeText(this,
                    R.string.lesser,
                    Toast.LENGTH_SHORT).show();
            return ;
        }
        a = a - 1;
        display(a);
    }
    /*The calling function from xml*/
    @SuppressLint("QueryPermissionsNeeded")
    public void SubmitOrder(View view) {
        EditText textfield = (EditText) findViewById(R.id.text_field);
        String text = textfield.getText().toString();

        CheckBox whippbox =  (CheckBox) findViewById(R.id.itemClicke);
        boolean haswjipbox = whippbox.isChecked();


        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        int price = calprice(haswjipbox,hasChocolate);
        String priceMessage = createOrderSummary(text,price,haswjipbox ,hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.subject));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//       displaymessage(priceMessage);
    }


    /*price calculation */
   private int calprice (boolean addwip,boolean addchoco){
        int basePrice = 5;
        if (addwip){
            basePrice = basePrice+1;
        }
        if (addchoco){
            basePrice = basePrice+2;
        }
       return a * basePrice ;
    }
    /**
     * Create summary of the order.
     *
     * @param name name text
     * @param c          of the order
     * @param tof is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */

    @SuppressLint("StringFormatInvalid")
    private String createOrderSummary(String name, int c, boolean tof, boolean addChocolate){
        String  b = getString(R.string.names,name);
        b += getString(R.string.amount,a);
        b += getString(R.string.addwhip,tof) ;
        b += getString(R.string.addchoco,addChocolate);
        b += getString(R.string.total,c);
        b += getString(R.string.thanks);
        return b;
    }
    /*This display the given no of orders*/
    @SuppressLint("SetTextI18n")
    private void display(int i) {
        TextView quantityTextView = findViewById(R.id.numbers);
        quantityTextView.setText("" + i);
    }
    /*
     * This method displays the given string on the screen.
     */


//    @SuppressLint("SetTextI18n")
//    private void displaymessage(String message) {
//        TextView showmsg = findViewById(R.id.ordersumry);
//        showmsg.setText(message);
//
//    }

}