package some;

import com.DataInfo;
import com.Story;

public class Sample {
    public static void main(String[] args) {
        DataInfo dataInfo = new DataInfo();
        dataInfo.setExtractedString("");
        System.out.println(dataInfo);

        Story story = new Story("title", "description");
        System.out.println(story.getTitle());
        System.out.println(story.getDescription());
    }
}