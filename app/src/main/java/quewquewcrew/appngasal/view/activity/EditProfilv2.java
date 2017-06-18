package quewquewcrew.appngasal.view.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;
import quewquewcrew.appngasal.view.fragment.auth.LoginFragment;

import static quewquewcrew.appngasal.view.activity.ParentActivity.doChangeActivity;

public class EditProfilv2 extends AppCompatActivity {
    private TextInputLayout tipname, tipemail, tipalamat, tiphp;
    private EditText edtxtname;
    private EditText edtxtalamat;
    private EditText edtxtnohp;
    private EditText edtxtemail;
    private Button Btnedit;
    private User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profilv2);
        tipname = (TextInputLayout) findViewById(R.id.edit_name_container);
        tipemail = (TextInputLayout) findViewById(R.id.edit_email_container);
        tipalamat = (TextInputLayout) findViewById(R.id.edit_almat_container);
        tiphp = (TextInputLayout) findViewById(R.id.edit_hp_container);
        edtxtname = (EditText) findViewById(R.id.edtxtname);
        edtxtemail = (EditText) findViewById(R.id.edtxtemail);
        edtxtnohp = (EditText) findViewById(R.id.edtxtnohp);
        edtxtalamat = (EditText) findViewById(R.id.edtxtalamat);
        Btnedit = (Button) findViewById(R.id.btnedtxt);

        event();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.setTitle("Edit Profil");
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
                doChangeActivity(getApplicationContext(),EditProfil.class);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void event() {
        Btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean istrue = true;
                tiphp.setErrorEnabled(false);
                tipname.setErrorEnabled(false);
                tipalamat.setErrorEnabled(false);
                tiphp.setErrorEnabled(false);
                users = SessionManager.with(getApplicationContext()).getuserloggedin();
                int wallet = users.getWallet();
                String password = users.getPassword();
                if (TextUtils.isEmpty(edtxtname.getText())) {
                    istrue = false;
                    tipname.setErrorEnabled(true);
                    tipname.setError("Nama Tidak boleh Kosong");
                }
                else if(edtxtname.getText().length() < 7)
                {
                    istrue = false;
                    tipname.setErrorEnabled(true);
                    tipname.setError("Nama Minimal 7 Karakter");
                }
                else if (!AuthActivity.isemailvalid(edtxtemail.getText().toString())) {
                    istrue = false;
                    tipemail.setErrorEnabled(true);
                    tipemail.setError("Email Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(edtxtnohp.getText())) {
                    istrue = false;
                    tiphp.setErrorEnabled(true);
                    tiphp.setError("Hp Tidak Boleh Kosong");
                }
                else if (edtxtnohp.getText().length() <=11)
                {
                    istrue = false;
                    tiphp.setErrorEnabled(true);
                    tiphp.setError("No Handphone Minimal 11 Angka");
                }
                else if (TextUtils.isEmpty(edtxtalamat.getText())) {
                    istrue = false;
                    tipalamat.setErrorEnabled(true);
                    tipalamat.setError("Alamat Tidak Boleh Kosong");
                }
                if (istrue) {

                    User newuser = new User(edtxtname.getText().toString(), edtxtemail.getText().toString(), password, wallet, edtxtnohp.getText().toString(), edtxtalamat.getText().toString());
//                    User.users.add(newuser);
                    SessionManager sessionManager = SessionManager.with(getApplicationContext());
                    sessionManager.createsession(newuser);

                    doChangeActivity(getApplicationContext(),EditProfil.class);

                }

            }
        });

    }
}
