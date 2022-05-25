package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep {
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
        for (File f : this.listFiles(this.getRootPath()) ) {
            for (String line: this.readLines(f)) {
                if (this.containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }
        this.writeToFile(matchedLines);
    }

    public List<File> listFiles(String rootDir) {
        ArrayList<File> out = new ArrayList<File>();
        File dir = new File(rootDir);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //recursion to get all sub file (if any)
                out.addAll(listFiles(file.getAbsolutePath()));
            } else {
                //add file to list
                out.add(file);
            }
        }
        return out;
    }

    public List<String> readLines(File inputFile) {
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
        return out;
    }

    public boolean containsPattern(String line) {
        Pattern pattern = Pattern.compile(this.getRegex(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    public void writeToFile(List<String> lines) throws IOException {
        FileWriter writer = new FileWriter(getOutFile());
        for (String s: lines) {
            writer.write(s+"\n");
        }
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            //throw new IllegalArgumentException("USAGE: JavaGrep regex rootpath outFile");
        }

        BasicConfigurator.configure();
        JavaGrepImp javaGrepImp = new JavaGrepImp();

        javaGrepImp.setRegex(arr[0]);
        javaGrepImp.setRootPath(arr[1]);
        javaGrepImp.setOutFile(arr[2]);
        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }
    }
}