package com.example.klem.webtesting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView edthtml;
    WebView webView;
    Button html;
    Button page;
    EditText editUrl;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        html = (Button) findViewById(R.id.html);
        edthtml = (TextView) findViewById(R.id.edthtml);
        page = (Button) findViewById(R.id.page);
        editUrl = (EditText) findViewById(R.id.edt);
        webView = (WebView) findViewById(R.id.web);

        html.setOnClickListener(listener);
        page.setOnClickListener(listener);
    }

    OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.html:
                    url = "http://" + String.valueOf(editUrl.getText());
                    HttpMy(url);
                    break;
                case R.id.page:
                    url = "http://" + String.valueOf(editUrl.getText());
                    WebMy(url);
            }
        }
    };

    public void HttpMy(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        edthtml.setText(response.substring(0));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                edthtml.setText("Не корректный URL");
            }
        });
        queue.add(stringRequest);
    }

    public void WebMy(String url){
        Intent intent = new
                Intent(getApplicationContext().getPackageName());
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
