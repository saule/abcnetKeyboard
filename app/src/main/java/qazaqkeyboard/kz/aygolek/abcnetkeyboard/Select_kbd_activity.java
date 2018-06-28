package qazaqkeyboard.kz.aygolek.abcnetkeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.TextView;

public class Select_kbd_activity extends AppCompatActivity {

    private Button btn_select_kbd;
    private TextView selected_kbd_txtview;
    private InputMethodSubtype currentInputMethodSubtype;

    private ShareActionProvider shareActionProvider=null;

    private Intent shareIntent = new Intent(Intent.ACTION_SEND);

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

        shareIntent.setType("text/plain");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

        String extra_text ="Sálem. Myna applicationdi ózine ornatsang bolady - Qazaq Latyn pernetaqtasy. \n";
        extra_text+="https://play.google.com/store/apps/details?id=" + "qz.aygolek.englishwordsapp";

        shareIntent.putExtra(Intent.EXTRA_TEXT,extra_text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem shareMenu = menu.findItem(R.id.action_share);

        shareActionProvider= (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenu);
        Log.d("saule","before");
        if(shareActionProvider!=null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
        else {
            Log.d("saule","shareactionporvider null");
        }

        return true;
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

            if (currentInputMethodSubtype.getLocale().equals("qazaq (Qazaqstan)")) {
              //  btn_select_kbd.setClickable(false);
                //btn_select_kbd.setEnabled(false);
                selected_kbd_txtview.setText("Қазақ латын пернетақтасы орнатылған");
            }
        }

    }
}
