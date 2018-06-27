package qazaqkeyboard.kz.aygolek.abcnetkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_select_kbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_select_kbd=findViewById(R.id.btn_select_kbd);
        btn_select_kbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager =
                        (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
            }
        });

    }
}
