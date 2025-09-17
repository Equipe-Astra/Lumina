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
import Entidades.FuncionarioPublicacaoReacao;
import Entidades.FuncionarioPublicacaoReacaoId;
import Entidades.Funcionarios;
import Entidades.LucroProjeto;
import Entidades.Projetos;
import Entidades.Publicacao;
import Entidades.PublicacaoReacao;
import Entidades.PublicacaoReacaoId;
import Entidades.Reacoes;

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
	
	public String buscaDescricaoArea(String usuarioLogado) {
		try {
			String sql = "SELECT INITCAP(descricao) FROM Funcionarios f JOIN Area a ON f.id_area = a.id_area WHERE lower(f.id_funcionario) = lower(:matricula)";
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

	public List<PublicacoesDTO> buscaPublicacao(String idUsuario) throws SQLException {
		String sql = "SELECT UPPER(a.descricao) area,  " + "INITCAP(f.nome) funcionario, " + "p.imagem,  "
				+ "p.descricao,  " + "p.objetivos,  " + "p.resultados,  " + "lp.lucro,  " + "p.id_publicacao, "
				+ "nvl((SELECT quantidade " + "FROM  Publicacao_Reacao pr " + "WHERE id_reacao = 1 "
				+ "AND pr.id_publicacao = p.id_publicacao),0) AS curtida, " + "nvl((SELECT quantidade "
				+ "FROM  Publicacao_Reacao pr " + "WHERE id_reacao = 2 "
				+ "AND pr.id_publicacao = p.id_publicacao),0) AS alegria, " + "nvl((SELECT quantidade "
				+ "FROM  Publicacao_Reacao pr " + "WHERE id_reacao = 3 "
				+ "AND pr.id_publicacao = p.id_publicacao),0) AS genial, " + "nvl((SELECT quantidade "
				+ "FROM Publicacao_Reacao pr " + "WHERE id_reacao = 4 "
				+ "AND pr.id_publicacao = p.id_publicacao),0) AS apaixonado,      "
				+ "nvl((SELECT COUNT(id_comentario) " + "FROM comentarios c "
				+ "WHERE c.id_publicacao = p.id_publicacao " + "GROUP BY c.id_publicacao),0) AS comentarios, "
				+ "nvl((SELECT fpr.id_reacao " + "FROM Funcionario_Publicacao_Reacao fpr "
				+ "WHERE fpr.id_funcionario = :idFuncionario "
				+ "AND fpr.id_publicacao = p.id_publicacao), 0) AS reacaoUsuario " + "FROM Publicacao p  "
				+ "JOIN Funcionarios f " + "ON p.id_funcionario = f.id_funcionario  " + "JOIN area a  "
				+ "ON p.id_area = a.id_area  " + "JOIN lucroprojeto lp  " + "ON p.id_projeto = lp.id_projeto  "
				+ "ORDER BY p.id_publicacao DESC";

		Query query = em.createNativeQuery(sql);
		query.setParameter("idFuncionario", idUsuario);
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
			BigDecimal curtida = (BigDecimal) row[8];
			BigDecimal alegria = (BigDecimal) row[9];
			BigDecimal genial = (BigDecimal) row[10];
			BigDecimal apaixonado = (BigDecimal) row[11];
			BigDecimal comentarios = (BigDecimal) row[12];
			BigDecimal reacaoUsuario = (BigDecimal) row[13];

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
			dto.setCurtida(Integer.parseInt(curtida.toString()));
			dto.setAlegria(Integer.parseInt(alegria.toString()));
			dto.setGenial(Integer.parseInt(genial.toString()));
			dto.setApaixonado(Integer.parseInt(apaixonado.toString()));
			dto.setComentarios(Integer.parseInt(comentarios.toString()));
			dto.setReacaoUsuario(Integer.parseInt(reacaoUsuario.toString()));
			publicacao.add(dto);
		}
		return publicacao;

	}

	public List<ComentarioDTO> buscaComentarios(ServletContext context) throws SQLException {
		String sql = "SELECT c.id_comentario, " + "c.id_publicacao, " + "c.descricao, " + "f.nome, " + "f.foto "
				+ "FROM Comentarios c " + "JOIN Funcionarios f "
				+ "ON c.id_funcionario = f.id_funcionario ORDER BY c.id_comentario ASC";

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
					byte[] imagem = imagemBlob.getBytes(1, (int) imagemBlob.length());
					imagemBase64 = Base64.getEncoder().encodeToString(imagem);
				} else {
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
			em.flush();
			em.clear();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public String buscaFotoPerfil(String usuarioId, ServletContext context) {
		try {
			TypedQuery<byte[]> query = em.createQuery("SELECT f.foto FROM Funcionarios f WHERE f.id = :id",
					byte[].class);
			query.setParameter("id", usuarioId);

			byte[] imagem = null;

			try {
				imagem = query.getSingleResult();
			} catch (NoResultException e) {
				imagem = null;
			}

			if (imagem != null) {
				return Base64.getEncoder().encodeToString(imagem);
			} else {
				try (InputStream is = context.getResourceAsStream("/assets/perfil_vazio.webp")) {
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

	public int buscaPublicacao(String idPublicacao, Double reacaoId) {
		try {
			String sql = "SELECT 1 FROM Publicacao_Reacao WHERE id_publicacao = :idPublicacao AND id_reacao = :reacaoId";
			Query query = em.createNativeQuery(sql);
			query.setParameter("idPublicacao", idPublicacao);
			query.setParameter("reacaoId", reacaoId);

			if (query.getSingleResult() == null) {
				return 0;
			}
			return Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return 0;
		}
	}

	public int buscaReacaoUsuario(String idPublicacao, Double reacaoId) {
		try {
			String sql = "SELECT 1 FROM Funcionario_Publicacao_Reacao WHERE id_publicacao = :idPublicacao AND id_reacao = :reacaoId";
			Query query = em.createNativeQuery(sql);
			query.setParameter("idPublicacao", idPublicacao);
			query.setParameter("reacaoId", reacaoId);

			if (query.getSingleResult() == null) {
				return 0;
			}
			return Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return 0;
		}
	}

	public void cadastraReacao(String idPublicacao, String tipo, String acao, String usuarioId) {
		Double reacaoId = null;
		switch (tipo) {
		case "curtida":
			reacaoId = 1.0;
			break;
		case "alegria":
			reacaoId = 2.0;
			break;
		case "genial":
			reacaoId = 3.0;
			break;
		case "apaixonado":
			reacaoId = 4.0;
			break;
		}
		int resultado = buscaPublicacao(idPublicacao, reacaoId);
		try {
	        em.getTransaction().begin();

	        if (resultado == 1) {
	            if (acao.equalsIgnoreCase("adicionar")) {
	                Publicacao publicacao = new Publicacao();
	                publicacao.setIdPublicacao(Long.parseLong(idPublicacao));

	                Reacoes reacao = new Reacoes();
	                reacao.setIdReacao(reacaoId);

	                Funcionarios funcionario = new Funcionarios();
	                funcionario.setId(usuarioId);

	                FuncionarioPublicacaoReacao funcPub = new FuncionarioPublicacaoReacao();
	                funcPub.setId(new FuncionarioPublicacaoReacaoId());
	                funcPub.setFuncionario(funcionario);
	                funcPub.setPublicacao(publicacao);
	                funcPub.setReacao(reacao);

	                em.persist(funcPub);

	                String sql = "UPDATE Publicacao_Reacao " +
	                             "SET quantidade = quantidade + 1 " +
	                             "WHERE id_publicacao = :idPublicacao " +
	                             "AND id_reacao = :idReacao";
	                Query query = em.createNativeQuery(sql);
	                query.setParameter("idPublicacao", Long.parseLong(idPublicacao));
	                query.setParameter("idReacao", reacaoId);
	                query.executeUpdate();

	            } else if (acao.equalsIgnoreCase("remover")) {
	                String delete = "DELETE FROM Funcionario_Publicacao_Reacao " +
	                                "WHERE id_publicacao = :idPublicacao " +
	                                "AND id_reacao = :idReacao " +
	                                "AND id_funcionario = :idFunc";
	                Query queryDelete = em.createNativeQuery(delete);
	                queryDelete.setParameter("idPublicacao", Long.parseLong(idPublicacao));
	                queryDelete.setParameter("idReacao", reacaoId);
	                queryDelete.setParameter("idFunc", usuarioId);
	                queryDelete.executeUpdate();

	                String sql = "UPDATE Publicacao_Reacao " +
	                             "SET quantidade = quantidade - 1 " +
	                             "WHERE id_publicacao = :idPublicacao " +
	                             "AND id_reacao = :idReacao";
	                Query query = em.createNativeQuery(sql);
	                query.setParameter("idPublicacao", Long.parseLong(idPublicacao));
	                query.setParameter("idReacao", reacaoId);
	                query.executeUpdate();
	            }
	        } else if (resultado == 0) {
	            Publicacao publicacao = new Publicacao();
	            publicacao.setIdPublicacao(Long.parseLong(idPublicacao));

	            Reacoes reacao = new Reacoes();
	            reacao.setIdReacao(reacaoId);

	            Funcionarios funcionario = new Funcionarios();
	            funcionario.setId(usuarioId);

	            PublicacaoReacao pubReacao = new PublicacaoReacao();
	            PublicacaoReacaoId idPubReac = new PublicacaoReacaoId();
	            idPubReac.setPublicacao(publicacao);
	            idPubReac.setReacao(reacao);
	            pubReacao.setId(idPubReac);
	            pubReacao.setQuantidade(1.0);

	            FuncionarioPublicacaoReacao funcPub = new FuncionarioPublicacaoReacao();
	            funcPub.setId(new FuncionarioPublicacaoReacaoId());
	            funcPub.setFuncionario(funcionario);
	            funcPub.setPublicacao(publicacao);
	            funcPub.setReacao(reacao);

	            em.persist(pubReacao);
	            em.persist(funcPub);
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}
}
