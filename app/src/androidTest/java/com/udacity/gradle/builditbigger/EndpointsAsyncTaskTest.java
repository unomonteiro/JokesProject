package com.udacity.gradle.builditbigger;

import org.junit.Test;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class EndpointsAsyncTaskTest {

    @Test
    public void EndpointsAsyncTask_returnsNonEmptyString() throws Exception {
        String jokeString = new EndpointsAsyncTask().execute().get();
        assertThat(jokeString, not(isEmptyOrNullString()));
    }

}