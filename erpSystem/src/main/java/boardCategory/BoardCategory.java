package boardCategory;

public class BoardCategory {

	private int id;
	private String name;
	
	public BoardCategory(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BoardCategory(BoardCategoryDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
