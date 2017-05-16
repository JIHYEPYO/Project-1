package com.example.ncs.project1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextURL;
    Button buttonSearch;
    WebView webView;

    private Handler handler;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextURL = (EditText) findViewById(R.id.EditTextURL);
        buttonSearch = (Button) findViewById(R.id.ButtonSearch);
        webView = (WebView) findViewById(R.id.WebView);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);

        flag=false;

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    flag=false;
                }
            }
        };

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editTextURL.getText().toString());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return false;
            }else {
                if (!flag) {
                    Toast.makeText(this, "'뒤로가기' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show();
                    flag = true;
                    handler.sendEmptyMessageDelayed(0, 2000);
                    return false;
                } else {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showFavorites:
                startActivity(new Intent(this, FavoritesActivity.class));
                return true;
            case R.id.registerFavorites:
                startActivity(new Intent(this, FavoritesActivity.class));
                return true;
            case R.id.removeFavorites:
                startActivity(new Intent(this, FavoritesActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
