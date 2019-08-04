package tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import tacos.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco design){
        long tacoId = saveTacoInfo(design);
        design.setId(tacoId);
        for (Ingredient ingredient:design.getIngredients()){
            saveIngredientToTaco(ingredient,tacoId);
        }
        return design;
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
       jdbc.update(
               "insert into Taco_Ingredients (taco, ingredient) " +
                       "values(?,?)",
               tacoId,ingredient
       );
    }

    private long saveTacoInfo(Taco design) {
        design.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco (name, createdAt) values(?,?)",
                        Types.VARCHAR,Types.TIMESTAMP)
                .newPreparedStatementCreator(
                        Arrays.asList(
                                design.getName(),
                                new Timestamp(design.getCreatedAt().getTime())
                        )
                );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);
        return keyHolder.getKey().longValue();
    }

}
