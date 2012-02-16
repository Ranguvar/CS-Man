/*
 * Pac-Man clone written in Java.
 *
 * Copyright (c) 2012 Devin Cofer <ranguvar@archlinux.us>
 * This file is part of CS-Man.
 *
 * CS-Man is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CS-Man is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CS-Man; see the file COPYING.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package csman;

import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.SwingUtilities;

public class CSMan extends JFrame
{
	public static final Dimension RESOLUTION = new Dimension(448, 576);
	private static final long serialVersionUID = 1L;

	public CSMan()
	{
		setTitle("CS-Man");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = menuBar.add(new JMenu("File"));
		JMenuItem fileMenuExit = fileMenu.add(new JMenuItem("Exit"));
		fileMenuExit.addActionListener(new ActionListener()
		{
			/*
			 * FIXME: This just closes all windows at the moment.
			 * There has to be a good way to just close the window that fired the event.
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (Frame frame : JFrame.getFrames())
					if (frame.isActive()) {
						WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
						Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosing);
					}
			}
		});

		/*
		 * Display a menu of possible `Look and Feel' options the user has.
		 * This is mostly for learning and testing purposes.
		 */
		JMenu lafMenu = menuBar.add(new JMenu("LAF"));
		try {
			/*
			 * Iterate through available LAFs, and present them as menu items which
			 * activate said LAF when selected.
			 *
			 * `laf' needs to be declared final to allow access from the ActionListener anonymous subclass.
			 * FIXME: That still doesn't make sense.
			 */
			for (final LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
				JMenuItem lafMenuItem = lafMenu.add(new JMenuItem(laf.getName()));
				lafMenuItem.addActionListener(new ActionListener()
				{
					// When the menu item is selected, change the LookAndFeel, and apply the change to all frames.
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
							UIManager.setLookAndFeel(laf.getClassName());
							for (Frame frame : JFrame.getFrames())
								if (frame.isActive()) {
									SwingUtilities.updateComponentTreeUI(frame);
									frame.pack();
								}
						} catch (Exception eAction) {
							System.out.println("error: " + eAction);
							System.exit(1);
						}
					}
				});
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
			System.exit(1);
		}
		setJMenuBar(menuBar);

		Canvas gameScreen = new Canvas();
		getContentPane().add(gameScreen);
		gameScreen.setSize(RESOLUTION);

		pack();
		setLocationRelativeTo(null);  // Center the window
		setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new CSMan();
			}
		});
	}
}