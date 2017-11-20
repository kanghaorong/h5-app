package app.h5.com.h5app.client;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by kanghaorong on 2017/11/17.
 */

public class ReWebChomeClient extends WebChromeClient{

    private ProgressBar mProgress;
    private WebView mWebview;


    private OpenFileChooserCallBack mOpenFileChooserCallBack;


    public ReWebChomeClient(Context context, WebView webView, OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
        mProgress = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgress.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 30));
        mWebview =webView;
        mWebview.addView(mProgress);
    }

    public void onProgressChanged(WebView view, int progress) {
        if (progress == 100) {
            mProgress.setVisibility(View.GONE);
        } else {
            if (View.GONE == mProgress.getVisibility()) {
                mProgress.setVisibility(View.VISIBLE);
            }
            mProgress.setProgress(progress);
        }
    }

    //For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
    }


    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }


    // For Android  > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }


    // For Android 5.0+
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams


            fileChooserParams) {
        mOpenFileChooserCallBack.showFileChooserCallBack(filePathCallback);
        return true;
    }


    public interface OpenFileChooserCallBack {
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);


        void showFileChooserCallBack(ValueCallback<Uri[]> filePathCallback);
    }
}
