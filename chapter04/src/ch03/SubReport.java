package ch03;

// 호출자
public class SubReport {
	
	String content;
	MakeReport onMakeReport;
	
	public SubReport(String content, MakeReport makeReport) {
		this.content = content;
		this.onMakeReport = makeReport;
	}

}
