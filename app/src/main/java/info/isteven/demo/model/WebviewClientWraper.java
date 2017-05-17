package info.isteven.demo.model;

import android.content.res.AssetManager;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/4/18.
 */

public class WebviewClientWraper extends WebViewClient {

    public final static String BaseUrl = "http://www.bitonair.com/";
    private AssetManager ams = null;

    public WebviewClientWraper(AssetManager ams) {
        this.ams = ams;
    }

    private final static Map<String, Object> urlTailMap = new HashMap<String, Object>() {
        {
            put("html", "html");
            put("js", "js");
            put("css", "css");
        }
    };

    private final static List<String> urlTailList = new ArrayList<String>() {
        {
            add("html");
//            add("js");
            add("css");
        }
    };

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        String urlTail = url.substring(url.lastIndexOf(".") + 1);
        if (urlTailList.contains(urlTail)) {
            InputStream is = null;
            if ("html".equals(urlTail)) {
                try {
                    is = ams.open("tpl/file_http.html");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return new WebResourceResponse("text/html", "utf-8", is);
        } else {
            return super.shouldInterceptRequest(view, url);
        }
    }
}
