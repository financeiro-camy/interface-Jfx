package DAO;

public class Categoria {
    private int id;
    private int id_usuario;
    private String nome;

    public Categoria (int id, int id_usuario, String nome){
    this.id = id;
    this.id_usuario = id_usuario;
    this.nome = nome;
  }

public Categoria (int id_usuario, String nome){
    this.id = id_usuario;
    this.nome = nome;
}

public int getId(){
    return id;
}

public void setId(int id){
    this.id = id;
}

public int getId_usuario(){
    return id_usuario;
}

public void setId_usuario(int id_usuario){
    this.id_usuario = id_usuario;
}

public String getNome(){
    return nome;
}

public void setNome(String nome){
   this.nome=nome;
}

@Override
    public String toString() {
        return "Categoria [id=" + id + ", id_usuario=" + id_usuario + ", nome=" + nome + "]";
    }

}
