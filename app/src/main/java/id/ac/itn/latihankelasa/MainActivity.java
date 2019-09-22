package id.ac.itn.latihankelasa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String RESULT_COUNT = "result_count";
    private EditText etPanjang, etLebar, etTinggi;
    private Button btnHitung;
    private TextView hsl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPanjang = findViewById(R.id.etPanjang);
        etLebar = findViewById(R.id.etLebar);
        etTinggi = findViewById(R.id.etTinggi);
        hsl = findViewById(R.id.tvHasil);
        btnHitung = findViewById(R.id.button);
        btnHitung.setOnClickListener(this);
        if(savedInstanceState !=null){
            hsl.setText(savedInstanceState.getString(RESULT_COUNT));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){
            String inputLength = etPanjang.getText().toString().trim();
            String inputWidth = etLebar.getText().toString().trim();
            String inputHeight = etTinggi.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                etPanjang.setError("Panjang harus diisi");
            }
            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                etLebar.setError("Lebar harus diisi");
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                etTinggi.setError("Tinggi harus diisi");
            }
            Double p = toDouble(inputLength);
            Double l = toDouble(inputWidth);
            Double t = toDouble(inputHeight);
            if (p == null) {
                isInvalidDouble = true;
                etPanjang.setError("Panjang harus diisi dengan angka yang valid");
            }
            if (l == null) {
                isInvalidDouble = true;
                etLebar.setError("Lebar harus diisi dengan angka yang valid");
            }
            if (t == null) {
                isInvalidDouble = true;
                etTinggi.setError("Tinggi harus diisi dengan angka yang valid");
            }
            if (!isEmptyFields && !isInvalidDouble) {
                double volume = p * l * t;
                hsl.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String inputLength) {
        try {
            return Double.valueOf(inputLength);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(RESULT_COUNT, hsl.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
