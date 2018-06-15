package io.monteirodev.jokelib;

import java.util.Random;

public class JokesLib {

    private static int mLastJokeNumber = 0;

    private String[] mJokes = {
            "Q. How did the programmer die in the shower?\n" +
                    "A. He read the shampoo bottle instructions: Lather. Rinse. Repeat.",

            "Why do programmers always mix up Halloween and Christmas?\n" +
                    "Because Oct 31 equals Dec 25.",

            "There are only 10 kinds of people in this world: those who know binary and those who don't.",

            "A programmer walks to the butcher shop and buys a kilo of meat.  An hour later he comes back upset that the butcher shortchanged him by 24 grams.",

            "Programming is 10% science, 20% ingenuity, and 70% getting the ingenuity to work with the science.",

            "Programming is like sex:\n" +
                    "One mistake and you have to support it for the rest of your life.",

            "There are three kinds of lies: Lies, damned lies, and benchmarks.",

            "All programmers are playwrights, and all computers are lousy actors.",

            "The generation of random numbers is too important to be left to chance.",

            "The computer is mightier than the pen, the sword, and usually, the programmer.",

            "Debugging: Removing the needles from the haystack.",

            "The three most dangerous things in the world are a programmer with a soldering iron, a hardware engineer with a software patch, and a user with an idea. - The Wizardry Compiled by Rick Cook"};

    public String getJoke() {
        String joke;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mJokes.length);
        if (mLastJokeNumber == randomNumber){
            //get next joke without exceeding Jokes length
            randomNumber = (randomNumber + 1) % (mJokes.length);
        }

        joke = mJokes[randomNumber];
        mLastJokeNumber = randomNumber;

        return joke;
    }
}
