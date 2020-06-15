package s14.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionDao implements Dao<Region> {
	private static Logger logger = LoggerFactory.getLogger(CoderDao.class);
	
	private static final String GET_BY_PK = "SELECT region_id, region_name FROM regions WHERE region_id = ?";
	private static final String INSERT = "INSERT INTO regions(region_id, region_name) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE regions SET region_name = ? WHERE region_id = ?";
	private static final String DELETE = "DELETE FROM regions WHERE region_id = ?";
	private static final String GET_ALL = "SELECT region_id, region_name FROM regions";
	
    @Override
    public Optional<Region> get(long id) {
        try (Connection conn = Connector.getConnection();
        		PreparedStatement ps = conn.prepareStatement(GET_BY_PK)) {
        	ps.setLong(1, id);
        	try (ResultSet rs = ps.executeQuery()) {
        		if (rs.next()) {
        			Region r = new Region(rs.getInt(1), rs.getString(2));
        			return Optional.of(r);
        		}
        	}
			
		} catch (SQLException se) {
			logger.error("Can't get region " + id, se);
		}
        return Optional.empty();
    }

    @Override
    public List<Region> getAll() {
    	List<Region> results = new ArrayList<>();
    	
        try (Connection conn = Connector.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(GET_ALL)) {
        	while (rs.next()) {
                Region region = new Region(rs.getLong(1), rs.getString(2));
                results.add(region);
            }
		} catch (SQLException se) {
			logger.error("Can't get all regions", se);
		}
        return results;
    }

    @Override
    public void save(Region t) {
        try (Connection conn = Connector.getConnection();
        		PreparedStatement ps = conn.prepareStatement(INSERT)) {
			ps.setLong(1, t.getRegionId());
			ps.setString(2, t.getRegionName());
			ps.executeUpdate();
		} catch (SQLException se) {
			logger.error("Can't save region " + t.getRegionId(), se);
		}
        
    }

    @Override
    public void update(Region t) {
        try (Connection conn = Connector.getConnection(); //
                PreparedStatement ps = conn.prepareStatement(UPDATE)) {
        	ps.setString(1, t.getRegionName());
        	ps.setLong(2, t.getRegionId());
        	int count = ps.executeUpdate();
        	if (count != 1) {
                logger.warn("Updated " + count + " lines for " + t);
            }
			
		} catch (SQLException se) {
			logger.error("Can't update coder " + t.getRegionId(), se);
		}
        
    }

    @Override
    public void delete(long id) {
        try (Connection conn = Connector.getConnection(); //
                PreparedStatement ps = conn.prepareStatement(DELETE)) {
			ps.setLong(1, id);
			int count = ps.executeUpdate();
			if (count != 1) {
                logger.warn("Deleted " + count + " lines for " + id);
            }
		} catch (SQLException se) {
			logger.error("Can't delete region " + id, se);
		}
        
    }
}
