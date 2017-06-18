package quewquewcrew.appngasal.view.fragment.user;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quewquewcrew.appngasal.R;
import quewquewcrew.appngasal.model.entity.History;
import quewquewcrew.appngasal.model.entity.Lapangan;
import quewquewcrew.appngasal.model.entity.User;
import quewquewcrew.appngasal.view.activity.ParentActivity;
import quewquewcrew.appngasal.view.adapter.ReportLapanganAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link reportpesanan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link reportpesanan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reportpesanan extends Fragment {
    private RecyclerView rv;
    private ReportLapanganAdapter adapter;

    public reportpesanan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _view =inflater.inflate(R.layout.fragment_reportpesanan,container,false);

        adapter = new ReportLapanganAdapter();
        rv =(RecyclerView)_view.findViewById(R.id.userdetailpesanan);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setHistory(History.History);
        rv.setAdapter(adapter);
        return _view;
    }

}
