package com.czp.jsunspot.setup;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.czp.jsunspot.util.DialogUtils;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Step;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;

/**
 *
 *
 * @author dylan.chen 2010-6-1
 * 
 */
public class Setup extends JFrame {
	
	private static final Log logger=LogFactory.getLog(Step.class);

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	
	private SetupService setupService;
	private JButton installButton = null;
	private JButton uninstallButton = null;

	/**
	 * This is the default constructor
	 */
	public Setup() {
		super();
		setupService=new SetupService();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(214, 179);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("安装程序 JSunspot");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getInstallButton(), null);
			jContentPane.add(getUninstallButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes installButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInstallButton() {
		if (installButton == null) {
			installButton = new JButton();
			installButton.setFont(new Font("宋体", Font.BOLD, 14));
			installButton.setSize(new Dimension(88, 33));
			installButton.setLocation(new Point(60, 32));
			installButton.setText("安 装");
			installButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						setupService.install();
						DialogUtils.showMessageOnDialog("安装成功");
					} catch (Exception exception) {
						DialogUtils.showErrorOnDialog(exception);
						logger.error(exception);
					}
					
				}
			});
		}
		return installButton;
	}

	/**
	 * This method initializes uninstallButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUninstallButton() {
		if (uninstallButton == null) {
			uninstallButton = new JButton();
			uninstallButton.setText("卸 载");
			uninstallButton.setSize(new Dimension(88, 33));
			uninstallButton.setFont(new Font("宋体", Font.BOLD, 14));
			uninstallButton.setLocation(new Point(60, 82));
			uninstallButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						setupService.uninstall();
						DialogUtils.showMessageOnDialog("卸载成功");
					} catch (Exception exception) {
						DialogUtils.showErrorOnDialog(exception);
						logger.error(exception);
					}
				}
			});
		}
		return uninstallButton;
	}

	public static void main(String[] args) {
		try {
			Setup setup=new Setup();
			setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setup.setVisible(true);
		} catch (Throwable throwable) {
			DialogUtils.showErrorOnDialog(throwable);
		}
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
