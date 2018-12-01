package ru.level3.lesson2.lesson2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

    public
    //Создание переменных
    EditText txtEdit;
    TextView txtView;
    private Observable<String> observable;
    private Observer<String> observer;
    private Disposable disposable;
    private Subscription subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtEdit = findViewById(R.id.textEdit);
        txtView = findViewById(R.id.textView);


        txtEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                observable = Observable.just(charSequence.toString());
                setSubscribe();

            }

            @Override
            public void afterTextChanged(Editable editable) {

                disposable.dispose();

            }
        });



    }

    private void setSubscribe() {

        try {
            observer = new Observer<String>() {

                @Override
                public void onSubscribe(Disposable d) {
                    disposable = d;
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    txtView.setText(s);
                }
            };

            observable.subscribe(observer);
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
