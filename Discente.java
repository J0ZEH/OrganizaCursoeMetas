public class Discente extends Pessoa{
    protected String codigoDisciplina;

    public Discente(String nome, String codigoDisciplina){
        super(nome);
        this.codigoDisciplina = codigoDisciplina;
    }
}
