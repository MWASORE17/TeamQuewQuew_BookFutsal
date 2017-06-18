package quewquewcrew.appngasal.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.History;
import quewquewcrew.appngasal.model.entity.Lapangan;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;
import static quewquewcrew.appngasal.view.activity.ParentActivity.doChangeActivity;

public class Pembayaran extends AppCompatActivity  {
    private User users;
    int total;
    int akhir,waktus;
    String tgl;
    TextView txtwallet;
    TextView txtsaldoakhirs;
    TextView btnCancel;
    TextView username , namalap;
    ImageView image;
    Button btnbyr;
    private Lapangan lapang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        final ProgressDialog progress = new ProgressDialog(this);
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
        Intent i = getIntent();
        //setlapangan item
        lapang = (Lapangan) getIntent().getExtras().get("Lapangan");
        image = (ImageView)findViewById(R.id.pemimage);
        image.setImageResource(lapang.getImg());
        namalap = (TextView)findViewById(R.id.namalap);
        namalap.setText(lapang.getNameLap());
        users = SessionManager.with(getApplicationContext()).getuserloggedin();
        //setuser item
        username = (TextView)findViewById(R.id.namasewa);
        username.setText(users.getName());

        //setwaktu
        waktus = i.getIntExtra("Waktu",0);
        TextView waktu = (TextView)findViewById(R.id.waktusewa);
        waktu.setText(String.valueOf(waktus));
        //userwallet
        txtwallet = (TextView)findViewById(R.id.Sisawallet);
        txtwallet.setText(String.valueOf(users.getWallet()));

        //tanggal
        tgl = i.getStringExtra("Tanggal");
        TextView tanggal = (TextView)findViewById(R.id.tglsewa);
        tanggal.setText(tgl);
        //total harga lap
        total = i.getIntExtra("Pembayaran",0);
        TextView waktu1 = (TextView)findViewById(R.id.totallapang);
        waktu1.setText(String.valueOf(total));


        btnbyr= (Button)findViewById(R.id.bayar);
        btnCancel = (TextView) findViewById(R.id.btnCancel);
        event();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.setTitle("Pembayaran");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void event()
    {

        btnbyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(users.getWallet() >= total)
            {

                int wallets = users.getWallet();
                akhir = wallets-total;
                users.setWallet(akhir);

                String uname = username.getText().toString();

                String nlap = namalap.getText().toString();

                String kec = lapang.getKecamatan().toString();

                String status = "SUKSES";

                int harga = total;

                History historynew = new History(nlap,uname,kec,status,harga,lapang.getImg());
                historynew.idok++;
                History.History.add(historynew);

                SessionManager sessionManager = SessionManager.with(getApplicationContext());
                sessionManager.createsession(users);
                Toast.makeText(Pembayaran.this,"Berhasil Pembayaran",Toast.LENGTH_LONG).show();
                doChangeActivity(getApplication(),MainActivity.class);
            }
            else
            {
                Toast.makeText(Pembayaran.this,"Wallet Tidak Cukup Pemotongan Harap Top-up terlebih dahulu",Toast.LENGTH_LONG).show();
                doChangeActivity(getApplication(),Topup.class);
            }


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String nlap = namalap.getText().toString();
                String kec = lapang.getKecamatan().toString();
                String status = "BATAL";
                int harga = total;
                History historynew = new History(nlap,uname,kec,status,harga,lapang.getImg());
                historynew.idcancel++;
                History.History.add(historynew);
                SessionManager sessionManager = SessionManager.with(getApplicationContext());
                sessionManager.createsession(users);
                doChangeActivity(getApplication(),MainActivity.class);
            }
        });

        }
}
