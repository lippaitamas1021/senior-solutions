package locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute(
                "create table locations (id bigint auto_increment, loc_name varchar(255), primary key (id))"
        );
        jdbcTemplate.execute(
                "insert into locations(loc_name) values ('Vásárosnamény')"
        );
        jdbcTemplate.execute(
                "insert into locations(loc_name) values ('Nagyvarsány')"
        );
    }
}
