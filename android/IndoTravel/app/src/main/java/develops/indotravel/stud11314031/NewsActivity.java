package develops.indotravel.stud11314031;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://news.detik.com/berita/d-3500649/menhub-harap-sistem-bike-sharing-di-china-bisa-ada-di-kampus-ri");
    }
}
