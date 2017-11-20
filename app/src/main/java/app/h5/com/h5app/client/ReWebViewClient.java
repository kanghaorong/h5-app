package app.h5.com.h5app.client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by kanghaorong on 2017/11/17.
 */

public class ReWebViewClient extends WebViewClient{
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url != null && url.startsWith("mailto:")
                || url.startsWith("geo:") || url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                    .parse(url));
            view.getContext().startActivity(intent);
            return true;
        }
        view.loadUrl(url);
        return true;
    }
}
