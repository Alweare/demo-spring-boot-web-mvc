package fr.eni.demo.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.demo.bo.Formateur;
@Repository
public class FormateurDAOImpl implements FormateurDAO{
	
	private static final String INSERT = "INSERT INTO FORMATEURS (email,nom,prenom) VALUES (:email,:nom,:prenom)";
	private static final String FIND_BY_EMAIL ="SELECT email, nom,prenom FROM FORMATEURS WHERE email =:email";
	private static final String UPDATE = "UPDATE FORMATEURS SET nom = :nom, prenom=:prenom WHERE email= :email";
	private static final String FINDAL_ALL = "SELECT email, nom,prenom FROM FORMATEURS";
	private static final String COUNT_BY_EMAIL ="SELECT count(*) FROM FORMATEURS WHERE email =:email";
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	public FormateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Formateur formateur) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", formateur.getEmail());
		namedParameters.addValue("nom", formateur.getNom());
		namedParameters.addValue("prenom", formateur.getPrenom());
	
		jdbcTemplate.update(INSERT, namedParameters);
		
	}

	@Override
	public Formateur read(String emailFormateur) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", emailFormateur);
		
		return jdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters,  new BeanPropertyRowMapper<>(Formateur.class));
		
	}

	@Override
	public void update(Formateur formateur) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", formateur.getEmail());
		namedParameters.addValue("nom", formateur.getNom());
		namedParameters.addValue("prenom", formateur.getPrenom());
		jdbcTemplate.update(UPDATE, namedParameters);
		
	}

	@Override
	public List<Formateur> findAll() {
		
		
		return jdbcTemplate.query(FINDAL_ALL, new BeanPropertyRowMapper<>(Formateur.class));
	}

	@Override
	public int countEmail(String email) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("email", email);
		return jdbcTemplate.queryForObject(COUNT_BY_EMAIL, mapSqlParameterSource, Integer.class);
	}

}
