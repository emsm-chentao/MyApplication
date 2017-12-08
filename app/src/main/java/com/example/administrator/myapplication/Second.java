package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by Administrator on 2017/11/23.
 */

public class Second extends AppCompatActivity {
    private static final String tagMsg = "CTSecondActivity===";
    public static final int PHOTO_REQUEST_GALLERY = 111;// 从相册中选择

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("CT==", tagMsg + "onCreate");
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tcv);
        textView.setText("Second");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });

        findViewById(R.id.tcv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        MyBroadRevice myBroadRevice = new MyBroadRevice();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ct.text");
        registerReceiver(myBroadRevice, intentFilter);

        Intent intent = new Intent();
        intent.setAction("com.ct.text");
        sendBroadcast(intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Second.PHOTO_REQUEST_GALLERY:// 相机
                if (resultCode == RESULT_OK) {
                    String path = null;
                    try {
                        path = getFilePathByUri(data.getData());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    final String url = "http://192.168.1.171:8080/web-app/goodsBandController/textpost.action"; //此处写上自己的URL

                    final Map<String, Object> paramMap = new HashMap<>(); //文本数据全部添加到Map里
                    paramMap.put("id", "12");
                    paramMap.put("name", "text测试樱花");
                    paramMap.put("content", "multipartcom");
                    final File pictureFile = new File(path); //通过路径获取文件
                    paramMap.put("filename", pictureFile);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                imageAndTextPost(url, paramMap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 获取文件路径通过u　rl
    private String getFilePathByUri(Uri mUri) throws FileNotFoundException {
        Cursor cursor = getApplicationContext().getContentResolver().query(mUri, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("CT==", tagMsg + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("CT==", tagMsg + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("CT==", tagMsg + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("CT==", tagMsg + "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("CT==", tagMsg + "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("CT==", tagMsg + "onDestroy");
    }


    private void imageAndTextPost(String url, Map<String, Object> params) throws IOException {
        HttpURLConnection conn = null;
        DataOutputStream dos = null;

        URL u = new URL(url);
        conn = (HttpURLConnection) u.openConnection();

        conn.setInstanceFollowRedirects(false);

        conn.setConnectTimeout(NET_TIMEOUT * 4);

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);

        dos = new DataOutputStream(conn.getOutputStream());

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            writeObject(dos, entry.getKey(), entry.getValue());
        }

        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
        dos.flush();
        dos.close();
        conn.connect();

        final int code = conn.getResponseCode();
        final String message = conn.getResponseMessage();
        String encoding = conn.getContentEncoding();

        byte[] data = toData(encoding, conn.getInputStream());
        final String t = new String(data);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), code + "code====message" + message + "t＝＝" + t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private byte[] toData(String encoding, InputStream is) throws IOException {

        boolean gzip = "gzip".equalsIgnoreCase(encoding);

        if (gzip) {
            is = new GZIPInputStream(is);
        }

        return toBytes(is);
    }

    private int NET_TIMEOUT = 30000;
    private final String lineEnd = "\r\n";
    private final String twoHyphens = "--";
    private final String boundary = "*****";

    public byte[] toBytes(InputStream is) {
        byte[] result = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            copy(is, baos);
            result = baos.toByteArray();
        } catch (IOException e) {

        }

        close(is);

        return result;
    }

    public void close(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
        }
    }

    private void writeObject(DataOutputStream dos, String name, Object obj) throws IOException {
        if (obj == null) return;
        if (obj instanceof File) {
            File file = (File) obj;
            writeData(dos, name, file.getName(), new FileInputStream(file));

        } else if (obj instanceof byte[]) {
            writeData(dos, name, name, new ByteArrayInputStream((byte[]) obj));
        } else if (obj instanceof InputStream) {
            writeData(dos, name, name, (InputStream) obj);
        } else {
            writeField(dos, name, obj.toString());
        }
    }

    private void writeData(DataOutputStream dos, String name, String filename, InputStream is) throws IOException {
        dos.writeBytes(twoHyphens + boundary + lineEnd);
        dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\";" + " filename=\"" + filename + "\"" + lineEnd);

        //added to specify type
        dos.writeBytes("Content-Type: application/octet-stream");
        dos.writeBytes(lineEnd);
        dos.writeBytes("Content-Transfer-Encoding: binary");
        dos.writeBytes(lineEnd);

        dos.writeBytes(lineEnd);

        copy(is, dos);

        dos.writeBytes(lineEnd);
    }

    private void writeField(DataOutputStream dos, String name, String value) throws IOException {
        dos.writeBytes(twoHyphens + boundary + lineEnd);
        dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"");
        dos.writeBytes(lineEnd);
        dos.writeBytes(lineEnd);

        byte[] data = value.getBytes("UTF-8");
        dos.write(data);

        dos.writeBytes(lineEnd);
    }

    public void copy(InputStream in, OutputStream out) throws IOException {
        copy(in, out, 0);
    }

    private final int IO_BUFFER_SIZE = 1024 * 4;

    public void copy(InputStream in, OutputStream out, int max) throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }
}

