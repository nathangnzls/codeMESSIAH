package net.netne.cmessiah.codemessiah;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class todolist_class extends Fragment {

    Time today = new Time(Time.getCurrentTimezone());
    DBAdapter myDB;
    EditText usersInput;
    ListView usersText;
    FloatingActionButton btnAdd,grabbg;
    Dialog dialog;
    ImageButton details,edit,remove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fam_tdl_gb,null);

        btnAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        grabbg = (FloatingActionButton) view.findViewById(R.id.grabbagBtn);
        usersText = (ListView) view.findViewById(R.id.list_todo);
        registerForContextMenu(usersText);

        listItemLongClicked();

        openDB();
        populatListView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
            onClick_add();
            }
       });

        grabbg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                WebView wv = new WebView(getActivity());
                    WebSettings wbs = wv.getSettings();
                if (AppStatus.getInstance(getActivity()).isOnline()) {
                    wbs.setEnableSmoothTransition(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setWebViewClient(new MyBrowser());
                    wv.loadUrl("http://mysite.ph/cms/?page_id=367");
                    alert.setView(wv);
                } else {
                    wv.loadUrl( "javascript:window.location.reload( true )" );
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    wv.getSettings().setAppCacheEnabled(true);
                    wbs.setDomStorageEnabled(true);
                    wbs.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                    wbs.setEnableSmoothTransition(true);
                    wv.setWebViewClient(new MyBrowser());
                    wv.loadUrl("http://mysite.ph/cms/?page_id=367");
                    alert.setView(wv);
                }
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
        return view;
    }

    private void openDB(){
        myDB = new DBAdapter(getActivity());
        myDB.open();
    }

    public void onClick_add(){
        LayoutInflater li = LayoutInflater.from(getActivity());
        final LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.new_item_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                usersInput = (EditText)newNoteBaseLayout.findViewById(R.id.usersInput);
                today.setToNow();
                String timeStamp = today.format("%D   %H:%M:%S");;
                if(!TextUtils.isEmpty(usersInput.getText().toString())){
                    myDB.insertRow(usersInput.getText().toString(), timeStamp);
                }else{
                    Toast.makeText(getActivity(),"Fields cannot be empty",Toast.LENGTH_LONG).show();
                }
                populatListView();
            }
        });
        builder.setNegativeButton("Cancel", null).setTitle("New Task");
        builder.setView(newNoteBaseLayout);
        builder.show();
    }

    private void populatListView(){
        Cursor cursor = myDB.getAllRows();
        String[] fromfieldnames = new String[] {DBAdapter.KEY_ROWID,DBAdapter.KEY_TASK};
        int[] toViewIDs = new int[] {R.id.idnum,R.id.txtList};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(),R.layout.todolist_parent,cursor,fromfieldnames,toViewIDs,0);
        usersText.setAdapter(myCursorAdapter);

    }

   public void onClick_clear(){
        myDB.deleteAll();
        populatListView();
    }

    private void listItemLongClicked(){
        usersText.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,final long id) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.menu_list);
                dialog.setTitle("Select Action");
                details = (ImageButton) dialog.findViewById(R.id.btnDetails);
                edit = (ImageButton)dialog.findViewById(R.id.btnEdit);
                remove = (ImageButton)dialog.findViewById(R.id.btnDelete);

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        displayToast(id);
                        dialog.dismiss();
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        LayoutInflater li = LayoutInflater.from(getActivity());
                        final LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.new_item_dialog, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        usersInput = (EditText)newNoteBaseLayout.findViewById(R.id.usersInput);
                        final Cursor cursor = myDB.getRow(id);
                        String list = cursor.getString(DBAdapter.COL_TASK);
                        usersInput.setText(list);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(!TextUtils.isEmpty(usersInput.getText().toString())) {
                                    if (cursor.moveToFirst()) {
                                        String list = usersInput.getText().toString();
                                        today.setToNow();
                                        String date = today.format("%D   %H:%M:%S");
                                        myDB.updateRow(id, list, date);
                                    }
                                    cursor.close();
                                    populatListView();
                                }
                                Toast.makeText(getActivity(), "Task successfully edited", Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", null).setTitle("Edit Task");
                        builder.setView(newNoteBaseLayout);
                        builder.show();

                    }
                });
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Do you really want to delete this task?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                myDB.deleteRow(id);
                                populatListView();
                                dialog.dismiss();
                                Toast.makeText(getActivity(),"Task successfully deleted",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        builder.show();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }

    public void displayToast(long id){
        Cursor cursor = myDB.getRow(id);
        if(cursor.moveToFirst()){
            long idDB = cursor.getLong(DBAdapter.COL_ROWID);
            String list = cursor.getString(DBAdapter.COL_TASK);
            String date = cursor.getString(DBAdapter.COL_DATE);

            String message = "ID: " + idDB + "\nTask: "+list+"\nDate&Time: "+date;
            Toast.makeText(getActivity(), message,Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        closeDB();
    }
    private void closeDB(){
        myDB.close();
    }

}
