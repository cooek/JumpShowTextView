package com.dazhou.chenke.test.Utlis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.constraint.Placeholder;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dazhou.chenke.test.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class JumpShowTextView extends FrameLayout {
    public Disposable mDisposable;
    ArrayList<String> content;
    String text;
    TextView mTextView;
    TextView mTextViewtwo;
    boolean isBoid;
    boolean isSinglen;
    int textColor;
    float textSiza;
    Context mContext;
    TypedArray mTypedArray;
    long time;
    boolean withanmation = true;
    String finnalReal;
    int counnt =0;
    int counnts= 0;
    boolean isRun =false;



    public JumpShowTextView(Context context) {
        this(context,null);
    }

    public JumpShowTextView( Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mTypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.JumpShowTextView);
        textSiza = mTypedArray.getDimension(R.styleable.JumpShowTextView_textSize, 20);
        textColor = mTypedArray.getColor(R.styleable.JumpShowTextView_textColor, Color.WHITE);
        isBoid = mTypedArray.getBoolean(R.styleable.JumpShowTextView_ISBold, false);
        isSinglen = mTypedArray.getBoolean(R.styleable.JumpShowTextView_IsSinglen, false);
        careatText();
        initView();
    }
    public JumpShowTextView( Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }
    public void setWithanmation(boolean withanmation){
                 this.withanmation =withanmation;
    }
    public void setTest(String text){
        this.text =text;
        mTextView.setText(text);
        mTextView.setVisibility(INVISIBLE);
        startView();
    }
    private void careatText() {
        mTextView = new TextView(mContext);
        mTextView.getPaint().setTextSize(textSiza);
        mTextView.getPaint().setShadowLayer(5, 0, 0, Color.BLACK);
        mTextView.setSingleLine(isSinglen);
        mTextView.setTextColor(textColor);
        mTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        mTextView.setVisibility(INVISIBLE);
        mTextViewtwo = new TextView(mContext);
        mTextViewtwo.getPaint().setTextSize(textSiza);
        mTextViewtwo.getPaint().setShadowLayer(5, 0, 0, Color.BLACK);
        mTextViewtwo.setSelected(isSinglen);
        mTextViewtwo.setTextColor(textColor);
        mTextViewtwo.getPaint().setFakeBoldText(isBoid);
        mTextViewtwo.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

    }

    private void initView() {
        removeAllViews();
        addView(mTextView);
        addView(mTextViewtwo);
    }
    private void startView() {
        counnt=0;
        if (withanmation){
            if (isRun){
                if (mDisposable != null&&mDisposable.isDisposed()) {
                     mDisposable.dispose();
                     isRun =false;
                     mTextViewtwo.setText(text);
                }
            }else {
                isRun =true;
                mDisposable = Flowable.interval(100, TimeUnit.MILLISECONDS)
                        .take(text.length())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                counnts=  counnt++;
                                if (counnts<=text.length()){
                                    finnalReal = text.substring(0,counnts);
                                }else {
                                    finnalReal =text;
                                    isRun = false;
                                }
                                mTextViewtwo.setText(finnalReal);
                            }
                        });
            }
            withanmation = false;
        }else {
            if (mDisposable != null&&!mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
            mTextViewtwo.setText(text);
        }
    }
    public void stop(){
        if (mDisposable != null&&!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        isRun =false;
    }



    public void SetStart(){
        mTextView.setText(text);
    }


}
