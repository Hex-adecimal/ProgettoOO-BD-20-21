package DAO;

public interface LectureDAO {
	public String getTitle(int codLecture);
	public String getLink(int codLecture);
	
	public Void setTitle(int codLecture, String title);
	public Void setLink(int codLecture, String link);
}
