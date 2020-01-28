package com.aryotech.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class dynamicFragment extends AppCompatActivity {

    private Button btnGanti, btnKembali;
    private simpleFragment simpleFragment;
    private anotherFragment anotherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);

        //isi object sinpleFragment dan anotherFragment
        simpleFragment = new simpleFragment(); // instansiasi object
        anotherFragment = new anotherFragment();

        btnGanti = findViewById(R.id.btn_ganti);
        btnKembali = findViewById(R.id.btn_kembali);

        //fragment manager
        FragmentManager fragManager = getSupportFragmentManager();

        //buat object fragment transaction
        FragmentTransaction fragTrans = fragManager.beginTransaction();

        //tambahkan object .simpleFragment (object) ke frame Layout frame_fragment
        fragTrans.add(R.id.frame_fragment, anotherFragment);
        fragTrans.hide(anotherFragment);
        fragTrans.add(R.id.frame_fragment,simpleFragment);

        //kemudian commit
        fragTrans.commit();

        btnGanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ketika btnGanti diclick maka akan pindah ke fragment lain
                FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
                //fragTrans.replace(R.id.frame_fragment, anotherFragment());

                if (anotherFragment.isAdded()){
                    fragTrans.show(anotherFragment);
                    fragTrans.remove(simpleFragment);
                    Toast.makeText(getApplicationContext(),"Fragment sudah ditambahkan sebelumnya",Toast.LENGTH_SHORT).show();
                }
                else {
                    fragTrans.replace(R.id.frame_fragment, anotherFragment);
                }
                fragTrans.addToBackStack("simple fragment");
                fragTrans.commit();

                btnKembali.setVisibility(View.VISIBLE);
                btnGanti.setVisibility(View.GONE);
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
                fragTrans.replace(R.id.frame_fragment,simpleFragment);
                fragTrans.addToBackStack("another fragment");
                fragTrans.commit();

                btnKembali.setVisibility(View.GONE);
                btnGanti.setVisibility(View.VISIBLE);
            }
        });
    }
}
