package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Produto;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class TProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtUniMedida;
	private JTextField txtCodigo;

	
	public TProduto(String diretorio, JFrame telaPrincipal) {
		
		Produto p = new Produto();
		
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
		panel_1.setBounds(0, 0, 974, 601);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(10, 64, 954, 530);
		panel_1.add(panel_2);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(10, 96, 74, 14);
		panel_2.add(lblDescricao);
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(10, 121, 934, 30);
		panel_2.add(txtDescricao);
		
		JLabel lblUniMedida = new JLabel("Unid. Medida:");
		lblUniMedida.setBounds(10, 162, 96, 14);
		panel_2.add(lblUniMedida);
		
		JComboBox cbxUniMedida = new JComboBox();
		cbxUniMedida.setModel(new DefaultComboBoxModel(new String[] {"Kg", "L", "g", "mL"}));
		cbxUniMedida.setBounds(57, 184, 96, 30);
		panel_2.add(cbxUniMedida);
		
		JLabel lblValor = new JLabel("Valor unit\u00E1rio:");
		lblValor.setBounds(10, 225, 96, 14);
		panel_2.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(10, 250, 143, 30);
		panel_2.add(txtValor);
		
		JButton btnSalvar = new JButton("salvar produto");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String codigo = txtCodigo.getText();
				String nome = txtDescricao.getText().trim();
				String uniMedida = txtUniMedida.getText().trim()+cbxUniMedida.getSelectedItem().toString();
				String valor = txtValor.getText().trim();
				valor = valor.replace(",", ".");
				
				if(txtCodigo.getText().isEmpty()) {
					
					if(txtDescricao.getText().isEmpty()||txtUniMedida.getText().isEmpty()||txtValor.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(null, "Alguma informação não foi preenchida.");
						
					}else {
						
						if(p.cadastrarProduto(diretorio,nome, valor, uniMedida)) {
							
							JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
							
							txtDescricao.setText(null);
							txtUniMedida.setText(null);
							txtValor.setText(null);
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Informação preeenchida incorretamente.");
						}
					}
				}else {
					
					if(p.editarProduto(diretorio, codigo, nome, valor, uniMedida)) {
						
						JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Informação do produto inválida.");
					}
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalvar.setBounds(10, 470, 224, 49);
		panel_2.add(btnSalvar);
		
		JButton btnLocalizar = new JButton("localizar produto");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cdgProcurado = JOptionPane.showInputDialog(null, "Digite o código do produto");
				
				if(p.localizarProduto(diretorio, cdgProcurado)) {
					
					txtCodigo.setText(p.getCodigo());
					txtDescricao.setText(p.getDescricao());
					txtUniMedida.setText(p.getUniMedida());
					txtValor.setText(p.getValor());
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Produto não encontrado.");
				}
			}
		});
		btnLocalizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLocalizar.setBounds(244, 470, 224, 49);
		panel_2.add(btnLocalizar);
		
		JButton btnEditar = new JButton("editar produto");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String codigo = txtCodigo.getText();
				String descricao = txtDescricao.getText();
				String uniMedida = txtUniMedida.getText()+cbxUniMedida.getActionCommand();
				String valor = txtValor.getText();
				
				if(codigo.isEmpty()||descricao.isEmpty()||uniMedida.isEmpty()||valor.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Produto não informado.");
					
				}else {
					
					if(p.editarProduto(diretorio, codigo, descricao, valor, uniMedida)) {
						
						JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Informação do produto inválida.");
					}
				}
				
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditar.setBounds(721, 470, 224, 49);
		panel_2.add(btnEditar);
		
		JButton btnExcluir = new JButton("excluir produto");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codigo = JOptionPane.showInputDialog(null,"Digite o código do produto:");
				
				if(codigo.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "O código está vazio.");
					
				}else {
					
					if(p.excluirProduto(diretorio, codigo)) {
						
						JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.");
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Produto não encontrado.");
					}
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(487, 470, 224, 49);
		panel_2.add(btnExcluir);
		
		txtUniMedida = new JTextField();
		txtUniMedida.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUniMedida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUniMedida.setColumns(10);
		txtUniMedida.setBounds(10, 184, 48, 30);
		panel_2.add(txtUniMedida);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(10, 55, 143, 30);
		panel_2.add(txtCodigo);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 30, 46, 14);
		panel_2.add(lblCodigo);
		
		JLabel lblInfo = new JLabel("informa\u00E7\u00F5es do produto:");
		lblInfo.setBounds(10, 5, 165, 14);
		panel_2.add(lblInfo);
		
		JLabel lblCadastrar = new JLabel("cadastrar produto");
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Segoe UI Semilight", Font.BOLD, 32));
		lblCadastrar.setBounds(10, 11, 954, 42);
		panel_1.add(lblCadastrar);
		
		
		
		
		setVisible(true);
	}
}
