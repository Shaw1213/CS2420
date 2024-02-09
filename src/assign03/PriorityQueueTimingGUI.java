package assign03;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class PriorityQueueTimingGUI extends JFrame {
    private XYSeries timingResults;

    public PriorityQueueTimingGUI() {
        setTitle("Priority Queue Timing");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timingResults = new XYSeries("Timing Results");

        XYSeriesCollection dataset = new XYSeriesCollection(timingResults);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Priority Queue Timing",
                "N",
                "Time (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Timing");
        startButton.addActionListener(e -> startTiming());
        add(startButton, BorderLayout.SOUTH);
    }

    private void startTiming() {
        int startN = 100000;
        int endN = 2000000;
        int stepN = 100000;
        int timesToLoop = 1000;

        for (int N = startN; N <= endN; N += stepN) {
            SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();

            for (int j = 0; j < N; j++) {
                queue.insert(j);
            }

            long startTime = System.nanoTime();

            for (int t = 0; t < timesToLoop; t++) {
                queue.findMax();
            }

            long endTime = System.nanoTime();

            double averageTime = ((endTime - startTime) / (double) timesToLoop);

            timingResults.add(N, averageTime);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PriorityQueueTimingGUI().setVisible(true));
    }
}