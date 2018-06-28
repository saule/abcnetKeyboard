package qazaqkeyboard.kz.aygolek.abcnetkeyboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.support.v7.widget.ShareActionProvider;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider=null;

    private Intent shareIntent = new Intent(Intent.ACTION_SEND);

    private Button btn_select_kbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_select_kbd=findViewById(R.id.btn_select_kbd);
        btn_select_kbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Select_kbd_layout.class);
                startActivity(intent);
            }
        });

        shareIntent.setType("text/plain");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.putExtra(Intent.EXTRA_TEXT,"https://bit.ly/2rSy9px");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailfragment, menu);
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


}
