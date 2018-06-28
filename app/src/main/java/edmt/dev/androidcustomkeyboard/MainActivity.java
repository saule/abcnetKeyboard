package edmt.dev.androidcustomkeyboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.ShareActionProvider;

import edmt.dev.androidcustomkeyboard.R;

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
                Intent intent = new Intent(MainActivity.this, Select_kbd_activity.class);
                startActivity(intent);
            }
        });

        shareIntent.setType("text/plain");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

        String extra_text ="Sálem. Myna applicationdi ózine ornatsang bolady - Qazaq Latyn pernetaqtasy. \n";
                extra_text+="https://play.google.com/store/apps/details?id=" + appPackageName;

        shareIntent.putExtra(Intent.EXTRA_TEXT,extra_text);

        createShortcutIcon();
    }


    final static public String PREFS_NAME = "PREFS_NAME";
    final static private String PREF_KEY_SHORTCUT_ADDED = "PREF_KEY_SHORTCUT_ADDED";

    // Creates shortcut on Android widget screen
    private void createShortcutIcon(){

        // Checking if ShortCut was already added
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        boolean shortCutWasAlreadyAdded = sharedPreferences.getBoolean(PREF_KEY_SHORTCUT_ADDED, false);
        if (shortCutWasAlreadyAdded) return;

        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Qaz Latyn keyboard");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.icon_qaz_kbd));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);

        // Remembering that ShortCut was already added
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_KEY_SHORTCUT_ADDED, true);
        editor.commit();
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


}
