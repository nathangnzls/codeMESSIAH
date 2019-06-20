package net.netne.cmessiah.codemessiah;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import net.netne.cmessiah.codemessiah.data.Atmosphere;
import net.netne.cmessiah.codemessiah.data.Channel;
import net.netne.cmessiah.codemessiah.data.Item;
import net.netne.cmessiah.codemessiah.data.LocationResult;
import net.netne.cmessiah.codemessiah.data.Wind;
import net.netne.cmessiah.codemessiah.listener.GeocodingServiceListener;
import net.netne.cmessiah.codemessiah.listener.WeatherServiceListener;
import net.netne.cmessiah.codemessiah.service.GoogleMapsGeocodingService;
import net.netne.cmessiah.codemessiah.service.WeatherCacheService;
import net.netne.cmessiah.codemessiah.service.YahooWeatherService;
public class TyphoonFragment extends Fragment implements WeatherServiceListener, GeocodingServiceListener, LocationListener {

    public ImageView weatherIconImageView,imageDay1,imageDay2,imageDay3,imageDay4,imageDay5;
    public TextView forecastTextView,temperatureTextView,conditionTextView,locationTextView,windTextView,pressureTextView,humidityTextView,visibilityTextView,sunriseTextView,sunsetTextView,timecheckedTextView,
            day1TextView_day,day1TextView_high,day1TextView_low,
            day2TextView_day,day2TextView_high,day2TextView_low,
            day3TextView_day,day3TextView_high,day3TextView_low,
            day4TextView_day,day4TextView_high,day4TextView_low,
            day5TextView_day,day5TextView_high,day5TextView_low;
    public int code1,code_day1,code_day2,code_day3,code_day4,code_day5;

    private YahooWeatherService weatherService;
    private GoogleMapsGeocodingService geocodingService;
    private WeatherCacheService cacheService;

    public ProgressDialog dialog;
    public Dialog d;

    private int weatherServiceFailures = 0;
    private String temperatureLabel,conditionLabel,windLabel,pressureLabel,humidityLabel,locationLabel,sunriseLabel,sunsetLabel,timeLabel,forecastLabel
            ,day1_dayfinal,day1_highfinal,day1_lowfinal,day2_dayfinal,day2_highfinal,day2_lowfinal,day3_dayfinal,day3_highfinal,day3_lowfinal
            ,day4_dayfinal,day4_highfinal,day4_lowfinal,day5_dayfinal,day5_highfinal,day5_lowfinal;
    private SharedPreferences preferences = null,pref;
    FloatingActionButton fb_temp,fb_typhoonmore;
    Context c;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fam_typhoon,container,false);
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        weatherService = new YahooWeatherService(this);
        geocodingService = new GoogleMapsGeocodingService(this);
        cacheService = new WeatherCacheService(getActivity());

        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        fb_temp = (FloatingActionButton) view.findViewById(R.id.temperatureBtn);
        fb_typhoonmore = (FloatingActionButton) view.findViewById(R.id.typhoonmoreBtn);

        final WebView wview = (WebView) view.findViewById(R.id.webview);
        final WebSettings webSettings = wview.getSettings();
        if (AppStatus.getInstance(getActivity()).isOnline()) {
            webSettings.setEnableSmoothTransition(true);
            wview.getSettings().setJavaScriptEnabled(true);
            wview.setWebViewClient(new MyBrowser());
            wview.loadUrl(" http://mysite.ph/Newsfeed/pagasa.html");
        } else {
            wview.loadUrl( "javascript:window.location.reload( true )" );
            wview.getSettings().setJavaScriptEnabled(true);
            wview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wview.getSettings().setAppCacheEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webSettings.setEnableSmoothTransition(true);
            wview.setWebViewClient(new MyBrowser());
            wview.loadUrl(" http://mysite.ph/Newsfeed/pagasa.html");
        }

        fb_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = new Dialog(getActivity());
                d.setContentView(R.layout.weather_layout);
                d.setTitle("Weather Forecast");
                d.show();
                d.setCanceledOnTouchOutside(true);

                weatherIconImageView = (ImageView) d.findViewById(R.id.imgIcon);
                temperatureTextView = (TextView) d.findViewById(R.id.txtTemperature);
                conditionTextView = (TextView) d.findViewById(R.id.txtCondition);
                locationTextView = (TextView) d.findViewById(R.id.txtLocation);
                windTextView = (TextView) d.findViewById(R.id.txtWind);
                pressureTextView = (TextView) d.findViewById(R.id.txtPressure);
                humidityTextView = (TextView) d.findViewById(R.id.txtHumidity);
                visibilityTextView = (TextView) d.findViewById(R.id.txtVisibility);
                sunriseTextView = (TextView) d.findViewById(R.id.txtSunrise);
                sunsetTextView = (TextView) d.findViewById(R.id.txtSunset);
                timecheckedTextView = (TextView) d.findViewById(R.id.txtLastChecked);

                day1TextView_day = (TextView) d.findViewById(R.id.txtDay1_day);
                day1TextView_high = (TextView) d.findViewById(R.id.txtDay1_high);
                day1TextView_low = (TextView) d.findViewById(R.id.txtDay1_low);
                imageDay1 = (ImageView) d.findViewById(R.id.imgIcon_day1);

                day2TextView_day = (TextView) d.findViewById(R.id.txtDay2_day);
                day2TextView_high = (TextView) d.findViewById(R.id.txtDay2_high);
                day2TextView_low = (TextView) d.findViewById(R.id.txtDay2_low);
                imageDay2 = (ImageView) d.findViewById(R.id.imgIcon_day2);

                day3TextView_day = (TextView) d.findViewById(R.id.txtDay3_day);
                day3TextView_high = (TextView) d.findViewById(R.id.txtDay3_high);
                day3TextView_low = (TextView) d.findViewById(R.id.txtDay3_low);
                imageDay3 = (ImageView) d.findViewById(R.id.imgIcon_day3);

                day4TextView_day = (TextView) d.findViewById(R.id.txtDay4_day);
                day4TextView_high = (TextView) d.findViewById(R.id.txtDay4_high);
                day4TextView_low = (TextView) d.findViewById(R.id.txtDay4_low);
                imageDay4 = (ImageView) d.findViewById(R.id.imgIcon_day4);

                day5TextView_day = (TextView) d.findViewById(R.id.txtDay5_day);
                day5TextView_high = (TextView) d.findViewById(R.id.txtDay5_high);
                day5TextView_low = (TextView) d.findViewById(R.id.txtDay5_low);
                imageDay5 = (ImageView) d.findViewById(R.id.imgIcon_day5);

                dialog = new ProgressDialog(getActivity());
                dialog.setMessage(getString(R.string.loading));
                dialog.setCancelable(false);
                dialog.show();

                String cachedLocation = preferences.getString(getString(R.string.location), null);
                if (cachedLocation == null) {
                    getWeatherFromCurrentLocation();
                } else {
                    weatherService.refreshWeather(cachedLocation);
                }
            }
        });

        fb_typhoonmore.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                WebView wv = new WebView(getActivity());
                WebSettings wbs = wview.getSettings();
                if (AppStatus.getInstance(getActivity()).isOnline()) {
                    wbs.setEnableSmoothTransition(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setWebViewClient(new MyBrowser());
                    wv.loadUrl(" http://mysite.ph/cms/?page_id=6");
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
                    wv.loadUrl(" http://mysite.ph/cms/?page_id=6");
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

    private void getWeatherFromCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria locationCriteria = new Criteria();
        locationCriteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        String provider = locationManager.getBestProvider(locationCriteria, true);
        locationManager.requestSingleUpdate(provider, this, null);
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        Wind wind = channel.getWind();
        Atmosphere atmosphere = channel.getAtmosphere();

        code1 = item.getCondition().getCode();
        int resourceId = getResources().getIdentifier("drawable/icon_" + code1, null, getActivity().getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureLabel = getResources().getString(R.string.temperature_output, item.getCondition().getTemperature(), channel.getUnits().getTemperature());
        conditionLabel = item.getCondition().getDescription();
        windLabel = wind.getSpeed()+ channel.getUnits().getSpeed();
        pressureLabel = atmosphere.getPressure()+channel.getUnits().getPressure();
        humidityLabel = atmosphere.getHumidity()+"%";
        locationLabel = channel.getLocation();
        sunriseLabel = channel.getAstronomy().getSunrise();
        sunsetLabel = channel.getAstronomy().getSunset();
        timeLabel = item.getCondition().getDate();
        forecastLabel = "<html>\n<body>\n"+item.getDescription()+"\n</body>\n</html>";
        Spanned sp = Html.fromHtml(forecastLabel);



        temperatureTextView.setText(temperatureLabel);
        conditionTextView.setText(conditionLabel);
        windTextView.setText(windLabel);
        pressureTextView.setText(pressureLabel);
        humidityTextView.setText(humidityLabel);
        locationTextView.setText(locationLabel);
        //visibilityTextView.setText(atmosphere.getVisibility()+"%");
        sunriseTextView.setText(sunriseLabel);
        sunsetTextView.setText(sunsetLabel);
        timecheckedTextView.setText(timeLabel);

        String fore = item.getForecast();
        String forecast = fore.substring(1, fore.length()-1).replace("\"", "");;
        String forecast_array[]=forecast.substring(1, forecast.length()-1).split("\\{");

        String day1 = forecast_array[1];
        String day1_array[] = day1.substring(0, day1.length()-2).split(",");
        String day1_code = day1_array[0];
        String day1_day = day1_array[2];
        String day1_high = day1_array[3];
        String day1_low = day1_array[4];

        String day1_code_array[] = day1_code.split(":");
        code_day1 =  Integer.parseInt(day1_code_array[1]);
        String day1_day_array[] = day1_day.split(":");
        String day1_high_array[] = day1_high.split(":");
        String day1_low_array[] = day1_low.split(":");
        day1_dayfinal = day1_day_array[1];
        day1_highfinal = day1_high_array[1]+"°C";
        day1_lowfinal = day1_low_array[1]+"°C";
        day1TextView_day.setText(day1_dayfinal);
        day1TextView_high.setText(day1_highfinal);
        day1TextView_low.setText(day1_lowfinal);

        int resourceId2 = getResources().getIdentifier("drawable/forecast_" + code_day1, null, getActivity().getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable2 = getResources().getDrawable(resourceId2);
        imageDay1.setImageDrawable(weatherIconDrawable2);

        String day2 = forecast_array[2];
        String day2_array[] = day2.substring(0, day2.length()-2).split(",");
        String day2_code = day2_array[0];
        String day2_day = day2_array[2];
        String day2_high = day2_array[3];
        String day2_low = day2_array[4];

        String day2_code_array[] = day2_code.split(":");
        code_day2 =  Integer.parseInt(day2_code_array[1]);
        String day2_day_array[] = day2_day.split(":");
        String day2_high_array[] = day2_high.split(":");
        String day2_low_array[] = day2_low.split(":");
        day2_dayfinal = day2_day_array[1];
        day2_highfinal = day2_high_array[1]+"°C";
        day2_lowfinal = day2_low_array[1]+"°C";
        day2TextView_day.setText(day2_dayfinal);
        day2TextView_high.setText(day2_highfinal);
        day2TextView_low.setText(day2_lowfinal);

        int resourceId3 = getResources().getIdentifier("drawable/forecast_" + code_day2, null, getActivity().getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable3 = getResources().getDrawable(resourceId3);
        imageDay2.setImageDrawable(weatherIconDrawable3);

        String day3 = forecast_array[3];
        String day3_array[] = day3.substring(0, day3.length()-2).split(",");
        String day3_code = day3_array[0];
        String day3_day = day3_array[2];
        String day3_high = day3_array[3];
        String day3_low = day3_array[4];

        String day3_code_array[] = day3_code.split(":");
        code_day3 =  Integer.parseInt(day3_code_array[1]);
        String day3_day_array[] = day3_day.split(":");
        String day3_high_array[] = day3_high.split(":");
        String day3_low_array[] = day3_low.split(":");
        day3_dayfinal = day3_day_array[1];
        day3_highfinal = day3_high_array[1]+"°C";
        day3_lowfinal = day3_low_array[1]+"°C";
        day3TextView_day.setText(day3_dayfinal);
        day3TextView_high.setText(day3_highfinal);
        day3TextView_low.setText(day3_lowfinal);

        int resourceId4 = getResources().getIdentifier("drawable/forecast_" + code_day3, null, getActivity().getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable4 = getResources().getDrawable(resourceId4);
        imageDay3.setImageDrawable(weatherIconDrawable4);


        String day4 = forecast_array[4];
        String day4_array[] = day4.substring(0, day4.length()-2).split(",");
        String day4_code = day4_array[0];
        String day4_day = day4_array[2];
        String day4_high = day4_array[3];
        String day4_low = day4_array[4];

        String day4_code_array[] = day4_code.split(":");
        code_day4 =  Integer.parseInt(day4_code_array[1]);
        String day4_day_array[] = day4_day.split(":");
        String day4_high_array[] = day4_high.split(":");
        String day4_low_array[] = day4_low.split(":");
        day4_dayfinal = day4_day_array[1];
        day4_highfinal = day4_high_array[1]+"°C";
        day4_lowfinal = day4_low_array[1]+"°C";
        day4TextView_day.setText(day4_dayfinal);
        day4TextView_high.setText(day4_highfinal);
        day4TextView_low.setText(day4_lowfinal);

        int resourceId5 = getResources().getIdentifier("drawable/forecast_" + code_day4, null, getActivity().getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable5 = getResources().getDrawable(resourceId5);
        imageDay4.setImageDrawable(weatherIconDrawable5);


        String day5 = forecast_array[5];
        String day5_array[] = day5.substring(0, day5.length()-2).split(",");
        String day5_code = day5_array[0];
        String day5_day = day5_array[2];
        String day5_high = day5_array[3];
        String day5_low = day5_array[4];

        String day5_code_array[] = day5_code.split(":");
        code_day5 =  Integer.parseInt(day5_code_array[1]);
        String day5_day_array[] = day5_day.split(":");
        String day5_high_array[] = day5_high.split(":");
        String day5_low_array[] = day5_low.split(":");
        day5_dayfinal = day5_day_array[1];
        day5_highfinal = day5_high_array[1]+"°C";
        day5_lowfinal = day5_low_array[1]+"°C";
        day5TextView_day.setText(day5_dayfinal);
        day5TextView_high.setText(day5_highfinal);
        day5TextView_low.setText(day5_lowfinal);

        int resourceId6 = getResources().getIdentifier("drawable/forecast_" + code_day5, null, getActivity().getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable6 = getResources().getDrawable(resourceId6);
        imageDay5.setImageDrawable(weatherIconDrawable6);


        SharedPreferences.Editor editor = pref.edit();
        editor.putString("code1",code1+"");
        editor.putString("temperature", temperatureLabel);
        editor.putString("condition", conditionLabel);
        editor.putString("wind", windLabel);
        editor.putString("pressure", pressureLabel);
        editor.putString("humidity", humidityLabel);
        editor.putString("location", locationLabel);
        editor.putString("sunrise", sunriseLabel);
        editor.putString("sunset", sunsetLabel);
        editor.putString("time", timeLabel);

        editor.putString("codeday1", code_day1+"");
        editor.putString("day1dayfinal", day1_dayfinal);
        editor.putString("day1highfinal", day1_highfinal);
        editor.putString("day1lowfinal", day1_lowfinal);

        editor.putString("codeday2", code_day2+"");
        editor.putString("day2dayfinal", day2_dayfinal);
        editor.putString("day2highfinal", day2_highfinal);
        editor.putString("day2lowfinal", day2_lowfinal);

        editor.putString("codeday3", code_day3+"");
        editor.putString("day3dayfinal", day3_dayfinal);
        editor.putString("day3highfinal", day3_highfinal);
        editor.putString("day3lowfinal", day3_lowfinal);

        editor.putString("codeday4", code_day4+"");
        editor.putString("day4dayfinal", day4_dayfinal);
        editor.putString("day4highfinal", day4_highfinal);
        editor.putString("day4lowfinal", day4_lowfinal);

        editor.putString("codeday5", code_day5+"");
        editor.putString("day5dayfinal", day5_dayfinal);
        editor.putString("day5highfinal", day5_highfinal);
        editor.putString("day5lowfinal", day5_lowfinal);
        editor.commit();
    }

    @Override
    public void serviceFailure(Exception exception) {
        // display error if this is the second failure
        if(!pref.getString("code1","").isEmpty()) {
        if (weatherServiceFailures > 0) {
            dialog.hide();

                int resourceId0 = getResources().getIdentifier("drawable/icon_" + pref.getString("code1", ""), null, getActivity().getPackageName());
                @SuppressWarnings("deprecation")
                Drawable weatherIconDrawable0 = getResources().getDrawable(resourceId0);
                weatherIconImageView.setImageDrawable(weatherIconDrawable0);

                int resourceId1 = getResources().getIdentifier("drawable/forecast_" + pref.getString("codeday1", ""), null, getActivity().getPackageName());
                @SuppressWarnings("deprecation")
                Drawable weatherIconDrawable1 = getResources().getDrawable(resourceId1);
                imageDay1.setImageDrawable(weatherIconDrawable1);

                int resourceId2 = getResources().getIdentifier("drawable/forecast_" + pref.getString("codeday2", ""), null, getActivity().getPackageName());
                @SuppressWarnings("deprecation")
                Drawable weatherIconDrawable2 = getResources().getDrawable(resourceId2);
                imageDay2.setImageDrawable(weatherIconDrawable2);

                int resourceId3 = getResources().getIdentifier("drawable/forecast_" + pref.getString("codeday3", ""), null, getActivity().getPackageName());
                @SuppressWarnings("deprecation")
                Drawable weatherIconDrawable3 = getResources().getDrawable(resourceId3);
                imageDay3.setImageDrawable(weatherIconDrawable3);

                int resourceId4 = getResources().getIdentifier("drawable/forecast_" + pref.getString("codeday4", ""), null, getActivity().getPackageName());
                @SuppressWarnings("deprecation")
                Drawable weatherIconDrawable4 = getResources().getDrawable(resourceId4);
                imageDay4.setImageDrawable(weatherIconDrawable4);

                int resourceId5 = getResources().getIdentifier("drawable/forecast_" + pref.getString("codeday5", ""), null, getActivity().getPackageName());
                @SuppressWarnings("deprecation")
                Drawable weatherIconDrawable5 = getResources().getDrawable(resourceId5);
                imageDay5.setImageDrawable(weatherIconDrawable5);

                temperatureTextView.setText(pref.getString("temperature", ""));
                conditionTextView.setText(pref.getString("condition", ""));
                windTextView.setText(pref.getString("wind", ""));
                pressureTextView.setText(pref.getString("pressure", ""));
                humidityTextView.setText(pref.getString("humidity", ""));
                locationTextView.setText(pref.getString("location", ""));
                //visibilityTextView.setText(atmosphere.getVisibility()+"%");
                sunriseTextView.setText(pref.getString("sunrise", ""));
                sunsetTextView.setText(pref.getString("sunset", ""));
                timecheckedTextView.setText(pref.getString("time", ""));

                day1TextView_day.setText(pref.getString("day1dayfinal", ""));
                day1TextView_high.setText(pref.getString("day1highfinal", ""));
                day1TextView_low.setText(pref.getString("day1lowfinal", ""));

                day2TextView_day.setText(pref.getString("day2dayfinal", ""));
                day2TextView_high.setText(pref.getString("day2highfinal", ""));
                day2TextView_low.setText(pref.getString("day2lowfinal", ""));

                day3TextView_day.setText(pref.getString("day3dayfinal", ""));
                day3TextView_high.setText(pref.getString("day3highfinal", ""));
                day3TextView_low.setText(pref.getString("day3lowfinal", ""));

                day4TextView_day.setText(pref.getString("day4dayfinal", ""));
                day4TextView_high.setText(pref.getString("day4highfinal", ""));
                day4TextView_low.setText(pref.getString("day4lowfinal", ""));

                day5TextView_day.setText(pref.getString("day5dayfinal", ""));
                day5TextView_high.setText(pref.getString("day5highfinal", ""));
                day5TextView_low.setText(pref.getString("day5lowfinal", ""));

        } else {
            weatherServiceFailures++;
            cacheService.load(this);
        }
        }else{
            Toast.makeText(getActivity(),"PLEASE CHECK YOU INTERNET CONNECTION",Toast.LENGTH_LONG).show();
            dialog.hide();
            d.hide();

        }
    }

    @Override
    public void geocodeSuccess(LocationResult location) {
        weatherService.refreshWeather(location.getAddress());

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.location), location.getAddress());
        editor.commit();
    }

    @Override
    public void geocodeFailure(Exception exception) {
        cacheService.load(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        geocodingService.refreshLocation(location);
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}
    @Override
    public void onProviderEnabled(String s) {}
    @Override
    public void onProviderDisabled(String s) {}

}