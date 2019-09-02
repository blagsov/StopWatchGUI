import javax.swing.*;

public class Form {

    private JPanel rootPanel;
    private JButton startButton;
    private JButton pauseButton;
    private JPanel buttons;
    private JLabel textLabel;
    private JButton stopButton;
    private StopWatch stopWatch;
    private long currentTime = 0L;
    private boolean pause;

    Form() { //задание условий для видимости, активности кнопок
        stopButton.setVisible(false);
        startButton.addActionListener(e -> {

            if (pause) {
                pause = false;
                stopWatch.setPause(false);
                synchronized (stopWatch) {
                    stopWatch.notifyAll();
                }
                startButton.setVisible(false);
                stopButton.setVisible(true);
                pauseButton.setEnabled(true);
            } else {
                stopWatch = new StopWatch(this);
                stopWatch.start();
            }
            startButton.setVisible(false);
            stopButton.setVisible(true);
            pauseButton.setEnabled(true);
        });

        //При нажатии Стоп прерываем и обнуляем время
        stopButton.addActionListener(e -> {
            stopWatch.interrupt();
            currentTime = 0L;
            stopButton.setVisible(false);
            startButton.setVisible(true);
            pauseButton.setEnabled(false);
        });

        //При нажатии паузы устанавливаем флажок паузы
        pauseButton.addActionListener(e -> {
            pause = true;
            stopButton.setVisible(false);
            startButton.setVisible(true);
            pauseButton.setEnabled(false);
            stopWatch.setPause(true);
        });

    }

    long getCurrentTime() {
        return currentTime;
    }


    JPanel getRootPanel() {
        return rootPanel;
    }

    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(JButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }


    public JLabel getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel) {
        this.textLabel = textLabel;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}