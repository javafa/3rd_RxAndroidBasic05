package net.flow9.rxandroidbasic05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // 얘는 머하는 애
    PublishSubject<String> publishSubject = PublishSubject.create();
    // 너는 머냐...
    public void doPublish(View view){
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    publishSubject.onNext("A" + i); // 발행
                    Log.i("Publish", "A" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {/* */}
                }
            }
        }.start();
    }
    // 구독
    public void getPublish(View view){
        publishSubject.subscribe(
                item -> Log.i("Subscribe", "item="+item)
        );
    }

    public void doBehavior(View view){

    }

    public void doReplay(View view){

    }

    public void doAsync(View view){

    }
}
