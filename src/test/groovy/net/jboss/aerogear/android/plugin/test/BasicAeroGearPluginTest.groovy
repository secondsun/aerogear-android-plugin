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
        appProject.plugins.apply('aerogear-android-plugin');
        Assert.assertTrue(appProject.getPlugins().hasPlugin("aerogear-android-plugin"));
        Assert.assertTrue(appProject.getPlugins().hasPlugin("com.android.application"));
    }


    @Test
    public void pluginDoesNotCreateUPSTask() throws URISyntaxException {
        Project appProject = project;
        appProject.plugins.apply('aerogear-android-plugin');
        Set tasks = appProject.getTasksByName("addUPSToManifestDebug", true);
        Assert.assertEquals(0, tasks.size());

    }


    @Test
    public void pluginCreatesUPSTaskIfConfigFileExists() throws URISyntaxException {
        Project appProject = upsProject;
        appProject.plugins.apply('aerogear-android-plugin');
        Set tasks = appProject.getTasksByName("addUPSToManifestDebug", true);
        Assert.assertEquals(1, tasks.size());
        tasks = appProject.getTasksByName("addUPSToManifestRelease", true);
        Assert.assertEquals(1, tasks.size());
    }

}
