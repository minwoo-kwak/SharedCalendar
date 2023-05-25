import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem {
	public void writeFile(String filePath,String fileName,String content) {
        try {
            File file = new File(filePath, fileName);
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("저장완료.");
            //return true;
        } catch (IOException e) {
            System.out.println("파일을 저장하는 도중에 오류가 발생했습니다.");
            e.printStackTrace();
            //return false;
        }
	}

}
