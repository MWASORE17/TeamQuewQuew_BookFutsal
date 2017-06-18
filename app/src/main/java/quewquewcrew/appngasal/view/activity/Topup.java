package quewquewcrew.appngasal.view.activity;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;
import quewquewcrew.appngasal.view.fragment.user.reportpesanan;

import static quewquewcrew.appngasal.view.activity.ParentActivity.doChangeActivity;

public class Topup extends AppCompatActivity {
    private User users;
    TextView jlhtops,reset;
    Button btntops;
    int hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        final ProgressDialog progress = new ProgressDialog(this);
        jlhtops =(TextView) findViewById(R.id.txtjlhtop);
        jlhtops.setText("0");
        progress.setMessage("loading ...");
        progress.show();
        Thread _thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    progress.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        _thread.start();
        createRadioButtons();
        btntops = (Button)findViewById(R.id.btntop);
        reset = (TextView)findViewById(R.id.reset);
        event();
        this.setTitle("Topup");
    }

    private void createRadioButtons() {
        RadioGroup group = (RadioGroup)findViewById(R.id.RGroup);

        int [] numpanels = getResources().getIntArray(R.array.topups);

        //create Radio Buttons

        for(int i =0;i<numpanels.length;i++)
        {
            final int numpanel = numpanels[i];
            RadioButton btn = new RadioButton(this);
            btn.setTextSize(16);
            btn.setText(getString(R.string.topups,numpanel));
            //TODO : Set on-click callback
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp = Integer.parseInt(jlhtops.getText().toString());
                    int num = numpanel;
                    hasil = num + temp;


                    jlhtops.setText(String.valueOf(hasil));
            }


            });

            group.addView(btn);
            //Add to radiogroup:
            //Select default button:
        }

    }

    private void event()
    {

        btntops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jlhtops.getText().toString().matches("0"))
            {
                Toast.makeText(Topup.this,"Topup masih Kosong" ,Toast.LENGTH_SHORT).show();
            }
            else if(jlhtops != null)
            {
                users = SessionManager.with(getApplicationContext()).getuserloggedin();
                int temp = users.getWallet();
                int tambah = temp+hasil;
                users.setWallet(tambah);
                SessionManager sessionManager = SessionManager.with(getApplicationContext());
                sessionManager.createsession(users);
                Toast.makeText(Topup.this,"Berhasil Top-up Sebesar " + hasil ,Toast.LENGTH_SHORT).show();
                doChangeActivity(getApplicationContext(),MainActivity.class);
            }
            else
            {
                Toast.makeText(Topup.this,"Silakan Memilih Top-up terlebih dahulu " ,Toast.LENGTH_SHORT).show();
            }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jlhtops.setText("0");
                hasil = 0;

            }
        });
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
}
