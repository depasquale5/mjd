package s14.dao;

//Java Bean
public class Region {
    private long regionId;
    private String regionName;
	
    public Region() {
	}    

	public Region(long regionId, String regionName) {
		this.regionId = regionId;
		this.regionName = regionName;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return "[regionId=" + regionId + ", regionName=" + regionName + "]";
	}    
}
