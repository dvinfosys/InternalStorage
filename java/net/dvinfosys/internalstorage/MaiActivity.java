package net.dvinfosys.internalstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    EditText food,shopping,fuel,phone;
    TextView txtprevExpence;
    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(saexample.vaksys_49example.vaksys_49example.vaksys_49vedInstanceState);
        setContentView(R.layout.activity_main);


        food= (EditText) findViewById(R.id.etfood);
        shopping= (EditText) findViewById(R.id.etshopping);
        fuel= (EditText) findViewById(R.id.etfuel);
        phone= (EditText) findViewById(R.id.etphone);
        txtprevExpence= (TextView) findViewById(R.id.prevExpence);



        calculate= (Button) findViewById(R.id.btncalculate);
        readData();
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int expence= Integer.valueOf(food.getText().toString())+Integer.valueOf(shopping.getText().toString()) + Integer.valueOf(fuel.getText().toString()) + Integer.valueOf(phone.getText().toString());
                Toast.makeText(MainActivity.this, "Your Expence Total Is : "+String.valueOf(expence), Toast.LENGTH_SHORT).show();

                //Saving Value Of SharedPrefernces

                txtprevExpence.setText("Previous Expence Is : "+String.valueOf(expence));
                saveData();
            }
        });
    }
    public void saveData()
    {
        try {
            FileOutputStream fileOutputStream=openFileOutput("expence.txt",MODE_PRIVATE);
            fileOutputStream.write(txtprevExpence.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readData()
    {
        try {
            FileInputStream inputStream=openFileInput("expence.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader reader=new BufferedReader(inputStreamReader);

            String data=reader.readLine();
            if (!data.isEmpty()){
            txtprevExpence.setText(data);   }
            else {
                txtprevExpence.setText("0");
            }

        } catch (FileNotFoundException e) {
            txtprevExpence.setText("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
