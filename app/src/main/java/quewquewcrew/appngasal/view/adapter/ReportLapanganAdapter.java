package quewquewcrew.appngasal.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.History;
import quewquewcrew.appngasal.model.entity.Lapangan;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.model.session.SessionManager;

/**
 * Created by User on 6/13/2017.
 */

public class ReportLapanganAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<History> history;
    public List<History> getHistory(){
        return history;
    }
    public void setHistory(List<History>history)
    {
        this.history = history;
    }

    public ReportLapanganAdapter()
    {
        this.history = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailpesanan_grid, parent, false);
        return new ReportLapanganAdapter.ItemReport(_view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ReportLapanganAdapter.ItemReport _holder = (ReportLapanganAdapter.ItemReport) holder;
        final History _history = this.history.get(position);
        _holder.namelap.setText(_history.getNamaLap());
        _holder.namakec.setText(_history.getKecamatan());
        _holder.harga.setText(String.valueOf(_history.getHarga()));
        _holder.username.setText(_history.getNamaPemesan());
        if(_history.getStatus() == "SUKSES")
        {
            _holder.status.setText(_history.getStatus());
            _holder.status.setTextColor(Color.GREEN);
        }
        else if(_history.getStatus() == "BATAL")
        {
            _holder.status.setText(_history.getStatus());
            _holder.status.setTextColor(Color.RED);
        }
        _holder.images.setImageResource(_history.getGambarLap());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    private class ItemReport extends RecyclerView.ViewHolder {
        private ImageView images;
        private TextView namelap, harga,namakec,username,status;

        public ItemReport(View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.itemreportimg);
            namelap = (TextView) itemView.findViewById(R.id.itemreportnamalap);
            namakec = (TextView) itemView.findViewById(R.id.itemreportnamakecamatan);
            harga = (TextView) itemView.findViewById(R.id.itemreportharga);
            status = (TextView) itemView.findViewById(R.id.itemreportstatus);
            username =  (TextView)itemView.findViewById(R.id.namauser);
        }

    }
}
