package kh.com.penhchet;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.User;

import java.util.List;

/**
 * Created by penhchet on 6/11/18.
 */
public class Main {

    public static void main(String args[]){

        FacebookClient facebookClient= new DefaultFacebookClient("EAACEdEose0cBAFfX6lCKM44GG3kIZB0N9nqFdeya4Bo1ZAZBmqO1ElyyhEpTRBHlonFpp8bqIeOHMCCXlZBu2hqMMikimAVyPOZBRZCdWMi9qZBqO3P2IsfJ0LaCw7RGJv8XgVZBU2BaeJGDhv4NIg7ZBsjcQSZBCJ68QyqiQ9SLJH7snTZAYCZANUB86BSQ5xSZACXAZD");
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "name,email"));
        System.out.println("User="+ user);
        System.out.println("UserName= "+ user.getName());
        System.out.println("Birthday= "+ user.getEmail());

        Connection<Comment> commentDetails = facebookClient.fetchConnection( "334108723597462_652140195127645/comments", Comment.class,
                Parameter.with("fields", "message"));
        if(commentDetails !=null){
            List<Comment> commentList = commentDetails.getData();
            System.out.println("---------Comments----------");
            for(Comment comment : commentList){

                System.out.println(comment.getMessage() + " - " + comment.getId());
                System.out.println(" - " + comment.getFrom().getName()); //line 101

            }

        }
    }
}
