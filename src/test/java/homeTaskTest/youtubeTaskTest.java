package homeTaskTest;
import com.bluenile.testkit.pages.desktop.YoutubePage;
import org.testng.annotations.Test;

import static com.bluenile.testkit.locators.YoutubeLocators.*;

public class youtubeTaskTest extends YoutubePage {


        @Test(groups = {})
        public void youtubeTest() throws Exception {
            String youTubeUrl = "https://www.youtube.com/";
            openUrl(youTubeUrl);
            typeTextOnTextFieldGlobal(searchField, "I Will Survive - Alien song");
            click(mirrorGlassIconButton);
            click(filterButton);
            click(videoButton);
            click(filterButton);
            click(ViewCountButton);
            scrollToElementAction(specificVideoLink);
            String userChannelName = getUserChannelName();
            System.out.println( "The user channel name is : "+ userChannelName+"");
            click(specificVideoLink);
            clickSkipAdIfPresentIfSoClickOnIt();
            scrollToElementAction(showMoreButton);
            clickShowMoreButton();
            String ArtistsName = getArtistsName();
            System.out.println("The artist's  name is : "+ ArtistsName+"");
        }

}
