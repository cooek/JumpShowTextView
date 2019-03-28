package com.dazhou.chenke.test.Utlis;

import android.widget.TextView;

public class Test {

    private static TextView mtextView;
    private static String info;
    static  int connt = 0;
    private static int connts;
    private static int length;
    private static  long time;

    public Test(TextView textView,String text ,long time) {
        this.mtextView = textView;
        this.info = text;
        this.time = time;
        this.length= text.length();
        startTv(connt);
    }

    public static void startTv(final int conntso) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String stv = info.substring(0, conntso);
                            if (mtextView != null) {
                            mtextView.post(new Runnable() {
                                @Override
                                public void run() {
                                    mtextView.setText(stv);
                                }
                            });
                            }
                            Thread.sleep(time);
                            connts = conntso + 1;
                            if (connts <= length) {
                                startTv(connts);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }
}

