package net.netne.cmessiah.codemessiah;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionMenu;

public class hotlines_tabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;
    EditText etphoneno;
    FloatingActionMenu fab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.floatingfab,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        fab = (FloatingActionMenu) x.findViewById(R.id.fab);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial();
            }
        });

        return x;
    }

    public void dial(){
        LayoutInflater li = LayoutInflater.from(getActivity());
        final LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.dial_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etphoneno = (EditText)newNoteBaseLayout.findViewById(R.id.et_phone_number);
                String number = etphoneno.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                String p = "tel:" + number;
                i.setData(Uri.parse(p));
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancel", null).setTitle("Enter Phone number to call");
        builder.setView(newNoteBaseLayout);
        builder.show();
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0 : return new hotlines_class();
                case 1 : return new Contacts_class();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0 :
                    return "Hotlines";
                case 1 :
                    return "Contacts";
            }
            return null;
        }
    }

}
