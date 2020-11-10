package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class Produto extends Arquivo{

	private String descricao,valor,uniMedida,codigo; 
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
	
	public boolean cadastrarProduto(String diretorio,String txtDescricao,String txtValor,String txtMedida) {
		
		String caminho = verificarCaminho(diretorio+"produto.txt",diretorio);
		String txtCodigo = criarCodigo(caminho);
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
			
			bw.write(String.format("%-3.3s %-25.25s %-5.5s %-8.8s", txtCodigo, txtDescricao, txtMedida, txtValor));
			bw.newLine();
			
			bw.close();
			
			return true;
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		return false;
	}
	
	
	
	
	public String criarCodigo(String caminho) {
		
		int cont = 0;
		String aux = null;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			
			while(br.ready()) {
				
				cont++;
				
				br.readLine();
			}
			
			br.close();
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		cont = cont+1;
		
		aux = Integer.toString(cont);
		
		if(cont<10) {
			
			aux = "00"+aux;
			
		}else {
			
			if(cont<100) {
				
				aux = "0"+aux;
			}
		}
		
		return aux;
	}
	
	
	
	
	public boolean localizarProduto(String diretorio,String cdgProcurado) {
		
		String caminho = verificarCaminho(diretorio+"produto.txt",diretorio);
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			
			while(br.ready()) {
				
				String linha = br.readLine();
				
				String cdg = linha.substring(0, 3).trim();
				
				if(cdg.equals(cdgProcurado)) {
					
					descricao = linha.substring(4, 29).trim();
					valor = linha.substring(36).trim();
					uniMedida = linha.substring(30, 35).trim();
					codigo = linha.substring(0, 3).trim();
					
					return true;
				}
				br.readLine();
				
			}
			
			br.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	
	
	
	
	public boolean excluirProduto(String diretorio,String cdgProcurado) {
		
		String caminho = verificarCaminho(diretorio+"produto",diretorio);
		String caminhoTemp = verificarCaminho(diretorio+"caminhoTemp.txt",diretorio);
		
		boolean erro = false;
		
		try {
			
			BufferedReader arq1 = new BufferedReader(new FileReader(caminho));
			BufferedWriter arq2 = new BufferedWriter(new FileWriter(caminhoTemp));
			
			while(arq1.ready()) {
				
				String linha = arq1.readLine();
				
				String codigo = linha.substring(0, 3);
				
				if(codigo.equals(cdgProcurado)) {
					
					erro = true;
				}else {
					
					arq2.write(linha);
					arq2.newLine();
				}
			}
			
			arq1.close();
			arq2.close();
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		retornarArquivo(caminho,caminhoTemp);
		
		new File(caminhoTemp).delete();
		
		return erro;
	}
	
	
	
	public void retornarArquivo(String caminho,String caminhoTemp) {
		
		try {
			
			BufferedWriter arq1 = new BufferedWriter(new FileWriter(caminho));
			BufferedReader arq2 = new BufferedReader(new FileReader(caminhoTemp));
			
			while(arq2.ready()) {
				
				String linha = arq2.readLine();
				
				arq1.write(linha);
			}
			
			arq1.close();
			arq2.close();
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
	}
	
	
	
	
	public boolean editarProduto(String diretorio,String codigo,String descricao,String valor,String uniMedida) {
		
		String caminho = verificarCaminho(diretorio+"produto.txt",diretorio);
		String caminhoTemp = verificarCaminho(diretorio+"caminhoTemp.txt",diretorio);
		
		boolean encontrado = false;
		
		String novaLinha = String.format("%-3.3s %-25.25s %-5.5s %-8.8s", codigo,descricao,uniMedida,valor);
		
		try {
			
			BufferedReader arq1 = new BufferedReader(new FileReader(caminho));
			BufferedWriter arq2 = new BufferedWriter(new FileWriter(caminhoTemp));
			
			while(arq1.ready()) {
				
				String linha = arq1.readLine();
				String codigo2 = linha.substring(0, 3).trim();
				
				if(codigo.equals(codigo2)) {
					
					arq2.write(novaLinha);
					
					encontrado = true;
					
				}else {
					
					arq2.write(linha);
				}
			}
			
			arq1.close();
			arq2.close();
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		return encontrado;
	}
	
	
	
	public String valorTotal(String valorStg, String quantidadeStg) {
		
		double valorDouble = Double.parseDouble(valorStg);
		int quantidade = Integer.parseInt(quantidadeStg);
		
		double vTotal = valorDouble*quantidade;
		
		String valorTotal = Double.toString(vTotal);
		
		valorTotal = valorTotal.replace(".", ",");
		valorTotal = "R$ "+valorTotal;
		
		return valorTotal;
	}
	
	
	
	
	public String botaoMenos(String quantidade) {
		
		int num = Integer.parseInt(quantidade);
		
		if(num > 1) {
			
			num = num-1;
		}
		
		quantidade = Integer.toString(num);
		
		return quantidade;
	}
	
	
	
	public String botaoMais(String quantidade) {
		
		int num = Integer.parseInt(quantidade);
			
		num = num+1;
		
		quantidade = Integer.toString(num);
		
		return quantidade;
	}
}
