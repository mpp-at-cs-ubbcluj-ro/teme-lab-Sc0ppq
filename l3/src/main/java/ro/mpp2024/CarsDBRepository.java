package ro.mpp2024;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger(CarsDBRepository.class);

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.info("Finding cars by manifacturer: {}", manufacturerN);
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE manufacturer=?";

        try (Connection con = dbUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, manufacturerN);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Car car = new Car(
                        rs.getString("manufacturer"),
                        rs.getString("model"),
                        rs.getInt("year")
                );
                car.setId(id);
                cars.add(car);
            }
        } catch (SQLException e) {
            logger.error("Error finding cars by manufacturer", e);
        }
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.info("Finding cars between years {} and {}", min, max);
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE year BETWEEN ? AND ?";

        try (Connection con = dbUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1,min);
            stmt.setInt(2,max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Car car = new Car(
                        rs.getString("manufacturer"),
                        rs.getString("model"),
                        rs.getInt("year")
                );
                car.setId(id);
                cars.add(car);
            }
        } catch (SQLException e) {
            logger.error("Error finding cars between years", e);
        }
        return cars;
    }

    @Override
    public void add(Car elem) {
        logger.info("Adding new car: {}", elem);
        String sql = "INSERT INTO cars (manufacturer, model, year) VALUES (?, ?, ?)";

        try (Connection con = dbUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1,elem.getManufacturer());
            stmt.setString(2,elem.getModel());
            stmt.setInt(3,elem.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding car", e);
        }
    }

    @Override
    public void update(Integer integer, Car elem) {
        logger.info("Updating car with id: {}", integer);
        String sql = "UPDATE cars SET manufacturer = ?, model = ?, year = ? WHERE id = ?";

        try (Connection con = dbUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1,elem.getManufacturer());
            stmt.setString(2,elem.getModel());
            stmt.setInt(3,elem.getYear());
            stmt.setInt(4,integer);

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating car", e);
        }
    }

    @Override
    public Iterable<Car> findAll() {
        logger.info("Finding all cars");
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection con = dbUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Car car = new Car(
                        rs.getString("manufacturer"),
                        rs.getString("model"),
                        rs.getInt("year")
                );
                car.setId(id);
                cars.add(car);
            }
        } catch (SQLException e) {
            logger.error("Error getting all cars", e);
        }
        return cars;
    }
}

