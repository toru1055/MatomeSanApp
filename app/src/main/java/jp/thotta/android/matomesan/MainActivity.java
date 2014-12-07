package jp.thotta.android.matomesan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                openPageActivity(url);
                return true;
            }

            @Override
            public void onPageStarted (WebView view, String url, Bitmap favicon) {
                showMessage("Loading...");
            }

            @Override
            public void onPageFinished (WebView view, String url) {
                showMessage("Load Complete.");
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getString(R.string.HOME_URL));
    }

    private void showMessage(String m) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }

    private void openPageActivity(String url) {
        Intent intent = new Intent(this, PageActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.action_refresh) {
            debugLog("[onOptionsItemSelected] Action Refresh was called.");
            WebView webView = (WebView) findViewById(R.id.webView);
            webView.loadUrl(getString(R.string.HOME_URL));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void debugLog(String log) {
        Log.d("MatomeSan", log);
    }
}
