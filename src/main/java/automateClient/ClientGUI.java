
package automateClient;

import java.util.Observable;
import java.util.Observer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * Représente l'interface graphique du client.
 * 
 * Contient la classe {@link Automate} qui représente le modèle
 * 
 */
@SuppressWarnings("serial")
public class ClientGUI extends JFrame implements Observer {

	/**
	 * L'automate représenté par cette interface graphique
	 */
	protected Automate automate;

	private int sommenpoche; // ## attribute sommenpoche
	private int sommetraitee; // ## attribute sommetraitee

	// Les différents widgets qui composent l'interface graphique
	private String typeoperation; // ## attribute typeoperation
	private JButton Quitter; // ## link Quitter
	private ButtonGroup buttonGroup1; // ## link buttonGroup1
	private JEditorPane jEdSaisie; // ## link jEdSaisie
	private JLabel jLabelSommenpoche; // ## link jLabelSommenpoche
	private JLabel jLabelSommetraitee; // ## link jLabelSommetraitee
	private JPanel jPanel2; // ## link jPanel2
	private JPanel jPanelNO; // ## link jPanelNO
	private JPanel jPanelcentre; // ## link jPanelcentre
	private JPanel jPanelsud; // ## link jPanelsud
	private JRadioButton jRadioButtonDepot; // ## link jRadioButtonDepot
	private JRadioButton jRadioButtonRetrait; // ## link jRadioButtonRetrait
	private JTextArea jTextArea1; // ## link jTextArea1
	private JTextField jTextFieldSommenpoche; // ## link jTextFieldSommenpoche
	private JButton valider; // ## link valider

	/**
	 * Constructeur par défaut
	 */
	public ClientGUI() {
		super();
		sommenpoche = 0;
		sommetraitee = 0;
		typeoperation = "retrait";

		// On crée le client TCP
		ClientTCP clienttcp = new ClientTCP("localhost", 6666);

		// Et l'automate (le modèle) de l'interface graphique
		automate = new Automate(clienttcp, 0);
		automate.addObserver((java.util.Observer) this); // Enregistre ClientGUI comme observateur de l'automate // Rajouté
		System.out.println("Creation de l'automate: " + automate);


		// On initialise ensuite l'interface graphique proprement dite
		initGUI();
	}

	@Override
	public void update(Observable o, Object nouvelleSomme) {
		sommenpoche = (int) nouvelleSomme;
		jTextFieldSommenpoche.setText(Integer.toString((int) nouvelleSomme));
	}
	/**
	 * ## operation getButtonGroup1()
	 */
	private ButtonGroup getButtonGroup1() {
		if (buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}

	/**
	 * ## operation getJLabelSommenpoche()
	 */
	private JLabel getJLabelSommenpoche() {
		if (jLabelSommenpoche == null) {
			jLabelSommenpoche = new JLabel();
			jLabelSommenpoche.setFont(new java.awt.Font("Tahoma", 1, 14));
			jLabelSommenpoche.setText("Somme en poche");
			jLabelSommenpoche.setHorizontalTextPosition(SwingConstants.LEFT);
		}
		return jLabelSommenpoche;
	}

	/**
	 * ## operation getJLabelSommetraitee()
	 */
	private JLabel getJLabelSommetraitee() {
		if (jLabelSommetraitee == null) {
			jLabelSommetraitee = new JLabel();
			jLabelSommetraitee.setText("Somme a traiter");
			jLabelSommetraitee.setFont(new java.awt.Font("Tahoma", 1, 14));
			jLabelSommetraitee.setPreferredSize(new java.awt.Dimension(150, 40));
			jLabelSommetraitee.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return jLabelSommetraitee;
	}

	/**
	 * ## operation getJPanelNO()
	 */
	private JPanel getJPanelNO() {
		if (jPanelNO == null) {
			jPanelNO = new JPanel();
			jPanelNO.setPreferredSize(new java.awt.Dimension(193, 47));
			jPanelNO.setBackground(new java.awt.Color(255, 128, 128));
		}
		return jPanelNO;
	}

	/**
	 * ## operation getJTextFieldSommenpoche()
	 */
	public JTextField getJTextFieldSommenpoche() {
		if (jTextFieldSommenpoche == null) {
			jTextFieldSommenpoche = new JTextField();
			jTextFieldSommenpoche.setText(Integer.toString(this.getSommenpoche()));
			jTextFieldSommenpoche.setPreferredSize(new java.awt.Dimension(43, 43));
			jTextFieldSommenpoche.setFont(new java.awt.Font("Tahoma", 1, 14));
			jTextFieldSommenpoche.setHorizontalAlignment(SwingConstants.LEFT);
			jTextFieldSommenpoche.setEditable(false);
		}
		return jTextFieldSommenpoche;
	}

	/**
	 * ## operation getSommenpoche()
	 */
	public int getSommenpoche() {
		return sommenpoche;
	}

	/**
	 * * @param sommenpoche ## operation setSommenpoche(int)
	 * 
	 * @param sommenpoche
	 */
	public void setSommenpoche(int sommenpoche) {
		this.sommenpoche = sommenpoche;
	}

	/**
	 * ## operation getSommetraitee()
	 */
	public int getSommetraitee() {
		return sommetraitee;
	}

	/**
	 * * @param sommetraitee ## operation setSommetraitee(int)
	 * 
	 * @param sommetraitee
	 */
	public void setSommetraitee(int sommetraitee) {
		this.sommetraitee = sommetraitee;
	}

	/**
	 * ## operation getTypeoperation()
	 */
	public String getTypeoperation() {
		return typeoperation;
	}

	/**
	 * * @param typeoperation ## operation setTypeoperation(String)
	 * 
	 * @param typeoperation
	 */
	public void setTypeoperation(String typeoperation) {
		this.typeoperation = typeoperation;
	}

	/**
	 * ## operation initGUI()
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setForeground(new java.awt.Color(255, 0, 128));
			this.setLocationRelativeTo(null);
			this.setVisible(true);

			this.setFont(new java.awt.Font("Antique Olive", 0, 10));
			this.setTitle("Automate Banquaire");
			getContentPane().setBackground(new java.awt.Color(255, 128, 64));
			{
				jPanelsud = new JPanel();
				getContentPane().add(jPanelsud, BorderLayout.SOUTH);
				jPanelsud.setPreferredSize(new java.awt.Dimension(392, 36));
				jPanelsud.setBackground(new java.awt.Color(255, 128, 128));
				{
					valider = new JButton();
					jPanelsud.add(valider);
					valider.setText("Valider");
					valider.setBackground(new java.awt.Color(0, 128, 0));
					valider.setPreferredSize(new java.awt.Dimension(119, 22));
					valider.setSize(190, 30);
					valider.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							automate.connexionBanque();
							System.out.println("la somme a traiter " + jEdSaisie.getText());
							setSommenpoche(Integer.parseInt(jTextFieldSommenpoche.getText()));
							System.out.println("le type d'operation : " + getTypeoperation());
							if (getTypeoperation() == "retrait") {
								System.out.println(" somme traitee en retrait " + jEdSaisie.getText());
								automate.demandeRetrait(Integer.parseInt(jEdSaisie.getText()));
							}
							if (getTypeoperation() == "depot") {
								System.out.println(" somme traitee en depot " + jEdSaisie.getText());
								automate.demandeDepot(Integer.parseInt(jEdSaisie.getText()));

							}
							automate.deconnexionBanque();
							System.out.println(automate);
						}

					});
				}
				{
					Quitter = new JButton();
					jPanelsud.add(Quitter);
					Quitter.setText("Quitter");
					Quitter.setBackground(new java.awt.Color(0, 128, 0));
					Quitter.setPreferredSize(new java.awt.Dimension(111, 24));
					Quitter.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							System.exit(0);
						}

					});
				}
			}
			{
				jPanel2 = new JPanel();
				GridLayout jPanel2Layout = new GridLayout(2, 1);
				jPanel2Layout.setHgap(5);
				jPanel2Layout.setVgap(5);
				jPanel2Layout.setColumns(1);
				// jPanel2Layout.setRows(3);
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, BorderLayout.WEST);
				jPanel2.setPreferredSize(new java.awt.Dimension(200, 118));
				jPanel2.setBackground(new java.awt.Color(255, 128, 128));
				// jPanel2.setPreferredSize(new java.awt.Dimension(150, 80));

				// jPanel2.add(getJTextAreaSommenpoche());
				jPanel2.add(getJLabelSommenpoche());
				jPanel2.add(getJTextFieldSommenpoche());

			}

			{
				jPanelcentre = new JPanel();
				getContentPane().add(jPanelcentre, BorderLayout.CENTER);
				jPanelcentre.setForeground(new java.awt.Color(255, 128, 64));
				jPanelcentre.setBackground(new java.awt.Color(255, 128, 0));
				jPanelcentre.setPreferredSize(new java.awt.Dimension(150, 80));
				{
					jEdSaisie = new JEditorPane();

					jPanelcentre.add(getJLabelSommetraitee());
					jPanelcentre.add(jEdSaisie);
					jEdSaisie.setPreferredSize(new java.awt.Dimension(140, 37));
					jEdSaisie.setBackground(new java.awt.Color(181, 217, 38));
					jEdSaisie.setForeground(new java.awt.Color(0, 0, 128));
					jEdSaisie.setFont(new java.awt.Font("Antique Olive", 1, 20));
					jEdSaisie.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));

				}
			}
			{
				jPanelNO = new JPanel();
				GridLayout jPanelNOLayout = new GridLayout(3, 1);
				jPanel2.add(getJPanelNO());
				jPanelNOLayout.setHgap(5);
				jPanelNOLayout.setVgap(5);
				jPanelNOLayout.setColumns(1);
				jPanelNO.setLayout(jPanelNOLayout);
				getContentPane().add(jPanelNO, BorderLayout.EAST);

				{
					jTextArea1 = new JTextArea();
					// jPanel2.add(getJTextAreaSommenpoche());
					jPanelNO.add(jTextArea1);
					jTextArea1.setText("Choix de l'Operation");
					jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 14));
					jTextArea1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					jTextArea1.setWrapStyleWord(true);
					jTextArea1.setPreferredSize(new java.awt.Dimension(183, 38));
					jTextArea1.setBackground(new java.awt.Color(0, 128, 64));
				}

				// buttonGroup1=new ButtonGroup();
				{
					jRadioButtonRetrait = new JRadioButton();
					jRadioButtonRetrait.setText("Retrait");
					jRadioButtonRetrait.setBackground(new java.awt.Color(23, 238, 233));
					jRadioButtonRetrait.setFont(new java.awt.Font("Tahoma", 1, 11));
					jRadioButtonRetrait.setSelected(true);
					setTypeoperation("retrait");

				}
				jPanelNO.add(jRadioButtonRetrait);

				{
					jRadioButtonDepot = new JRadioButton();
					jPanelNO.add(jRadioButtonDepot);
					jRadioButtonDepot.setText("Depot");
					jRadioButtonDepot.setBackground(new java.awt.Color(128, 255, 0));
					jRadioButtonDepot.setFont(new java.awt.Font("Tahoma", 1, 11));
					jRadioButtonDepot.setSelected(false);

				}
				jPanelNO.add(jRadioButtonDepot);

				buttonGroup1 = new ButtonGroup();
				buttonGroup1.add(jRadioButtonDepot);

				jRadioButtonDepot.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jRadioButtonDepot.actionPerformed, event=" + evt);
						setTypeoperation("depot");
					}
				});

				getButtonGroup1().add(jRadioButtonRetrait);
				jRadioButtonRetrait.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jRadioButtonRetrait.actionPerformed, event=" + evt);
						setTypeoperation("retrait");
					}
				});
			}

			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// #]
		// #]
	}

}
