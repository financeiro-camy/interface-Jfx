package DAO;

public class Periodicidade {
    
        private int id;
        private int idUsuario;
        private String nome;
    
        public Periodicidade(int id, int idUsuario, String nome) {
            this.id = id;
            this.idUsuario = idUsuario;
            this.nome = nome;
        }

        public Periodicidade( int idUsuario, String nome) {
            this.idUsuario = idUsuario;
            this.nome = nome;
        }
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public int getIdUsuario() {
            return idUsuario;
        }
    
        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }
    
        public String getNome() {
            return nome;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
         public String toString() {
        return "Periodicidade [id=" + id + ", idUsuario=" + idUsuario + ", nome=" + nome + "]";
    }
    }
    

