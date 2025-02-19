package interface_menu;

import java.util.Scanner;

import ecopontos.Ecoponto;
import ecopontos.Endereco;
import ecopontos.Pessoa;

public class Interface {
		private Ecoponto[] ecopontos = new Ecoponto[1000];
		private int num_eco = 0;
		private int aux,aux1,cont;
		private String stringaux;
		public void menu() {
			System.out.println("0.Sair");
			System.out.println("1.Cadastrar Ecopontos");
			System.out.println("2.Gerenciar resíduos");
			System.out.println("3.Cadastrar/alterar responsável");
			System.out.println("4.Apresentar todos os Ecopontos cadastrados");
			System.out.println("5.Apresentar todos os Ecopontos cadastrados que tem o mesmo responsável");
			System.out.println("6.Apresentar todos os Ecopontos cadastrados que recebem um determinado tipo de resíduo");
			System.out.println("7.Informações sobre ecopontos");
			System.out.println("8.Endereço do ecoponto");
			System.out.println("9.Apresentar todos os Ecopontos cadastrados no meu estado");
			System.out.println("10.Apresentar todos os Ecopontos cadastrados na minha cidade");
			System.out.println("11.Apresentar todos os Ecopontos sem responsável");
			System.out.println("12.Apresentar todos os Ecopontos sem resíduos cadastrados");
			System.out.println("13.Alterar nome do ecoponto");
			System.out.println("14.Alterar endereço do ecoponto");
			Ecoponto ecoponto ;
			
			Scanner entrada = new Scanner(System.in);
			int opcao = entrada.nextInt();
			entrada.nextLine();
			while( opcao != 0) {
				switch(opcao) {
				case 1:
					//NOME
					ecoponto = new Ecoponto();
					System.out.println("Digite o nome do ecoponto a ser cadastrado:");
					String nome = entrada.nextLine();
					ecoponto.setNome(nome);
					//ENDEREÇO
					
					System.out.println("Digite o estado do ecoponto:");
					String estado = entrada.nextLine();
					System.out.println("Digite a cidade do ecoponto:");
					String cidade = entrada.nextLine();
					System.out.println("Digite a rua do ecoponto:");
					String rua = entrada.nextLine();
					System.out.println("Digite o cep do ecoponto:");
					String cep = entrada.nextLine();
					System.out.println("Digite o número do ecoponto:");
					aux = entrada.nextInt();
					Endereco adress = new Endereco(cep,rua,cidade,estado,aux);
					
					ecoponto.setEndereco(adress);
					
					//RESÍDUOS
					
					System.out.println("Digite a quantidade de resíduos que o Ecoponto vai recolher:");
					aux = entrada.nextInt();
					entrada.nextLine();
					if(aux > 0) {
					String residuo[] = new String[aux];
					for(int i = 0;i<aux;i++) {
						System.out.println("Digite o " + (i+1) + " resíduo");
						residuo[i] = entrada.nextLine();
						
						for(aux1 = 0;aux1<i;aux1++) {
							if(residuo[aux1].toUpperCase().equals(residuo[i].toUpperCase())) {
								System.out.println("Este resíduo já está no ecoponto, por favor digite outro resíduo:");
								residuo[i] = entrada.nextLine();
								aux1 = -1;
							}
						}
					}
					ecoponto.setResiduos(residuo);
					}
					//RESPONSAVEL
					
					System.out.println("0 - Quero cadastrar o responsável agora.\n1 - Quero cadastrar o responsável depois.");
					aux = entrada.nextInt();
					if(aux == 0) {
						System.out.println("Digite o nome do responsável:");
						entrada.nextLine();
						nome = entrada.nextLine();
						System.out.println("Digite o cpf:");
						String cpf = entrada.nextLine();
						Pessoa responsavel = new Pessoa(nome,cpf);
						ecoponto.setPessoa(responsavel);
					}
					
					//CONTADOR DE ECOPONTOS
					ecopontos[num_eco] = ecoponto;
					num_eco++;
					
					System.out.println("Ecoponto cadastrado com sucesso!");
					
					break;
				case 2:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						System.out.println("Digite o ID do ecoponto:");
						aux = entrada.nextInt();
						while(aux < 0 || aux > (num_eco-1)){
						System.out.println("Ecoponto não encontrado, tente novamente");
						aux = entrada.nextInt();
						}
						System.out.println("1. Incluir um resíduo.");
						System.out.println("2. Remover um resíduo.");
						aux1 = entrada.nextInt();
						while(aux1!=1 && aux1!=2) {
							System.out.println("Tente novamente!");
							
							System.out.println("1. Incluir um resíduo.");
							System.out.println("2. Remover um resíduo.");
							aux1 = entrada.nextInt();
				
						}
						switch(aux1) {
						case 1:
							System.out.println("Qual resíduo você quer incluir?");
							entrada.nextLine();
							String novoresiduo = entrada.nextLine();
							int flag=0;
							String novastringresiduo[] = new String[(ecopontos[aux].getResiduos().length) + 1];
							for(int i = 0;i<ecopontos[aux].getResiduos().length;i++) {
								if(novoresiduo.toUpperCase().equals(ecopontos[aux].getResiduos()[i].toUpperCase())) {
									flag = 1;
								}
								novastringresiduo[i] = ecopontos[aux].getResiduos()[i];
							}
							novastringresiduo[(ecopontos[aux].getResiduos().length)] = novoresiduo;
							if(flag != 1) {
							ecopontos[aux].setResiduos(novastringresiduo);
							System.out.println("Resíduo incluido!");
							}
							else {
								System.out.println("O resíduo já foi cadastrado nesse ecoponto!");
							}
							break;
							
						case 2:
							System.out.println("Qual resíduo você quer remover?");
							entrada.nextLine();
							novoresiduo = entrada.nextLine();
							aux1=0;
							String novastringsemresiduo[] = new String[(ecopontos[aux].getResiduos().length) - 1];
							int k=0;
							for(int i = 0;i<ecopontos[aux].getResiduos().length;i++) {
								if(novoresiduo.toUpperCase().equals(ecopontos[aux].getResiduos()[i].toUpperCase()) && k == 0) {
								k = 1;
								}
								else {
									if((i-k) != (ecopontos[aux].getResiduos().length - 1)) {
									novastringsemresiduo[i-k] = ecopontos[aux].getResiduos()[i];
									}
									else {
										System.out.println("Resíduo não encontrado no ecoponto!");
										aux1 = 1;
									}
								}
							}
							if(aux1 == 0) {
							ecopontos[aux].setResiduos(novastringsemresiduo);
							System.out.println("Resíduo removido!");
							}
							break;
						}
					}
					break;
				case 3:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						System.out.println("Digite o nome do responsável:");
						nome = entrada.nextLine();
						System.out.println("Digite o cpf:");
						String cpf = entrada.nextLine();
						Pessoa responsavel = new Pessoa(nome,cpf);
						
						System.out.println("Digite o ID do ecoponto:");
						int numresponsa = entrada.nextInt();
						while(numresponsa < 0 || numresponsa > (num_eco-1)){
							System.out.println("Ecoponto não encontrado, tente novamente");
							numresponsa = entrada.nextInt();
						}
						ecopontos[numresponsa].setPessoa(responsavel);
					}
					break;
				case 4:
					if(num_eco == 0) {
						System.out.println("Não há nenhum ecoponto cadastrado!");
					}
					else {
						for(int i = 0;i < num_eco ; i++) {
							System.out.println("");
							System.out.println("Ecoponto ID " + i + ":");
							System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
							System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
							if(ecopontos[i].pegaResiduos() != null) {
							System.out.println("Resíduos coletados por esse ecoponto: " + ecopontos[i].pegaResiduos());
							}
							else {
								System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
							}
							if(ecopontos[i].getPessoa() != null) {
								System.out.println("Nome do responsável: " + ecopontos[i].getPessoa().getNome());
							}
							else {
								System.out.println("Nome do responsável: Responsável não cadastrado!");
							}
						}
						System.out.println("");
					}
					break;
				case 5:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						cont = 0;
						System.out.println("");
						System.out.println("Digite o CPF do responsável");
						stringaux = entrada.nextLine();
						
						
						for(int i = 0;i < num_eco ; i++) {
							if(ecopontos[i].getPessoa() != null) {
								if(stringaux.equals(ecopontos[i].getPessoa().getCpf())) {
									cont++;
									System.out.println("");
									System.out.println("Ecoponto ID " + i + ":");
									System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
									System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
									if(ecopontos[i].pegaResiduos() != null) {
									System.out.println("Resíduos coletados por esse ecoponto: " + ecopontos[i].pegaResiduos());
									}
									else {
										System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
									}
									System.out.println("Nome do responsável: " + ecopontos[i].getPessoa().getNome());
								}
								System.out.println("");
							}
						}
						if(cont == 0) {
							System.out.println("");
							System.out.println("Não achamos nenhum ecoponto com esse responsável!");
							System.out.println("");
						}
					}
					break;
				case 6:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						cont = 0;
						System.out.println("Digite o resíduo a ser filtrado:");
						stringaux = entrada.nextLine();
						
						
						for(int i = 0;i < num_eco ; i++) {
							int flag = 0;
							if(ecopontos[i].getResiduos() != null) {
								for(int k = 0;k<ecopontos[i].getResiduos().length;k++) {
									if(stringaux.toUpperCase().equals(ecopontos[i].getResiduos()[k].toUpperCase())) {
										flag = 1;
									}
								}
							}
							if(flag == 1) {
								cont++;
								System.out.println("");
								System.out.println("Ecoponto ID " + i + ":");
								System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
								System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
								if(ecopontos[i].pegaResiduos() != null) {
								System.out.println("Resíduos coletados por esse ecoponto: " + ecopontos[i].pegaResiduos());
								}
								else {
									System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
								}
								if(ecopontos[i].getPessoa() != null) {
									System.out.println("Nome do responsável: " + ecopontos[i].getPessoa().getNome());
								}
								else {
									System.out.println("Nome do responsável: Responsável não cadastrado!");
								}
							}
							System.out.println("");
						}
						if(cont == 0) {
							System.out.println("");
							System.out.println("Não achamos nenhum ecoponto que colete esse resíduo!");
							System.out.println("");
						}
					}
					break;
				case 7:
					System.out.println("");
					System.out.println("Os Ecopontos são locais de entrega voluntária para coletar itens que não são mais utilizados por nós.");
					System.out.println("Não necessariamente materiais recicláveis.");
					System.out.println("");
					System.out.println("Você provavelmente já viu algum sofá velho ou televisão antiga abandonados nas calçadas de sua");
					System.out.println("cidade ou até entulho gerado por construções, demolições e pequenas reformas em prédios ou");	
					System.out.println("residências, que são jogados de maneira ilegal em avenidas, ruas e praças. Esse tipo de descarte");	
					System.out.println("irregular gera sérios problemas ambientais.");	
					System.out.println("");
					System.out.println("Bem, os Ecopontos foram criados exatamente com o objetivo de dar fim ao despejo desses tipos de");	
					System.out.println("itens em vias públicas, rios e terrenos baldios, que ocasiona desde problemas de saúde a enchentes,");	
					System.out.println("além aumentar os gastos com a limpeza pública. Materiais recicláveis (papel, papelão, vidro e");	
					System.out.println("alumínio) também podem ser levados para estes espaços e de lá serão encaminhados às centrais de");	
					System.out.println("triagem da cidade. Para recebê-los, os Ecopontos contam com PEVs (Ponto de Entrega Voluntária");	
					System.out.println("de Recicláveis), caixas verdes semelhantes a contêineres instaladas em locais públicos para estimular");	
					System.out.println("a entrega voluntária desse tipo de material.");	
					System.out.println("");
					System.out.println("Informação importante! Existe um limite diário para o descarte de materiais nestes locais: 1m³ de");	
					System.out.println("material por pessoa, o que equivale a cerca de 25% do volume de uma caçamba ou a uma caixa");	
					System.out.println("d’água de mil litros.");	
					System.out.println("");	
					break;
				case 8:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						System.out.println("Digite o ID do ecoponto:");
						aux = entrada.nextInt();
						while(aux < 0 || aux > (num_eco-1)){
							System.out.println("Ecoponto não encontrado, tente novamente");
							aux = entrada.nextInt();
						}
						if(ecopontos[aux].getEndereco().getCep() != null) {
						System.out.println("Acesse o link:");
						System.out.println("https://www.google.com.br/maps/place/"+ecopontos[aux].getEndereco().getRua() + ",+"+ecopontos[aux].getEndereco().getNumero()+ "+-+,+"+ecopontos[aux].getEndereco().getCidade()+"+-+,+"+ecopontos[aux].getEndereco().getCep()+"/");
						
						}
					}
					
					//www.google.com.br/maps/place/R. Eugênio Cardoso Duarte,+246+-+Bela Vista,+Caxias do Sul+-+RS,+95076-780/
					break;
				case 9:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						
						cont = 0;
						System.out.println("");
						System.out.println("Digite o seu estado");
						stringaux = entrada.nextLine();
	
						for(int i = 0;i < num_eco ; i++) {
								if(stringaux.toUpperCase().equals(ecopontos[i].getEndereco().getEstado().toUpperCase())) {
									cont++;
									System.out.println("");
									System.out.println("Ecoponto ID " + i + ":");
									System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
									System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
									if(ecopontos[i].pegaResiduos() != null) {
									System.out.println("Resíduos coletados por esse ecoponto: " + ecopontos[i].pegaResiduos());
									}
									else {
										System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
									}
									if(ecopontos[i].getPessoa() != null) {
										System.out.println("Nome do responsável: " + ecopontos[i].getPessoa().getNome());
									}
									else {
										System.out.println("Nome do responsável: Responsável não cadastrado!");
									}
								}
								System.out.println("");
						}
						if(cont == 0) {
							System.out.println("");
							System.out.println("Não achamos nenhum ecoponto no seu estado!");
							System.out.println("");
						}
					}
					break;
				case 10:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						cont = 0;
						System.out.println("");
						System.out.println("Digite a sua cidade");
						stringaux = entrada.nextLine();
	
						for(int i = 0;i < num_eco ; i++) {
								if(stringaux.toUpperCase().equals(ecopontos[i].getEndereco().getCidade().toUpperCase())) {
									cont++;
									System.out.println("");
									System.out.println("Ecoponto ID " + i + ":");
									System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
									System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
									if(ecopontos[i].pegaResiduos() != null) {
									System.out.println("Resíduos coletados por esse ecoponto: " + ecopontos[i].pegaResiduos());
									}
									else {
										System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
									}
									if(ecopontos[i].getPessoa() != null) {
										System.out.println("Nome do responsável: " + ecopontos[i].getPessoa().getNome());
									}
									else {
										System.out.println("Nome do responsável: Responsável não cadastrado!");
									}
								}
								System.out.println("");
						}
						if(cont == 0) {
							System.out.println("");
							System.out.println("Não achamos nenhum ecoponto na sua cidade!");
							System.out.println("");
						}
					}
					break;
				case 11:
					if(num_eco == 0) {
						System.out.println("Não há nenhum ecoponto sem responsável!");
					}
					else {
						
						cont = 0;
	
						for(int i = 0;i < num_eco ; i++) {
							if(ecopontos[i].getPessoa() == null) {
									cont++;
									System.out.println("");
									System.out.println("Ecoponto ID " + i + ":");
									System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
									System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
									if(ecopontos[i].pegaResiduos() != null) {
									System.out.println("Resíduos coletados por esse ecoponto: " + ecopontos[i].pegaResiduos());
									}
									else {
										System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
									}
										System.out.println("Nome do responsável: Responsável não cadastrado!");
								}
								System.out.println("");
						}
						if(cont == 0) {
							System.out.println("");
							System.out.println("Não há nenhum ecoponto sem responsável!");
							System.out.println("");
						}
						
						
					}
					break;
				case 12:
					if(num_eco == 0) {
						System.out.println("Não há nenhum ecoponto sem resíduos cadastrados!");
					}
					else {
						cont = 0;
						
						for(int i = 0;i < num_eco ; i++) {
							if(ecopontos[i].getResiduos() == null) {
									cont++;
									System.out.println("");
									System.out.println("Ecoponto ID " + i + ":");
									System.out.println("Nome do ecoponto: " + ecopontos[i].getNome());
									System.out.println("Endereço do ecoponto: " + ecopontos[i].getEndereco().getRua() + ", " + ecopontos[i].getEndereco().getCidade() + ", " + ecopontos[i].getEndereco().getEstado() + ", CEP: " + ecopontos[i].getEndereco().getCep() + ", Número: " + ecopontos[i].getEndereco().getNumero());
									System.out.println("Resíduos coletados por esse ecoponto: Não foi cadastrado nenhum resíduo para esse ecoponto!");
									if(ecopontos[i].getPessoa() != null) {
										System.out.println("Nome do responsável: " + ecopontos[i].getPessoa().getNome());
									}
									else {
										System.out.println("Nome do responsável: Responsável não cadastrado!");
									}
								System.out.println("");
							}
						}
						if(cont == 0) {
							System.out.println("");
							System.out.println("Não há nenhum ecoponto sem resíduos cadastrados!");
							System.out.println("");
						}
						
						
					}
					break;
				case 13:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						System.out.println("Digite o ID do ecoponto:");
						int numresponsa = entrada.nextInt();
						while(numresponsa < 0 || numresponsa > (num_eco-1)){
							System.out.println("Ecoponto não encontrado, tente novamente");
							numresponsa = entrada.nextInt();
						}
						entrada.nextLine();
						
						System.out.println("Digite o novo nome para o ecoponto:");
						nome = entrada.nextLine();

						ecopontos[numresponsa].setNome(nome);
					}
					
					break;
				case 14:
					if(num_eco == 0) {
						System.out.println("Cadastre um ecoponto primeiro!");
					}
					else {
						System.out.println("Digite o ID do ecoponto:");
						int numresponsa = entrada.nextInt();
						
						while(numresponsa < 0 || numresponsa > (num_eco-1)){
							System.out.println("Ecoponto não encontrado, tente novamente");
							numresponsa = entrada.nextInt();
						}
						entrada.nextLine();
						
						System.out.println("Digite o estado do ecoponto:");
						estado = entrada.nextLine();
						System.out.println("Digite a cidade do ecoponto:");
						cidade = entrada.nextLine();
						System.out.println("Digite a rua do ecoponto:");
						rua = entrada.nextLine();
						System.out.println("Digite o cep do ecoponto:");
						cep = entrada.nextLine();
						System.out.println("Digite o número do ecoponto:");
						aux = entrada.nextInt();
						adress = new Endereco(cep,rua,cidade,estado,aux);
						
						ecopontos[numresponsa].setEndereco(adress);
					}
					break;
				}
			
				System.out.println("0.Sair");
				System.out.println("1.Cadastrar Ecopontos");
				System.out.println("2.Gerenciar resíduos");
				System.out.println("3.Cadastrar/alterar responsável");
				System.out.println("4.Apresentar todos os Ecopontos cadastrados");
				System.out.println("5.Apresentar todos os Ecopontos cadastrados que tem o mesmo responsável");
				System.out.println("6.Apresentar todos os Ecopontos cadastrados que recebem um determinado tipo de resíduo");
				System.out.println("7.Informações sobre ecopontos");
				System.out.println("8.Endereço do ecoponto");
				System.out.println("9.Apresentar todos os Ecopontos cadastrados no meu estado");
				System.out.println("10.Apresentar todos os Ecopontos cadastrados na minha cidade");
				System.out.println("11.Apresentar todos os Ecopontos sem responsável");
				System.out.println("12.Apresentar todos os Ecopontos sem resíduos cadastrados");
				System.out.println("13.Alterar nome do ecoponto");
				System.out.println("14.Alterar endereço do ecoponto");
				opcao = entrada.nextInt();
				entrada.nextLine();
			}
			System.out.println("Programa encerrado!");
		}
}