package ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExc implements IRegexExc{
    @Override
    public boolean matchJpeg(String filename) {
        Pattern pattern = Pattern.compile(".\\.(jpeg|jpg$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(filename);
        return matcher.find();
    }

    @Override
    public boolean matchIp(String ip) {
        Pattern pattern = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    @Override
    public boolean matchEmptyLine(String line) {
        Pattern pattern = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}
