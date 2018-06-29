package com.joel.phonerecord;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import com.joel.phonerecord.SensorManagerHelper.OnShakeListener;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private MediaRecorder mediaRecorder;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service=new Intent(this,RecordService.class);
        init();
        startService(service);

    }
    private void init()
    {
        SensorManagerHelper shakeListener = new SensorManagerHelper(this);
        shakeListener.setOnShakeListener(new OnShakeListener()
        {
            // 调用setOnShakeListener方法进行监听
            public void onShake()
            {
                java.util.Timer t=new  java.util.Timer();
                t.schedule(task,0,600000);
                mediaRecorder.stop();
                mediaRecorder.release();
            }
            TimerTask task= new TimerTask(){
                @Override
                public void run() {
                    try
                    {
                        file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".3gp");
                        mediaRecorder = new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);   //获得声音数据源
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);   // 按3gp格式输出
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mediaRecorder.setOutputFile(file.getAbsolutePath());   //输出文件
                        mediaRecorder.prepare();    //准备
                        mediaRecorder.start();
                    }
                    catch (IllegalStateException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        });
    }


}
