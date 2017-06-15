/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Twitter_Ex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author artur
 */
public class Metodos {

    static ConfigurationBuilder cb;
    static Twitter twitter;
    static Status status;
    static DirectMessage message;
    static ArrayList<Status> twits = new ArrayList<Status>();
    static Iterator<Status> it = twits.iterator();

    public Metodos() {

    }

    public static void conexion() {

//        cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true);
//        cb.setOAuthConsumerKey("NEtXOREHLPR8ty23kCouO7pzR");
//        cb.setOAuthConsumerSecret("BiqbksDvgqExNi2fxohUZsE40QWognzxNcjUg7UWiCByFQXVY6");
//        cb.setOAuthAccessToken("1139875974-OBqxLhV6bAOz3djPs2sExrsPCuyhy7AJNNxcYbF");
//        cb.setOAuthAccessTokenSecret("jgnNKQPj555UlBp6ZhJ6tiNXF7rcvnCUqHAK0GGJWpkO4");
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public static void lineaTiempo() {

        List<Status> statuses;
        try {
            statuses = twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":"
                        + status.getText());
                twits.add(status);

            }
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Twitter_COD_Examen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void twitearN(String latestStatus) {

        try {
            status = twitter.updateStatus(latestStatus);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void buscarTwit(String busqueda) {

        Query query = new Query(busqueda);
        QueryResult result;
        try {
            result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());

            }
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void enviarMensaje(String destinatario, String mensaje) {

        try {

            message = twitter.sendDirectMessage(destinatario, mensaje);
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sent: " + message.getText() + " to @" + message.getRecipientScreenName());

    }

    public static void seleccion() {
        while (it.hasNext()) {

        }
        for (int i = 0; i < twits.size(); i++) {
            System.out.println(twits.get(i).getUser() + "" + twits.get(i).getGeoLocation() + "" + twits.get(i).getText());

        }

    }
}
