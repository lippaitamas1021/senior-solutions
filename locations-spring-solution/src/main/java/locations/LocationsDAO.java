package locations;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
@AllArgsConstructor
public class LocationsDAO {

    private JdbcTemplate jdbcTemplate;

    public List<Location> findAll() {
        return jdbcTemplate.query("SELECT id, loc_name FROM locations",
                LocationsDAO::mapRow);
    }

    public Location findById(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, loc_name FROM locations WHERE id = ?",
                LocationsDAO::mapRow,
                id);
    }

    public void createLocation(Location location) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO locations(loc_name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, location.getName());
                return ps;
        }, keyHolder);
        location.setId(keyHolder.getKey().longValue());
    }

    public void updateLocation(Location location) {
        jdbcTemplate.update("UPDATE locations SET loc_name = ? WHERE id = ?",
                location.getName(), location.getId());
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM locations WHERE id = ?", id);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM locations");
    }

    private static Location mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("loc_name");
        Location location = new Location(id, name);
        return location;
    }
}
