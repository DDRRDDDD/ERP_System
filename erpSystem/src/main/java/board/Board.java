package board;

public class Board {

	// read only

	private int id;
	private String title;
	private String main;
	private String modifiedDate;
	private String registeredDate;
	private int writer;
	private int categoryId;

	public Board(int id, String title, String main, String modi, String regi, int writer, int category) {
		this.id = id;
		this.title = title;
		this.main = main;
		this.modifiedDate = modi;
		this.registeredDate = regi;
		this.writer = writer;
		this.categoryId = category;
	}
	
	public Board(BoardDTO dto) {
		this.id = dto.getId();
		this.title = dto.getTitle();
		this.main = dto.getMain();
		this.modifiedDate = dto.getModifiedDate();
		this.registeredDate = dto.getReigisteredDate();
		this.writer = dto.getWriter();
		this.categoryId = dto.getCategoryId();
	}

	public int getWriter() {
		return writer;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMain() {
		return main;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public int getCategoryId() {
		return categoryId;
	}
	
}
