package quewquewcrew.appngasal.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.session.SessionManager;
import quewquewcrew.appngasal.view.fragment.user.UserASCGrid;
import quewquewcrew.appngasal.view.fragment.user.reportpesanan;


public class MainActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         /* checking the session */
        if (!SessionManager.with(getApplicationContext()).isuserlogin()) {
            this.doChangeActivity(getApplicationContext(), AuthActivity.class);
        }
         this.changefragment(new UserASCGrid());
          this.setTitle("Lapangan");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.home)
        {
            doChangeActivity(getApplicationContext(),MainActivity.class);
        }
        else if(id==R.id.editpro){
            doChangeActivity(getApplicationContext(),EditProfil.class);
        }
        else if(id == R.id.topup)
        {
            doChangeActivity(getApplication(),Topup.class);
        }
        else if (id == R.id.report)
        {
            this.changefragment(new reportpesanan());
        }
        else if(id == R.id.Wallet)
        {
            doChangeActivity(getApplication(),Wallet.class);
        }
        else if(id== R.id.logout)
        {
            SessionManager.with(getApplicationContext()).clearsession();
            doChangeActivity(getApplicationContext(), AuthActivity.class);
        }
        return true;
    }
    public void changefragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, fragment).commit();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}


