package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.helper.Student;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CompareMapsTest{
    static Map<Integer, Student> m1 = new HashMap<Integer,Student>();
    static Map<Integer,Student> m2 = new HashMap<Integer,Student>();
    static Map<Integer,Student> m3 = new HashMap<Integer,Student>();
    static Map<Integer,Student> m4 = new HashMap<Integer,Student>();
    static Map<Integer,Student> mMalformed = new HashMap<Integer,Student>();
    static Map<Integer,Student> mEmpty = new HashMap<Integer,Student>();
    static Map<Integer,Student> mNull;

    @BeforeClass
    public static void init() {
        Student s1 = new Student();
        s1.setId(1);s1.setName("bernard");

        Student s2 = new Student();
        s2.setId(2);s2.setName("bernard");

        Student s3 = new Student();
        s3.setId(3);s3.setName("vishay");

        Student s4 = new Student();
        s4.setId(4);s4.setName("amos");

        Student s5 = new Student();
        s5.setId(1);s5.setName("bernard");

        Student s6 = new Student();
        s6.setId(6);s6.setName("trevor");

        m1.put(s1.hashCode(),s1);
        m1.put(s2.hashCode(),s2);
        m1.put(s3.hashCode(),s3);
        m1.put(s4.hashCode(),s4);
        m1.put(s5.hashCode(),s5);

        m2.put(s1.hashCode(),s1);
        m2.put(s2.hashCode(),s2);
        m2.put(s3.hashCode(),s3);
        m2.put(s4.hashCode(),s4);
        m2.put(s5.hashCode(),s5);

        m3.put(s1.hashCode(),s1);
        m3.put(s2.hashCode(),s2);
        m3.put(s3.hashCode(),s3);
        m3.put(s4.hashCode(),s4);

        m4.put(s1.hashCode(),s1);
        m4.put(s2.hashCode(),s2);
        m4.put(s3.hashCode(),s3);
        m4.put(s4.hashCode(),s4);
        m4.put(s6.hashCode(),s6);

        mMalformed.put(s1.hashCode(),s1);
        mMalformed.put(s2.hashCode(),s2);
        mMalformed.put(s3.hashCode(),s3);
    }


    @Test
    public void compareMaps_Equal() {
        assertTrue(m1.equals(m2));
        assertTrue(CompareMaps.compareMaps(m1,m2));
    }

    @Test
    public void compareMaps_EqualMapping() {
        assertTrue(m1.equals(m3));
        assertTrue(CompareMaps.compareMaps(m1,m3));
        assertTrue(m2.equals(m3));
        assertTrue(CompareMaps.compareMaps(m2,m3));
    }

    @Test
    public void compareMaps_nonEqual() {
        assertFalse(m1.equals(m4));
        assertFalse(CompareMaps.compareMaps(m1,m4));
        assertFalse(m2.equals(m4));
        assertFalse(CompareMaps.compareMaps(m2,m4));
    }

    @Test
    public void compareMaps_nonEqualMalformed() {
        assertFalse(m1.equals(mMalformed));
        assertFalse(CompareMaps.compareMaps(m1,mMalformed));
    }

    @Test
    public void compareMaps_EqualEmpty() {
        assertTrue(mEmpty.equals(mEmpty));
        assertTrue(CompareMaps.compareMaps(mEmpty,mEmpty));
    }

    @Test
    public void compareMaps_nonEqualEmpty() {
        assertFalse(m1.equals(mEmpty));
        assertFalse(CompareMaps.compareMaps(m1,mEmpty));
    }

    @Test
    public void compareMaps_nonEqualNull() {
        assertFalse(CompareMaps.compareMaps(m1,mNull));
        assertFalse(CompareMaps.compareMaps(mNull,m1));
    }
}