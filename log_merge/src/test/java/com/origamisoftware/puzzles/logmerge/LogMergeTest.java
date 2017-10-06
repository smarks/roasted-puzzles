/*
 *  Copyright  (c)  2017.  Spencer Marks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.origamisoftware.puzzles.logmerge;

import com.origamisoftware.puzzles.logmerge.apps.LogMerger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogMergeTest {

    private static File fileOne;
    private static File fileTwo;
    private static File fileThree;
    private static File fileFour;
    private static Path logDirectory;

    private static String fileOneData = "2017-04-26 12:00:01,431  INFO - Aenean at massa sit amet est pulvinar cursus quis et nisl\n" +
            "2017-04-26 12:00:02,329  INFO - Cras in dolor quis mi porttitor dapibus et et nunc\n" +
            "2017-04-26 12:00:02,479  INFO - Duis fermentum orci vel libero hendrerit vehicula\n" +
            "2017-04-26 12:00:02,843  WARN - Nam tincidunt leo pretium velit sagittis, ac convallis tellus hendrerit\n" +
            "2017-04-26 12:00:03,667  DEBUG - Aenean condimentum dui in nisi tristique condimentum\n" +
            "2017-04-26 12:00:04,006  INFO - Vivamus fermentum magna eu rutrum porta\n" +
            "2017-04-26 12:00:04,799  DEBUG - Lorem ipsum dolor sit amet, consectetur adipiscing elit\n" +
            "2017-04-26 12:00:05,738  DEBUG - Donec sagittis lorem at porta imperdiet\n" +
            "2017-04-26 12:00:05,797  INFO - Curabitur at sapien venenatis augue ultrices consectetur ac sed ipsum\n";


    private static String fileTwoData = "2017-04-26 12:00:01,107  DEBUG - Nunc eget erat aliquet, posuere dolor in, egestas leo\n" +
            "2017-04-26 12:00:01,126  INFO - Suspendisse convallis elit id tincidunt vestibulum\n" +
            "2017-04-26 12:00:01,484  DEBUG - Suspendisse convallis elit id tincidunt vestibulum\n" +
            "2017-04-26 12:00:02,310  WARN - Integer nec nunc vel eros porta maximus et in turpis\n" +
            "2017-04-26 12:00:02,443  DEBUG - Vestibulum at augue rutrum, cursus nulla eget, maximus orci\n" +
            "2017-04-26 12:00:02,700  WARN - Sed hendrerit tortor non facilisis interdum\n" +
            "2017-04-26 12:00:03,212  WARN - Cras imperdiet justo et turpis fringilla lacinia\n" +
            "2017-04-26 12:00:03,696  INFO - Maecenas sed diam molestie, sollicitudin erat vitae, rhoncus metus\n" +
            "2017-04-26 12:00:04,663  DEBUG - Phasellus viverra diam sed nibh tempor iaculis\n" +
            "2017-04-26 12:00:05,553  INFO - Quisque iaculis metus at sem iaculis bibendum\n" +
            "2017-04-26 12:00:06,470  INFO - Maecenas sit amet arcu sed sem condimentum aliquet sit amet quis urna\n" +
            "2017-04-26 12:00:06,591  DEBUG - Nunc in leo quis risus tempus dignissim\n" +
            "2017-04-26 12:00:07,160  WARN - Proin tincidunt felis vitae fermentum congue\n" +
            "2017-04-26 12:00:08,104  ERROR - Proin varius ex a arcu consectetur lobortis ut id nunc\n" +
            "2017-04-26 12:00:08,447  ERROR - Proin nec massa nec velit luctus pretium vel sollicitudin tellus\n" +
            "2017-04-26 12:00:09,205  DEBUG - Duis mattis odio id bibendum vehicula\n" +
            "2017-04-26 12:00:09,492  INFO - Integer iaculis quam non mauris rhoncus, eget imperdiet magna ornare\n" +
            "2017-04-26 12:00:09,783  WARN - Fusce quis urna eget urna semper semper eget et libero\n" +
            "2017-04-26 12:00:10,676  DEBUG - Sed hendrerit tortor non facilisis interdum\n" +
            "2017-04-26 12:00:11,235  WARN - Curabitur maximus ante ac porta tristique\n" +
            "2017-04-26 12:00:11,473  INFO - Pellentesque auctor velit quis fringilla euismod\n" +
            "2017-04-26 12:00:11,901  WARN - Donec sagittis lorem at porta imperdiet\n" +
            "2017-04-26 12:00:12,015  WARN - Nulla tempus nisl non cursus auctor\n" +
            "2017-04-26 12:00:12,391  WARN - Phasellus viverra diam sed nibh tempor iaculis\n" +
            "2017-04-26 12:00:13,177  WARN - Fusce tempus metus ut felis semper rhoncus\n" +
            "2017-04-26 12:00:13,824  ERROR - Curabitur egestas est in dolor pharetra, vel cursus ante faucibus\n" +
            "2017-04-26 12:00:14,483  ERROR - Nunc in leo quis risus tempus dignissim\n";

    private static String fileThreeData="2017-04-26 12:00:03,744  INFO - Curabitur convallis ex pulvinar, euismod ipsum vel, porttitor felis\n" +
            "2017-04-26 12:00:04,092  WARN - Aliquam imperdiet felis nec diam pulvinar, quis porta justo eleifend\n" +
            "2017-04-26 12:00:04,876  INFO - Aenean non nisi quis ligula consequat tempus nec nec leo\n" +
            "2017-04-26 12:00:05,199  DEBUG - Mauris iaculis orci sed tellus eleifend, eget porta odio scelerisque\n" +
            "2017-04-26 12:00:05,495  WARN - Pellentesque nec mauris eget nunc luctus lacinia\n" +
            "2017-04-26 12:00:05,601  DEBUG - Pellentesque id leo nec leo imperdiet maximus ac sed est\n" +
            "2017-04-26 12:00:05,905  WARN - Donec pulvinar turpis vel erat accumsan interdum\n" +
            "2017-04-26 12:00:06,628  DEBUG - Donec sagittis lorem at porta imperdiet\n" +
            "2017-04-26 12:00:07,068  INFO - Phasellus viverra diam sed nibh tempor iaculis\n" +
            "2017-04-26 12:00:07,772  WARN - Quisque cursus ligula vel massa dapibus, vitae mollis leo volutpat\n" +
            "2017-04-26 12:00:07,875  WARN - Nulla et mi porta, tristique justo vitae, blandit risus\n" +
            "2017-04-26 12:00:07,958  DEBUG - Mauris sit amet nunc sed sapien elementum iaculis\n" +
            "2017-04-26 12:00:08,273  INFO - Pellentesque auctor velit quis fringilla euismod\n" +
            "2017-04-26 12:00:08,679  ERROR - Nunc finibus sem sit amet ligula dignissim interdum\n" +
            "2017-04-26 12:00:09,590  WARN - Pellentesque id leo nec leo imperdiet maximus ac sed est";

    private static String fileFourData="2017-04-26 12:00:01,354  DEBUG - Quisque interdum mi et dui luctus iaculis\n" +
            "2017-04-26 12:00:01,644  INFO - Pellentesque suscipit odio ac ex congue congue\n" +
            "2017-04-26 12:00:01,875  DEBUG - Integer iaculis quam non mauris rhoncus, eget imperdiet magna ornare\n" +
            "2017-04-26 12:00:02,753  WARN - Nulla et mi porta, tristique justo vitae, blandit risus\n" +
            "2017-04-26 12:00:02,912  DEBUG - Aliquam imperdiet felis nec diam pulvinar, quis porta justo eleifend\n" +
            "2017-04-26 12:00:03,766  WARN - Curabitur imperdiet nisi gravida lectus posuere pellentesque\n" +
            "2017-04-26 12:00:04,371  DEBUG - Donec a ante vitae elit consequat posuere vel pretium urna\n" +
            "2017-04-26 12:00:04,761  INFO - Vivamus nec mauris at nunc porta commodo nec eget nisl\n" +
            "2017-04-26 12:00:05,329  DEBUG - Quisque tempor ipsum ac urna cursus, vitae placerat justo vulputate\n" +
            "2017-04-26 12:00:06,105  DEBUG - Vivamus fermentum magna eu rutrum porta\n" +
            "2017-04-26 12:00:06,680  WARN - Curabitur maximus metus in orci volutpat convallis sit amet rutrum arcu\n" +
            "2017-04-26 12:00:06,944  DEBUG - Nulla et mi porta, tristique justo vitae, blandit risus\n" +
            "2017-04-26 12:00:07,126  INFO - Nunc eget erat aliquet, posuere dolor in, egestas leo\n" +
            "2017-04-26 12:00:07,550  INFO - Donec a ante vitae elit consequat posuere vel pretium urna\n" +
            "2017-04-26 12:00:07,666  INFO - Fusce quis urna eget urna semper semper eget et libero\n" +
            "2017-04-26 12:00:08,412  DEBUG - Sed hendrerit tortor non facilisis interdum\n" +
            "2017-04-26 12:00:09,062  DEBUG - Etiam mattis risus non dui efficitur elementum\n" +
            "2017-04-26 12:00:09,217  WARN - Aenean a metus vitae risus dapibus semper\n" +
            "2017-04-26 12:00:10,036  INFO - Nulla sit amet massa a arcu imperdiet egestas in non neque\n" +
            "2017-04-26 12:00:11,032  WARN - Donec sagittis lorem at porta imperdiet\n" +
            "2017-04-26 12:00:11,894  WARN - Curabitur egestas est in dolor pharetra, vel cursus ante faucibus\n" +
            "2017-04-26 12:00:12,432  DEBUG - Pellentesque suscipit odio ac ex congue congue\n" +
            "2017-04-26 12:00:12,759  WARN - Phasellus euismod dui sit amet nisi suscipit auctor\n" +
            "2017-04-26 12:00:13,299  INFO - Aenean non nisi quis ligula consequat tempus nec nec leo\n" +
            "2017-04-26 12:00:13,556  DEBUG - Phasellus viverra diam sed nibh tempor iaculis\n" +
            "2017-04-26 12:00:13,588  DEBUG - In condimentum lacus sed purus vestibulum pulvinar\n" +
            "2017-04-26 12:00:14,582  WARN - Pellentesque id leo nec leo imperdiet maximus ac sed est\n" +
            "2017-04-26 12:00:14,946  ERROR - Ut lacinia risus quis ex consectetur posuere\n" +
            "2017-04-26 12:00:15,047  DEBUG - Curabitur accumsan libero sed accumsan scelerisque\n" +
            "2017-04-26 12:00:15,271  DEBUG - Fusce sit amet mauris vitae quam molestie tempus at non nibh\n" +
            "2017-04-26 12:00:15,699  INFO - Mauris in libero eu lectus malesuada commodo\n" +
            "2017-04-26 12:00:16,434  INFO - Nulla rhoncus mauris eget aliquam feugiat\n" +
            "2017-04-26 12:00:16,845  WARN - Suspendisse sollicitudin magna in cursus luctus\n" +
            "2017-04-26 12:00:17,617  WARN - Nulla tempus nisl non cursus auctor";

    private void writeData(File file, String data) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();
    }

    @Before
    public void setup() throws Exception {

        logDirectory = Files.createTempDirectory("test");

        fileOne = Files.createTempFile(logDirectory, "fileone", ".log").toFile();
        fileTwo = Files.createTempFile(logDirectory, "filetwo", ".log").toFile();
        fileThree = Files.createTempFile(logDirectory, "filethree", ".log").toFile();
        fileFour = Files.createTempFile(logDirectory, "filefour", ".log").toFile();

        writeData(fileOne,fileOneData);
        writeData(fileTwo,fileTwoData);
        writeData(fileThree,fileThreeData);
        writeData(fileFour,fileFourData);
    }

    @Test
    public void testBasic() throws Exception  {
        Path output = Files.createTempFile("output", ".log");
        String args[] = new String[]  { logDirectory.toString(), "foo.bar"};
        LogMerger.main(args);
    }


}
