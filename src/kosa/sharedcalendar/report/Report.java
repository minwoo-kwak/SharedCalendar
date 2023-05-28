package kosa.sharedcalendar.report;
import java.io.Serializable;

public class Report implements Serializable {
	private String name;
	private String writer;
	private String period;
	private String dept;
	private String contents; // 보고서 내용

	public Report() {
	}

	public Report(String name, String writer, String period, String dept, String contents) {
		super();
		this.name = name;
		this.writer = writer;
		this.period = period;
		this.dept = dept;
		this.contents = contents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
