package com.ashokslsk;

import java.util.Random;


public class JokeProvider {


    final static String[] JOKES = new String[]{
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.\n",
            "What is the difference between a snowman and a snowwoman?\n",
                    "-\n" +
                    "Snowballs.\n" ,
            "When my wife starts to sing I always go out and do some garden work so our neighbors can see there's no domestic violence going on.\n" +
                    "\n" ,
            "Do you know why women aren’t allowed in space? \n" +
                    "To avoid scenarios like: \"Houston, we have a problem!\" \n" +
                    "\"What is the problem?\" \n" +
                    "\"Yeah, great, pretend like you don’t know what I’m talking about!",
                    "Pessimist: \"Things just can't get any worse!\" \n" +
                    "\n" +"\n"+
                    "Optimist: \"Nah, of course they can!\"\n" ,
                    "\n" +
                   "A naked women robbed a bank. Nobody could remember her face.\n" +
                    "\n"
    };

    public static String getNewJoke() {
        Random r = new Random();
        return JOKES[r.nextInt(JOKES.length)];
    }

}
