/* 
 * Pac-Man clone written in Java.
 * 
 * Copyright (c) 2010 Devin Cofer <ranguvar@archlinux.us>
 * 
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
 * 
 */

package csman;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CSMan extends JFrame
{
	public static final Dimension RESOLUTION = new Dimension(448, 576);
	
	public CSMan()
	{
		setTitle("CS-Man");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(RESOLUTION);
		setLocationRelativeTo(null);  // Center the window
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
                        @SuppressWarnings("unused")
                        public void run()
			{
				new CSMan();
			}
		});
	}
}