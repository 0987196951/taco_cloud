package com.example.demo.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Ingredient;
	

@Repository
public class JdbcIngredientRepository{
	private JdbcTemplate jdbc;
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	public Ingredient findOne(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id = ?",this::mapRowToIngredient, id); 
	}

	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id , name, type) values ?, ?, ?",
				ingredient.getId(), 
				ingredient.getName(),
				ingredient.getType().toString());
		return ingredient;
	}
	private Ingredient mapRowToIngredient(ResultSet rs, int i) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}
}