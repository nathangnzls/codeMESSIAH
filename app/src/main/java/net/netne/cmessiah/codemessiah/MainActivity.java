package net.netne.cmessiah.codemessiah;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    NotificationManager notificationManager;
    boolean isNotificActive = false;
    int notifID = 33;
    ProgressDialog dialog;
    Spinner sp;
    ArrayAdapter<CharSequence> adapter;

    private static final String KEY_UNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_CNUM = "contact";
    private static final String KEY_RECIPIENT = "recp";

    TextView nametxt, phonenotxt, tvAddress ;
    EditText phoneno, tvAddress_offline, umessage;
    String  getAddress,prefixnum,finalnum;


    SharedPreferences pref;
    GPSTracker gps;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notifID);
        runThread();

        pref = getSharedPreferences("SMSPref", MODE_PRIVATE);
        gps = new GPSTracker(MainActivity.this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.homeBtn:
                        fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
                        break;
                    case R.id.hotlinesBtn:
                       hotlinesMenuItem();
                        break;
                    case R.id.todolistBtn:
                        todoList();
                        break;

                    case R.id.sendMessageBtn:
                        if (AppStatus.getInstance(MainActivity.this).isOnline()) {
                                getLocation();
                        }else{
                            send_sms_offline();
                           }
                        break;
                    case R.id.setting:
                        edit_sms();
                        break;

                }

                return true;
            }

        });


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void edit_sms(){
        LayoutInflater li = LayoutInflater.from(this);
        final LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.sms_edit_settings, null);
        phoneno = (EditText) newNoteBaseLayout.findViewById(R.id.phoneno);
        sp = (Spinner) newNoteBaseLayout.findViewById(R.id.spinnerNum2);
        adapter =  ArrayAdapter.createFromResource(MainActivity.this,R.array.numberprefix,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prefixnum = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String user_phoneno = phoneno.getText().toString();
                if(user_phoneno.equals("")){
                    Toast.makeText(MainActivity.this,"Fields Cannot be Empty!",Toast.LENGTH_LONG).show();
                }else{
                    if(user_phoneno.length()==7){
                        finalnum = prefixnum+user_phoneno;
                        SharedPreferences.Editor edit = pref.edit();
                        edit.putString(KEY_RECIPIENT, finalnum);
                        edit.commit();
                        Toast.makeText(MainActivity.this, "saved successfully!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Contact Number!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        builder.setNegativeButton("Cancel", null).setTitle("SMS Set Up");
        builder.setView(newNoteBaseLayout);
        builder.show();
    }
    private void hotlinesMenuItem(){
        new AlertDialog.Builder(this).setTitle("Hotlines").setMessage("Don't know who to call in case of disaster? Just click the button to view the hotlines.").setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView,new hotlines_tabFragment()).commit();
            }
        }).show();
    }
    private void todoList(){
        new AlertDialog.Builder(this).setTitle("To-do-List").setMessage("You don't know when and where a disaster will come, Make/Update your plans now.").setNeutralButton("CREATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView,new todolist_class()).commit();
            }
        }).show();
    }
    private void showNotification(){
        NotificationCompat.Builder notificBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Code:Messiah")
                .setContentText("Check what is happening right now")
                .setTicker("You have a new notification!")
                .setAutoCancel(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true);
        Intent moreInfoIntent = new Intent(this,MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(notifID, notificBuilder.build());
        isNotificActive = true;
    }

    private void runThread(){
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        showNotification();
                    }
                }
            };
            thread.start();
    }
    private void getLocation(){
        SharedPreferences pre = this.getSharedPreferences("edit_user_info", Context.MODE_PRIVATE);
        final String getFname,getLname,getnumber;
        getFname = pre.getString(KEY_FNAME, "");
        getLname = pre.getString(KEY_LNAME, "");
        getnumber = pref.getString(KEY_RECIPIENT, "");

        if (getnumber.equals("")) {
            edit_sms();
            Toast.makeText(getBaseContext(), "SMS Settings was not properly set. Please set it properly to use the SOS", Toast.LENGTH_LONG).show();
          }else {
            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try{
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if(addresses != null){
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder();
                    for(int i=0; i<=returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(", ");
                    }
                    getAddress = strReturnedAddress.toString();
                    Toast.makeText(getApplicationContext(), getAddress, Toast.LENGTH_SHORT).show();
                    LayoutInflater li = LayoutInflater.from(MainActivity.this);
                    final LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.get_sms_pref, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    nametxt = (TextView) newNoteBaseLayout.findViewById(R.id.get_tv_name);
                    phonenotxt = (TextView) newNoteBaseLayout.findViewById(R.id.get_tv_number);
                    tvAddress = (TextView) newNoteBaseLayout.findViewById(R.id.get_tv_address);
                    nametxt.setText("Your Name: " + getFname +" "+getLname);
                    tvAddress.setText("Current Address: "+ getAddress.toString());
                    phonenotxt.setText("Message will be send to: " + getnumber);
                    builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendMessage(getnumber, getFname+" "+getLname+ " is at " + getAddress.toString() + " \n\n" + "Coordinates: " + latitude + " " + longitude + " and I need help!");
                            Toast.makeText(getApplicationContext(), "Name: " + getFname+" "+getLname + "\nLocation: " + getAddress.toString() + "\nMessage: I need Help!", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", null).setTitle("SMS");
                    builder.setView(newNoteBaseLayout);
                    builder.show();
                } else{
                    Toast.makeText(getApplicationContext(), "No Address returned", Toast.LENGTH_SHORT).show();
                }
            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to get Address!", Toast.LENGTH_SHORT).show();
            }
          }
    }


    private void send_sms_offline(){
        SharedPreferences pre = this.getSharedPreferences("edit_user_info", Context.MODE_PRIVATE);
        final String getFname,getLname,getnumber;
        getFname = pre.getString(KEY_FNAME, "");
        getLname = pre.getString(KEY_LNAME, "");
        getnumber = pref.getString(KEY_RECIPIENT, "");

        LayoutInflater li = LayoutInflater.from(this);
        final LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.get_sms_pref_offline, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        nametxt = (TextView) newNoteBaseLayout.findViewById(R.id.get_tv_name);
        phonenotxt = (TextView) newNoteBaseLayout.findViewById(R.id.get_tv_number);
        tvAddress_offline = (EditText) newNoteBaseLayout.findViewById(R.id.get_tv_address_offline);
        umessage = (EditText)newNoteBaseLayout.findViewById(R.id.get_message_offline);
        nametxt.setText("Your Name: " + getFname+" "+getLname);
        phonenotxt.setText("Message will be send to: " + getnumber.toString());
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                nametxt.setText(getFname+" "+getLname);
                phonenotxt.setText(getnumber);
                String address=tvAddress_offline.getText().toString();
                String message=umessage.getText().toString();
                if (getnumber.length() > 0 && getFname.length()>0) {
                    if (address.length()>0 && message.length()>0) {
                        sendMessageOffline(getnumber, getFname+" "+getLname+ " is at " + address + "\nMESSAGE:" + message);
                        Toast.makeText(getApplicationContext(), "Name: " + getFname+" "+getLname+ "\nLocation: " + address + "\nMESSAGE: " + message, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getBaseContext(), "Fields cannot be empty!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getBaseContext(), "SMS Settings was not properly set. send failed.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null).setTitle("Send SOS offline by typin your current location with message.");
        builder.setView(newNoteBaseLayout);
        builder.show();
    }
    SmsManager smsManager = SmsManager.getDefault();
    private void sendMessage(String phoneNo, String message) {
        try {
            smsManager.sendTextMessage(phoneNo,null, message, null, null);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void sendMessageOffline(String phoneNo, String message){
        try {
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        builder.setNegativeButton("Cancel", null).setTitle("Do you really want to exit?");
        builder.show();
    }
}