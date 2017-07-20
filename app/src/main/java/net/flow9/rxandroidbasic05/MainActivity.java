package net.flow9.rxandroidbasic05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

import static java.lang.Thread.sleep;

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
                for (int i = 0; i < 10; i++) {
                    Log.i("Publish", "A" + i);
                    publishSubject.onNext("A" + i); // 발행
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
    //------------------------------------------------------
    BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
    // 발행
    public void doBehavior(View view){
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.i("Behavior", "B" + i);
                    behaviorSubject.onNext("B" + i); // 발행
                    try { Thread.sleep(1000); } catch (InterruptedException e) {/* */}
                }
            }
        }.start();
    }
    public void getBehavior(View view){
        behaviorSubject.subscribe(
                item -> Log.i("Behavior", "item="+item)
        );
    }
    //------------------------------------------------------
    ReplaySubject<String> replaySubject = ReplaySubject.create();
    public void doReplay(View view){
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.i("Replay", "C" + i);
                    replaySubject.onNext("C" + i); // 발행
                    try { Thread.sleep(1000); } catch (InterruptedException e) {/* */}
                }
            }
        }.start();
    }
    public void getReplay(View view){
        replaySubject.subscribe(
                item -> Log.i("Replay", "item="+item)
        );
    }
    //------------------------------------------------------
    AsyncSubject<String> asyncSubject = AsyncSubject.create();
    public void doAsync(View view){
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.i("Async", "D" + i);
                    asyncSubject.onNext("D" + i); // 발행
                    try { Thread.sleep(1000); } catch (InterruptedException e) {/* */}
                }
                asyncSubject.onComplete();
            }
        }.start();
    }
    public void getAsync(View view){
        asyncSubject.subscribe(
                item -> Log.i("Replay", "item="+item)
        );
    }
}
