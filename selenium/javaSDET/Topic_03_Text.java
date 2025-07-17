package javaSDET;



public class Topic_03_Text {

	public static void main(String[] args) {
		//System.out.println("Khong co khoang trang o dau va cuoi");
		//System.out.println("  co khoang trang o dau");
		//System.out.println("co khoang trang o cuoi  ");
		//System.out.println("  co khoang trang o dau va cuoi   ");
		//System.out.println("\nco xuong dong o dau");
		//System.out.println("co xuong dong o cuoi\n");
		//System.out.println("\nco xuong dong o dau");
		//System.out.println("\tco tab o dau");
		//System.out.println("co tab o cuoi\t");
		
		
		//String FirstName ="Michael";
		//String LastName ="Jackson";
		
		//Nối chuỗi
		//System.out.println(FirstName + " " + LastName );
		
		//Nối chuỗi
		//System.out.println(FirstName.concat(" " + LastName));

		// Lấy ra đường dẫn tương đối của Project
		String projectPath = System.getProperty("user.dir");

		String fightingName = "fighting.jpg";
		String strongName = "strong.jpg";
		String treeName = "tree.jpg";

		String fightingFilePath = projectPath + "\\UploadFiles" + fightingName;
		String strongFilePath = projectPath +"\\UploadFiles" + strongName;
		String treeFilePath = projectPath +"\\UploadFiles" + treeName;

		System.out.println(fightingFilePath);
		System.out.println(strongFilePath);
		System.out.println(treeFilePath);
		
	}

}