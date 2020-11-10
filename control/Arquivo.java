package control;

	import java.io.File;

	public class Arquivo {
		
		


		public String verificarCaminho(String caminho,String diretorio) {
			
			File d = new File(diretorio);
			
			if(d.exists()) {
				
				try {
					
					File c = new File(caminho);
					
					if(c.exists()) {
						
						return caminho;
						
					}else {
						
						if(c.createNewFile()) {
							
							return caminho;
						}
					}
					
					
					
				}catch(Exception e) {
					
					System.out.println("Erro na criação do arquivo. Erro:"+e);
				}
				
			}else {
				
				if(d.mkdirs()) {
					
					try {
						
						File c = new File(caminho);
						
						if(c.exists()) {
							
							return caminho;
							
						}else{
							
							if(c.createNewFile()){
								
								return caminho;
							}
						}
						
					}catch(Exception e) {
						
						System.out.println("Erro na criação do arquivo. Erro:"+e);
					}
				}else {
					
					System.out.println("Erro na criação do diretório.");
				}
			}
			return null;
		}
	}
	


