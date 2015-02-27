package com.czp.jsunspot.configuration;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.Rectangle;
import javax.swing.JButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.util.DialogUtils;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import java.awt.CardLayout;

/**
 *
 *
 * @author dylan.chen 2010-5-31
 * 
 */
public class Configuration extends JFrame {
	
	private final static Log logger=LogFactory.getLog(Configuration.class);  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane = null;
	private JPanel setPanel = null;
	private JTextField javaFileEditorTextField = null;
	private JButton okButton = null;
	private JButton exitButton = null;
	private JButton openFileChooserButton = null;
	private ConfigurationService configurationService;

	private JPanel aboutPanel = null;

	private JPanel fileChoosePanel = null;

	private JCheckBox recursiveSubdirectoriesCheckBox = null;

	private JScrollPane aboutScrollPane = null;

	private JTextArea aboutTextArea = null;

	private JButton helpButton = null;

	/**
	 * This is the default constructor
	 */
	public Configuration() {
		super();
		configurationService=new ConfigurationService();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("JSunspot");
		this.setSize(348, 243);
		this.setLocationRelativeTo(null);
		this.setContentPane(getTabbedPane());
	}

	/**
	 * This method initializes javaFileEditorTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setVisible(true);
			tabbedPane.setBounds(new Rectangle(0, 0,this.getWidth(),this.getHeight()));
			tabbedPane.addTab("设置", null, getSetPanel(), null);
			tabbedPane.addTab("关于", null, getAboutPanel(), null);
			
		}
		return tabbedPane;
	}

	/**
	 * This method initializes setPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSetPanel() {
		if (setPanel == null) {
			setPanel = new JPanel();
			setPanel.setLayout(null);
			setPanel.add(getExitButton(), null);
			setPanel.add(getFileChoosePanel(), null);
			setPanel.add(getRecursiveSubdirectoriesCheckBox(), null);
			setPanel.add(getOkButton(), null);
			setPanel.add(getHelpButton(), null);
		}
		return setPanel;
	}



	/**
	 * This method initializes openJavaFileProgramTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJavaFileEditorTextField() {
		if (javaFileEditorTextField == null) {
			javaFileEditorTextField = new JTextField();
			try {
				javaFileEditorTextField.setText(configurationService.getCurrentJavaFileEditor());
				javaFileEditorTextField.setBounds(new Rectangle(18, 30, 203, 26));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return javaFileEditorTextField;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("确定");
			okButton.setLocation(new Point(193, 150));
			okButton.setSize(new Dimension(60, 27));
			okButton.setMinimumSize(new Dimension(60, 35));
			okButton.setMaximumSize(new Dimension(60, 35));
			okButton.setName("okButton");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						configurationService.modifyConfig(javaFileEditorTextField.getText(),recursiveSubdirectoriesCheckBox.isSelected());
						DialogUtils.showMessageOnDialog("配置修改成功");
					} catch (Exception exception) {
						DialogUtils.showErrorOnDialog(exception);
						logger.error(exception);
					}
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes exitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setText("退出");
			exitButton.setSize(new Dimension(60, 27));
			exitButton.setName("cancelButton");
			exitButton.setLocation(new Point(266, 150));
			exitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitButton;
	}

	/**
	 * This method initializes openFileChooserButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOpenFileChooserButton() {
		if (openFileChooserButton == null) {
			openFileChooserButton = new JButton();
			openFileChooserButton.setText("浏览");
			openFileChooserButton.setBounds(new Rectangle(226, 28, 64, 27));
			openFileChooserButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			openFileChooserButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fileChooser=new JFileChooser();
					int resultCode=fileChooser.showOpenDialog(Configuration.this);
					if(JFileChooser.APPROVE_OPTION==resultCode){
						File file=fileChooser.getSelectedFile();
						javaFileEditorTextField.setText(file.getPath());
					}
				}
			});
		}
		return openFileChooserButton;
	}
	
	/**
	 * This method initializes aboutPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAboutPanel() {
		if (aboutPanel == null) {
			aboutPanel = new JPanel();
			aboutPanel.setLayout(new CardLayout());
			aboutPanel.setSize(new Dimension(3000, 5000));
			aboutPanel.add(getAboutScrollPane(), getAboutScrollPane().getName());
		}
		return aboutPanel;
	}

	/**
	 * This method initializes fileChoosePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFileChoosePanel() {
		if (fileChoosePanel == null) {
			fileChoosePanel = new JPanel();
			fileChoosePanel.setLayout(null);
			fileChoosePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Java源代码查看程序", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			fileChoosePanel.setName("fileChoosePanel");
			fileChoosePanel.setSize(new Dimension(315, 76));
			fileChoosePanel.setLocation(new Point(12, 50));
			fileChoosePanel.add(getJavaFileEditorTextField(), null);
			fileChoosePanel.add(getOpenFileChooserButton(), null);
		}
		return fileChoosePanel;
	}

	/**
	 * This method initializes recursiveSubdirectoriesCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getRecursiveSubdirectoriesCheckBox() {
		if (recursiveSubdirectoriesCheckBox == null) {
			try {
				recursiveSubdirectoriesCheckBox = new JCheckBox("递归处理子目录",configurationService.isRecursiveSubdirectories());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			recursiveSubdirectoriesCheckBox.setName("recursiveSubdirectoriesCheckBox");
			recursiveSubdirectoriesCheckBox.setLocation(new Point(12, 4));
			recursiveSubdirectoriesCheckBox.setSize(new Dimension(117, 39));
		}
		return recursiveSubdirectoriesCheckBox;
	}

	/**
	 * This method initializes aboutScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAboutScrollPane() {
		if (aboutScrollPane == null) {
			aboutScrollPane = new JScrollPane();
			aboutScrollPane.setName("aboutScrollPane");
			aboutScrollPane.setViewportView(getAboutTextArea());
		}
		return aboutScrollPane;
	}

	/**
	 * This method initializes aboutTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getAboutTextArea() {
		if (aboutTextArea == null) {
			aboutTextArea = new JTextArea();
			aboutTextArea.setLineWrap(true);
			aboutTextArea.setEditable(false);
			try {
				aboutTextArea.setText(configurationService.getAboutInfo());
			} catch (Exception e) {
				new RuntimeException(e);
			}
		}
		return aboutTextArea;
	}

	/**
	 * This method initializes helpButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setText("帮助");
			helpButton.setSize(new Dimension(60, 27));
			helpButton.setLocation(new Point(15, 150));
			helpButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						configurationService.showHelp();
					} catch (Exception exception) {
						DialogUtils.showErrorOnDialog(exception);
						logger.error(exception);
					}
				}
			});
		}
		return helpButton;
	}

	public static void main(String[] args) {
		try {
			Configuration configuration=new Configuration();
			configuration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			configuration.setVisible(true);
		} catch (Throwable throwable) {
			DialogUtils.showErrorOnDialog(throwable);
		}
		
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
