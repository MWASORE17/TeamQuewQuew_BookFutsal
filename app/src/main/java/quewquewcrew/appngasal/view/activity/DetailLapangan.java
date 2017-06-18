package quewquewcrew.appngasal.view.activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.Lapangan;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;

@TargetApi(Build.VERSION_CODES.N)
@RequiresApi(api = Build.VERSION_CODES.N)
public class DetailLapangan extends AppCompatActivity implements View.OnClickListener {

    Button btndate, btntimestart, btntimestop, btnbook;
    EditText etextdate, etexttimestart, etexttimestop;
    private int day, month, year, hours, minute;
    private Lapangan lapangs;
    private User users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lapangs = (Lapangan)getIntent().getExtras().get("Lapangan");
        setContentView(R.layout.activity_detail_lapangan);
        users = SessionManager.with(getApplicationContext()).getuserloggedin();
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

        TextView namalapang = (TextView) findViewById(R.id.item_lapang_grid_name);
        namalapang.setText(lapangs.getNameLap());
        TextView namaKecamatan = (TextView) findViewById(R.id.item_lapang_grid_kecamatan);
        namaKecamatan.setText(lapangs.getKecamatan());
        TextView namaAlamat = (TextView) findViewById(R.id.item_lapang_grid_alamat);
        namaAlamat.setText(lapangs.getAlamatLap());
        TextView noHp = (TextView) findViewById(R.id.item_lapang_grid_notel);
        noHp.setText(lapangs.getNotel());
        TextView harga = (TextView)findViewById(R.id.item_lapang_grid_harga);
        harga.setText(String.valueOf(lapangs.getHarga()));
        TextView jenis = (TextView)findViewById(R.id.jenis);
        jenis.setText(lapangs.getJenis());
        ImageView image = (ImageView)findViewById(R.id.item_lapang_grid_image);
        image.setImageResource(lapangs.getImg());

        //btn date
        btndate = (Button)findViewById(R.id.btndate);
        btnbook = (Button)findViewById(R.id.btnbooking);
        etextdate = (EditText)findViewById(R.id.etextdate);
        etextdate.setFocusable(false);
        //btn time start
        btntimestart = (Button)findViewById(R.id.btntimestrat);
        etexttimestart= (EditText) findViewById(R.id.etexttimestart);
        etexttimestart.setFocusable(false);
        //btn time stop
        btntimestop = (Button) findViewById(R.id.btntimestop);
        etexttimestop = (EditText) findViewById(R.id.etexttimestop);
        etexttimestop.setFocusable(false);


        btnbook.setOnClickListener(this);
        btndate.setOnClickListener(this);
        btntimestop.setOnClickListener(this);
        btntimestart.setOnClickListener(this);
        event();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        this.setTitle(lapangs.getNameLap());
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

    Calendar calender = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calender.set(Calendar.YEAR, year);
            calender.set(Calendar.MONTH, month);
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    //set date in textview

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etextdate.setText(sdf.format(calender.getTime()));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == btndate) {
            new DatePickerDialog(DetailLapangan.this, date,calender.get(Calendar.YEAR), calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH)).show();
        }
        if (v == btntimestart) {
            final Calendar calendar = Calendar.getInstance();
            hours = calendar.get(calendar.HOUR_OF_DAY);
            minute = calendar.get(calendar.MINUTE);

            TimePickerDialog timepickerdialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String hour = "0";
                    String minutes ="0";
                    if(hourOfDay < 10 && minute < 10)
                    {
                        hour += hourOfDay;
                        minutes += minute;
                        etexttimestart.setText(hour + ":" + minutes);
                    }
                    else if(hourOfDay < 10 && minute > 10)
                    {
                        hour += hourOfDay;
                        etexttimestart.setText(hour + ":" + minute);
                    }
                    else if(hourOfDay > 10 && minute < 10)
                    {
                        minutes += minute;
                        etexttimestart.setText(hourOfDay + ":" + minutes);
                    }
                    else
                    {
                        etexttimestart.setText(hourOfDay + ":" + minute);
                    }

                }
            }, hours, minute, true);
            timepickerdialog.show();
        }

        if (v == btntimestop) {
            final Calendar calendar = Calendar.getInstance();
            hours = calendar.get(calendar.HOUR_OF_DAY);
            minute = calendar.get(calendar.MINUTE);

            TimePickerDialog timepickerdialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String hour = "0";
                    String minutes ="0";

                    if(hourOfDay < 10 && minute < 10)
                    {
                        hour += hourOfDay;
                        minutes += minute;
                        etexttimestop.setText(hour + ":" + minutes);
                    }
                    else if(hourOfDay < 10 && minute > 10)
                    {
                        hour += hourOfDay;
                        etexttimestop.setText(hour + ":" + minute);
                    }
                    else if(hourOfDay > 10 && minute < 10)
                    {
                        minutes += minute;
                        etexttimestop.setText(hourOfDay + ":" + minutes);
                    }
                    else
                    {
                        etexttimestop.setText(hourOfDay + ":" + minute);
                    }


                }
            }, hours, minute, true);
            timepickerdialog.show();
        }


    }

    long data = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String dateString = sdf.format(data);
    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
    String timeSekarang = sdf1.format(calender.getTime());


    private void event()
    {

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etextdate.getText().toString().matches(""))
                {
                    Toast.makeText(DetailLapangan.this,"Date Yang Dipilih tidak boleh kosong",Toast.LENGTH_LONG).show();
                }
                else if(ConvertDate(etextdate.getText().toString()) < ConvertDate(dateString))
                {
                    Toast.makeText(DetailLapangan.this,"Tanggal yang dipilih tidak sesuai",Toast.LENGTH_LONG).show();
                }
                else if(((WaktuBooking(etexttimestart.getText().toString()) ==0 || WaktuBooking(etexttimestart.getText().toString()) <=6) || WaktuBooking(etexttimestart.getText().toString()) == 23) ||
                        ((WaktuBooking(etexttimestop.getText().toString()) ==0 || WaktuBooking(etexttimestop.getText().toString()) <=6) || WaktuBooking(etexttimestop.getText().toString()) == 23)                        )
                {
                    Toast.makeText(DetailLapangan.this,"Waktu Booking adalah dari Jam 07.00 - 23.00",Toast.LENGTH_LONG).show();
                }
                else if(TotalWaktu(timeSekarang) > TotalWaktu(etexttimestart.getText().toString()))
                {
                    Toast.makeText(DetailLapangan.this,"Jam Yang Anda pilih Sudah Lewat BROOOO",Toast.LENGTH_LONG).show();
                }
                else if (etexttimestart.getText().toString().matches("") ||
                        etexttimestop.getText().toString().matches(""))
                {
                    Toast.makeText(DetailLapangan.this, "Waktu yang dipilih tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

                else if(ConvertTime(etexttimestart.getText().toString()) >= ConvertTime(etexttimestop.getText().toString()))
                {
                    Toast.makeText(DetailLapangan.this,"Jam Yang Dipilih tidak sesuai",Toast.LENGTH_LONG).show();
                }
                else if(((ConvertTime(etexttimestop.getText().toString())-ConvertTime(etexttimestart.getText().toString())))%3600 !=0)
                {

                    Toast.makeText(DetailLapangan.this,"Harus Kelipatan 1Jam ",Toast.LENGTH_LONG).show();
                }
                else
                {

                    String tanggal = etextdate.getText().toString();
                    int jam = toHour(ConvertTime(etexttimestop.getText().toString())-ConvertTime(etexttimestart.getText().toString()));

                    Intent _intent = new Intent(view.getContext(),Komfirmasi.class);

                    _intent.putExtra("Lapangan",lapangs);
                    _intent.putExtra("Tanggal",tanggal);
                    _intent.putExtra("Jam", jam);

                    //Toast.makeText(DetailLapangan.this,"next time",Toast.LENGTH_LONG).show();
                    view.getContext().startActivity(_intent);
                }
            }

        });
    }


    public static int toSecond(String s)
    {
        String[] Waktu = s.split(":");
        int jam = Integer.parseInt(Waktu[0]);
        int menit = Integer.parseInt(Waktu[1]);
        int Jamkedetik = jam * 3600;
        int Menitkedetik = menit * 60;
        return Jamkedetik + Menitkedetik;
    }

    public static int toHour(int s) {

        int detik = s;
        return detik / 3600;
    }

    public int ConvertTime(String s)
    {
        int waktu;
        waktu = toSecond(s);
        return waktu;
    }
    public int ConvertDate(String s)
    {
        String[] Tanggal = s.split("-");
        int tanggal = Integer.parseInt(Tanggal[0]);
        int bulan = Integer.parseInt(Tanggal[1]);
        int tahun = Integer.parseInt(Tanggal[2]);
        return tanggal+ bulan + tahun;
    }

    public int WaktuBooking(String s)
    {
        String[] Waktu = s.split(":");
        int jam = Integer.parseInt(Waktu[0]);
        return  jam;
    }

    public int TotalWaktu (String s)
    {
        String[] Waktu = s.split(":");
        int jam = Integer.parseInt(Waktu[0]);
        int menit = Integer.parseInt(Waktu[1]);
        return  jam + menit;
    }

}
