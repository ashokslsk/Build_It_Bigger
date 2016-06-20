package com.ashokslsk;

import java.util.Random;


public class JokeProvider {


    final static String[] JOKES = new String[]{
            "I wanted to grow my own food but I could not get bacon seeds anywhere.",
            "After many years of studying at a university, I’ve finally become a PhD…\nor Pizza Hut Deliveryman as people call it.",
            "The 21st century: Deleting history is often more important than making it.",
            "Do you know why women aren’t allowed in space? \n" +
                    "To avoid scenarios like: \"Houston, we have a problem!\" \n" +
                    "\"What is the problem?\" \n" +
                    "\"Yeah, great, pretend like you don’t know what I’m talking about!"
    };

    public static String getNewJoke() {
        Random r = new Random();
        return JOKES[r.nextInt(JOKES.length)];
    }

}
