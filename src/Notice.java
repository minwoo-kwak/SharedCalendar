import java.util.Date;

public class Notice {
	private String content;
	private String writer;
	private Date date;

	// Constructors
	public Notice() {
	}

	public Notice(String content, String writer, Date date) {
		super();
		this.content = content;
		this.writer = writer;
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
