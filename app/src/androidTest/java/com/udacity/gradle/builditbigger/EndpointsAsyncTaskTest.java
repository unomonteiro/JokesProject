package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;


@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void EndpointsAsyncTask_returnsNonEmptyString() throws InterruptedException {
//        String jokeString = new EndpointsAsyncTask().execute().get();
//        assertThat(jokeString, not(isEmptyOrNullString()));
        final CountDownLatch count = new CountDownLatch(1);
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                //check that the result is not an error message from the asyncTask
                assertTrue(!result.contains("Error"));
                assertThat(result, not(isEmptyOrNullString()));
                count.countDown();
            }
        };
        jokeTask.execute();
        count.await();
    }

}