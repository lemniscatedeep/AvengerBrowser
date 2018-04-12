package com.example.daman.avengerbrowser;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go,forward,back,clear,reload;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        brow = (WebView) findViewById(R.id.wv_brow);
        urledit = (EditText) findViewById(R.id.btn_url);
        go = (Button) findViewById(R.id.btn_go);
        forward = (Button) findViewById((R.id.fwd_bttn));
        back = (Button) findViewById(R.id.back_bttn);
        clear = (Button) findViewById(R.id.clr_bttn);
        reload = (Button) findViewById(R.id.re_bttn);

        WebSettings webSettings = brow.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //for open the page in the same browser, we use webviewClient
        brow.setWebViewClient(new ourViewClient());
        //for progress bar, we use webchromeclient
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        brow.loadUrl("http://www.google.com");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextValue = urledit.getText().toString();

                if(!editTextValue.startsWith("http://")){
                    editTextValue = "http://" + editTextValue;
                }
                String url = editTextValue;
                brow.loadUrl(url);

                //hide the keyboard after searching

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brow.canGoForward()){
                    brow.goForward();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brow.canGoBack()){
                    brow.goBack();
                }
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.reload();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.clearHistory();
            }
        });
    }
}
