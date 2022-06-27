package ca.jrvs.apps.practice;

public interface IRegexExc {
    /**
     * return true if filename extension is jpeg or jpg (case insensitive)
     * @param filename
     * @return
     */
    public boolean matchJpeg(String filename) ;

    /**
     * return true if ip is valid for range [0.0.0.0 - 999.999.999.999]
     * @param ip
     * @return
     */
    public boolean matchIp(String ip) ;

    /**
     * return true if line is empty (e.g. empty, white space, tabs, etc.)
     * @param line
     * @return
     */
    public boolean matchEmptyLine(String line) ;
}
