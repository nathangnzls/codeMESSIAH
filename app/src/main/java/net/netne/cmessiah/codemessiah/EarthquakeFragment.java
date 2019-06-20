package net.netne.cmessiah.codemessiah;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.clans.fab.FloatingActionButton;


public class EarthquakeFragment extends Fragment {

    FloatingActionButton fb_eq_tips,fb_volc_tips,fb_volc_prec;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fam_eq_volc,container,false);

        fb_eq_tips = (FloatingActionButton) view.findViewById(R.id.EarthquakeBtn);
        fb_volc_tips = (FloatingActionButton) view.findViewById(R.id.VolcBtn);
        fb_volc_prec = (FloatingActionButton) view.findViewById(R.id.VolcPrecBtn);

        fb_volc_prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                WebView wv = new WebView(getActivity());
                WebSettings wbs = wv.getSettings();
                if (AppStatus.getInstance(getActivity()).isOnline()) {
                    wbs.setEnableSmoothTransition(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setWebViewClient(new MyBrowser());
                    wv.loadUrl("http://mysite.ph/cms/?page_id=307");
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
                    wv.loadUrl("http://mysite.ph/cms/?page_id=307");
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

        fb_eq_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                WebView wv = new WebView(getActivity());
                WebSettings wbs = wv.getSettings();
                if (AppStatus.getInstance(getActivity()).isOnline()) {
                    wbs.setEnableSmoothTransition(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setWebViewClient(new MyBrowser());
                    wv.loadUrl("http://mysite.ph/cms/?page_id=274");
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
                    wv.loadUrl("http://mysite.ph/cms/?page_id=274");
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

        fb_volc_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                WebView wv = new WebView(getActivity());
                WebSettings wbs = wv.getSettings();
                if (AppStatus.getInstance(getActivity()).isOnline()) {
                    wbs.setEnableSmoothTransition(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setWebViewClient(new MyBrowser());
                    wv.loadUrl(" http://mysite.ph/cms/?page_id=19");
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
                    wv.loadUrl("http://mysite.ph/cms/?page_id=19");
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

        final WebView wview = (WebView) view.findViewById(R.id.webview2);
        final WebSettings webSettings = wview.getSettings();
        if (AppStatus.getInstance(getActivity()).isOnline()) {
            webSettings.setEnableSmoothTransition(true);
            wview.getSettings().setJavaScriptEnabled(true);
            wview.setWebViewClient(new MyBrowser());
            wview.loadUrl(" http://mysite.ph/Newsfeed/phivolcs.html");
        } else {
            wview.loadUrl( "javascript:window.location.reload( true )" );
            wview.getSettings().setJavaScriptEnabled(true);
            wview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wview.getSettings().setAppCacheEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webSettings.setEnableSmoothTransition(true);
            wview.setWebViewClient(new MyBrowser());
            wview.loadUrl("http://mysite.ph/Newsfeed/phivolcs.html");
        }

        return view;
    }
}