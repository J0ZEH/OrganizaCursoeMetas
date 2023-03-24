//Trab.2 por José Florêncio e Vinicius Cerveira - Linguagem de Programação II

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner entrada = new Scanner(System.in);
        ArrayList<Cadeira> cadeiras = new ArrayList<>();
        Aluno aluno = null;

        cadeiras.add(new Cadeira("A", "INTRODUÇÃO À COMPUTAÇÃO", "Exatas", "Obrigatória", "SEG", "14:00h"));
        cadeiras.add(new Cadeira("B", "ALGORITMOS I", "Exatas", "Obrigatória", "TER", "15:50h"));
        cadeiras.add(new Cadeira("C", "CÁLCULO I", "Exatas", "Obrigatória", "QUA", "15:50h"));
        cadeiras.add(new Cadeira("D", "CÁLCULO VETORIAL", "Exatas", "Obrigatória", "QUI", "14:00h"));
        cadeiras.add(new Cadeira("E", "ÉTICA E CIDADANIA", "Humanas", "Obrigatória", "SEX", "15:50h"));
        cadeiras.add(new Cadeira("F", "FÍSICA I", "Exatas", "Obrigatória", "SEG", "14:00h", "C"));
        cadeiras.add(new Cadeira("G", "LINGUAGEM DE PROGRAMAÇÃO I", "Exatas", "Obrigatória", "TER",  "17:40h", "B"));
        cadeiras.add(new Cadeira("H", "MATEMÁTICA DISCRETA E LÓGICA", "Exatas", "Obrigatória", "QUA", "15:50h"));
        cadeiras.add(new Cadeira("I", "CÁLCULO II", "Exatas", "Obrigatória", "QUI",  "14:00h", "C"));
        cadeiras.add(new Cadeira("J", "ÁLGEBRA LINEAR I", "Exatas", "Obrigatória", "SEX",  "15:50", "D"));
        cadeiras.add(new Cadeira("K", "FÍSICA III", "Exatas", "Obrigatória", "SEG", "17:40h", "F"));
        cadeiras.add(new Cadeira("L", "ARQUITETURA DE COMPUTADORES", "Exatas", "Obrigatória", "TER", "15:50h", "H"));
        cadeiras.add(new Cadeira("M", "ESTRUTURA DE DADOS I", "Exatas", "Obrigatória", "QUA", "15:50h", "G"));
        cadeiras.add(new Cadeira("N", "LINGUAGEM DE PROGRAMAÇÃO II", "Exatas", "Obrigatória", "QUI", "14:00h", "B"));
        cadeiras.add(new Cadeira("N", "CÁLCULO III", "Exatas", "Obrigatória", "SEX", "17:40h", "I"));
        cadeiras.add(new Cadeira("O", "SISTEMAS OPERACIONAIS I", "Exatas", "Obrigatória", "SEG", "14:00h", "M"));
        cadeiras.add(new Cadeira("P", "ESTRUTURA DE DADOS II", "Exatas", "Obrigatória", "SEG", "15:50h", "M"));
        cadeiras.add(new Cadeira("Q", "ENGENHARIA DE SOFTWARE", "Exatas", "Obrigatória", "TER", "15:50h", "N"));
        cadeiras.add(new Cadeira("R", "ESTATÍSTICA E PROBABILIDADE", "Exatas", "Obrigatória", "SEX", "19:30h", "N"));
        cadeiras.add(new Cadeira("S", "LINGUAGENS FORMAIS E AUTÔMATOS", "Exatas", "Obrigatória", "QUI", "14:00h", "H"));
        cadeiras.add(new Cadeira("T", "REDES DE COMPUTADORES I", "Exatas", "Obrigatória", "QUA", "14:00h", "L"));

        while(true){
            System.out.println("""
                Menu principal
                (1) Cadastrar aluno
                (2) Fazer matrícula
                (3) Desmatricular
                (4) Cadastrar cadeira
                (5) Consultar matrículas
                (6) Lançar notas
                (7) Consultar histórico
                (8) Finalizar período 
                (9) Consultar sequência aconselhada
                (10) Montar grade automaticamente
                """);

            String opcao = entrada.next();
            switch(opcao){
                case "1":
                    System.out.print("""
                        Cadastro de aluno
                        Nome:  """);
                    String nomeAluno = entrada.next();

                    aluno = new Aluno(nomeAluno);
                    System.out.println("~ Aluno cadastrado!");
                    System.out.println("");
                    break;

                case "2":
                    if(aluno != null){
                        while(true){
                            System.out.println("Matricular cadeira");
                            for(Cadeira cadeira : cadeiras){ //Listar todas as cadeiras
                                System.out.println("(Código: "+cadeira.getCodigo()+") "+cadeira.getDia()+"/"+cadeira.getHorario()+" - "+cadeira.getNomeCadeira());
                            }
                            System.out.println("Digite * para voltar");
                            System.out.print("\nDigite o código da cadeira: ");
                            String codigo = entrada.next();

                            if(codigo.equals("*")){
                                break;
                            }

                            Cadeira cadeiraAtual = null;
                            for(Cadeira cadeira : cadeiras){ //Encontrar cadeira através do código
                                if(codigo.equals(cadeira.getCodigo())){
                                    cadeiraAtual = cadeira;
                                }
                            }

                            if(cadeiraAtual == null){
                                System.out.println("~ Insira um código válido!");
                                continue;
                            }

                            boolean matriculada = false;
                            for(Cadeira cadeira : aluno.getCadeirasAtuais()){ //Checar se a cadeira já foi matriculada
                                if(cadeiraAtual.equals(cadeira)){
                                    matriculada = true;
                                    break;
                                }
                            }

                            if(matriculada == true){
                                System.out.println("~ O aluno já está matriculado nesta cadeira.");
                                continue;
                            }

                            boolean preRequisito = false;
                            if(cadeiraAtual.getPreRequisitoCodigo() == null){
                                preRequisito = true;
                            }else{
                                for(Cadeira cadeira : aluno.getHistorico()){ //Checar se cumpre o pré-requisito
                                    if(cadeiraAtual.getPreRequisitoCodigo().equals(cadeira.getCodigo())){
                                        preRequisito = true;
                                        break;
                                    }
                                }
                            }

                            if(preRequisito == false){
                                System.out.println("~ O aluno não possui o pré-requisito desta cadeira.");
                                continue;
                            }

                            boolean choqueHorario = false;
                            for(Cadeira cadeira : aluno.getCadeirasAtuais()){ //Checar se há choque de horário
                                if(cadeiraAtual.getDia().equals(cadeira.getDia())){
                                    if(cadeiraAtual.getHorario().equals(cadeira.getHorario())){
                                        choqueHorario = true;
                                        break;
                                    }
                                }
                            }

                            if(choqueHorario == true){
                                System.out.println("~ Há choque de horário entre as disciplinas.");
                                continue;
                            }

                            boolean cadeiraFinalizada = false;
                            for(Cadeira cadeira : aluno.getHistorico()){
                                if(cadeiraAtual.equals(cadeira)){
                                    cadeiraFinalizada = true;
                                    break;
                                }
                            }

                            if(cadeiraFinalizada){
                                System.out.println("~ Esta cadeira já foi finalizada");
                                continue;
                            }

                            aluno.matricularCadeira(cadeiraAtual);
                            System.out.println("~ O aluno foi matriculado na cadeira.");
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno");
                    }
                    System.out.println("");
                    break;

                case "3":
                    if(aluno != null){
                        while(true){
                            if(aluno.getCadeirasAtuais().size() > 0){
                                System.out.println("Desmatricular cadeira");
                                for(Cadeira cadeira : aluno.getCadeirasAtuais()){ //Listar todas as cadeiras
                                    System.out.println("(Código: "+cadeira.getCodigo()+") "+cadeira.getDia()+"/"+cadeira.getHorario()+" - "+cadeira.getNomeCadeira());
                                }
                                System.out.println("Digite * para voltar");
                                System.out.print("\nDigite o código da cadeira: ");
                                String codigo = entrada.next();

                                if(codigo.equals("*")){
                                    break;
                                }

                                Cadeira cadeiraMatriculada = null;
                                for(Cadeira cadeira : aluno.getCadeirasAtuais()){ //Encontrar cadeira através do código
                                    if(codigo.equals(cadeira.getCodigo())){
                                        cadeiraMatriculada = cadeira;
                                    }
                                }

                                if(cadeiraMatriculada == null){
                                    System.out.println("~ Código inválido.");
                                    continue;
                                }

                                cadeiraMatriculada.setNota(0.0f);
                                aluno.desmatricularCadeira(cadeiraMatriculada);
                                System.out.println("~ Cadeira desmatriculada.");
                                System.out.println("");
                            }else{
                                System.out.println("~ O aluno não está matriculado em nenhuma cadeira.");
                                break;
                            }
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno");
                    }
                    System.out.println("");
                    break;

                case "4":
                    System.out.println("Cadastrar cadeira");
                    System.out.print("Nome: ");
                    String nomeCadeira = entrada.next();

                    String codigoCadeira;
                    while(true){ //Código para a cadeira
                        System.out.println("Código da cadeira");
                        System.out.println("Código: ");
                        codigoCadeira = entrada.next();

                        boolean codigoExiste = false;
                        for(Cadeira cadeira : cadeiras){
                            if(codigoCadeira.equals(cadeira.getCodigo())){
                                codigoExiste = true;
                                break;
                            }
                        }

                        if(codigoExiste == true){
                            System.out.println("~ Já existe uma cadeira com este código.");
                        }else{
                            break;
                        }
                    }

                    String areaCadeira;
                    while(true){ //Selecionar área da cadeira
                        System.out.println("Área da cadeira");
                        System.out.println("[Exatas; Humanas; Natureza]");
                        areaCadeira = entrada.next();

                        if(areaCadeira.equals("Exatas") || areaCadeira.equals("Humanas") || areaCadeira.equals("Natureza")){
                            break;
                        }else{
                            System.out.println("~ Digite uma entrada válida");
                        }
                    }

                    String tipoCadeira;
                    while(true){ //Selecionar tipo da cadeira
                        System.out.println("Tipo da cadeira");
                        System.out.println("[Obrigatória; Opcional]");
                        tipoCadeira = entrada.next();

                        if(tipoCadeira.equals("Obrigatória") || tipoCadeira.equals("Opcional")){
                            break;
                        }else{
                            System.out.println("~ Digite uma entrada válida");
                        }
                    }

                    String diaCadeira;
                    while(true){ //Selecionar dia da cadeira
                        System.out.println("Dia da cadeira");
                        System.out.println("[SEG; TER; QUA; QUI; SEX]");
                        diaCadeira = entrada.next();

                        if(diaCadeira.equals("SEG") || diaCadeira.equals("TER") || diaCadeira.equals("QUA") || diaCadeira.equals("QUI") || diaCadeira.equals("SEX")){
                            break;
                        }else{
                            System.out.println("~ Digite uma entrada válida");
                        }
                    }

                    String horarioCadeira;
                    while(true){ //Selecionar horário da cadeira
                        System.out.println("Horário da cadeira");
                        System.out.println("[14:00h; 15:50h; 17:40h; 19:30h]");
                        horarioCadeira = entrada.next();

                        if(horarioCadeira.equals("14:00h") || horarioCadeira.equals("15:50h") || horarioCadeira.equals("17:40h") || horarioCadeira.equals("19:30h")){
                            break;
                        }else{
                            System.out.println("~ Digite uma entrada válida");
                        }
                    }

                    while(true){ //Selecionar pre-requisito
                        System.out.println("Pré-requisito da cadeira");

                        for(Cadeira cadeira : cadeiras){ //Listar todas as cadeiras
                            System.out.println("(Código: "+cadeira.getCodigo()+") "+cadeira.getNomeCadeira());
                        }
                        System.out.println("(Código: *) Sem pré-requisito");
                        System.out.print("\nDigite o código da cadeira: ");
                        String codigo = entrada.next();



                        if(codigo.equals("*")){
                            cadeiras.add(new Cadeira(codigoCadeira, nomeCadeira, areaCadeira, tipoCadeira, diaCadeira, horarioCadeira));
                            System.out.println("~ Uma nova cadeira foi cadastrada.\n");
                            break;
                        }else{
                            Cadeira cadeiraAtual = null;
                            for(Cadeira cadeira : cadeiras){ //Encontrar cadeira através do código
                                if(codigo.equals(cadeira.getCodigo())){
                                    cadeiraAtual = cadeira;
                                }
                            }

                            if(cadeiraAtual != null){
                                cadeiras.add(new Cadeira(codigoCadeira, nomeCadeira, areaCadeira, tipoCadeira, diaCadeira, horarioCadeira, cadeiraAtual.getCodigo()));
                                System.out.println("~ Uma nova cadeira foi cadastrada.\n");
                                break;
                            }else{
                                System.out.println("~ Digite uma entrada válida");
                            }
                        }
                    }
                    System.out.println("");
                    break;

                case "5":
                    if(aluno != null){
                        if(aluno.getCadeirasAtuais().size() > 0){
                            System.out.println("Cadeiras matriculadas");
                            for(Cadeira cadeira : aluno.getCadeirasAtuais()){
                                System.out.println("(Código: "+cadeira.getCodigo()+") "+cadeira.getNomeCadeira()+" - Nota: "+cadeira.getNota());
                            }
                        }else{
                            System.out.println("~ Não existem cadeiras matriculadas.");
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno.");
                    }
                    System.out.println("");
                    break;

                case "6":
                    if(aluno != null){
                        if(aluno.getCadeirasAtuais().size() > 0){
                            System.out.println("Lançar notas");
                            for(Cadeira cadeira : aluno.getCadeirasAtuais()){
                                System.out.println(cadeira.getNomeCadeira());

                                System.out.print("Nota: ");
                                float notaCadeira;
                                while(true){
                                    try{
                                        notaCadeira = entrada.nextFloat();
                                        break;
                                    }catch(InputMismatchException exception){
                                        System.out.print("Nota: ");
                                        entrada.nextLine();
                                    }
                                }
                                cadeira.setNota(notaCadeira);
                                System.out.println("\n");
                            }
                        }else{
                            System.out.println("~ O aluno não está matriculado em nenhuma cadeira.");
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno");
                    }
                    System.out.println("");
                    break;
                case "7":
                    if(aluno != null){
                        if(aluno.getHistorico().size() > 0){
                            System.out.println("Histórico de cadeiras");
                            for(Cadeira cadeira : aluno.getHistorico()){
                                System.out.println("(Código: "+cadeira.getCodigo()+") "+cadeira.getNomeCadeira()+" - Nota: "+cadeira.getNota());
                            }
                        }else{
                            System.out.println("~ Não existem cadeiras na lista de finalizadas.");
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno.");
                    }
                    System.out.println("");
                    break;

                case "8":
                    if(aluno != null){
                        if(aluno.getCadeirasAtuais().size() > 0){
                            int quantidadeReprovacoes = 0;
                            for(Cadeira cadeira : aluno.getCadeirasAtuais()){
                                if(cadeira.getNota() >= 7){
                                    aluno.finalizarCadeira(cadeira);
                                }else{
                                    cadeira.setNota(0.0f);
                                    quantidadeReprovacoes++;
                                }
                            }
                            aluno.getCadeirasAtuais().clear();
                            System.out.println("~ As cadeiras foram finalizadas.");
                            System.out.println("~ O aluno reprovou em "+quantidadeReprovacoes+" cadeiras.");
                        }else{
                            System.out.println("~ O aluno não está matriculado em nenhuma cadeira.");
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno.");
                    }
                    System.out.println("");
                    break;

                case "9":
                    System.out.println("Sequência aconselhada\n");
                    System.out.println("(Período 1)");
                    for(int i = 0; i < 5; i++){
                        System.out.println(cadeiras.get(i).getNomeCadeira());
                    }

                    System.out.println("\n(Período 2)");
                    for(int i = 5; i < 10; i++){
                        System.out.println(cadeiras.get(i).getNomeCadeira());
                    }

                    System.out.println("\n(Período 3)");
                    for(int i = 10; i < 15; i++){
                        System.out.println(cadeiras.get(i).getNomeCadeira());
                    }

                    System.out.println("\n(Período 4)");
                    for(int i = 15; i < 21; i++){
                        System.out.println(cadeiras.get(i).getNomeCadeira());
                    }
                    System.out.println("");
                    break;

                case "10":
                    if(aluno != null){
                        for(Cadeira cadeira : aluno.getCadeirasAtuais()){
                            cadeira.setNota(0.0f);
                        }
                        aluno.getCadeirasAtuais().clear();

                        System.out.println("Grade montada automaticamente");
                        for(Cadeira cadeiraAtual : cadeiras){
                            int quantidadeDisciplina = 0;

                            boolean preRequisito = false;
                            if(cadeiraAtual.getPreRequisitoCodigo() == null){
                                preRequisito = true;
                            }else{
                                for(Cadeira cadeira : aluno.getHistorico()){ //Checar se cumpre o pré-requisito
                                    if(cadeiraAtual.getPreRequisitoCodigo().equals(cadeira.getCodigo())){
                                        preRequisito = true;
                                        break;
                                    }
                                }
                            }

                            boolean choqueHorario = false;
                            for(Cadeira cadeira : aluno.getCadeirasAtuais()){ //Checar se há choque de horário
                                if(cadeiraAtual.getDia().equals(cadeira.getDia())){
                                    if(cadeiraAtual.getHorario().equals(cadeira.getHorario())){
                                        choqueHorario = true;
                                        break;
                                    }
                                }
                            }

                            boolean cadeiraFinalizada = false;
                            for(Cadeira cadeira : aluno.getHistorico()){ //Checar se a cadeira foi finalizada
                                if(cadeiraAtual.equals(cadeira)){
                                    cadeiraFinalizada = true;
                                    break;
                                }
                            }

                            if(preRequisito == false){
                                continue;
                            }
                            if(choqueHorario == true){
                                continue;
                            }
                            if(cadeiraFinalizada == true){
                                continue;
                            }

                            quantidadeDisciplina++;
                            if(quantidadeDisciplina == 5){
                                break;
                            }

                            System.out.println("("+cadeiraAtual.getCodigo()+") "+cadeiraAtual.getNomeCadeira());
                            aluno.matricularCadeira(cadeiraAtual);
                        }
                    }else{
                        System.out.println("~ Cadastre o aluno.");
                    }
                    System.out.println("");
                    break;

                default:
                    System.out.println("~ Insira uma opção válida!");
                    System.out.println("");
                    break;
            }
        }


        
        
    } 
}
