/*
 * Copyright 2015 JBoss, Home of Professional Open Source
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
