package net.jboss.aerogear.android.plugin.test

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.internal.dsl.AndroidSourceSetFactory
import net.jboss.aerogear.android.plugin.task.AddUPSToManifestTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.junit.Assert
import org.junit.Test

/**
 * Created by summers on 7/10/15.
 */
class UPSAeroGearPluginTest implements HasAndroidProject {


    @Test
    public void pluginChecksGoodReceiver() throws URISyntaxException {
        Project appProject = upsProject;
        appProject.plugins.apply('aerogear-android-plugin');
        AddUPSToManifestTask task = appProject.getTasksByName("addUPSToManifestMain", true)[0];
        def manifest = new XmlSlurper().parse(task.manifest.srcFile);
        Assert.assertTrue(task.checkReceiver(manifest));
        Assert.assertTrue(task.checkPermissions(manifest));
        Assert.assertTrue(task.checkService(manifest));
    }

    @Test
    public void pluginChecksBadReceiver() throws URISyntaxException {
        Project appProject = brokenUpsProject;
        appProject.plugins.apply('aerogear-android-plugin');
        AddUPSToManifestTask task = appProject.getTasksByName("addUPSToManifestMain", true)[0];
        def manifest = new XmlSlurper().parse(task.manifest.srcFile);
        Assert.assertFalse(task.checkReceiver(manifest));
        Assert.assertFalse(task.checkPermissions(manifest));
        Assert.assertFalse(task.checkService(manifest));
    }

}
