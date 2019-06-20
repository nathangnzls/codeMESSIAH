package net.netne.cmessiah.codemessiah;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class SelectUserAdapter extends BaseAdapter {
    public List<SelectUser> _data;
    private ArrayList<SelectUser> arraylist;
    Context _c;
    ViewHolder v;

    public SelectUserAdapter(List<SelectUser> selectUsers, Context context) {
        _data = selectUsers;
        _c = context;
        this.arraylist = new ArrayList<SelectUser>();
        this.arraylist.addAll(_data);
    }
    @Override
    public int getCount() {
        return _data.size();
    }
    @Override
    public Object getItem(int i) {
        return _data.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.contacts_user_layout, null);
            Log.e("Inside", "here--------------------------- In view1");
        } else {
            view = convertView;
            Log.e("Inside", "here--------------------------- In view2");
        }
        v = new ViewHolder();
        v.title = (TextView) view.findViewById(R.id.name);
        v.btnCall = (ImageButton) view.findViewById(R.id.btncall);
        v.phone = (TextView) view.findViewById(R.id.no);
        v.imageView = (ImageView) view.findViewById(R.id.pic);
        final SelectUser data = (SelectUser) _data.get(i);
        v.title.setText(data.getName());
        v.phone.setText(data.getPhone());
        try {

            if (data.getThumb() != null) {
                v.imageView.setImageBitmap(data.getThumb());
            } else {
                v.imageView.setImageResource(R.mipmap.user_ico);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        Log.e("Image Thumb", "--------------" + data.getThumb());
        v.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder build= new AlertDialog.Builder(_c);
                build.setTitle("Confirmation");
                build.setMessage("Do you want to continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                }).setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_CALL);
                        String p = "tel:" + data.getPhone();
                        i.setData(Uri.parse(p));
                        _c.startActivity(i);
                    }
                });
                build.show();
            }
        });
        view.setTag(data);
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        _data.clear();
        if (charText.length() == 0) {
            _data.addAll(arraylist);
        } else {
            for (SelectUser wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    _data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    static class ViewHolder {
        ImageView imageView;
        TextView title, phone;
        ImageButton btnCall;
    }
}
