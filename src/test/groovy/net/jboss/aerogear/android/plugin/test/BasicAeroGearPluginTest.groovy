package net.jboss.aerogear.android.plugin.test

import org.gradle.api.Project
import org.junit.Assert
import org.junit.Test

/**
 * Created by summers on 7/10/15.
 */
class BasicAeroGearPluginTest implements HasAndroidProject {

    @Test
    public void detectAndroidApplicationProject() throws URISyntaxException {
        Project appProject = project;
        project.plugins.apply('aerogear-android-plugin')
        Assert.assertTrue(appProject.getPlugins().hasPlugin("aerogear-android-plugin"));
        Assert.assertTrue(appProject.getPlugins().hasPlugin("com.android.application"));
    }

}
