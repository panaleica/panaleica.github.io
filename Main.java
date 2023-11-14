import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Main {
    final static File ROOT = new File("music");
    final static String URL = "";
    //final static String URL = "https://panaleica.github.io/";
    final static String FILENAME = "music.js";
    // final static String FILENAME = "music_external.js";

    public static void main(String[] args) throws IOException {
        StringBuilder musicList = getMusicList(ROOT);
        FileWriter fileWriter = new FileWriter(FILENAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("let json = [");
        printWriter.print(musicList.substring(0, musicList.length() - 2));
        printWriter.print("]");
        printWriter.flush();
    }

    static StringBuilder getMusicList(File file) {
        StringBuilder str = new StringBuilder();
        if (file.isDirectory() && Objects.requireNonNull(file.list()).length > 0) {
            File[] files = file.listFiles();
            assert files != null;
            if (files[0].isFile()) {
                return printList(file);
            } else {
                for (File file1 : files) {
                    str.append(getMusicList(file1));
                }
            }
        }
        if (file.isDirectory() && Objects.requireNonNull(file.list()).length == 0) {
            return str;
        }
        return str;
    }

    static StringBuilder printList(File file) {
        StringBuilder str = new StringBuilder();
        File[] files = file.listFiles();
        String coverName = "";
        assert files != null;
        for (File file1 : files) {
            if (file1.getName().endsWith(".jpg") || file1.getName().endsWith(".png")) {
                coverName = file1.getName();
            }
        }
        for (File file1 : files)
            if (!file1.getName().endsWith(".jpg") && !file1.getName().endsWith(".png")) {
                str.append(String.format("""
                        {
                            "name": "%s",
                            "url": "%s",
                            "image": "%s"
                        },
                        """, file1.getName().substring(0, file1.getName().lastIndexOf('.')).replaceAll("F_I_R_", "F.I.R."), URL + file.getPath().replace('\\', '/') + "/" + file1.getName().replaceAll("#", "%23"), URL + file.getPath().replace('\\', '/') + "/" + coverName));
            }
        return str;
    }
}