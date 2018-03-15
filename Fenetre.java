
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.*;
import java.io.*;

public class Fenetre extends JFrame
{
	private Optimiseur opti=new Optimiseur();
	private JButton bouton1 = new JButton("File name");
	private String path="";
	private JPanel content = new JPanel();

	private String ChooseButtonActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select a SVG file");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SVG file", "svg");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getPath();
		}
		else
			return "";
	}

	public Fenetre()
	{
		this.setTitle("Java");
		this.setSize(1900, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		bouton1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				path=ChooseButtonActionPerformed(event);
				opti.setPath(path);
			}
		});
		JPanel cell1 = new JPanel();
		cell1.setBackground(Color.BLUE);
		cell1.setPreferredSize(new Dimension(100, 900));	

		opti.pan.setBackground(Color.WHITE);
		opti.pan.setPreferredSize(new Dimension(1800, 900));
		opti.pan.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.RED));
		opti.pan.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.RED));
		opti.pan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
		opti.pan.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.RED));


		content.setPreferredSize(new Dimension(1900, 900));
		content.setBackground(Color.WHITE);


		GridBagConstraints gbc = new GridBagConstraints();
		content.setLayout(new GridBagLayout());
		cell1.setLayout(new GridBagLayout());


		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		content.add(cell1, gbc);
		gbc.gridx = 1;
		content.add(opti.pan, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 5;
		cell1.add(bouton1, gbc);





		this.setContentPane(content);
		this.setVisible(true);

	}


	public static void main(String[] args)
	{
		Fenetre fen = new Fenetre();
	}       

}