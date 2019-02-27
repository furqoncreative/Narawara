package com.sera5.narawara.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sera5.narawara.AboutActivity;
import com.sera5.narawara.LoginActivity;
import com.sera5.narawara.MainActivity;
import com.sera5.narawara.MyFragment;
import com.sera5.narawara.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class AccountFragment extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ArrayList<String> ars = new ArrayList<>();
        final Intent[] intent = new Intent[1];

        TextView nama_u = view.findViewById(R.id.txt__namauser);
        TextView reward_u = view.findViewById(R.id.txt_reward);

        if(uc.getUser()!=null) {
            String user = uc.getUser().getEmail();
            nama_u.setText("Hai, "+user+"!");
            ars.add("Keluar");
        } else {
            nama_u.setText("Hai, Tamu!");
            reward_u.setText("");
            ars.add("Login");
        }

        ars.add("Tentang aplikasi ini");

        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_items, ars.toArray(new String[0]));

        ListView listView = view.findViewById(R.id.mobile_list);

        if(uc.getUser()==null) {
            listView.setOnItemClickListener((parent, view1, position, id) -> {

                switch (position) {
                    case 0:
                        intent[0] = new Intent(view.getContext(), LoginActivity.class);
                        view.getContext().startActivity(intent[0]);
                        break;
                    case 1:
                        intent[0] = new Intent(view.getContext(), AboutActivity.class);
                        view.getContext().startActivity(intent[0]);
                        break;
                    default:

                }
            });
        } else {
            listView.setOnItemClickListener((parent, view1, position, id) -> {
                switch (position) {
                    case 3:

                        break;
                    case 1:
                        intent[0] = new Intent(view.getContext(), AboutActivity.class);
                        view.getContext().startActivity(intent[0]);
                        break;
                    case 0:
                        uc.signOut();
                        Toast.makeText(getActivity(),"Keluar sukses!",Toast.LENGTH_SHORT).show();
                        intent[0] = getActivity().getIntent();
                        getActivity().finish();
                        view.getContext().startActivity(intent[0]);
                        break;
                    default:

                }
            });
        }

        listView.setAdapter(adapter);
        return view;
    }
}