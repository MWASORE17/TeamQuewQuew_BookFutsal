package quewquewcrew.appngasal.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Window;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.view.fragment.auth.LoginFragment;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthActivity extends ParentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        changefragment(new LoginFragment());
    }

    public static boolean isemailvalid(String email) {
//        String _expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String _expression = "^[a-z]([a-z0-9-\\.]+)?+@[a-z]+\\.[a-z]{2,4}+(\\.[a-z]{2,4})?$";
        CharSequence _email = email;
        Pattern _pattern = Pattern.compile(_expression, Pattern.CASE_INSENSITIVE);
        Matcher _mathcer = _pattern.matcher(_email);

        if (_mathcer.matches()) {
            return true;
        }
        return false;
    }

    public static boolean ispasswordvalid(String password) {
        String _expression = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[@!\\*?$&\\^#-])[\\w@!\\*?$&\\^#-]{8,}$";
        CharSequence _password = password;
        Pattern _pattern = Pattern.compile(_expression, Pattern.CASE_INSENSITIVE);
        Matcher _mathcer = _pattern.matcher(_password);

        if (_mathcer.matches()) {
            return true;
        }
        return false;
    }

    public void changefragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_auth, fragment).commit();
    }

}
