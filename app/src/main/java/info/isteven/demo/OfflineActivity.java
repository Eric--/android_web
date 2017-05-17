package info.isteven.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import info.isteven.demo.model.WebviewClientWraper;

public class OfflineActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebviewClientWraper(getAssets()));
        webView.loadUrl(WebviewClientWraper.BaseUrl + "file_http.html");

        setContentView(webView);
    }
}
