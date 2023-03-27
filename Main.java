import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    final static String URL = "https://panaleica.github.io/";

    public static void main(String[] args) throws IOException {
        File file = new File("music");
        File[] files = file.listFiles();
        String str = "";
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            str += String.format("""
                    {
                        "name": "%s",
                        "url": "%s",
                        "image": "%s"
                    },
                    """, name.substring(0, name.lastIndexOf('.')), URL + "music/" + name, URL + "cover/sunyanzi13.jpg");
        }
        FileWriter fw = new FileWriter("list.json");
        PrintWriter pw = new PrintWriter(fw);
        pw.print('[');
        pw.print(str.substring(0, str.length() - 2));
        pw.print(']');
        pw.flush();
    }
}
