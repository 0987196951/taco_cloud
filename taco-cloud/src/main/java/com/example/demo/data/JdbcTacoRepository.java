package com.example.demo.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.Ingredient;
import com.example.demo.Taco;
@Repository
public class JdbcTacoRepository{
	private JdbcTemplate jdbc;
	@Autowired
	public JdbcTacoRepository (JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	public Taco save(Taco taco) {
		long tacoID = saveTacoInfo(taco);
		taco.setId(tacoID);
		for(Ingredient ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoID);
		}
		return taco;
	}
	private long saveTacoInfo(Taco taco) {
		taco.setCreateAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values (?, ?)", 
				Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc = 
						pscf.newPreparedStatementCreator(
							Arrays.asList(taco.getName(), new Timestamp(taco.getCreateAt().getTime())));
		
	GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	jdbc.update(psc, keyHolder);
	return keyHolder.getKey().longValue();
	}
	private void saveIngredientToTaco(Ingredient ingredient, long tacoID) {
		jdbc.update("insert into Taco_Ingredients (Taco, ingredient) values(?, ?)",
				tacoID, ingredient.getId());
	}
}
