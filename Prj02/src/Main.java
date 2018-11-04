import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Main {

    public static void main(String[] args) {
        // будем искать в папке music2
        String dir = "C:\\Users\\Sony\\Downloads\\music2";
        // в этой папке будем искать файлы с расширением .mp3
        String ext = ".mp3";
        // вызываем метод поиска файлов с расширением .mp3 в папке music2
        List<String> fileLocations = findFiles(dir, ext);
        String body = "";

        try {
            for(String fileLocation : fileLocations) {
                InputStream input = new FileInputStream(new File(fileLocation));
                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(input, handler, metadata, parseCtx);
                input.close();

                System.out.println("----------------------------------------------");
                System.out.println("Artists: " + metadata.get("xmpDM:artist"));
                System.out.println("Album : " + metadata.get("xmpDM:album"));
                System.out.println("Title: " + metadata.get("title"));
                System.out.println("Duration : " + metadata.get("xmpDM:duration"));


                File htmlTemplateFile = new File(".idea\\template.html");
                String htmlString = FileUtils.readFileToString(htmlTemplateFile);
                String title = "Music";
                body += "\nArtists: " + metadata.get("xmpDM:artist") + "\n" +
                        "\tAlbum : " + metadata.get("xmpDM:album") + "\n" +
                        "\t\tTitle: " + metadata.get("title") +
                        " Duration : " + metadata.get("xmpDM:duration") + "\n";
                htmlString = htmlString.replace("$title", title);
                htmlString = htmlString.replace("$body", body);
                File newHtmlFile = new File(".idea\\new.html");
                FileUtils.writeStringToFile(newHtmlFile, htmlString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
    }

    // Реализация интерфейса FileNameFilter
    public static class MyFileNameFilter implements FilenameFilter{
        private String ext;

        MyFileNameFilter(String ext) {
            this.ext = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
    }

    // метод поиска
    private static List<String> findFiles(String dir, String ext) {
        File file = new File(dir);
        List<String> filesInDir = new ArrayList<>();

        if (!file.exists()) {
            System.out.println(dir + " Такая папка не существует!");
        }

        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));

        if (listFiles.length == 0) {
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        } else {
            for (File f : listFiles) {
                String locationOfFile = dir + File.separator + f.getName();
                System.out.println(locationOfFile);
                filesInDir.add(locationOfFile);
            }
        }
        return filesInDir;
    }
}

