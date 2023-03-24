public class Cadeira {
    protected String codigo,
                     nomeCadeira,
                     areaInteresse,
                     tipo,
                     diaSemana,
                     horario,
                     preRequisitoCodigo;
    protected float nota;

    public Cadeira(String codigo, String nomeCadeira, String areaInteresse,
                   String tipo, String diaSemana, String horario, String preRequisitoCodigo){
        this.codigo = codigo;
        this.nomeCadeira = nomeCadeira;
        this.areaInteresse = areaInteresse;
        this.tipo = tipo;
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.preRequisitoCodigo = preRequisitoCodigo;
        this.nota = 0.0f;
    }

    public Cadeira(String codigo, String nomeCadeira, String areaInteresse,
                   String tipo, String diaSemana, String horario){
        this.codigo = codigo;
        this.nomeCadeira = nomeCadeira;
        this.areaInteresse = areaInteresse;
        this.tipo = tipo;
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.preRequisitoCodigo = null;
    }

    public String getNomeCadeira(){
        return this.nomeCadeira;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getDia(){
        return this.diaSemana;
    }

    public String getHorario(){
        return this.horario;
    }

    public String getPreRequisitoCodigo(){
        return this.preRequisitoCodigo;
    }

    public void setPreRequisitoCodigo(String preRequisitoCodigo){
        this.preRequisitoCodigo = preRequisitoCodigo;
    }

    public float getNota(){
        return this.nota;
    }

    public void setNota(float nota){
        this.nota = nota;
    }
}
