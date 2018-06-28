package qazaqkeyboard.kz.aygolek.abcnetkeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.TextView;

public class Select_kbd_layout extends AppCompatActivity {

    private Button btn_select_kbd;
    private TextView selected_kbd_txtview;
    private InputMethodSubtype currentInputMethodSubtype;

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
                if (imeManager != null) {
                    imeManager.showInputMethodPicker();
                }


            }
        });

        selected_kbd_txtview=findViewById(R.id.selected_kbd);
        refreshKbdSelectionName(100);

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        refreshKbdSelectionName(3);
    }



    private void refreshKbdSelectionName(int i){

        Log.v("abcnet saule", Integer.toString(i));
        InputMethodManager imeManager =
                (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);

        if (imeManager!=null) {
            String str = new String();
            currentInputMethodSubtype = imeManager.getCurrentInputMethodSubtype();

            if (currentInputMethodSubtype.getLocale().equals("abcnet.kz")) {
                btn_select_kbd.setClickable(false);
                btn_select_kbd.setEnabled(false);
                selected_kbd_txtview.setText("Қазақ латын пернетақтасы орнатылған");
            }
        }

    }
}
