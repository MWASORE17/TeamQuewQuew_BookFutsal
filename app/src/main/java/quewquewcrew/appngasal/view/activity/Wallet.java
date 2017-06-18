package quewquewcrew.appngasal.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;
import quewquewcrew.appngasal.view.fragment.user.reportpesanan;

import static quewquewcrew.appngasal.view.activity.ParentActivity.doChangeActivity;

public class Wallet extends AppCompatActivity implements View.OnClickListener {
    private User users;
    Button btntops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        users = SessionManager.with(getApplicationContext()).getuserloggedin();
        TextView txtwallet = (TextView)findViewById(R.id.txtwallet);
        txtwallet.setText(String.valueOf(users.getWallet()));
        //setbuttontops
        btntops= (Button)findViewById(R.id.btntop);
        btntops.setOnClickListener(this);
        this.setTitle("Wallet");

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
    public void onClick(View v) {
        if(v==btntops)
        {
            doChangeActivity(getApplication(),Topup.class);
        }
    }
}
