package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public List<Projetos> listarProjetos() {
        String sql = "SELECT p.id_projeto, p.titulo, p.descricao, p.id_status, s.descricao AS status_desc " +
                "FROM Projetos p " +
                "JOIN Status s ON p.id_status = s.id_status " +
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

    public List<FuncionariosDTO> buscarFuncionariosEuron(Double idArea) {
        String sql = "SELECT id_funcionario, nome, email FROM FUNCIONARIOS WHERE id_cargo = 3 AND id_area = :idArea";
        Query query = em.createNativeQuery(sql);
        query.setParameter("idArea", idArea);

        List<Object[]> resultados = query.getResultList();
        List<FuncionariosDTO> listaFuncionarios = new ArrayList<>();

        for (Object[] row : resultados) {
            String id = (String) row[0];
            String nome = (String) row[1];
            String email = (String) row[2];

            FuncionariosDTO dto = new FuncionariosDTO(id, nome, email, idArea);

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
    
    public List<FuncionariosDTO> buscarParticipantesPorProjeto(Long projetoId) {
        String sql = "SELECT f.id_funcionario, f.nome, f.email " +
                     "FROM funcionarios f " +
                     "JOIN projeto_funcionario pf ON f.id_funcionario = pf.funcionarios_id_funcionario " +
                     "WHERE pf.projetos_id_projeto = :projetoId";

        Query query = em.createNativeQuery(sql);
        query.setParameter("projetoId", projetoId);

        List<Object[]> resultados = query.getResultList();
        List<FuncionariosDTO> participantes = new ArrayList<>();

        for (Object[] row : resultados) {
            String id = (String) row[0];
            String nome = (String) row[1];
            String email = (String) row[2];

            FuncionariosDTO dto = new FuncionariosDTO(id, nome, email, null);
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



}
