package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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
        ArrayList<File> out = new ArrayList<File>();
        File dir = new File(rootDir);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //recursion to get all sub file (if any)
                out.addAll((Collection<? extends File>) listFiles(file.getAbsolutePath()));
            } else {
                //add file to list
                out.add(file);
            }
        }
        return out.stream();
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
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
        try {
            Process process = Runtime.getRuntime().exec("ls");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "USAGE: JavaGrep regex rootpath outFile e.g. " +
                    "java -cp target/grep-1.0-SNAPSHOT-UBER.jar " +
                    "ca.jrvs.apps.grep.JavaGrep "+
                    ".*Romeo.*Juliet.* "+
                    "./src/main/resources/data "+
                    "./src/main/resources/out/out.txt"
            );
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