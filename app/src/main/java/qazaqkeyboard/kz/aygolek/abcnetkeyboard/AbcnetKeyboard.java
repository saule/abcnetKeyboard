package qazaqkeyboard.kz.aygolek.abcnetkeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class AbcnetKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardViewMine kv;
    private Keyboard keyboard;
    private boolean isQwerty=true;
    private boolean isKZ = true;
    private  boolean isCaps = false;
    private int beforeKey=-1000;
    private boolean capsAll=false;

    private KeyboardViewMine keyboardViewMine;
    //Press Ctrl+O


    @Override
    public View onCreateInputView() {
        // kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard,null);
        kv = (KeyboardViewMine)getLayoutInflater().inflate(R.layout.keyboard,null);
        keyboard = new Keyboard(this,R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        System.out.println("tttttt");
        //  keyboardViewMine = (KeyboardViewMine)getLayoutInflater().inflate(R.layout.keyboard,null);
        return kv;
    }


    /**
     * Helper to send a key down / key up pair to the current editor.
     */
    private void keyDownUp(int keyEventCode) {
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int i, int[] ints) {
        System.out.println("onKey");
        InputConnection ic = getCurrentInputConnection();
        playClick(i);
        switch (i)
        {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                System.out.println("shift");
                isCaps = !isCaps;
                capsAll=false;
                //keyboardViewMine.onDraw(canvas???);
                keyboard.setShifted(isCaps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                System.out.println("Keyboard.KEYCODE_DONE == "+Keyboard.KEYCODE_DONE);
                // ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
                /** Handle the 'done' action accordingly to the IME Options. */

                EditorInfo curEditor = getCurrentInputEditorInfo();
                switch (curEditor.imeOptions & EditorInfo.IME_MASK_ACTION) {
                    case EditorInfo.IME_ACTION_DONE:
                        keyDownUp(66);
                        break;
                    case EditorInfo.IME_ACTION_GO:
                        getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_GO);
                        break;
                    case EditorInfo.IME_ACTION_NEXT:
                        keyDownUp(66);
                        break;
                    case EditorInfo.IME_ACTION_SEARCH:
                        getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_SEARCH);
                        break;
                    case EditorInfo.IME_ACTION_SEND:
                        keyDownUp(66);
                        break;
                    default:
                        keyDownUp(66);
                        break;
                }
                break;

            case -2:
                if (isQwerty) {
                    keyboard = new Keyboard(this,R.xml.symbols);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty=false;
                    break;
                }
                else{
                    keyboard = new Keyboard(this, R.xml.qwerty);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty=true;
                    break;
                }

            case -3:
                if (isKZ) {
                    keyboard = new Keyboard(this, R.xml.qwerty);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty = true;
                    break;
                } else
                {
                    keyboard = new Keyboard(this, R.xml.russianalphabet);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isQwerty = true;
                    break;
                }
            case -7:
                if (isKZ) {
                    keyboard = new Keyboard(this,R.xml.russianalphabet);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isKZ=false;
                    break;
                }
                else{
                    keyboard = new Keyboard(this, R.xml.qwerty);
                    kv.setKeyboard(keyboard);
                    kv.invalidateAllKeys();
                    isKZ=true;
                    break;
                }
                //-100 if caps lock long press
                //turn caps lock on
            case -100:
                System.out.println("caps on -100");
                capsAll=true;
                isCaps=true;
                keyboard.setShifted(isCaps);
                kv.invalidateAllKeys();
                break;
            //32 is a space key
            //if end of sentence turn autocapitalisation
            case 32:
                if(beforeKey==46){
                    isCaps=true;
                    beforeKey=32;
                    keyboard.setShifted(isCaps);
                    kv.invalidateAllKeys();
                }
            default:
                char code = (char)i;
                if(Character.isLetter(code) && isCaps) {
                    code = Character.toUpperCase(code);
                    if(!capsAll) {
                        isCaps = !isCaps;
                        //keyboardViewMine.onDraw(canvas???);
                        keyboard.setShifted(isCaps);
                        kv.invalidateAllKeys();
                    }
                }
                beforeKey=i;
                ic.commitText(String.valueOf(code),1);
        }

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void playClick(int i) {

        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(i)
        {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }


}
