package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Pagamento;
import control.Produto;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TCaixa extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCaixa frame = new TCaixa();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String diretorio = "F:\\pasta.java\\";
	private JTextField txtCodigo;
	private JTextField txtQuantidade;
	private JTextField txtSubTotal;
	private JTextField txtDescricao;
	private JTextField txtValor;
	
	/**
	 * Create the frame.
	 */
	public TCaixa() {
		
		JFrame telaPrincipal = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 250, 250));
		panel_1.setBounds(0, 0, 974, 601);
		panel.add(panel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(new Color(255, 250, 250));
		txtCodigo.setBounds(10, 36, 155, 42);
		panel_1.add(txtCodigo);
		
		JLabel label = new JLabel("C\u00F3digo do produto:");
		label.setBounds(10, 11, 140, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("quantidade:");
		label_1.setBounds(320, 11, 80, 14);
		panel_1.add(label_1);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setEditable(false);
		txtQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBackground(new Color(255, 250, 250));
		txtQuantidade.setBounds(361, 36, 72, 42);
		panel_1.add(txtQuantidade);
		txtQuantidade.setText("1");
		
		JTextArea txtLista = new JTextArea();
		txtLista.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtLista.setEditable(false);
		txtLista.setBackground(new Color(245, 245, 245));
		txtLista.setBounds(428, 119, 536, 471);
		panel_1.add(txtLista);
		
		JLabel label_2 = new JLabel("x");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_2.setBounds(285, 36, 36, 42);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("lista de compras:");
		label_3.setBounds(429, 94, 132, 14);
		panel_1.add(label_3);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSubTotal.setEditable(false);
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(556, 36, 408, 42);
		panel_1.add(txtSubTotal);
		txtSubTotal.setText("0,00");
		
		JLabel label_5 = new JLabel("Sub Total:");
		label_5.setBounds(556, 11, 61, 14);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("descri\u00E7\u00E3o do produto:");
		label_6.setBounds(10, 94, 155, 14);
		panel_1.add(label_6);
		
		txtDescricao = new JTextField();
		txtDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDescricao.setEditable(false);
		txtDescricao.setColumns(10);
		txtDescricao.setBackground(new Color(255, 250, 250));
		txtDescricao.setBounds(10, 119, 265, 44);
		panel_1.add(txtDescricao);
		
		JLabel label_7 = new JLabel("valor:");
		label_7.setBounds(298, 94, 46, 14);
		panel_1.add(label_7);
		
		txtValor = new JTextField();
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtValor.setEditable(false);
		txtValor.setColumns(10);
		txtValor.setBackground(new Color(255, 250, 250));
		txtValor.setBounds(298, 119, 120, 44);
		panel_1.add(txtValor);
		
		JButton button = new JButton("localizar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Produto p = new Produto();
				
				String codigo = txtCodigo.getText().trim();
				
				if(p.localizarProduto(diretorio, codigo)){
					
					String nome = p.getDescricao();
					String uniMedida = p.getUniMedida();
					String valor = p.getValor();
					
					txtDescricao.setText(nome+uniMedida);
					txtValor.setText(valor);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Produto não encontrado");
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBackground(new Color(152, 251, 152));
		button.setBounds(175, 36, 108, 42);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Incluir item na lista de compras");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Produto p = new Produto();
				
				if(txtCodigo.getText().trim().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Produto não informado.");
					
				}else {
					
					if(p.localizarProduto(diretorio, txtCodigo.getText())) {
						
						String codigo = p.getCodigo();
						String descricao = p.getDescricao();
						String valor = p.getValor();
						String uniMedida = p.getUniMedida();
						
						String lista = codigo+" "+descricao+" "+valor+" "+uniMedida; 
						
						Pagamento pg = new Pagamento(); 
						
						String valorTotal = p.valorTotal(txtValor.getText(),txtQuantidade.getText());
						
						txtLista.append(lista+" "+txtQuantidade.getText()+" "+valorTotal);
						
						txtSubTotal.setText(pg.valorTotal(valorTotal));
						
					}
				}
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBackground(new Color(173, 216, 230));
		button_1.setBounds(10, 174, 408, 42);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("Finalizar compra");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TPagamento tPagamento = new TPagamento(diretorio,telaPrincipal);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBounds(10, 548, 408, 42);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("Cadastrar produto");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String senha = JOptionPane.showInputDialog(null, "Insira a senha do gerente:");
				
				if(senha.equals("12345")) {
					
					TProduto tp = new TProduto(diretorio,telaPrincipal);
					setVisible(false);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Senha incorreta");
					
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_3.setBackground(new Color(255, 248, 220));
		button_3.setBounds(10, 386, 408, 42);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("Cancelar compra");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String senha = JOptionPane.showInputDialog(null, "Digite a senha do gerente");
				Pagamento pg = new Pagamento();
				
				if(senha.equals("12345")) {
					
					txtLista.setText(null);
					txtSubTotal.setText(null);
					pg.setValor(null);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Senha inválida.");
				}
			}
		});
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_4.setBackground(new Color(178, 34, 34));
		button_4.setBounds(10, 333, 408, 42);
		panel_1.add(button_4);
		
		JButton button_5 = new JButton("Cancelar item");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtDescricao.setText(null);
				txtValor.setText(null);
				txtQuantidade.setText("1");
				
			}
		});
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_5.setBackground(new Color(255, 215, 0));
		button_5.setBounds(10, 280, 408, 42);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("Apagar tela");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtDescricao.setText(null);
				txtValor.setText(null);
				txtQuantidade.setText("1");
				txtLista.setText(null);
				txtSubTotal.setText(null);
			}
		});
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_6.setBackground(Color.YELLOW);
		button_6.setBounds(10, 227, 408, 42);
		panel_1.add(button_6);
		
		JButton button_7 = new JButton("-");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto p = new Produto();
				
				String num = p.botaoMenos(txtQuantidade.getText());
				
				txtQuantidade.setText(num);
			}
		});
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_7.setBounds(320, 36, 43, 42);
		panel_1.add(button_7);
		
		JButton button_8 = new JButton("+");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto p = new Produto();
				
				String num = p.botaoMais(txtQuantidade.getText());
				
				txtQuantidade.setText(num);
			}
		});
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_8.setBounds(428, 36, 43, 42);
		panel_1.add(button_8);
		
		
		setVisible(true);
	}
}
