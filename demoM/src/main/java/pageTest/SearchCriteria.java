package pageTest;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria {
	
	private String searchType = "all"; //컬럼선택
	private String keyword;
	private String[] check;

} //class
