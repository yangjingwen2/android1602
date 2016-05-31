package com.androidxx.yangjw.uploadokhttpdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "amdroidxx";
    private ImageView mShowImg;
    public static final int CHOOSE_PIC = 1;
    private OkHttpClient client = new OkHttpClient();
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowImg = (ImageView) findViewById(R.id.upload_show_pic_img);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.upload_choose_pic_btn:
                choosePic();
                break;
            case R.id.upload_upload_btn:
                try {
                    uploadImage() ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 图片上传
     */
    private void uploadImage() throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        MediaType mediaType = MediaType.parse("image/*");
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = inputStream.read(buffer)) != -1) {
            byteStream.write(buffer,0,len);
            byteStream.flush();
        }
        byte[] bytes = byteStream.toByteArray();
        /**
         * 参数一：上传的名称
         * 参数二：文件名:上传到服务器的文件命名
         * 参数三：需要上传的文件
         */
        builder.addFormDataPart("abc","abc.jpg", RequestBody.create(mediaType,bytes));
        //上传多张图片，将此代码执行多次
//        builder.addFormDataPart("bcd","android.jpg", RequestBody.create(mediaType,bytes));
        MultipartBody multipartBody = builder.build();

        Request request = new Request.Builder().post(multipartBody)
                .url("http://192.168.42.1:8080/WebServer/upload.do")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 选择图片
     */
    private void choosePic() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mShowImg.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
