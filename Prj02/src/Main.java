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
        final String MESSAGE_TO_USER = "Input a directory (some directories) as a parameter (parameters) of this program. " +
                "The input of the parameter (parameters) was incorrect!";
        final int MIN_NUM_OF_PARAMS = 1;//the user may input one directory or more
        // directories for searching
        List<String> directories = new ArrayList<>();
        final String extension = ".mp3";//this program searches files with .mp3 extension

        if(args.length < MIN_NUM_OF_PARAMS) {
            System.out.println(MESSAGE_TO_USER);
        } else {
            for(String arg : args) {
                directories.add(String.valueOf(arg));
            }
        }

        String body = "";//the body for the output HTML file

        for(String directory : directories) {
            // this program calls the method for searching files with .mp3 extension in the necessary directories
            List<String> fileLocations = findFiles(directory, extension);

            try {
                for(String fileLocation : fileLocations) {
                    InputStream input = new FileInputStream(new File(fileLocation));
                    ContentHandler handler = new DefaultHandler();
                    Metadata metadata = new Metadata();
                    Parser parser = new Mp3Parser();
                    ParseContext parseCtx = new ParseContext();
                    parser.parse(input, handler, metadata, parseCtx);
                    input.close();

                    File htmlTemplateFile = new File(".idea\\template.html");
                    String htmlString = FileUtils.readFileToString(htmlTemplateFile);
                    String title = "Music";
                    body += "\n<p>Artists: " + metadata.get("xmpDM:artist") + "</p>\n" +
                            "\t<p>Album : " + metadata.get("xmpDM:album") + "</p>\n" +
                            "\t\t<p>Title: " + metadata.get("title") +
                            " Duration : " + metadata.get("xmpDM:duration") + " " +
                            "<a title=\"Link to the file.\" href=\"" + fileLocation + "\">Link to the file.</a>" +
                            "</p>\n\n";
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
    }

    // the realisation of the FileNameFilter interface
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

    // the method of searching
    private static List<String> findFiles(String dir, String ext) {
        File file = new File(dir);
        List<String> filesInDir = new ArrayList<>();

        if (!file.exists()) {
            System.out.println(dir + " Such folder doesn't exist!");
        }

        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));

        if (listFiles.length == 0) {
            System.out.println(dir + " doesn't contain files with " + ext + " extension!");
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

