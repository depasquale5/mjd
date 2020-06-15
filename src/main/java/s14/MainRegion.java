package s14;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import s14.dao.Region;
import s14.dao.RegionDao;

public class MainRegion {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		RegionDao rd = new RegionDao();
		
		//create a new Region
		Region oceania = new Region(5, "Oceania");
		rd.save(oceania);
		
		//get a Region
		Optional<Region> opt = rd.get(5);
		if (opt.isPresent()) {
            System.out.println(opt.get());
        } else {
            logger.error("Unexpected! Can't get region 5");
        }
		
		//rename a Region
		oceania.setRegionName("Australia");
		rd.update(oceania);
		
		opt = rd.get(5);
        if (opt.isPresent()) {
            System.out.println(opt.get());
        } else {
            logger.error("Unexpected! Can't get region 5");
        }
        
        //delete a Region
        rd.delete(5);
        
        opt = rd.get(5);
        if (opt.isPresent()) {
            logger.error("Unexpected! Region 5 still stored: " + opt.get());
        } else {
            System.out.println("Region 5 is no more");
        }     
        
        //get all Regions
        List<Region> regions = rd.getAll();
        System.out.println(regions);        
	}
}
