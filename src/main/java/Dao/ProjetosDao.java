package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import Entidades.Area;
import Entidades.Funcionarios;
import Entidades.ProjetoFuncionario;
import Entidades.Projetos;
import Entidades.Status;
import Dto.FuncionariosDTO;

public class ProjetosDao {
    private EntityManagerFactory emf; 
    private EntityManager em;

    public ProjetosDao() {
        emf = Persistence.createEntityManagerFactory("Lumina");
        em = emf.createEntityManager();
    }

    public Projetos cadastrarProjeto(Projetos projeto) {
        if (projeto.getDataCriacao() == null) {
            projeto.setDataCriacao(new Date());
        }

        try {
            em.getTransaction().begin();
            em.persist(projeto);
            em.flush();  
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }

        return projeto;
    }

    public Double buscaCargo(String usuarioLogado) {
        try {
            String sql = "SELECT id_cargo FROM Funcionarios WHERE lower(id_funcionario) = lower(:matricula)";
            Query query = em.createNativeQuery(sql);
            query.setParameter("matricula", usuarioLogado);

            if (query.getSingleResult() == null) {
                return null;
            }
            return Double.parseDouble(query.getSingleResult().toString());
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<Projetos> listarProjetos() {
        String sql = "SELECT p.id_projeto, UPPER(p.titulo), p.descricao, " +
                     "p.id_status, s.descricao AS status_desc, " +
                     "p.id_area, a.descricao AS area_nome " + 
                     "FROM Projetos p " +
                     "JOIN Status s ON p.id_status = s.id_status " +
                     "JOIN Area a ON p.id_area = a.id_area " +
                     "ORDER BY p.id_projeto";

        Query query = em.createNativeQuery(sql);
        List<Object[]> resultados = query.getResultList();

        List<Projetos> projetos = new ArrayList<>();

        for (Object[] row : resultados) {
            Projetos projeto = new Projetos();
            projeto.setIdProjeto(((Number) row[0]).longValue());
            projeto.setTitulo((String) row[1]);
            projeto.setDescricao((String) row[2]);

            Status status = new Status();
            status.setId(((Number) row[3]).doubleValue());
            status.setDescricao((String) row[4]);
            projeto.setStatus(status);

            if (row[5] != null) {
                Area area = new Area();
                area.setIdArea(((Number) row[5]).doubleValue());
                area.setDescricao((String) row[6]); 
                projeto.setIdArea(area);
            }

            projetos.add(projeto);
        }

        return projetos;
    }

    public List<Projetos> listarProjetosPorArea(Double idArea) {
        String sql = "SELECT p.id_projeto, UPPER(p.titulo), p.descricao, " +
                     "p.id_status, s.descricao AS status_desc, " +
                     "p.id_area, a.descricao AS area_nome " +
                     "FROM Projetos p " +
                     "JOIN Status s ON p.id_status = s.id_status " +
                     "JOIN Area a ON p.id_area = a.id_area " +
                     "WHERE p.id_area = :idArea " +
                     "ORDER BY p.id_projeto";

        Query query = em.createNativeQuery(sql);
        query.setParameter("idArea", idArea);
        List<Object[]> resultados = query.getResultList();

        List<Projetos> projetos = new ArrayList<>();

        for (Object[] row : resultados) {
            Projetos projeto = new Projetos();
            projeto.setIdProjeto(((Number) row[0]).longValue());
            projeto.setTitulo((String) row[1]);
            projeto.setDescricao((String) row[2]);

            Status status = new Status();
            status.setId(((Number) row[3]).doubleValue());
            status.setDescricao((String) row[4]);
            projeto.setStatus(status);

            if (row[5] != null) {
                Area area = new Area();
                area.setIdArea(((Number) row[5]).doubleValue());
                area.setDescricao((String) row[6]);
                projeto.setIdArea(area);
            }

            projetos.add(projeto);
        }

        return projetos;
    }

    public List<Projetos> listarProjetosPorParticipacao(Double idArea, String idFuncionario) {
        String sql = "SELECT p.id_projeto, UPPER(p.titulo), p.descricao, " +
                     "p.id_status, s.descricao AS status_desc, " +
                     "p.id_area, a.descricao AS area_nome " +
                     "FROM Projetos p " +
                     "JOIN Status s ON p.id_status = s.id_status " +
                     "JOIN Area a ON p.id_area = a.id_area " +
                     "JOIN projeto_funcionario pf ON p.id_projeto = pf.projetos_id_projeto AND p.id_area = :idArea " +
                     "JOIN funcionarios f ON f.id_funcionario = pf.funcionarios_id_funcionario " +
                     "WHERE f.id_cargo = 3 " +
                     "AND pf.funcionarios_id_funcionario = :idFuncionario " +
                     "OR p.criado_por = :idFuncionario " +
                     "ORDER BY p.id_projeto";

        Query query = em.createNativeQuery(sql);
        query.setParameter("idArea", idArea);
        query.setParameter("idFuncionario", idFuncionario);
        List<Object[]> resultados = query.getResultList();

        List<Projetos> projetos = new ArrayList<>();

        for (Object[] row : resultados) {
            Projetos projeto = new Projetos();
            projeto.setIdProjeto(((Number) row[0]).longValue());
            projeto.setTitulo((String) row[1]);
            projeto.setDescricao((String) row[2]);

            Status status = new Status();
            status.setId(((Number) row[3]).doubleValue());
            status.setDescricao((String) row[4]);
            projeto.setStatus(status);

            if (row[5] != null) {
                Area area = new Area();
                area.setIdArea(((Number) row[5]).doubleValue());
                area.setDescricao((String) row[6]);
                projeto.setIdArea(area);
            }

            projetos.add(projeto);
        }

        return projetos;
    }

    public Double buscaArea(String usuarioLogado) {
        try {
            String sql = "SELECT id_area FROM Funcionarios WHERE lower(id_funcionario) = lower(:matricula)";
            Query query = em.createNativeQuery(sql);
            query.setParameter("matricula", usuarioLogado);

            if (query.getSingleResult() == null) {
                return null;
            }
            return Double.parseDouble(query.getSingleResult().toString());
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FuncionariosDTO> buscarFuncionariosEuron(Double idArea) throws SQLException {
        String sql = "SELECT id_funcionario, nome, email, foto FROM FUNCIONARIOS WHERE id_cargo = 3 AND id_area = :idArea";
        Query query = em.createNativeQuery(sql);
        query.setParameter("idArea", idArea);

        List<Object[]> resultados = query.getResultList();
        List<FuncionariosDTO> listaFuncionarios = new ArrayList<>();

        for (Object[] row : resultados) { 
            String id = (String) row[0];
            String nome = (String) row[1];
            String email = (String) row[2];
            Blob imagemBlob = (Blob) row[3];
            
            String foto = "null"; 

            if (imagemBlob != null) {
                byte[] imagem = imagemBlob.getBytes(1, (int) imagemBlob.length());
                foto = (imagem != null && imagem.length > 0) ? Base64.getEncoder().encodeToString(imagem) : "null";
            }


            FuncionariosDTO dto = new FuncionariosDTO(id, nome, email, idArea, foto);
 
            listaFuncionarios.add(dto);
        }

        return listaFuncionarios;
    }

    @Transactional
    public void salvarProjetoFuncionario(Long projetoId, String funcionarioId) {
        String sql = "INSERT INTO projeto_funcionario (projetos_id_projeto, funcionarios_id_funcionario) VALUES (:projetoId, :funcionarioId)";

        try {
            em.getTransaction().begin(); 

            em.createNativeQuery(sql)
                .setParameter("projetoId", projetoId)
                .setParameter("funcionarioId", funcionarioId)
                .executeUpdate();

            em.getTransaction().commit(); 
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
            e.printStackTrace();
        }
    }
    
    public List<FuncionariosDTO> buscarParticipantesPorProjeto(Long projetoId) throws SQLException {
        String sql = "SELECT f.id_funcionario, f.nome, f.email, f.foto " +
                     "FROM funcionarios f " +
                     "JOIN projeto_funcionario pf ON f.id_funcionario = pf.funcionarios_id_funcionario " +
                     "WHERE pf.projetos_id_projeto = :projetoId";

        Query query = em.createNativeQuery(sql);
        query.setParameter("projetoId", projetoId);

        List<Object[]> resultados = query.getResultList();
        List<FuncionariosDTO> participantes = new ArrayList<>();

        for (Object[] row : resultados) {
            String idFuncionario = (String) row[0];
            String nome = (String) row[1];
            String email = (String) row[2];
            Blob imagemBlob = (Blob) row[3];
            
            String foto = "null";  

            if (imagemBlob != null) {
                byte[] imagem = imagemBlob.getBytes(1, (int) imagemBlob.length());
                foto = (imagem != null && imagem.length > 0) ? Base64.getEncoder().encodeToString(imagem) : "null";
            }

            FuncionariosDTO dto = new FuncionariosDTO(idFuncionario, nome, email, null, foto);
            participantes.add(dto);
        }
        return participantes;
    }
    
    @Transactional
    public void atualizarStatusProjeto(Long projetoId, Double statusId) {
        try {
            em.getTransaction().begin();

            String sql = "UPDATE Projetos SET id_status = :statusId WHERE id_projeto = :projetoId";

            Query query = em.createNativeQuery(sql);
            query.setParameter("statusId", statusId);
            query.setParameter("projetoId", projetoId);

            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
    
    @Transactional
    public void atualizarProjeto(Long idProjeto, String novoTitulo, String novaDescricao) {
        try {
            em.getTransaction().begin();

            String sql = "UPDATE Projetos SET titulo = :titulo, descricao = :descricao WHERE id_projeto = :idProjeto";

            em.createNativeQuery(sql)
                .setParameter("titulo", novoTitulo)
                .setParameter("descricao", novaDescricao)
                .setParameter("idProjeto", idProjeto)
                .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
    
    @Transactional
    public void sincronizarParticipantes(Long projetoId, List<String> novosParticipantes) {
        try {
            em.getTransaction().begin();

            String sqlBusca = "SELECT funcionarios_id_funcionario FROM projeto_funcionario WHERE projetos_id_projeto = :projetoId";
            List<String> participantesAtuais = em.createNativeQuery(sqlBusca)
                .setParameter("projetoId", projetoId)
                .getResultList();

            // Participantes a remover
            for (String antigo : participantesAtuais) {
                if (!novosParticipantes.contains(antigo)) {
                    String sqlDelete = "DELETE FROM projeto_funcionario WHERE projetos_id_projeto = :projetoId AND funcionarios_id_funcionario = :funcionarioId";
                    em.createNativeQuery(sqlDelete)
                        .setParameter("projetoId", projetoId)
                        .setParameter("funcionarioId", antigo)
                        .executeUpdate();
                }
            }

            for (String novo : novosParticipantes) {
                if (!participantesAtuais.contains(novo)) {
                    String sqlInsert = "INSERT INTO projeto_funcionario (projetos_id_projeto, funcionarios_id_funcionario) VALUES (:projetoId, :funcionarioId)";
                    em.createNativeQuery(sqlInsert)
                        .setParameter("projetoId", projetoId)
                        .setParameter("funcionarioId", novo)
                        .executeUpdate();
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
    
    @Transactional
    public void deletarProjeto(Long projetoId) {
        try {
            em.getTransaction().begin();

            String sqlDeleteParticipantes = "DELETE FROM projeto_funcionario WHERE projetos_id_projeto = :projetoId";
            em.createNativeQuery(sqlDeleteParticipantes)
                .setParameter("projetoId", projetoId)
                .executeUpdate();

            String sqlDeleteProjeto = "DELETE FROM Projetos WHERE id_projeto = :projetoId";
            em.createNativeQuery(sqlDeleteProjeto)
                .setParameter("projetoId", projetoId)
                .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }



}
