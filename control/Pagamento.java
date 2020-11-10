package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Pagamento extends Arquivo{

	private String valor = null,formaPagamento = null;

	
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
	
	
	public boolean cadastrarPagamento(String diretorio,String txtValor,String cbxPagamento) {
		
		String caminho = verificarCaminho(diretorio+"pagamento.txt", diretorio);
		
		String codigo = criarCodigo(caminho);
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
			
			bw.write(String.format("%-3.3s %-9.9s %-7.7s", codigo, txtValor, cbxPagamento));
			bw.newLine();
			
			bw.close();
			
			return true;
			
		}catch(Exception e) {
			
			return false;
		}
	}
	
	
	
	
	public String baseDeDados(String diretorio) {
		
		String caminho = verificarCaminho(diretorio+"pagamento.txt", diretorio);
		
		String aux = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(caminho));
			
			while(br.ready()) {
				
				String linha = br.readLine();
				
				aux = aux+linha+"\n";
				
				br.readLine();
			}	
				
			br.close();
			
		}catch(Exception e) {
			
			System.out.println(e);
			
		}
		
		return aux;
	}
	
	
	
	
	public String criarCodigo(String caminho) {
		
		int cont = 0;
		
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
		
		cont++;
		
		String aux = Integer.toString(cont);
		
		if(cont<10) {
			
			aux = "00"+aux;
			
		}else {
			
			if(cont<100) {
				
				aux = "0"+aux;
				
			}
		}
		
		return aux;
	}
	
	
	
	
	public String valorTotal(String valorTotal) {
		
		valorTotal = valorTotal.replace(",", ".");
		valorTotal = "R$ "+valorTotal;
		
		valor = valor.replace(",", ".");
		valor = valor.replace("R$ ", "");
		
		double intValor = Double.parseDouble(valorTotal);
		double subTotal = Double.parseDouble(valor);
		
		subTotal =+ intValor;
		
		valor = Double.toString(subTotal);
		
		valor = valor.replace(".", ",");
		valor = "R$ "+valor;
		
		return valor;
	}
}
