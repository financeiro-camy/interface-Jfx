package DAO;

public class UsuarioAtributo {
        private int id;
        private int id_usuario;
        private String nome_atributo;
        private String valor_atributo;
    
        public UsuarioAtributo(int id, int id_usuario, String nome_atributo, String valor_atributo) {
            this.id = id;
            this.id_usuario = id_usuario;
            this.nome_atributo = nome_atributo;
            this.valor_atributo = valor_atributo;
        }
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public int getId_usuario() {
            return id_usuario;
        }
    
        public void setId_usuario(int id_usuario) {
            this.id_usuario = id_usuario;
        }
    
        public String getNome_atributo() {
            return nome_atributo;
        }
    
        public void setNome_atributo(String nome_atributo) {
            this.nome_atributo = nome_atributo;
        }
    
        public String getValor_atributo() {
            return valor_atributo;
        }
    
        public void setValor_atributo(String valor_atributo) {
            this.valor_atributo = valor_atributo;
        }
}
