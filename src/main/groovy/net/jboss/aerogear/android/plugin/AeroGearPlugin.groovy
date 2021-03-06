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
package net.jboss.aerogear.android.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.AndroidSourceSet
import net.jboss.aerogear.android.plugin.task.AddUPSToManifestTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class AeroGearPlugin implements Plugin<Project>{


    private Project project;

    void apply(Project project) {
        this.project = project;
        if(!project.getPlugins().findPlugin('com.android.application'))  {
            return;
        }


        project.plugins.withType(AppPlugin) {   appPlugin ->
            appPlugin.extension.sourceSets.all { AndroidSourceSet sourceSet ->
                def sourceSetTaskName = 'addUPSToManifest' + sourceSet.name.capitalize()

                String pushConfigPath = [project.getProjectDir().absolutePath, 'src', 'main', 'assets', 'push-config.json'].join(File.separator);

                def unifiedPushFile = new File( pushConfigPath );
                if (unifiedPushFile.exists()) {
                    def task = project.tasks.create(sourceSetTaskName, AddUPSToManifestTask)
                    task.manifest = sourceSet.manifest;

                }

            }


        }


    }

    private void createTaskAddUPSToManifest() {
        project.task("addUPSToManifest", {type : AddUPSToManifestTask})
    }
}

