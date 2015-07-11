package net.jboss.aerogear.android.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AeroGearPlugin implements Plugin<Project>{


    private Project project;

    void apply(Project project) {
        this.project = project;
        if(!project.getPlugins().findPlugin('com.android.application'))  {
            return;
        }
    }
    
}

