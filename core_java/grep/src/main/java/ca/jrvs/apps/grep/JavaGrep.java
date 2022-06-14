package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class JavaGrep implements IJavaGrep{
    final Logger logger = LoggerFactory.getLogger (JavaGrep.class);
    static String[] arr = {
            ".*Romeo.*Juliet.*",
            "./core_java/grep/src/main/resources/data" ,
            "./core_java/grep/src/main/resources/out/out.txt"
    };

    private String regex;
    private String rootPath;
    private String outFile;

    public String getRootPath() {
        return this.rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRegex() {
        return this.regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getOutFile() {
        return this.outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public void process() throws IOException {
        ArrayList<String> matchedLines = new ArrayList<String>();
        this.listFiles(this.getRootPath()).forEach((file)-> {
            Stream<String> lines = this.readLines(file);
            lines.forEach(line -> {
                if (this.containsPattern(line)) {
                    matchedLines.add(String.valueOf(line));
                }
            });
        });
        this.writeToFile(matchedLines.stream());
    }

    public Stream<File> listFiles(String rootDir) {
        try {
            return Files.walk(Paths.get(rootDir))
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> readLines(File inputFile) {
        ArrayList<String> out = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            String line = reader.readLine();
            while (line != null) {
                out.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            logger.error("Error: IOException", e);
        }
        return out.stream();
    }

    public boolean containsPattern(String line) {
        Pattern pattern = Pattern.compile(this.getRegex(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    public void writeToFile(Stream<String> lines) throws IOException {
        FileWriter writer = new FileWriter(getOutFile());
        lines.forEach(line-> {
            try {
                writer.write(line+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("no args");
        }
        for (String s : args) {
            System.out.println("args: "+s);
        }
        BasicConfigurator.configure();
        JavaGrep javaGrep = new JavaGrep();

        javaGrep.setRegex(args[0]);
        javaGrep.setRootPath(args[1]);
        javaGrep.setOutFile(args[2]);

        try {
            javaGrep.process();
        } catch (Exception ex) {
            javaGrep.logger.error("Error: Unable to process", ex);
        }
    }
}