package fr.eni.demo.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.demo.bo.Cours;
@Repository
public class CoursDAOImpl implements CoursDAO{

	private static final String FIND_BY_ID ="SELECT id,titre,duree FROM COURS_ENI WHERE id = :id";
	private static final String FINDAL_ALL = "SELECT email, nom,prenom FROM FORMATEURS";
	private static final String INSERT = "INSERT INTO COURS_FORMATEUR (email_formateur,id_cours) VALUES (:email,:idCours)";
	private static final String FIND_BY_EMAIL = "SELECT id,titre,duree FROM COURS_ENI ce INNER JOIN COURS_FORMATEUR cf ON ce.id = cf.id_cours  WHERE email_formateur = :emailFormateur";
	private NamedParameterJdbcTemplate jdbcTemplate;

	public CoursDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Cours read(long id) {
		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("id", id);
		

		return jdbcTemplate.queryForObject(FIND_BY_ID, mapParameterSource, new BeanPropertyRowMapper<>(Cours.class)) ;
	}

	@Override
	public List<Cours> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(FINDAL_ALL, new BeanPropertyRowMapper<>(Cours.class));
	}

	@Override
	public void insertCoursFormateur(long idCours, String emailFormateur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("email", emailFormateur);
		mapSqlParameterSource.addValue("idCours", idCours);
		jdbcTemplate.update(INSERT, mapSqlParameterSource);
	}

	@Override
	public List<Cours> findByFormateur(String emailFormateur) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("emailFormateur", emailFormateur);
		
		
		// TODO Auto-generated method stub
		return jdbcTemplate.query(FIND_BY_EMAIL, mapSqlParameterSource,  new BeanPropertyRowMapper<>(Cours.class));
	}



}
