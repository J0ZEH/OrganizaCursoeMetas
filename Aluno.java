import java.util.ArrayList;

public class Aluno extends Pessoa{
    protected ArrayList<Cadeira> cadeirasAtuais,
                                    historico;

    public Aluno(String nome){
        super(nome);
        cadeirasAtuais = new ArrayList<>();
        historico = new ArrayList<>();
    }

    public void finalizarCadeira(Cadeira c){
        this.historico.add(c);
    }

    public void matricularCadeira(Cadeira c){
        this.cadeirasAtuais.add(c);
    }

    public void desmatricularCadeira(Cadeira c){
        this.cadeirasAtuais.remove(c);
    }

    public ArrayList<Cadeira> getCadeirasAtuais(){
        return this.cadeirasAtuais;
    }

    public ArrayList<Cadeira> getHistorico(){
        return this.historico;
    }

    
}
