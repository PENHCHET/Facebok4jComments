package kh.com.penhchet;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@SpringBootApplication
public class Facebook4jcommentsApplication {

	public static void main(String[] args) throws FacebookException {
		SpringApplication.run(Facebook4jcommentsApplication.class, args);

		// Generate facebook instance.
		Facebook facebook = new FacebookFactory().getInstance();
		// Use default values for oauth app id.
		facebook.setOAuthAppId("776306919082812", "b2e74d95f3f04769aba7e94c9cc3096d");
		// Get an access token from:
		// https://developers.facebook.com/tools/explorer
		// Copy and paste it below.
		String accessTokenString = "EAACEdEose0cBAFfX6lCKM44GG3kIZB0N9nqFdeya4Bo1ZAZBmqO1ElyyhEpTRBHlonFpp8bqIeOHMCCXlZBu2hqMMikimAVyPOZBRZCdWMi9qZBqO3P2IsfJ0LaCw7RGJv8XgVZBU2BaeJGDhv4NIg7ZBsjcQSZBCJ68QyqiQ9SLJH7snTZAYCZANUB86BSQ5xSZACXAZD";

        //AccessToken extendedToken = facebook.extendTokenExpiration(accessTokenString);
        //facebook.setOAuthAccessToken(extendedToken);

        AccessToken at = new AccessToken(accessTokenString);
		// Set access token.
		facebook.setOAuthAccessToken(at);

		System.out.println("Successfully Connection with Facebook4j");

        Reading reading = new Reading().summary().fields("id", "message", "likes.summary(true)", "name" , "from", "picture" ,"created_time", "updated_time" ,"shares","object_id","type" , "status_type" ,"link" , "comments");//.limit(20);//.since("-1 month");

//		ResponseList<Post> posts = facebook.getPost("334108723597462_652140195127645");
//
//		System.out.println("COUNT ==> " + posts.size());
//
//		for(Post post: posts) {
//			System.out.println(post);
//			System.out.println(post.getComments().size());
//		}

        Post post = facebook.getPost("334108723597462_652140195127645", reading);

        System.out.println(post);



        List<Comment> fullComments = new ArrayList<>();

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 =0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        int count10 = 0;
        Pattern stringWith2Numbers = Pattern.compile(".*?(\\d+).*?(\\d+).*?");
        try {
            // get first few comments using getComments from post
            PagableList<Comment> comments = post.getComments();
            Paging<Comment> paging;
            do {
                for (Comment comment: comments) {
                    System.out.println(comment);
                    try {
                        String strNumber = comment.getMessage().replaceAll("\\D+","");
                        //System.out.println(comment.getMessage() + " ==> " + strNumber);
                        if(strNumber.equals(""))
                            continue;
//                        if(comment.getMessage().contains("10")){
//                            System.out.println(comment.getMessage() + " ==> " + strNumber + " ==> [1] = 10, " + "[2] = " + strNumber.replaceAll("10","").replaceAll("0",""));
//                        }else {
//                            System.out.println(comment.getMessage() + " ==> " + strNumber + " ==> [1] = " + Integer.valueOf(strNumber) / 10+ ", " + "[2] = " + Integer.valueOf(strNumber) % 10);
//                        }
                        if(comment.getMessage().contains("10")){
                            System.out.println("\""+comment.getMessage() + "\"," + strNumber + ",10," + strNumber.replaceAll("10","").replaceAll("0",""));
                        }else {
                            System.out.println("\""+comment.getMessage() + "\"," + strNumber + "," + Integer.valueOf(strNumber) / 10+ "," + Integer.valueOf(strNumber) % 10);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    if(comment.getMessage().contains("9")){
                        count9++;
                    }

                    if(comment.getMessage().contains("10")){
                        count10++;
                    }else{
                        if(comment.getMessage().contains("1")){
                            count1++;
                        }
                    }

                    if(comment.getMessage().contains("6")){
                        count6++;
                    }

                    if(comment.getMessage().contains("7")){
                        count7++;
                    }

                    if(comment.getMessage().contains("2")){
                        count2++;
                    }

                    if(comment.getMessage().contains("3")){
                        count3++;
                    }

                    if(comment.getMessage().contains("4")){
                        count4++;
                    }

                    if(comment.getMessage().contains("5")){
                        count5++;
                    }

                    if(comment.getMessage().contains("8")){
                        count8++;
                    }
                    fullComments.add(comment);
                }

                // get next page
                // NOTE: somehow few comments will not be included.
                // however, this won't affect much on our research
                paging = comments.getPaging();
            } while ((paging != null) &&
                    ((comments = facebook.fetchNext(paging)) != null));

        } catch (FacebookException ex) {
            Logger.getLogger(Facebook.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        System.out.println(fullComments.size());

        System.out.println("1. = " + count1);
        System.out.println("2. = " + count2);
        System.out.println("3. = " + count3);
        System.out.println("4. = " + count4);
        System.out.println("5. = " + count5);
        System.out.println("6. = " + count6);
        System.out.println("7. = " + count7);
        System.out.println("8. = " + count8);
        System.out.println("9. = " + count9);
        System.out.println("10. = " + count10);
	}

}
