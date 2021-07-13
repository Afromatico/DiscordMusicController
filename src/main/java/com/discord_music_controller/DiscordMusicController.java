package com.discord_music_controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.google.inject.Inject;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.DynamicGridLayout;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;

class DiscordMusicController extends PluginPanel{

    private static final Color BACKGROUND_COLOR = ColorScheme.DARK_GRAY_COLOR;
    private static final Color BACKGROUND_HOVER_COLOR = ColorScheme.DARK_GRAY_HOVER_COLOR;

    @Inject
    DiscordMusicController(final DiscordMusicControllerPlugin plugin) {
        super(false);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET));
        panel.setLayout(new DynamicGridLayout(0, 1, 0, 3));

        // Wrap content to anchor to top and prevent expansion
        final JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(panel, BorderLayout.NORTH);
        final JScrollPane scrollPane = new JScrollPane(northPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane, BorderLayout.CENTER);

        JButton playButton = createPlayButton();
        JButton pauseButton = createPauseButton();
        JButton stopButton = createStopButton();
        JButton skipButton = createSkipButton();
        JButton addButton = createAddButton();
        JLabel songName = new JLabel();
        songName.setText("AAAAAAA");
        JLabel currentTime = new JLabel();
        currentTime.setText("BBBBBBB");

        GroupLayout layout = new GroupLayout(northPanel);
        northPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(songName))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(currentTime))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(playButton)
                                                .addGap(5, 5, 5)
                                                .addComponent(pauseButton)
                                                .addGap(5, 5, 5)
                                                .addComponent(stopButton)
                                                .addGap(5, 5, 5)
                                                .addComponent(skipButton)
                                                .addGap(5, 5, 5)
                                                .addComponent(addButton)))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addContainerGap(133, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(songName)
                                .addGap(26, 26, 26)
                                .addComponent(currentTime)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(playButton)
                                        .addComponent(pauseButton)
                                        .addComponent(stopButton)
                                        .addComponent(skipButton)
                                        .addComponent(addButton))
                                .addGap(33, 33, 33)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addContainerGap(108, Short.MAX_VALUE))
        );

    }

    JButton createPlayButton(){
        final Icon play = new ImageIcon(ImageUtil.loadImageResource(
                DiscordMusicControllerPlugin.class,"media-player/play.png")
                .getScaledInstance(16,16, 1));
        final JButton label = new JButton(play);
        label.setFocusable(false);
        label.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                label.setBackground(BACKGROUND_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e){
                label.setBackground(BACKGROUND_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e){
                play();
            }
        });

        return label;
    }

    JButton createPauseButton(){
        final Icon pause = new ImageIcon(
                ImageUtil.loadImageResource(DiscordMusicControllerPlugin.class,"media-player/pause.png")
                        .getScaledInstance(16,16, 1));
        final JButton label = new JButton(pause);
        label.setFocusable(false);
        label.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                label.setBackground(BACKGROUND_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e){
                label.setBackground(BACKGROUND_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e){
                pause();
            }
        });

        return label;
    }

    JButton createStopButton(){
        final Icon stop = new ImageIcon(
                ImageUtil.loadImageResource(DiscordMusicControllerPlugin.class,"media-player/stop.png")
                        .getScaledInstance(16,16, 1));
        final JButton label = new JButton(stop);
        label.setFocusable(false);
        label.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                label.setBackground(BACKGROUND_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e){
                label.setBackground(BACKGROUND_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e){
                stop();
            }
        });

        return label;
    }

    JButton createSkipButton(){
        final Icon skip = new ImageIcon(
                ImageUtil.loadImageResource(DiscordMusicControllerPlugin.class,"media-player/skip.png")
                        .getScaledInstance(16,16, 1));
        final JButton label = new JButton(skip);
        label.setFocusable(false);
        label.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                label.setBackground(BACKGROUND_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e){
                label.setBackground(BACKGROUND_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e){
                skip();
            }
        });

        return label;
    }

    JButton createAddButton(){
        final Icon add = new ImageIcon(
                ImageUtil.loadImageResource(DiscordMusicControllerPlugin.class,"media-player/search.png")
                        .getScaledInstance(16,16, 1));
        final JButton label = new JButton(add);
        label.setFocusable(false);
        label.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                label.setBackground(BACKGROUND_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e){
                label.setBackground(BACKGROUND_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e){
                sendAdd();
            }
        });

        return label;
    }

    private void play() {
    }

    private void pause() {
    }

    private void stop() {
    }

    private void skip() {
    }

    private void sendAdd() {
    }

}


