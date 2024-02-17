package board;

public class BoardDTO {

	private int id;
	private String title;
	private String main;
	private String modifiedDate;
	private String reigisteredDate;
	private int writer;
	private int categoryId;

	public BoardDTO(int id, String title, String main, String modi, String regi, int writer, int category) {
		this.id = id;
		this.title = title;
		this.main = main;
		this.modifiedDate = modi;
		this.reigisteredDate = regi;
		this.writer = writer;
		this.categoryId = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getReigisteredDate() {
		return reigisteredDate;
	}

	public void setReigisteredDate(String reigisteredDate) {
		this.reigisteredDate = reigisteredDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
