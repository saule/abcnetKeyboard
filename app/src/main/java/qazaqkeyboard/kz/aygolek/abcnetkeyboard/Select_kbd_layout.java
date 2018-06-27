package qazaqkeyboard.kz.aygolek.abcnetkeyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class Select_kbd_layout extends AppCompatActivity {

    private Button btn_select_kbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_kbd);

        btn_select_kbd=findViewById(R.id.btn_configure_kbd);
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
