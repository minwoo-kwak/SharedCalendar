import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileSystem {
	public boolean writeFile(String filePath,String fileName,StringBuilder sb) {
        try {
            File file = new File(filePath, fileName);
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            String content=sb.toString();
            bw.write(content);
            bw.newLine();
            bw.close();
            fw.close();
            //System.out.println("저장완료.");
            return true;
        } catch (IOException e) {
//            System.out.println("파일을 저장하는 도중에 오류가 발생했습니다.");
            e.printStackTrace();
            return false;
            
        }
	}
	public static void save(String filePath, String fileName, Object obj) {
      ObjectOutputStream oos = null;
        try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath+"\\"+fileName));
			oos.writeObject(obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				oos.flush();
				oos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}  
	}
	
    public static Object load(String filePath, String fileName) {
        ObjectInputStream ois = null;
        Object obj = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath + "\\" + fileName));
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return obj;
    }

}
