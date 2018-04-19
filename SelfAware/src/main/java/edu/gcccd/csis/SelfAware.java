package edu.gcccd.csis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelfAware implements Language {


    public static void main(String[] args) throws Exception {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";

        SelfAware sa = new SelfAware();

        sa.append(code,"\n//Keyword occurrences: " + sa.occurrences(code));

    }

    @Override
    public int occurrences(String sourceFile) throws Exception {
        int occurrences = 0;

        /*additional code in attempt to try to print out exact keywords with their matched numbers**/
//        int [] matchedNumbers = new int[ReservedWords.length];
        Language.sort();

        for (int i = 0; i < ReservedWords.length; i++)
        {
            Pattern p = Pattern.compile(ReservedWords[i]);
            Matcher m = p.matcher(new String (Files.readAllBytes(Paths.get(sourceFile))));
                while (m.find())
                {

                     occurrences++;

                     /*additional code in attempt to try to print out exact keywords with their matched numbers**/
//                    matchedNumbers[i]++;
                }
            /*additional code in attempt to try to print out exact keywords with their matched numbers**/
//            if (matchedNumbers[i] > 0)
//            {
//                System.out.println(ReservedWords[i] + " matches: " + matchedNumbers[i]);
//            }

        }
        return occurrences;
    }

    @Override
    public void append(final String sourceFile, final String message) throws IOException {

        Path p = Paths.get(sourceFile);
        Files.write(p, message.getBytes() , StandardOpenOption.APPEND);
    }
}
