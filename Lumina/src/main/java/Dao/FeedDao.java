package Dao;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;

import Dto.ComentarioDTO;
import Dto.PublicacoesDTO;
import Entidades.Comentarios;
import Entidades.Funcionarios;
import Entidades.LucroProjeto;
import Entidades.Projetos;
import Entidades.Publicacao;

public class FeedDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public FeedDao() {
		emf = Persistence.createEntityManagerFactory("Lumina");
		em = emf.createEntityManager();
	}

	public String buscaNome(String usuarioLogado) {

		try {
			String sql = "SELECT UPPER(nome) FROM Funcionarios WHERE lower(id_funcionario) = lower(:matricula)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("matricula", usuarioLogado);

			if (query.getSingleResult() == null) {
				return null;
			}
			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
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

	public List<Projetos> buscaProjetos(String usuarioLogado) {
		Double area = buscaArea(usuarioLogado);
		try {
			String jpql = "SELECT p FROM Projetos p WHERE p.idArea.id = :area AND p.status = 3 AND p.idProjeto NOT IN (SELECT idProjeto FROM LucroProjeto)";
			TypedQuery<Projetos> query = em.createQuery(jpql, Projetos.class);
			query.setParameter("area", area);

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Projetos buscaProjeto(String idProjeto) {
		return em.find(Projetos.class, Long.valueOf(idProjeto));
		
	}

	public void cadastraPublicacao(Publicacao publicacao) {
		try {
			em.getTransaction().begin();
			em.persist(publicacao);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void cadastraLucro(LucroProjeto lucro) {
		try {
			em.getTransaction().begin();
			em.persist(lucro);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	public List<PublicacoesDTO> buscaPublicacao() throws SQLException {
		String sql = "SELECT UPPER(a.descricao) area, "
				+ "       INITCAP(f.nome) funcionario, "
				+ "       p.imagem, "
				+ "       p.descricao, "
				+ "       p.objetivos, "
				+ "       p.resultados, "
				+ "       lp.lucro, "
				+ "       p.id_publicacao "
				+ "FROM Publicacao p "
				+ "JOIN Funcionarios f "
				+ "ON p.id_funcionario = f.id_funcionario "
				+ "JOIN area a "
				+ "ON p.id_area = a.id_area "
				+ "JOIN lucroprojeto lp "
				+ "ON p.id_projeto = lp.id_projeto "
				+ "ORDER BY p.id_publicacao DESC";

		Query query = em.createNativeQuery(sql);
		List<Object[]> results = query.getResultList();

		List<PublicacoesDTO> publicacao = new ArrayList<>();
		for (Object[] row : results) {
			String area = (String) row[0];
			String funcionario = (String) row[1];
			Blob imagemBlob = (Blob) row[2];
			Clob descricaoBlob = (Clob) row[3];
			Clob objetivosBlob = (Clob) row[4];
			Clob resultadosBlob = (Clob) row[5];
			byte[] imagem = imagemBlob.getBytes(1, (int) imagemBlob.length());
			String descricao = descricaoBlob.getSubString(1, (int) descricaoBlob.length());
			String objetivos = objetivosBlob.getSubString(1, (int) objetivosBlob.length());
			String resultados = resultadosBlob.getSubString(1, (int) resultadosBlob.length());
			Double lucro = null;
			if (row[6] != null) {
			    lucro = ((BigDecimal) row[6]).doubleValue();
			}
			BigDecimal idPublicacao = (BigDecimal) row[7];

			String imagemBase64 = (imagem != null && imagem.length > 0) ? Base64.getEncoder().encodeToString(imagem)
					: "";


			PublicacoesDTO dto = new PublicacoesDTO();
			
			dto.setIdPublicacao(idPublicacao.toString());
			dto.setArea(area);
			dto.setFuncionario(funcionario);
			dto.setImagemBase64(imagemBase64); 
			dto.setDescricao(descricao);
			dto.setObjetivos(objetivos);
			dto.setResultados(resultados);
			dto.setLucro(lucro);
			
			publicacao.add(dto);
		}
		return publicacao;
		
	}
	
	public List<ComentarioDTO> buscaComentarios(ServletContext context) throws SQLException {
		String sql = "SELECT c.id_comentario, "
				      +"c.id_publicacao, "
				      +"c.descricao, "
				      +"f.nome, "
				      +"f.foto "
				    +"FROM Comentarios c "
					+"JOIN Funcionarios f "
					+"ON c.id_funcionario = f.id_funcionario";

		Query query = em.createNativeQuery(sql);
		List<Object[]> results = query.getResultList();

		List<ComentarioDTO> comentarios = new ArrayList<>();
		for (Object[] row : results) {
			BigDecimal idComentario = (BigDecimal) row[0];
			BigDecimal idPublicacao = (BigDecimal) row[1];
			Clob descricaoClob = (Clob) row[2];
			String nome = (String) row[3];
			Blob imagemBlob = (Blob) row[4];
			
			String imagemBase64 = "";
			
			try {
		        if (imagemBlob != null && imagemBlob.length() > 0) {
		            byte[] imagem= imagemBlob.getBytes(1, (int) imagemBlob.length());
		            imagemBase64 = Base64.getEncoder().encodeToString(imagem);
		        }  else{
		        	InputStream is = context.getResourceAsStream("/assets/perfil_vazio.webp");
		        
		        if (is != null) {
		            byte[] defaultBytes = is.readAllBytes();
		            imagemBase64 = Base64.getEncoder().encodeToString(defaultBytes);
		        } else {
		            imagemBase64 = ""; 
		        
		        }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        imagemBase64 = ""; 
		    }
			
			String descricao = descricaoClob.getSubString(1, (int) descricaoClob.length());


			ComentarioDTO dto = new ComentarioDTO();
			
			dto.setIdPublicacao(idPublicacao.toString());
			dto.setIdComentario(idComentario.toString());
			
			dto.setDescricao(descricao);
			dto.setNomeFuncionario(nome);
			dto.setImagemBase64(imagemBase64); 
			
			comentarios.add(dto);
		}
		return comentarios;
		
	}
	
	public void cadastraComentario(Comentarios comentario) {
		try {
			em.getTransaction().begin();
			em.persist(comentario);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void cadastraFotoPerfil(Funcionarios funcionario) {
	    try {
	        em.getTransaction().begin();
	        Funcionarios funcionarioDoBanco = em.find(Funcionarios.class, funcionario.getId());
	        if (funcionarioDoBanco != null) {
	            funcionarioDoBanco.setFoto(funcionario.getFoto());
	        }
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}

	public String buscaFotoPerfil(String usuarioId, ServletContext context) {
	    try {
	        Funcionarios funcionario = em.find(Funcionarios.class, usuarioId);
	        if (funcionario != null && funcionario.getFoto() != null) {
	            byte[] imagem = funcionario.getFoto();
	            return Base64.getEncoder().encodeToString(imagem);
	        } else {
	            try (InputStream is = context.getResourceAsStream("/assets/perfil_vazio.webp");) {
	                if (is != null) {
	                    byte[] defaultBytes = is.readAllBytes();
	                    return Base64.getEncoder().encodeToString(defaultBytes);
	                }
	            }
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	    return ""; 
	}



}
