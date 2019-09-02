import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class StopWatch extends Thread implements Runnable {
    private JTextPane textPanel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton continueButton;
    private Form form;
    private boolean pause = false;

    public StopWatch(Form form) {
        this.form = form;
    }

    @Override
    public void run() {

        Calendar calendar = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS"); //установка формата даты...
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));//синхронизация с часовым поясом

        //время начала отсчета. Вычитаем текущий показатель, если таймер был поставлен на паузу, равен нулю, если остановлен.
        long startTime = System.currentTimeMillis() - form.getCurrentTime();

        while (!isInterrupted()) {

            //Если пауза, то ждем, после пробуждения изменяем стартовое время.
            if (pause) {
                synchronized (this) {
                    try {
                        wait();
                        startTime = System.currentTimeMillis() - form.getCurrentTime();
                        pause = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //Класс Duration служит для хранения продолжительности времени на основе секунд и наносекунд
                Duration duration = Duration.ofMillis(System.currentTimeMillis() - startTime);
                calendar.setTimeInMillis(duration.toMillis());

                String currentTimeString = sdf.format(calendar.getTime());

                //простое оформление и установка времени
                form.getTextLabel().setForeground(Color.blue);
                form.getTextLabel().setFont(new Font("TimesRoman", Font.PLAIN, 20));
                form.getTextLabel().setText(currentTimeString.substring(0, currentTimeString.length() - 1));


                try {
                    sleep(10);
                    form.setCurrentTime(duration.toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

        }
    }

    void setPause(boolean pause) {
        this.pause = pause;
    }
}

