package tokyslav.gui;

import javax.swing.*;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;

import tokyslav.FileTypes;
import tokyslav.filereader.filereader;

import java.awt.*;
import java.io.File;

// import org.jfree.data.xy.*;

public class StartGUI {

    public JPanel startGUIJPanel() {

        JPanel startJPanelGUI = new JPanel();
        startJPanelGUI.setLayout(new GridBagLayout());
        GridBagConstraints layoutGBCStartGUI = new GridBagConstraints();

        layoutGBCStartGUI.fill = GridBagConstraints.BOTH;

        layoutGBCStartGUI.gridx = 0;
        layoutGBCStartGUI.gridy = 0;
        layoutGBCStartGUI.weightx = 1.0;
        layoutGBCStartGUI.weighty = 0.02;
        layoutGBCStartGUI.anchor = GridBagConstraints.WEST;

        JPanel startGUIHeadJPanel = startGUIHeadPanel();
        startJPanelGUI.add(startGUIHeadJPanel, layoutGBCStartGUI);

        layoutGBCStartGUI.gridx = 0;
        layoutGBCStartGUI.gridy = 1;
        layoutGBCStartGUI.weightx = 1.0;
        layoutGBCStartGUI.weighty = 0.96;
        layoutGBCStartGUI.fill = GridBagConstraints.BOTH;
        JPanel startGUICenterJPanel = startGUICenterJPanel();
        startJPanelGUI.add(startGUICenterJPanel, layoutGBCStartGUI);

        return startJPanelGUI;
    }

    private JPanel startGUIHeadPanel() {

        SettingGUI settingGUI = new SettingGUI();

        JPanel createStartGUIHeadPanel = new JPanel();
        createStartGUIHeadPanel.setLayout(new BorderLayout(8, 0));
        createStartGUIHeadPanel.setBackground(Color.WHITE);

        JButton headStartGUISettingButton = new JButton();
        headStartGUISettingButton.setText("Settings");
        // headStartGUISettingButton.addActionListener(e -> settingGUI.settingJPanel());
        createStartGUIHeadPanel.add(headStartGUISettingButton, BorderLayout.WEST);

        return createStartGUIHeadPanel;
    }

    private JPanel startGUICenterJPanel() {

        JPanel createStartGUICenterJPanel = new JPanel(new GridLayout(2, 1));

        File[] listOfFileRoots = filereader.getRoots();

        JPanel createStartGUIDiagrammJPanel = new JPanel(new GridLayout(1, 2));

        JPanel diagrammPlaceHolderJPanel = new JPanel();

        diagrammPlaceHolderJPanel.setBackground(Color.white);

        createStartGUIDiagrammJPanel.add(diagrammJPanelCentert(), BorderLayout.CENTER);
        createStartGUIDiagrammJPanel.add(diagrammPlaceHolderJPanel);
        JPanel createStartGUIRootsJPanel = new JPanel(new GridLayout(listOfFileRoots.length, 0));

        for (int i = 0; i < listOfFileRoots.length; i++) {
            createStartGUIRootsJPanel.add(createStartGUIRootsJPanel(listOfFileRoots[i]));

        }

        createStartGUICenterJPanel.add(createStartGUIDiagrammJPanel);
        createStartGUICenterJPanel.add(createStartGUIRootsJPanel);

        return createStartGUICenterJPanel;
    }

    private JPanel createStartGUIRootsJPanel(File tempNameFile) {
        GetImagePath getMyClassGetImagePath = new GetImagePath();

        long totalSpace = tempNameFile.getTotalSpace();
        long freeSpace = tempNameFile.getFreeSpace();
        long usedSpace = totalSpace - freeSpace;

        JPanel createStartGUIRootsJPanel = new JPanel(new BorderLayout());

        createStartGUIRootsJPanel.setBackground(Color.white);

        JButton createStartGUIButtonForJPanel = new JButton();

        createStartGUIButtonForJPanel.setOpaque(false);
        createStartGUIButtonForJPanel.setContentAreaFilled(false);
        createStartGUIButtonForJPanel.setBorderPainted(false);
        createStartGUIButtonForJPanel.setLayout(new BorderLayout());
        createStartGUIButtonForJPanel.addActionListener(e -> startGUIGetInDriveCenterPanel(tempNameFile.toString()));

        JPanel createStartGUIJPanelForDrive = new JPanel();

        createStartGUIJPanelForDrive.setBackground(Color.white);
        createStartGUIJPanelForDrive.setLayout(new BorderLayout());
        createStartGUIJPanelForDrive.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        ImageIcon driveIcon = new ImageIcon(getMyClassGetImagePath.getImagePath(FileTypes.DRIVE));
        Image scaledImg = driveIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        driveIcon = new ImageIcon(scaledImg);

        JPanel textOfDriveSizeJPanel = new JPanel();
        textOfDriveSizeJPanel.setBackground(Color.white);

        JLabel textSpaceJLabel = new JLabel(GUILogic.calculateSizeDisplayNumber(freeSpace) + " frei "
                + GUILogic.calculateSizeDisplayNumber(totalSpace));
        textOfDriveSizeJPanel.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));
        textOfDriveSizeJPanel.add(textSpaceJLabel);

        JPanel grapheOfDriveSizeJPanel = new JPanel();
        grapheOfDriveSizeJPanel.setBackground(Color.white);
        grapheOfDriveSizeJPanel.setLayout(new BorderLayout());
        grapheOfDriveSizeJPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 50));

        JLabel grahpeJLabel = GUILogic.customJLabel("", precentageOfUsedSpace(totalSpace, usedSpace));
        grahpeJLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        grahpeJLabel.setPreferredSize(new Dimension(150, 20));
        grapheOfDriveSizeJPanel.add(grahpeJLabel);

        createStartGUIJPanelForDrive.add(new JLabel(tempNameFile.toString(), driveIcon, JLabel.LEFT),
                BorderLayout.WEST);
        createStartGUIJPanelForDrive.add(textOfDriveSizeJPanel, BorderLayout.CENTER);
        createStartGUIJPanelForDrive.add(grapheOfDriveSizeJPanel, BorderLayout.LINE_END);

        createStartGUIButtonForJPanel.add(createStartGUIJPanelForDrive, BorderLayout.CENTER);
        createStartGUIRootsJPanel.add(createStartGUIButtonForJPanel, BorderLayout.CENTER);

        return createStartGUIRootsJPanel;
    }

    private static JLabel diagrammJPanelCentert() {

        String title = "Drive INFO";
        String xAxisTimeString = "Months";
        String yAxis = "Size in GB";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1, "Files", "Jan");
        dataset.addValue(3, "Files", "Feb");
        dataset.addValue(2, "Files", "Mar");

        JFreeChart chart = ChartFactory.createLineChart(
                title,
                xAxisTimeString,
                yAxis,
                dataset);

        ChartPanel chartPanel = new ChartPanel(chart);

        JLabel createDiagrammJPanelCenter = new JLabel();
        createDiagrammJPanelCenter.setLayout(new BorderLayout());
        createDiagrammJPanelCenter.setBackground(Color.WHITE);

        createDiagrammJPanelCenter.add(chartPanel);

        return createDiagrammJPanelCenter;
    }

    private void startGUIGetInDriveCenterPanel(String tempDrivePath) {
        FolderGUI myFolderGUI = new FolderGUI();
        FunctionGUI.removeContainerPanel();

        FunctionGUI.addContainerPanelToFrame(myFolderGUI.createJPanelInToDrive(tempDrivePath));
    }

    public int precentageOfUsedSpace(long totalSpace, long usedSpace) {
        long a = totalSpace;
        long b = usedSpace * 100;
        long percentage = b / a;

        return (int) percentage;
    }
}