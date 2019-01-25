package com.jiayu.workhome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kangkang
 * @since 2018-10-28
 */
public class WorkhomeController {
    @FXML
    private Button pausePrintBtn;

    @FXML
    private Button startPrintBtn;

    @FXML
    private Text tipMsgText;

    final AtomicBoolean pauseFlag = new AtomicBoolean(false);
    final AtomicInteger count = new AtomicInteger(0);

    @FXML
    public void pausePrint(ActionEvent actionEvent) {
        if (pauseFlag.get()) {
            pauseFlag.set(Boolean.FALSE);
            pausePrintBtn.setText("暂停");
            synchronized (pauseFlag) {
                pauseFlag.notifyAll();
            }
        } else {
            pauseFlag.set(Boolean.TRUE);
            pausePrintBtn.setText("继续");
        }
    }

    @FXML
    public void startPrint(ActionEvent actionEvent) {
        startPrintBtn.setDisable(true);
        pausePrintBtn.setDisable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    tipMsgText.setText(String.valueOf(count.get()));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (pauseFlag.get()) {
                        try {
                            synchronized (pauseFlag) {
                                pauseFlag.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count.incrementAndGet();
                    System.out.println(44444);

                }
            }
        }).start();

    }
}
